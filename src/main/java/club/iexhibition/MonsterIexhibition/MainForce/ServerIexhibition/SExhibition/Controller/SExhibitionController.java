package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Controller;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.*;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Service.SExhibitionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
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
@Log4j2
@Api(tags = "展览接口-后台")
@RestController
@RequestMapping(value = "/server/exhibition")
public class SExhibitionController {

    @Autowired
    private SExhibitionService sExhibitionService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "添加展览", httpMethod = "POST")
    @PostMapping(value = "addExhibition")
    public ResponseResult addExhibition(@RequestBody Exhibition exhibition) {
        try {
            sExhibitionService.addExhibition(exhibition);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            log.error("添加展览异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    /**
     * 添加城市
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "添加城市", httpMethod = "POST")
    @ApiImplicitParam(name = "name", value = "城市名称")
    @PostMapping(value = "/addCity")
    public ResponseResult addCity(String name) {
        try {
            if (sExhibitionService.addCity(name)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_INSERT_CITY);
            }
        } catch (Exception e) {
            log.error("添加城市异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    /**
     * 添加区域
     *
     * @param area
     * @return
     */
    @ApiOperation(value = "添加区域", httpMethod = "POST")
    @PostMapping(value = "/addArea")
    public ResponseResult addArea(@RequestBody Area area) {
        try {
            if (sExhibitionService.addArea(area)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_INSERT_AREA);
            }

        } catch (Exception e) {
            log.error("添加区域异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    /**
     * 添加展馆
     * @return
     */
    @ApiOperation(value = "添加展馆", httpMethod = "POST")
    @PostMapping(value = "/addExhibitionHall")
    public ResponseResult addExhibitionHall(@RequestBody ExhibitionHall exhibitionHall) {
        try {
            if (sExhibitionService.addExhibitionHall(exhibitionHall)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_INSERT_EXHIBITIONHALL);
            }
        } catch (Exception e) {
            log.error("添加展馆异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    /**
     * 添加展览标签
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "添加展览标签", httpMethod = "POST")
    @PostMapping(value = "/addExhibitionTag")
    public ResponseResult addExhibitionTag(String name) {
        try {
            if (sExhibitionService.addExhibitionTag(name)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_INSERT_EXHIBITIONTAG);
            }
        } catch (Exception e) {
            log.error("添加展览标签异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "查询城市", httpMethod = "GET")
    @GetMapping(value = "/findCity")
    public ResponseResult findCity(ParamBean paramBean) {
        try {
            PageInfo<City> pageInfo = sExhibitionService.findAllCity(paramBean);
            return new ResponseResult(CommonCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error("查询所有城市异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "删除城市", httpMethod = "GET")
    @GetMapping(value = "/deleteCity")
    @ApiImplicitParam(name = "cid", value = "城市id")
    public ResponseResult deleteCity(String cid) {
        try {
            if (sExhibitionService.deleteCity(cid)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_DELETE_CITY);
            }
        } catch (Exception e) {
            log.error("删除城市异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过城市id查询区域", httpMethod = "GET")
    @ApiImplicitParam(name = "cid", value = "城市id")
    @GetMapping(value = "/findAreaByCid")
    public ResponseResult findAreaByCid(String cid, ParamBean paramBean) {
        try {
            PageInfo<Area> pageInfo = sExhibitionService.findAreaByCid(cid, paramBean);
            return new ResponseResult(CommonCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error("通过城市id查询区域异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "删除区域", httpMethod = "GET")
    @ApiImplicitParam(name = "aid", value = "区域id")
    @GetMapping(value = "/deleteArea")
    public ResponseResult deleteArea(String aid) {
        try {
            if (sExhibitionService.deleteArea(aid)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_DELETE_AREA);
            }
        } catch (Exception e) {
            log.error("删除区域异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询所有展馆", httpMethod = "GET")
    @GetMapping(value = "/findAllExhibitionHall")
    public ResponseResult findAllExhibitionHall(ParamBean paramBean) {
        try {
            PageInfo<ExhibitionHall> pageInfo = sExhibitionService.findAllExhibitionHall(paramBean);
            return new ResponseResult(CommonCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error("查询所有展馆异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "删除展馆", httpMethod = "GET")
    @ApiImplicitParam(name = "hid", value = "展馆id")
    @GetMapping(value = "/deleteExhibitionHall")
    public ResponseResult deleteExhibitionHall(String hid) {
        try {
            if (sExhibitionService.deleteExhibitionHall(hid)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_DELETE_EXHIBITIONHALL);
            }
        } catch (Exception e) {
            log.error("删除展馆异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "展览上下架", httpMethod = "GET")
    @ApiImplicitParam(name = "eid", value = "展览id")
    @GetMapping(value = "/exhibitionUpDown")
    public ResponseResult exhibitionUpDown(String eid, Integer status) {
        try {
            sExhibitionService.exhibitionUpDown(eid, status);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            log.error("展览上下架异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑城市", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "城市id"),
            @ApiImplicitParam(name = "cname", value = "城市名称")
    })
    @PostMapping(value = "/updateCity")
    public ResponseResult updateCity(String cid, String cname) {
        try {
            if (sExhibitionService.updateCity(cid, cname)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_UPDATE_CITY);
            }
        } catch (Exception e) {
            log.error("编辑城市异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑区域", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aid", value = "区域id"),
            @ApiImplicitParam(name = "aname", value = "区域名称")
    })
    @PostMapping(value = "/updateArea")
    public ResponseResult updateArea(String aid, String aname) {
        try {
            if (sExhibitionService.updateArea(aid, aname)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_UPDATE_AREA);
            }
        } catch (Exception e) {
            log.error("编辑区域异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "编辑展馆", httpMethod = "POST")
    @PostMapping(value = "/updateExhibitionHall")
    public ResponseResult updateExhibitionHall(@RequestBody ExhibitionHall exhibitionHall) {
        try {
            if (sExhibitionService.updateExhibitionHall(exhibitionHall)) {
                return new ResponseResult(CommonCode.SUCCESS);
            } else {
                return new ResponseResult(CommonCode.CANNOT_UPDATE_EXHIBITIONHALL);
            }
        } catch (Exception e) {
            log.error("编辑展馆异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询所有展览标签", httpMethod = "GET")
    @GetMapping(value = "/findAllETag")
    public ResponseResult findAllETag(ParamBean paramBean) {
        try {
            PageInfo<ExhibitionTag> pageInfo = sExhibitionService.findAllExhibitionTag(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        } catch (Exception e) {
            log.error("查询所有展览标签异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑展览标签",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid",value = "展览标签id"),
            @ApiImplicitParam(name = "tname",value = "展览标签名称")
    })
    @PostMapping(value = "/updateETag")
    public ResponseResult updateETag(String tid,String tname){
        try {
            if(sExhibitionService.updateExhibitionTag(tid,tname)){
                return new ResponseResult(CommonCode.SUCCESS);
            }else {
                return new ResponseResult(CommonCode.CANNOT_UPDATE_EXHIBITIONTAG);
            }
        }catch (Exception e){
            log.error("编辑展览标签异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "删除展览标签",httpMethod = "GET")
    @ApiImplicitParam(name = "tid",value = "展览标签id")
    @GetMapping("/deleteETag")
    public ResponseResult deleteETag(String tid){
        try {
            sExhibitionService.deleteExhibitionTag(tid);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("删除展览标签异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询所有展览",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "1上架   0下架"),
            @ApiImplicitParam(name = "timeStatus",value = "1展览未结束    2已结束    3未开始  4已开始  未结束")
    })
    @GetMapping(value = "/findAllExhibition")
    public ResponseResult findAllExhibition(ParamBean paramBean,Integer status,Integer timeStatus){
        try {
            PageInfo<Exhibition> pageInfo=sExhibitionService.findAllExhibition(paramBean,status,timeStatus);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询所有展览异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑展览信息",httpMethod = "POST")
    @PostMapping(value = "/updateExhibition")
    public ResponseResult updateExhibition(@RequestBody Exhibition exhibition){
        try {
            sExhibitionService.updateExhibition(exhibition);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            log.error("编辑展览基本信息异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "更换展览封面头像",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "展览id"),
            @ApiImplicitParam(name = "avatar",value = "封面头像url")
    })
    @PostMapping(value = "/updateEAvatar")
    public ResponseResult updateEAvatar(String id,String avatar){
        try {
            sExhibitionService.updateEAvatar(id,avatar);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            log.error("更换展览封面头像异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "删除展览",httpMethod = "GET")
    @ApiImplicitParam(name = "eid",value = "展览id")
    @GetMapping(value = "/deleteExhibition")
    public ResponseResult deleteExhibition(String eid){
        try {
            sExhibitionService.deleteExhibition(eid);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            log.error("删除展览异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "查询城市区域",httpMethod = "GET")
    @GetMapping(value = "/findCityArea")
    public ResponseResult findCityArea(){
        try {
            List<City> ca=sExhibitionService.findCityArea();
            return new ResponseResult(CommonCode.SUCCESS,ca);
        }catch (Exception e){
            log.error("查询城市区域异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "查询单个展览详情")
    @GetMapping(value = "/findExhibitionDetails")
    @ApiImplicitParam(name = "eid",value = "展览id")
    public ResponseResult findExhibitionDetails(String eid){
        try {
            String eDetails = sExhibitionService.findExhibitionDetails(eid);
            return new ResponseResult(CommonCode.SUCCESS,eDetails);
        } catch (Exception e) {
            log.error("查询展览详情异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑展览详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "展览id"),
            @ApiImplicitParam(name = "details",value = "详情内容")
    })
    @PostMapping(value = "/updateExhibitionDetails")
    public ResponseResult updateExhibitionDetails(String eid,String details){
        try {
            sExhibitionService.updateExhibitionDetails(eid,details);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            log.error("编辑展览详情异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过id查询展览基本信息",httpMethod = "GET")
    @ApiImplicitParam(name = "eid",value = "展览id")
    @GetMapping(value = "/findExhibitionById")
    public ResponseResult findExhibitionById(String eid){
        try {
            Exhibition exhibition=sExhibitionService.findExhibitionById(eid);
            return new ResponseResult(CommonCode.SUCCESS,exhibition);
        }catch (Exception e){
            log.error("通过id查询展览异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询拥有展馆的城市区域")
    @GetMapping(value = "/findCityAreaToExhibitionHall")
    public ResponseResult findCityAreaToExhibitionHall(){
        try {
            List<City> cities=sExhibitionService.findCityAreaToExhibitionHall();
            return new ResponseResult(CommonCode.SUCCESS,cities);
        }catch (Exception e){
            log.error("查询拥有展馆的城市区域");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过区域id查询展馆")
    @ApiImplicitParam(name = "aid",value = "区域id")
    @GetMapping(value = "/findExhibitionHallByAid")
    public ResponseResult findExhibitionHallByAid(String aid){
        try {
           List<ExhibitionHall> exhibitionHalls= sExhibitionService.findExhibitionHallByAid(aid);
           return new ResponseResult(CommonCode.SUCCESS,exhibitionHalls);
        }catch (Exception e){
            log.error("通过区域id查询展馆异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
