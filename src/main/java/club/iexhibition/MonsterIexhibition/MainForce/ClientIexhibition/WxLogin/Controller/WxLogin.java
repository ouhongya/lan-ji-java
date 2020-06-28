package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Controller;

import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Beans.User;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Service.WxLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/client/wxlogin")
@Log4j2
@Api(tags = "WX登录接口")
public class WxLogin {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private WxLoginService wxLoginService;

    @ApiOperation(value = "WX登录",httpMethod = "GET")
    @ApiImplicitParam(name = "code",value = "前端调用WX接口返回的临时凭证")
    @GetMapping(value = "/wxLogin")
    public ResponseResult wxLogin(String code, HttpServletResponse response){
        try {
            Map<String, String> wxMap = wxLoginService.wxLogin(code);
            //把Session-Id设置到请求头
            response.setHeader("Session-Id",wxMap.get("token"));
            return new ResponseResult(CommonCode.SUCCESS,wxMap.get("state"));
        }catch (Exception e){
            log.error("WX登录异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "WX用户注册完善用户信息",httpMethod = "POST")
    @ApiImplicitParam(name = "userJson",value = "用户信息json字符串")
    @PostMapping(value = "/addUser")
    public ResponseResult addUser(@RequestBody String userJson, HttpServletRequest request){
        try {
            String SessionId = request.getHeader("Session-Id");
            wxLoginService.addUser(userJson,SessionId);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("WX用户注册完善用户信息异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询当前登录用户信息",httpMethod = "GET",response = User.class)
    @GetMapping(value = "/findNowUser")
    public ResponseResult findNowUser(HttpServletRequest request){
        try {
            User user=wxLoginService.findNowUser(request.getHeader("Session-Id"));
            return new ResponseResult(CommonCode.SUCCESS,user);
        }catch (Exception e){
            log.error("查询登录用户信息");
            log.error(e);
            log.error("Session-Id:"+request.getHeader("Session-Id"));
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
