package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Beans.SActivity;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Mapper.SActivityMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Service.SActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SActivityServiceImpl implements SActivityService {

    @Autowired
    private SActivityMapper sActivityMapper;

    /**
     * 添加活动
     * @param sActivity
     */
    @Override
    public void addActivity(SActivity sActivity) {
        sActivity.setId(UUIDGenerator.uuid());
        sActivityMapper.addActivity(sActivity);
    }

    /**
     * 查询活动列表
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<SActivity> findAllActivity(ParamBean paramBean) {
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<SActivity> sActivities=sActivityMapper.findAllActivity(paramBean.getSearch());

        //设置活动上下架状态
        for (SActivity sActivity : sActivities) {
            if(sActivity.getStatus()==1){
                sActivity.setState(1);
            }
            if(sActivity.getStatus()==0){
                sActivity.setState(2);
            }
        }

        PageInfo<SActivity> pageInfo = new PageInfo<>(sActivities);

        return pageInfo;
    }

    /**
     * 通过id查询活动详细信息
     * @param id
     * @return
     */
    @Override
    public SActivity findActivityById(String id) {
        SActivity sActivity = sActivityMapper.findActivityById(id);
        //设置活动上下架状态
        if(sActivity.getStatus()==1){
            sActivity.setState(1);
        }
        if(sActivity.getStatus()==0){
            sActivity.setState(2);
        }
        return sActivity;
    }

    /**
     * 活动上下架
     * @param id
     */
    @Override
    public void activityUpDown(String id) {
        //查询活动当前状态
       Integer status=sActivityMapper.findActivityStatusById(id);
        if(status!=null){
            if(status==1){
                status=0;
            }else if(status==0){
                status=1;
            }

            //修改活动状态
            sActivityMapper.updateActivityStatus(id,status);
        }
    }

    /**
     * 编辑活动
     * @param sActivity
     */
    @Override
    public void updateActivity(SActivity sActivity) {
        sActivityMapper.updateActivity(sActivity);
    }

    /**
     * 删除活动
     * @param id
     */
    @Override
    public void deleteActivity(String id) {
        //删除活动
        sActivityMapper.updateActivityStatus(id,-1);
    }
}
