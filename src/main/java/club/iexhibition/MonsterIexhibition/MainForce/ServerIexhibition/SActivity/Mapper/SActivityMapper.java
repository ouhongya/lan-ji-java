package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Beans.SActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SActivityMapper {
    void addActivity(SActivity sActivity);

    List<SActivity> findAllActivity(@Param("search") String search);

    SActivity findActivityById(String id);

    Integer findActivityStatusById(String id);

    void updateActivityStatus(@Param("id") String id,@Param("status") Integer status);

    void updateActivity(SActivity sActivity);
}
