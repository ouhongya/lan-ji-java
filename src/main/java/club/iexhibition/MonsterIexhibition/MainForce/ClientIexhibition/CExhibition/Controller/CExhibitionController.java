package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Controller;


import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.CAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Beans.Cexhibition;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Service.CExhibitionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/client/exhibition")
@Log4j2
@Api(tags = "WX端展览接口")
public class CExhibitionController {

    @Autowired
    private CExhibitionService cExhibitionService;

    private SimpleDateFormat sf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @ApiOperation(value = "查询展览列表",httpMethod = "GET")
    @GetMapping(value = "/findExhibition")
    public ResponseResult findExhibition(ParamBean paramBean, HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            PageInfo<Cexhibition> pageInfo=cExhibitionService.findExhibition(paramBean,sessionId);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询展览列表异常-WX");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过展览id查询展览详细信息",httpMethod = "GET",response = Cexhibition.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "展览id"),
            @ApiImplicitParam(name = "uid",value = "用户id")
    })
    @GetMapping(value = "/findExhibitionById")
    public ResponseResult findExhibitionById(String eid,HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            Cexhibition cexhibition=cExhibitionService.findExhibitionById(eid,sessionId);
            return new ResponseResult(CommonCode.SUCCESS,cexhibition);
        }catch (Exception e){
            log.error("通过id查询展览详细信息异常-WX");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "用户关注或取消关注展馆",httpMethod = "GET")
    @ApiImplicitParam(name = "eid",value = "展览id")
    @GetMapping(value = "/exhibitionAttention")
    public ResponseResult exhibitionAttention(HttpServletRequest request,String eid){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            cExhibitionService.exhibitionAttention(sessionId,eid);
                return new ResponseResult(CommonCode.SUCCESS);

        }catch (Exception e){
            log.error("用户关注展馆");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
