package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Mapper.AssembleMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.*;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Mapper.SExhibitionMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Service.SExhibitionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
@Service
public class SExhibitionServiceImpl implements SExhibitionService {

    @Autowired
    private SExhibitionMapper sExhibitionMapper;

    @Autowired
    private AssembleMapper assembleMapper;


    /**
     * 添加展览
     * @param exhibition
     * @throws Exception
     */
    @Override
    public void addExhibition(Exhibition exhibition) throws Exception {
        if("".equals(exhibition.getExpenses())){
            exhibition.setExpenses(null);
        }
        //添加展览基本信息
        exhibition.setId(UUIDGenerator.uuid());
        exhibition.setCreatedTime(new Date());
        sExhibitionMapper.addExhibition(exhibition);
        //添加展览标签
        sExhibitionMapper.addTagToExhibition(exhibition.getId(),exhibition.getTags());
    }


    /**
     * 添加城市
     * @param name
     * @return
     */
    @Override
    public Boolean addCity(String name) throws Exception {
        //查询城市名称是否重复
       City city= sExhibitionMapper.findCityByName(name);
       if(city==null){
           //城市可以添加
           sExhibitionMapper.addCity(UUIDGenerator.uuid(),name,new Date());
           return true;
       }else {
           return false;
       }
    }


    /**
     * 添加区域
     * @param area
     * @return
     */
    @Override
    public boolean addArea(Area area) throws Exception {
        //查询区域是否有重复的
        Area area1=sExhibitionMapper.findAreaByName(area.getCityId(),area.getName());
        if(area1==null){
            //区域可以添加
            area.setId(UUIDGenerator.uuid());
            area.setCreatedTime(new Date());
            sExhibitionMapper.addArea(area);
            return true;
        }else {
            return false;
        }
    }


    /**
     * 添加展馆
     * @param exhibitionHall
     * @return
     */
    @Override
    public boolean addExhibitionHall(ExhibitionHall exhibitionHall) throws Exception {
       //查询展馆名称是否重复
        ExhibitionHall exhibitionHall1=sExhibitionMapper.findExhibitionHallByAidAndHname(exhibitionHall.getAreaId(),exhibitionHall.getName());
        if(exhibitionHall1==null){
            //可以添加
            exhibitionHall.setId(UUIDGenerator.uuid());
            exhibitionHall.setCreatedTime(new Date());
            sExhibitionMapper.addExhibitionHall(exhibitionHall);
            return true;
        }else {
            return false;
        }
    }


