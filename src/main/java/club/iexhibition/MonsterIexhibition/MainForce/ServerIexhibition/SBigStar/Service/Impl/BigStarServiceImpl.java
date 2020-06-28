package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Service.Impl;


import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.*;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Mapper.BigStarMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Service.BigStarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG
//          佛曰:
//                  写字楼里写字间，写字间里程序员；
//                  程序人员写程序，又拿程序换酒钱。
//                  酒醒只在网上坐，酒醉还来网下眠；
//                  酒醉酒醒日复日，网上网下年复年。
//                  但愿老死电脑间，不愿鞠躬老板前；
//                  奔驰宝马贵者趣，公交自行程序员。
//                  别人笑我忒疯癫，我笑自己命太贱；
//                  不见满街漂亮妹，哪个归得程序员？
@Service
public class BigStarServiceImpl implements BigStarService {


    @Autowired
    private BigStarMapper bigStarMapper;

    /**
     * 添加大咖
     * @param bigStar
     * @return
     */
    @Override
    public boolean addBigStar(BigStar bigStar) throws Exception {
        //查询大咖名称是否重复
        BigStar bigStar1=bigStarMapper.findBigStarByName(bigStar.getName());

        if(bigStar1==null){
            bigStar.setId(UUIDGenerator.uuid());

            bigStarMapper.addBigStar(bigStar,new Date());

            return true;
        }
        return false;
    }


    /**
     * 编辑大咖基本信息
     * @param bigStar
     * @return
     */
    @Override
    public boolean updateBigStar(BigStar bigStar) throws Exception {
        //查询大咖名称是否重复
        BigStar bigStar1 = bigStarMapper.findBigStarByName(bigStar.getName());

        if(bigStar1==null||bigStar.getId().equals(bigStar1.getId())){
            //可以编辑
            bigStarMapper.updateBigStar(bigStar);
            return true;
        }
        return false;
    }


    /**
     * 查询大咖
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<BigStar> findBigStar(ParamBean paramBean) throws Exception {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();

        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);

        List<BigStar> bigStars = bigStarMapper.findBigStar(paramBean.getSearch());

        PageInfo<BigStar> pageInfo = new PageInfo<>(bigStars);

        return pageInfo;
    }

    /**
     * 删除大咖
     * @param bid
     */
    @Override
    public void deleteBigStar(String bid) throws Exception {
        bigStarMapper.updateBigStarStatus(bid,-1);
    }


    /**
     * 添加大咖相册
     * @param bid
     * @param bigStarAlbums
     */
    @Override
    public void addBigStarAlbum(String bid, List<BigStarAlbum> bigStarAlbums) throws Exception {
        for (BigStarAlbum bigStarAlbum : bigStarAlbums) {
            //添加大咖相册
            bigStarMapper.addBigStarAlbum(UUIDGenerator.uuid(),bid,bigStarAlbum.getUrl(),bigStarAlbum.getUrlSort(),new Date());
        }
    }


    /**
     * 编辑大咖相册
     * @param bigStarAlbum
     */
    @Override
    public void updateBigStarAlbum(BigStarAlbum bigStarAlbum) throws Exception {
        bigStarMapper.updateBigStarAlbum(bigStarAlbum);
    }

    /**
     * 删除大咖相册
     * @param bid
     */
    @Override
    public void deleteBigStarAlbum(String bid) throws Exception {
        bigStarMapper.updateBigStarAlbumStatus(bid,-1);
    }


    /**
     * 查询大咖相册
     * @param bid
     * @return
     */
    @Override
    public List<BigStarAlbum> findBigStarAlbum(String bid) throws Exception {
       return bigStarMapper.findBigStarAlbum(bid);
    }

    /**
     * 添加大咖商品
     * @param bigStarShop
     */
    @Override
    public void addBigStarShop(BigStarShop bigStarShop) throws Exception {
        bigStarShop.setId(UUIDGenerator.uuid());
        bigStarShop.setCreatedTime(new Date());
        bigStarMapper.addBigStarShop(bigStarShop);
        //添加商品规格
        for (BigStarShopSpec starShopSpec : bigStarShop.getStarShopSpecs()) {
            bigStarMapper.addBigStarShopSpec(bigStarShop.getId(),UUIDGenerator.uuid(),starShopSpec,new Date());
        }
    }


