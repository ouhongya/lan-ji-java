package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans.*;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Mapper.CBigStarMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Service.CBigStarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CBigStarServiceImpl implements CBigStarService {


    @Autowired
    private CBigStarMapper cBigStarMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public IronMan findIronMan(ParamBean paramBean) {
        //钢铁侠初始化
        IronMan ironMan = new IronMan();
        //查询明星大咖 或  通过id查询大咖
        CBigStar cBigStar=cBigStarMapper.findSuperBigStar();
        //查询其他大咖  分页
        if(cBigStar!=null){
            PageHelper.startPage(1,4);

            List<CBigStar> cBigStars=cBigStarMapper.findElseBigStar(cBigStar.getId());

            cBigStar.setCBigStars(cBigStars);
        }else {
            cBigStar=new CBigStar();
            //查询所有大咖
            PageHelper.startPage(1,4);
            List<CBigStar> cBigStars=cBigStarMapper.findAllBigStar();
            cBigStar.setCBigStars(cBigStars);
        }

        ironMan.setCBigStar(cBigStar);

        //查询览集推荐
        PageHelper.startPage(1,3);

        List<Goods> goodsList=cBigStarMapper.findGoods();

        ironMan.setGoods(goodsList);

        //查询览集活动
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<CActivity> cActivities=cBigStarMapper.findActivity(paramBean.getSearch());

        PageInfo<CActivity> pageInfo = new PageInfo<>(cActivities);

        ironMan.setPageInfo(pageInfo);

        return ironMan;
    }


    /**
     * 查询览集推荐商品
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<Goods> findGoods(ParamBean paramBean) {
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<Goods> goodsList=cBigStarMapper.findGoods();

        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);

        return pageInfo;
    }


    /**
     * 查询览集活动
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<CActivity> findActivity(ParamBean paramBean) {
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());
        List<CActivity> cActivities=cBigStarMapper.findActivity(paramBean.getSearch());
        PageInfo<CActivity> pageInfo = new PageInfo<>(cActivities);
        return pageInfo;
    }


    /**
     * 通过id查询大咖详细信息
     * @param id
     * @return
     */
    @Override
    public CBigStar findBigStarById(String id) {
        return cBigStarMapper.findBigStarById(id);
    }

    /**
     * 查询更多大咖
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<CBigStar> findBigStar(ParamBean paramBean) {
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());
        List<CBigStar> cBigStars=cBigStarMapper.findBigStar(paramBean.getSearch());
        PageInfo<CBigStar> pageInfo = new PageInfo<>(cBigStars);
        return pageInfo;
    }

    /**
     * 通过id查询活动详情
     * @param id
     * @return
     */
    @Override
    public CActivity findActivityById(String id) {
       return  cBigStarMapper.findActivityById(id);
    }

    /**
     * 参加活动
     * @param inActivity
     * @return
     */
    @Override
    public boolean inActivity(InActivity inActivity,String sessionId) {
        String uid = tokenUtil.getOpenId(sessionId);
        inActivity.setUserId(uid);
        //查询该用户是否已经报名活动
        String userId=cBigStarMapper.findActivityUser(inActivity.getActivityId(),inActivity.getUserId());

        if(userId!=null){
            return false;
        }

       //查询活动报名人数是否已满
        Map<String,Object> map = cBigStarMapper.findActivityNum(inActivity.getActivityId());

        if(Integer.parseInt(map.get("apn").toString())>Integer.parseInt(map.get("apc").toString())){
            //查询活动信息
            CActivity cActivity = cBigStarMapper.findActivityById(inActivity.getActivityId());

            //校验费用计算是否正确
            int i = new BigDecimal(inActivity.getSignUpNum()).multiply(cActivity.getPrice()).compareTo(inActivity.getSumExpenses());

            if(i!=0){
                return false;
            }

            cBigStarMapper.inActivity(UUIDGenerator.uuid(),inActivity.getUserId(),inActivity.getActivityId(),cActivity.getTitle(),cActivity.getUrl(),cActivity.getSpec(),cActivity.getPrice(),inActivity.getTickName(),inActivity.getPhoneNum(),inActivity.getSignUpNum(),inActivity.getSumExpenses());

            return true;
        }

        return false;
    }
}
