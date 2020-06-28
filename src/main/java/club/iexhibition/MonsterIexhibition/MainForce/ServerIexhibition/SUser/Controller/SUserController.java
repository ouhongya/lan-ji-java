package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Controller;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUser;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUserAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUserComment;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Service.SUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Log4j2
@Api(tags = "用户接口-后台")
@RequestMapping(value = "/server/user")
public class SUserController {

    @Autowired
    private SUserService sUserService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "查询用户列表",httpMethod = "GET")
    @GetMapping(value = "/findAllUser")
    public ResponseResult findAllUser(ParamBean paramBean){
        try {
            PageInfo<SUser> pageInfo=sUserService.findAllUser(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询用户列表异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "通过id查询用户详细信息",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "用户id")
    @GetMapping(value = "/findSUserById")
    public ResponseResult findSUserById(String id){
        try {
            if(id==null||"".equals(id)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"id="+id);
            }
            SUser sUser=sUserService.findSUserById(id);
            return new ResponseResult(CommonCode.SUCCESS,sUser);
        }catch (Exception e){
            log.error("通过id查询用户信息异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "通过用户id查询用户的评论")
    @ApiImplicitParam(name="id",value = "id")
    @GetMapping(value = "/findCommentByUid")
    public ResponseResult findCommentByUid(String id,ParamBean paramBean){
        try {
            PageInfo<SUserComment> pageInfo=sUserService.findCommentByUid(id,paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("通过用户id查询用户的评论异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询用户参加过的召集")
    @ApiImplicitParam(name = "id",value = "用户id")
    @GetMapping(value = "/findInAssembleByUid")
    public ResponseResult findInAssembleByUid(String id,ParamBean paramBean){
        try {
            PageInfo<SUserAssemble> pageInfo=sUserService.findInAssembleByUid(id,paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询用户参加过的召集异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询用户发起的召集")
    @ApiImplicitParam(name = "id",value = "用户id")
    @GetMapping(value = "/findAssembleByUid")
    public ResponseResult findAssembleByUid(String id,ParamBean paramBean){
        try {
            PageInfo<SUserAssemble> pageInfo=sUserService.findAssembleByUid(id,paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询用户发起的召集异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


}