    /**
     * 编辑大咖商品
     * @param bigStarShop
     */
    @Override
    public void updateBigStarShop(BigStarShop bigStarShop) throws Exception {
        //编辑商品基本信息
        bigStarMapper.updateBigStarShop(bigStarShop);
        //编辑规格
//        //删除所有旧的规格
//        bigStarMapper.deleteSpecByShopId(bigStarShop.getId());
//
//        //添加新规格
//        for (BigStarShopSpec starShopSpec : bigStarShop.getStarShopSpecs()) {
//            bigStarMapper.addBigStarShopSpec(bigStarShop.getId(),UUIDGenerator.uuid(),starShopSpec,new Date());
//        }

        //查询所有旧规格
        List<String> bigStarSpecIds=bigStarMapper.findBigStarShopSpecIds(bigStarShop.getId());

        //将所有该商品所有规格置位-1
        bigStarMapper.updateAllSpecStatusByShopId(bigStarShop.getId(),-1);

        for (BigStarShopSpec starShopSpec : bigStarShop.getStarShopSpecs()) {
            if(bigStarSpecIds.contains(starShopSpec.getId())){
                //编辑  并恢复状态为1
                bigStarMapper.updateBigStarShopSpec(starShopSpec);
            }else {
                //添加
                bigStarMapper.addBigStarShopSpec(bigStarShop.getId(),UUIDGenerator.uuid(),starShopSpec,new Date());
            }
        }
    }


    /**
     * 大咖商品上下架
     * @param bid
     * @param status
     */
    @Override
    public void signUDBigStarShop(String bid, Integer status) throws Exception {
        bigStarMapper.updateBigStarShopStatus(bid,status);
    }


    /**
     * 通过id查询大咖
     * @param bid
     * @return
     */
    @Override
    public BigStar findBigStarById(String bid) throws Exception {
        return bigStarMapper.findBigStarById(bid);
    }


    /**
     * 删除大咖商品
     * @param bid
     */
    @Override
    public void deleteBigStarShop(String bid) throws Exception {
        bigStarMapper.updateBigStarShopStatus(bid,-1);

        //将该商品所有规格删除
        bigStarMapper.updateAllSpecStatusByShopId(bid,-1);
    }


    /**
     * 查询大咖商品
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<BigStarShop> findBigStarShop(ParamBean paramBean) {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();

        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);

        List<BigStarShop> bigStarShops=bigStarMapper.findBigStarShop(paramBean.getSearch());

        PageInfo<BigStarShop> pageInfo = new PageInfo<>(bigStarShops);

        return pageInfo;
    }


    /**
     * 添加大咖商品分类
     * @param name
     * @return
     */
    @Override
    public boolean addBigStarShopClass(String name) throws Exception {
        //查询分类名称是否相同
        BigStarShopClass bigStarShopClass=bigStarMapper.findBigStarShopClassByName(name);

        if(bigStarShopClass==null){
            //可以添加
            bigStarMapper.addBigStarShopClass(UUIDGenerator.uuid(),name,new Date());
            return true;
        }
        return false;
    }


    /**
     * 编辑大咖商品分类
     * @param bigStarShopClass
     * @return
     */
    @Override
    public boolean updateBigStarShopClass(BigStarShopClass bigStarShopClass) throws Exception {
        //查询分类名称是否重复
        BigStarShopClass bigStarShopClass1 = bigStarMapper.findBigStarShopClassByName(bigStarShopClass.getName());

        if(bigStarShopClass1==null){
            //可以修改
            bigStarMapper.updateBigStarShopClass(bigStarShopClass);
            return true;
        }
        return false;
    }


    /**
     * 删除大咖商品分类
     * @param bid
     */
    @Override
    public Boolean deleteBigStarShopClass(String bid) throws Exception {
        //查询该分类下是否有商品
        List<String> classIds=bigStarMapper.findBigStarShopIdByCid(bid);

        if(classIds==null||classIds.size()<=0){
            bigStarMapper.updateBigStaRShopClassStatus(bid,-1);
            return true;
        }
        return false;

    }


    /**
     * 查询大咖商品分类
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<BigStarShopClass> findBigStarShopClass(ParamBean paramBean) throws Exception {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();

        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);

        List<BigStarShopClass> bigStarShopClasses=bigStarMapper.findBigStarShopClass(paramBean.getSearch());

        PageInfo<BigStarShopClass> pageInfo = new PageInfo<>(bigStarShopClasses);

        return pageInfo;
    }


    /**
     * 通过id查询商品信息和规格
     * @param bid
     * @return
     */
    @Override
    public BigStarShop findBigStarShopById(String bid) throws Exception {
       return bigStarMapper.findBigStarShopById(bid);
    }


    /**
     * 将某个大咖设为明星大咖
     * @param id
     */
    @Override
    public void setSuperBigStar(String id) {
        //将现明星大咖设为普通大咖
        bigStarMapper.setAllBigStarTypes(0);
        //通过id设置明星大咖
        bigStarMapper.setSuperBigStarById(id);
    }

    /**
     * 修改大咖排序
     * @param id
     * @param sort
     */
    @Override
    public void updateBigStarSort(String id, int sort) {
        bigStarMapper.updateBigStarSort(id,sort);
    }
}
