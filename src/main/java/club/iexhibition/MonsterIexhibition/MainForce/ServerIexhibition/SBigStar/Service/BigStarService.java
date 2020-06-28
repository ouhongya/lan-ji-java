package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.BigStar;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.BigStarAlbum;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.BigStarShop;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.BigStarShopClass;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BigStarService {
    boolean addBigStar(BigStar bigStar) throws Exception;

    boolean updateBigStar(BigStar bigStar) throws Exception;

    PageInfo<BigStar> findBigStar(ParamBean paramBean) throws Exception;

    void deleteBigStar(String bid) throws Exception;

    void addBigStarAlbum(String bid, List<BigStarAlbum> bigStarAlbums) throws Exception;

    void updateBigStarAlbum(BigStarAlbum bigStarAlbum) throws Exception;

    void deleteBigStarAlbum(String bid) throws Exception;

    List<BigStarAlbum> findBigStarAlbum(String bid) throws Exception;

    void addBigStarShop(BigStarShop bigStarShop) throws Exception;

    void updateBigStarShop(BigStarShop bigStarShop) throws Exception;

    void signUDBigStarShop(String bid, Integer status) throws Exception;

    BigStar findBigStarById(String bid) throws Exception;

    void deleteBigStarShop(String bid) throws Exception;

    PageInfo<BigStarShop> findBigStarShop(ParamBean paramBean);

    boolean addBigStarShopClass(String name) throws Exception;

    boolean updateBigStarShopClass(BigStarShopClass bigStarShopClass) throws Exception;

    Boolean deleteBigStarShopClass(String bid) throws Exception;

    PageInfo<BigStarShopClass> findBigStarShopClass(ParamBean paramBean) throws Exception;

    BigStarShop findBigStarShopById(String bid) throws Exception;

    void setSuperBigStar(String id);

    void updateBigStarSort(String id, int sort);
}
