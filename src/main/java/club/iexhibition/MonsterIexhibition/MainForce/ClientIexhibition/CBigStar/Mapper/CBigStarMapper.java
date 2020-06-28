package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans.CActivity;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans.CBigStar;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans.Goods;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CBigStarMapper {
    CBigStar findSuperBigStar();

    List<CBigStar> findElseBigStar(String id);

    List<Goods> findGoods();

    List<CActivity> findActivity(@Param("search") String search);

    CBigStar findBigStarById(String id);

    List<CBigStar> findBigStar(String search);

    CActivity findActivityById(String id);

    List<CBigStar> findAllBigStar();

    Map<String, Object> findActivityNum(String activityId);

    String findActivityUser(@Param("aid") String activityId,@Param("uid") String userId);

    void inActivity(@Param("id") String uuid, @Param("uid") String userId, @Param("aid") String activityId, @Param("aName") String title, @Param("aUrl") String url, @Param("spec") String spec,@Param("price") BigDecimal price,@Param("tickName") String tickName,@Param("phoneNum") String phoneNum,@Param("signUpNum") String signUpNum,@Param("sumExpenses") BigDecimal sumExpenses);
}
