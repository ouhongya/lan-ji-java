package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Controller;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Beans.SActivity;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Service.SActivityService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/server/activity")
@Log4j2
@Api(tags = "活动接口-后台")
public class SActivityController {

    @Autowired
    private SActivityService sActivityService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "添加活动",httpMethod = "POST")
    @PostMapping(value = "addActivity")
    public ResponseResult addActivity(@RequestBody SActivity sActivity){
        try {
            sActivityService.addActivity(sActivity);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("添加活动异常-后台");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询活动列表",httpMethod = "GET")
    @GetMapping(value = "/findAllActivity")
    public ResponseResult findAllActivity(ParamBean paramBean){
        try {
            PageInfo<SActivity> pageInfo=sActivityService.findAllActivity(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询活动列表异常-后台");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "通过id查询活动详细信息",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "活动id")
    @GetMapping(value = "/findActivityById")
    public ResponseResult findActivityById(String id){
        try {
            SActivity sActivity=sActivityService.findActivityById(id);
            return new ResponseResult(CommonCode.SUCCESS,sActivity);
        }catch (Exception e){
            log.error("通过id查询活动详细信息异常-后台");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "活动上下架",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "活动id")
    @GetMapping(value = "activityUpDown")
    public ResponseResult activityUpDown(String id){
        try {
            sActivityService.activityUpDown(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("活动上下架异常-后台");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "编辑活动",httpMethod = "POST")
    @PostMapping(value = "/updateActivity")
    public ResponseResult updateActivity(@RequestBody SActivity sActivity){
        try {
            sActivityService.updateActivity(sActivity);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("编辑活动异常-后台");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "删除活动",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "活动id")
    @GetMapping(value = "/deleteActivity")
    public ResponseResult deleteActivity(String id){
        try {
            sActivityService.deleteActivity(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("删除活动异常-后台");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


}
