package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Controller;


import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Beans.Integral;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Service.IntegralService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Log4j2
@Api(tags = "积分接口-后台")
@RequestMapping(value = "/server/integral")
public class IntegralController {

    @Autowired
    private IntegralService integralService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "查询积分发放",httpMethod = "GET",response = Integral.class)
    @GetMapping(value = "/findIntegral")
    public ResponseResult findIntegral(ParamBean paramBean){
        try {
            PageInfo<Integral> pageInfo=integralService.findIntegral(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询积分发放异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "发放积分",httpMethod = "POST")
    @PostMapping(value = "/giveIntegral")
    public ResponseResult giveIntegral(@RequestBody Integral integral, HttpServletRequest request){
        try {
            //获取当前账户id
            integralService.giveIntegral(integral,request.getCookies());
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("发放积分异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
