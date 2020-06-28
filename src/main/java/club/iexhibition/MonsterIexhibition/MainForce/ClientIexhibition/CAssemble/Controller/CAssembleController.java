package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Controller;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.CAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.InAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Service.CAssembleService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/client/assemble")
@Log4j2
@Api(tags = "WX召集接口")
public class CAssembleController {

    @Autowired
    private CAssembleService cAssembleService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @ApiOperation(value = "查询热门召集列表",httpMethod = "GET",response = CAssemble.class)
    @GetMapping(value = "/findAssemble")
    public ResponseResult findAssemble(ParamBean paramBean,HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            PageInfo<CAssemble> pageInfo=cAssembleService.findAssemble(paramBean,sessionId);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询热门召集列表异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "通过id查询召集详情",httpMethod = "GET",response = CAssemble.class)
    @ApiImplicitParam(name = "id",value = "召集id")
    @GetMapping(value = "/findAssembleById")
    public ResponseResult findAssembleById(String id,HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            CAssemble cAssemble=cAssembleService.findAssembleById(id,sessionId);
            return new ResponseResult(CommonCode.SUCCESS,cAssemble);
        }catch (Exception e){
            log.error("通过id查询召集详情异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过展览id查询召集",httpMethod = "GET",response = CAssemble.class)
    @ApiImplicitParam(name = "eid",value = "展览id")
    @GetMapping(value = "/findAssembleByEid")
    public ResponseResult findAssembleByEid(String eid,ParamBean paramBean,HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            PageInfo<CAssemble> pageInfo=cAssembleService.findAssembleByEid(eid,paramBean,sessionId);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("通过展览id查询召集异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "发起召集",httpMethod = "POST")
    @PostMapping(value = "/addAssemble")
    public ResponseResult addAssemble(@RequestBody String assembleJson, HttpServletRequest request){
        try {
           Map<String,Object> assembleMap = (Map<String,Object>) JSON.parse(assembleJson);
           String id = cAssembleService.addAssemble(assembleMap, request.getHeader("Session-Id"));
           if("滚".equals(id)){
               return new ResponseResult(CommonCode.TOKEN_TIME_OUT);
           }
            return new ResponseResult(CommonCode.SUCCESS,id);
        }catch (Exception e){
            log.error("发起召集");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "用户关注或取消关注召集人",httpMethod = "GET")
    @ApiImplicitParam(name = "aUid",value = "召集发起者id")
    @GetMapping(value = "/assembleAttention")
    public ResponseResult assembleAttention(String aUid,HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
                cAssembleService.assembleAttention(aUid,sessionId);
                return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("用户关注或取消关注召集人异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "加入召集",httpMethod = "POST")
    @PostMapping(value = "/inAssemble")
    public ResponseResult inAssemble(@RequestBody InAssemble inAssemble,HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }

            if(cAssembleService.inAssemble(inAssemble,sessionId)){
                return new ResponseResult(CommonCode.SUCCESS);
            }else {
                return new ResponseResult(CommonCode.NUMBER_FULL);
            }
        }catch (Exception e){
            log.error("加入召集异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    


}
