package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Beans.Integral;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralMapper {
    List<Integral> findIntegral(@Param("search") String search);

    void addIntegralJournal(Integral integral);

    void addUserIntegral(@Param("fid") String fid, @Param("ids") List<String> ids,@Param("num") Integer num);
}
