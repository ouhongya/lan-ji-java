package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Beans.Cexhibition;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Mapper.CExhibitionMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Service.CExhibitionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CExhibitionServiceImpl implements CExhibitionService {

    @Autowired
    private CExhibitionMapper cExhibitionMapper;

    @Autowired
    private TokenUtil tokenUtil;


    /**
     * 查询展览列表
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<Cexhibition> findExhibition(ParamBean paramBean,String sessionId) {
        String uid = tokenUtil.getOpenId(sessionId);
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());
        //查询展览列表
        List<Cexhibition> cexhibitions= cExhibitionMapper.findExhibition(uid);

        //判断用户关注了哪些展馆  拼接详细地址

        for (Cexhibition cexhibition : cexhibitions) {

            cexhibition.setAddress(cexhibition.getHallAddress()+cexhibition.getHallName());

            if(cexhibition.getHallId()==null){

                cexhibition.setIsFollow(false);
            }
        }

        PageInfo<Cexhibition> pageInfo = new PageInfo<>(cexhibitions);

        return pageInfo;
    }


    /**
     * 通过id查询展览详细信息
     * @param eid
     * @return
     */
    @Override
    public Cexhibition findExhibitionById(String eid, String sessionId) {

        String uid = tokenUtil.getOpenId(sessionId);

        Cexhibition cexhibition=cExhibitionMapper.findExhibitionById(eid,uid);

        if(cexhibition==null){
            return null;
        }

        if(cexhibition.getHallId()==null){
            cexhibition.setIsFollow(false);
        }

        //拼接地址
        cexhibition.setAddress(cexhibition.getHallAddress()+cexhibition.getAddress());

        return cexhibition;
    }

    /**
     * 用户关注展馆
     * @param eid
     * @return
     */
    @Override
    public void exhibitionAttention(String sessionId, String eid) {
        String uid = tokenUtil.getOpenId(sessionId);
        //通过展览id查询展馆id
        String hallId=cExhibitionMapper.findExhibitionHallIdByEid(eid);
        //查询用户是否已经关注此展馆
        String userId=cExhibitionMapper.findExhibitionAttention(uid,hallId);

        if(userId!=null){
           //取消收藏
            cExhibitionMapper.deleteExhibitionAttention(uid,hallId);
            return;
        }

        //正常关注
        cExhibitionMapper.exhibitionAttention(uid,hallId);
    }
}
