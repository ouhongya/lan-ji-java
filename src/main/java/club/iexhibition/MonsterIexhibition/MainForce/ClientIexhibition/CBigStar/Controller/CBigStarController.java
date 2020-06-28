package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Controller;


import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans.*;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Service.CBigStarService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/client/bigstar")
@Log4j2
@Api(tags = "WX大咖空间接口")
public class CBigStarController {

    @Autowired
    private CBigStarService cBigStarService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "查询大咖首页",httpMethod = "GET",response = IronMan.class)
    @GetMapping(value = "/findIronMan")
    public ResponseResult findIronMan(ParamBean paramBean){
        try {
            IronMan ironMan=cBigStarService.findIronMan(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,ironMan);
        }catch (Exception e){
            log.error("查询大咖首页异常-WX");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询更多大咖",httpMethod = "GET",response = CBigStar.class)
    @GetMapping(value = "/findBigStar")
    public ResponseResult findBigStar(ParamBean paramBean){
        try {
            PageInfo<CBigStar> pageInfo=cBigStarService.findBigStar(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询更多大咖异常-WX");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过id查询大咖详细信息",httpMethod = "GET",response = CBigStar.class)
    @ApiImplicitParam(name = "id",value = "大咖id")
    @GetMapping(value = "/findBigStarById")
    public ResponseResult findBigStarById(String id){
        try {
            CBigStar cBigStar=cBigStarService.findBigStarById(id);
            return new ResponseResult(CommonCode.SUCCESS,cBigStar);
        }catch (Exception e){
            log.error("通过id查询大咖详细信息异常-WX");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询更多览集推荐",httpMethod = "GET",response = Goods.class)
    @GetMapping(value = "/findGoods")
    public ResponseResult findGoods(ParamBean paramBean){
        try {
            PageInfo<Goods> pageInfo = cBigStarService.findGoods(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询更多览集推荐异常-WX");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过id查询活动详情",httpMethod = "GET",response = CActivity.class)
    @ApiImplicitParam(name = "id",value = "活动id")
    @GetMapping(value = "/findActivityById")
    public ResponseResult findActivityById(String id){
        try {
            CActivity cActivity=cBigStarService.findActivityById(id);
            return new ResponseResult(CommonCode.SUCCESS,cActivity);
        }catch (Exception e){
            log.error("通过id查询活动详情异常-WX");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "参加活动",httpMethod = "POST")
    @PostMapping(value = "/inActivity")
    public ResponseResult inActivity(@RequestBody InActivity inActivity, HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            //通过正则表达式校验用户手机号
            Pattern pattern = Pattern.compile("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$");
            if(!pattern.matcher(inActivity.getPhoneNum()).matches()){
                return new ResponseResult(CommonCode.PHONE_NUM_ERROR);
            }
            if(cBigStarService.inActivity(inActivity,sessionId)){
                return new ResponseResult(CommonCode.SUCCESS);
            }
            return new ResponseResult(CommonCode.NUMBER_FULL);
        }catch (Exception e){
            log.error("参加活动异常-WX");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
