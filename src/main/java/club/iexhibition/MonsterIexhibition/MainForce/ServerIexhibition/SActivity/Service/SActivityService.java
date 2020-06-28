package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Beans.SActivity;
import com.github.pagehelper.PageInfo;

public interface SActivityService {
    void addActivity(SActivity sActivity);

    PageInfo<SActivity> findAllActivity(ParamBean paramBean);

    SActivity findActivityById(String id);

    void activityUpDown(String id);

    void updateActivity(SActivity sActivity);

    void deleteActivity(String id);
}