    /**
     * 添加展览标签
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public boolean addExhibitionTag(String name) throws Exception {
        //查询标签名是否重复
        ExhibitionTag exhibitionTag=sExhibitionMapper.findExhibitionTagByName(name);
        if(exhibitionTag==null){
            //可以添加
            sExhibitionMapper.addExhibitionTag(UUIDGenerator.uuid(),name,new Date());
            return true;
        }else {
            return false;
        }

    }


    /**
     * 查询所有城市
     * @return
     * @throws Exception
     */
    @Override
    public  PageInfo<City> findAllCity(ParamBean paramBean) throws Exception {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);
        List<City> cities = sExhibitionMapper.findAllCity(paramBean.getSearch());
        PageInfo<City> pageInfo = new PageInfo<>(cities);
        return pageInfo;
    }


    /**
     * 查询城市下所有区域
     * @param cid
     * @param paramBean
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<Area> findAreaByCid(String cid,ParamBean paramBean) throws Exception {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);
        List<Area> areas = sExhibitionMapper.findAllAreaByCid(cid);
        PageInfo<Area> pageInfo = new PageInfo<>(areas);
        return pageInfo;
    }


    /**
     * 删除城市
     * @param cid
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteCity(String cid) throws Exception {
        //查询该城市是否绑定了区域
        List<Area> areas = sExhibitionMapper.findAllAreaByCid(cid);
        if(areas==null||areas.size()<=0){
            sExhibitionMapper.updateCityStatus(cid,-1);
            return true;
        }
        return false;
    }


    /**
     * 查询所有展馆
     * @param paramBean
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<ExhibitionHall> findAllExhibitionHall(ParamBean paramBean) throws Exception{
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);
        List<ExhibitionHall> exhibitionHalls=sExhibitionMapper.findAllExhibitionHall(paramBean.getSearch());
        PageInfo<ExhibitionHall> pageInfo = new PageInfo<>(exhibitionHalls);
        return pageInfo;
    }


    /**
     * 删除区域
     * @param aid
     * @return
     */
    @Override
    public boolean deleteArea(String aid) {
        //查询该区域是否绑定了展馆
        List<ExhibitionHall> exhibitionHalls=sExhibitionMapper.findExhibitionHallByAid(aid);

        if(exhibitionHalls==null||exhibitionHalls.size()<=0){
            //可以删除
            sExhibitionMapper.updateAreaStatus(aid,-1);
            return true;
        }
        return false;
    }


    /**
     * 删除展馆
     * @param hid
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteExhibitionHall(String hid) throws Exception{
        //查询该展馆是否有未结束的活动
        List<Exhibition> exhibitions=sExhibitionMapper.findExhibitionByHid(hid);

        if(exhibitions==null||exhibitions.size()<=0){
            //可以删除
            sExhibitionMapper.updateExhibitionHallStatus(hid,-1);
            return true;
        }
        return false;
    }

    /**
     * 展览上下架
     * @param eid
     * @param status
     * @throws Exception
     */
    @Override
    public void exhibitionUpDown(String eid, Integer status) throws Exception {
        sExhibitionMapper.updateExhibitionStatus(eid,status);

        //召集随着展览上下架 跟着变
        assembleMapper.updateAssembleStatus(eid,status);
    }


    /**
     * 编辑城市
     * @param cid
     * @param cname
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateCity(String cid, String cname) throws Exception {
       //查询该城市名称是否存在
        City city = sExhibitionMapper.findCityByName(cname);
        if(city!=null&&cid.equals(city.getId())){
            return true;
        }

        if(city==null){
            //修改城市名称
            sExhibitionMapper.updateCityName(cid,cname);
            return true;
        }
        return false;
    }


    /**
     * 编辑区域
     * @param aid
     * @param aname
     * @return
     */
    @Override
    public boolean updateArea(String aid, String aname) {
        //检查该城市下区域名称是否存在
            Area area=sExhibitionMapper.findAreaById(aid);

            if(area!=null){
                if(area.getName().equals(aname)){
                    return true;
                }

                //通过are下的cityid查询城市下是否有名称相同的区域
                Area area1=sExhibitionMapper.findAreaByCidAndAname(area.getCityId(),aname);

                if(area1==null){
                    //可以修改
                    sExhibitionMapper.updateAreaName(aid,aname);
                    return true;
                }else {
                    return false;
                }
            }
        return false;
    }


    /**
     * 编辑展馆
     * @param exhibitionHall
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateExhibitionHall(ExhibitionHall exhibitionHall) throws Exception {
        //通过id查询展馆信息
        ExhibitionHall exhibitionHall1 = sExhibitionMapper.findExhibitionHallByAidAndHname(exhibitionHall.getAreaId(), exhibitionHall.getName());
        //判断该展馆在当前区域下是否重名
        if(exhibitionHall1==null){
            //可以修改
            sExhibitionMapper.updateExhibitionHall(exhibitionHall);
            return true;
        }else if (exhibitionHall1.getId().equals(exhibitionHall.getId())){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 搜索所有展览标签
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<ExhibitionTag> findAllExhibitionTag(ParamBean paramBean) throws Exception {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);
        List<ExhibitionTag> exhibitionTags=sExhibitionMapper.findAllExhibitionTag(paramBean.getSearch());
        PageInfo<ExhibitionTag> pageInfo = new PageInfo<>(exhibitionTags);
        return pageInfo;
    }


    /**
     * 编辑展览标签
     * @param tid
     * @return
     */
    @Override
    public boolean updateExhibitionTag(String tid,String tname) throws Exception {
        //查询标签名称是否重复
        ExhibitionTag exhibitionTag = sExhibitionMapper.findExhibitionTagByName(tname);

        if(exhibitionTag==null){
            //可以修改
            sExhibitionMapper.updateExhibitionTagName(tid,tname);
            return true;
        }else if(tid.equals(exhibitionTag.getId())){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 删除展览标签
     * @param tid
     * @throws Exception
     */
    @Override
    public void deleteExhibitionTag(String tid) throws Exception {
        //删除展览和该标签的所有中间关系
        sExhibitionMapper.deleteETag_EX(tid);
        //将标签状态置位-1
        sExhibitionMapper.updateETagStatus(tid,-1);
    }


    /**
     * 查询所有展览
     * @return
     */
    @Override
    public PageInfo<Exhibition> findAllExhibition(ParamBean paramBean,Integer status,Integer timeStatus) {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();

        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);

        List<Exhibition> exhibitions=sExhibitionMapper.findAllExhibition(paramBean.getSearch(),status,timeStatus,new Date());

        PageInfo<Exhibition> pageInfo = new PageInfo<>(exhibitions);

        return pageInfo;
    }



    /**
     * 编辑展览基本信息
     * @param exhibition
     */
    @Override
    public void updateExhibition(Exhibition exhibition) throws Exception {
        //编辑展览信息
        sExhibitionMapper.updateExhibition(exhibition);
        //编辑展览标签
        //删除该展览的标签
        sExhibitionMapper.deleteTagByEid(exhibition.getId());
        //添加新的标签
        sExhibitionMapper.addTagToExhibition(exhibition.getId(),exhibition.getTags());
    }



    /**
     * 更换展览封面头像
     * @param id
     * @param avatar
     */
    @Override
    public void updateEAvatar(String id, String avatar) throws Exception {
            sExhibitionMapper.updateEAvatar(id,avatar);
    }


    /**
     * 删除展览
     * @param eid
     * @throws Exception
     */
    @Override
    public void deleteExhibition(String eid) throws Exception {
        sExhibitionMapper.updateExhibitionStatus(eid,-1);
        //删除展览 同时删除展览下的召集
        assembleMapper.updateAssembleStatus(eid,-1);
    }


    /**'
     * 查询城市区域
     * @return
     * @throws Exception
     */
    @Override
    public List<City> findCityArea() throws  Exception{
       return sExhibitionMapper.findAllCityArea();
    }


    @Override
    public String findExhibitionDetails(String eid) throws Exception {
        return sExhibitionMapper.findExhibitionDetails(eid);
    }

    @Override
    public void updateExhibitionDetails(String eid, String details) throws Exception {
        sExhibitionMapper.updateExhibitionDetails(eid,details);
    }

    @Override
    public Exhibition findExhibitionById(String eid) throws Exception {
       return sExhibitionMapper.findExhibitionById(eid);
    }

    @Override
    public List<City> findCityAreaToExhibitionHall() {
        return sExhibitionMapper.findCityAreaToExhibitionHall();
    }

    @Override
    public List<ExhibitionHall> findExhibitionHallByAid(String aid) {
        return sExhibitionMapper.findExhibitionHallByAid(aid);
    }
}
