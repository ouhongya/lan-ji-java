package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.CAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.CAssembleAlbum;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.InAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Mapper.CAssembleMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Service.CAssembleService;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Beans.Cexhibition;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Mapper.CExhibitionMapper;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CAssembleServiceImpl implements CAssembleService {

    @Autowired
    private CAssembleMapper cAssembleMapper;

    @Autowired
    private CExhibitionMapper cExhibitionMapper;

    @Autowired
    private TokenUtil tokenUtil;
    /**
     * 查询热门召集列表
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<CAssemble> findAssemble(ParamBean paramBean, String sessionId) {
        String uid = tokenUtil.getOpenId(sessionId);
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<CAssemble> cAssembles=cAssembleMapper.findAssemble(uid,new Date());

        for (CAssemble cAssemble : cAssembles) {
            if(cAssemble.getToAssembleUserId()==null){
                cAssemble.setIsFollow(false);
            }
            if(cAssemble.getUserId().equals(uid)){
                cAssemble.setIsFollow(null);
            }
        }


        PageInfo<CAssemble> pageInfo = new PageInfo<>(cAssembles);

        return pageInfo;
    }


    /**
     * 通过召集id查询召集详情
     * @param id
     * @return
     */
    @Override
    public CAssemble findAssembleById(String id, String sessionId) {
        String uid = tokenUtil.getOpenId(sessionId);
        CAssemble cAssemble=cAssembleMapper.findAssembleById(id,uid,new Date());

        //该用户是否关注此召集人
        if(cAssemble.getToAssembleUserId()==null){
            cAssemble.setIsFollow(false);
        }

        //该用户是否已经参加了此召集
        if(cAssemble.getInAssembleUserId()==null){
            cAssemble.setIsInAssemble(false);
        }

        //统计召集参与人数
        Integer cheerNum=cAssembleMapper.findCheerNum(id);

        if(cheerNum==null){
            cheerNum=0;
        }

        //统计热度
        Integer hotValue=cAssembleMapper.findHotValue(id);

        if(hotValue==null){
            hotValue=0;
        }

        cAssemble.setCheerNum(cheerNum);
        cAssemble.setHotNum(hotValue);

        //查询用户创建召集的相册
        if(cAssemble.getExhibitionId()==null){
            cAssemble.setUrls(cAssembleMapper.findAssembleAlbum(cAssemble.getId()));
        }

        return cAssemble;
    }

    /**
     * 通过展览id查询召集
     * @param eid
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<CAssemble> findAssembleByEid(String eid, ParamBean paramBean,String sessionId) {
        //分页
        String uid = tokenUtil.getOpenId(sessionId);
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());
        List<CAssemble> cAssembles=cAssembleMapper.findAssembleByEid(eid,uid);
        //设置用户关注召集状态
        for (CAssemble cAssemble : cAssembles) {
            if(cAssemble.getToAssembleUserId()==null){
                if(cAssemble.getToAssembleUserId()==null){
                    cAssemble.setIsFollow(false);
                }
                if(cAssemble.getUserId().equals(uid)){
                    cAssemble.setIsFollow(null);
                }
            }
        }
        PageInfo<CAssemble> pageInfo = new PageInfo<>(cAssembles);
        return pageInfo;
    }

    /**
     * 发起召集
     */
    @Override
    public String addAssemble(Map<String,Object> assembleMap,String sessionId) throws Exception {
        if(sessionId==null||"".equals(sessionId)){
            return "滚";
        }
        CAssemble cAssemble = new CAssemble();
            //将map中的属性封装到实体类中
            cAssemble.setTitle(assembleMap.get("title").toString());
            cAssemble.setSumNum(Integer.parseInt(assembleMap.get("people").toString()));
            cAssemble.setContent(assembleMap.get("notice").toString());
            cAssemble.setDetails(assembleMap.get("details").toString());
            cAssemble.setAddress(assembleMap.get("address").toString());
            cAssemble.setPhoneNum(assembleMap.get("phoneNum").toString());
            //费用
            Map<String,Object> costsMap=(Map<String, Object>) JSON.parse(assembleMap.get("costs").toString());
            boolean isFree = Boolean.parseBoolean(costsMap.get("isFree").toString());
            if(isFree){
                cAssemble.setExpenses(new BigDecimal("0"));
            }else {
                cAssemble.setExpenses(new BigDecimal(costsMap.get("value").toString()));
            }
            //活动时间
            Map<String,Object> dataTimeMap=(Map<String,Object>) JSON.parse(assembleMap.get("datetime").toString());
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date date = sf.parse(dataTimeMap.get("date").toString() + " " + dataTimeMap.get("time").toString());

            cAssemble.setAssembleTime(date);

            //召集封面图
            Map<String,Object> thumImageMap= (Map<String,Object>) JSON.parse(assembleMap.get("thumImage").toString());
            cAssemble.setUrl(thumImageMap.get("url").toString());

            if("".equals(assembleMap.get("id").toString())){
            //召集详情图
            List<CAssembleAlbum> detailsImages;
            if(assembleMap.get("detailsImage")!=null&&!"".equals(assembleMap.get("detailsImage").toString())){
                detailsImages=JSON.parseArray(assembleMap.get("detailsImage").toString(), CAssembleAlbum.class);
            }else {
                detailsImages=null;
            }
            if(detailsImages!=null&&detailsImages.size()>0){
                for (int i = 0; i < detailsImages.size(); i++) {
                    detailsImages.get(i).setId(UUIDGenerator.uuid());
                    detailsImages.get(i).setUrlSort(i+1);
                }
            }
            cAssemble.setcAssembleAlbums(detailsImages);
            cAssemble.setExhibitionId(assembleMap.get("id").toString());
        }else {
                //通过展览id查询展览信息
                Cexhibition cexhibition=cExhibitionMapper.findExhibitionByIdAssemble(assembleMap.get("id").toString());
                cAssemble.setAddress(cexhibition.getHallAddress()+cAssemble.getAddress());
                cAssemble.setUrl(cexhibition.getUrl());
                cAssemble.setDetails(cexhibition.getDetails());
                cAssemble.setExhibitionId(assembleMap.get("id").toString());
            }

        cAssemble.setId(UUIDGenerator.uuid());
        cAssemble.setUserId(tokenUtil.getOpenId(sessionId));
        //判断用户发起的召集是否关联展览
        if(cAssemble.getExhibitionId()==null||"".equals(cAssemble.getExhibitionId())){
            //不带展览
            cAssembleMapper.addAssembleNoE(cAssemble);
            //添加召集图片
            if(cAssemble.getcAssembleAlbums()!=null||cAssemble.getcAssembleAlbums().size()>0){
                //设置id
                for (CAssembleAlbum cAssembleAlbum : cAssemble.getcAssembleAlbums()) {
                    cAssembleAlbum.setId(UUIDGenerator.uuid());
                }
                //添加图片
                cAssembleMapper.addAssembleAlbum(cAssemble.getId(),cAssemble.getcAssembleAlbums());
            }
        }else {
            //带展览
            cAssembleMapper.addAssemble(cAssemble);
        }

        return cAssemble.getId();
    }

    /**
     * 用户关注召集人
     * @param aUid
     * @return
     */
    @Override
    public void assembleAttention(String aUid, String sessionId) {
         String uid = tokenUtil.getOpenId(sessionId);
        //查询该用户是否已经关注此召集人
        String assembleUserId=cAssembleMapper.findAttention(uid,aUid);

        if(assembleUserId!=null){
            //取消关注
            cAssembleMapper.deleteAttention(aUid,uid);
            return;
        }
        //正常关注
        cAssembleMapper.addAssembleAttention(uid,aUid);
    }

    /**
     * 参与召集
     * @return
     */
    @Override
    public boolean inAssemble(InAssemble inAssemble,String sessionId) {
        String uid = tokenUtil.getOpenId(sessionId);
        inAssemble.setUid(uid);
        //查询召集人数是否已满
        Map<String,Long> map=cAssembleMapper.findDoubleAssembleUserNum(inAssemble.getAid());
        //获取召集总人数和已参加人数
        Long sumNum = map.get("sumNum");

        Long num = map.get("num");

        if(num<sumNum){
            //查询用户是否已经加入召集
            String userId=cAssembleMapper.findInAssemble(inAssemble.getAid(),inAssemble.getUid());

            if(userId!=null){
                return false;
            }
            //加入召集
            inAssemble.setId(UUIDGenerator.uuid());
            cAssembleMapper.inAssemble(inAssemble);
            return true;
        }
        //召集人数已满
        return false;
    }
}
