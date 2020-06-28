package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.*;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BigStarMapper {
    BigStar findBigStarByName(String name);

    void addBigStar(@Param("bigstar") BigStar bigStar,@Param("date") Date date);

    void updateBigStar(@Param("bigStar") BigStar bigStar);

    List<BigStar> findBigStar(String search);

    void updateBigStarStatus(@Param("bid") String bid,@Param("status") int i);

    void addBigStarAlbum(@Param("id") String uuid,@Param("bid") String bid,@Param("url") String url,@Param("urlSort") String urlSort,@Param("date") Date date);

    void updateBigStarAlbum(BigStarAlbum bigStarAlbum);

    void updateBigStarAlbumStatus(@Param("bid") String bid,@Param("status") int i);

    List<BigStarAlbum> findBigStarAlbum(String bid);

    void addBigStarShop(BigStarShop starShop);

    void updateBigStarShop(BigStarShop bigStarShop);

    void updateBigStarShopStatus(@Param("id") String bid,@Param("status") Integer status);

    BigStar findBigStarById(String bid);

    List<BigStarShop> findBigStarShop(@Param("search") String search);

    BigStarShopClass findBigStarShopClassByName(String name);

    void addBigStarShopClass(@Param("id") String uuid,@Param("name") String name,@Param("date") Date date);

    void updateBigStarShopClass(BigStarShopClass bigStarShopClass);

    void updateBigStaRShopClassStatus(@Param("id") String bid,@Param("status") int i);

    List<BigStarShopClass> findBigStarShopClass(String search);

    void addBigStarShopSpec(@Param("shopId") String id, @Param("id") String uuid,@Param("starShopSpec") BigStarShopSpec starShopSpec,@Param("date") Date date);

    void updateAllSpecStatusByShopId(@Param("shopId") String id,@Param("status") int i);

    void deleteSpecByShopId(String id);

    List<String> findBigStarShopSpecIds(String shopId);

    void updateBigStarShopSpec(BigStarShopSpec starShopSpec);

    BigStarShop findBigStarShopById(String bid);

    List<String> findBigStarShopIdByCid(String bid);

    void setAllBigStarTypes(int i);

    void setSuperBigStarById(@Param("id") String id);

    void updateBigStarSort(@Param("id") String id,@Param("sort") int sort);
}
