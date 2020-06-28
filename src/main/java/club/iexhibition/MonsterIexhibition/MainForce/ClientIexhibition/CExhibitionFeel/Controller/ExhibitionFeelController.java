package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Controller;


import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeel;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeelComment;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Service.ExhibitionFeelService;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.AddMicroBlog;
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

@RestController
@RequestMapping(value = "/client/exhibitionFeel")
@Log4j2
@Api(tags = "WX展评接口")
public class ExhibitionFeelController {

    @Autowired
    private ExhibitionFeelService exhibitionFeelService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    @ApiOperation(value = "查询展评",httpMethod = "GET",response = ExhibitionFeel.class)
//    @ApiImplicitParam(name = "uid",value = "用户id")
//    @GetMapping(value = "/findExhibitionFeel")
//    public ResponseResult findExhibitionFeel(ParamBean paramBean,String uid){
//            try {
//                PageInfo<ExhibitionFeel> pageInfo=exhibitionFeelService.findExhibitionFeel(paramBean,uid);
//                return new ResponseResult(CommonCode.SUCCESS,pageInfo);
//            }catch (Exception e){
//                log.error("查询展评异常");
//                log.error(e);
//                log.error(sf.format(new Date()));
//                return new ResponseResult(CommonCode.SYSTEMBUSY);
//            }
//    }


    @ApiOperation(value = "我要展评",httpMethod = "POST")
    @PostMapping(value = "/addExhibitionFeel")
    public ResponseResult addExhibitionFeel(@RequestBody AddMicroBlog addMicroBlog, HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            String id = exhibitionFeelService.addExhibitionFeel(addMicroBlog,sessionId);
            return new ResponseResult(CommonCode.SUCCESS,id);
        }catch (Exception e){
            log.error("我要展评异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "通过id查询展评详情",httpMethod = "GET",response = ExhibitionFeel.class)
    @ApiImplicitParam(name = "fid",value = "展评id")
    @GetMapping(value = "/findExhibitionFeelById")
    public ResponseResult findExhibitionFeelById(HttpServletRequest request,String fid,ParamBean paramBean){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            ExhibitionFeel exhibitionFeel=exhibitionFeelService.findExhibitionFeelById(sessionId,fid,paramBean);
            return new ResponseResult(CommonCode.SUCCESS,exhibitionFeel);
        }catch (Exception e){
            log.error("通过id查询展评详情异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "展评点赞或取消点赞",httpMethod = "GET")
    @ApiImplicitParam(name = "fid",value = "展评id")
    @GetMapping(value = "/exhibitionFeelUp")
    public ResponseResult exhibitionFeelUp(String fid,HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            exhibitionFeelService.exhibitionFeelUp(fid,sessionId);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("展评点赞或取消点赞异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "展评评论",httpMethod = "POST",notes = "评论内容  展评id")
    @PostMapping(value = "/exhibitionFeelComment")
    public ResponseResult exhibitionFeelComment(@RequestBody ExhibitionFeelComment exhibitionFeelComment, HttpServletRequest request){
        try {
            exhibitionFeelService.exhibitionFeelComment(exhibitionFeelComment,request.getHeader("Session-Id"));
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("展评评论异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "删除展评评论",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "评论id")
    @GetMapping(value = "/deleteExhibitionFeelComment")
    public ResponseResult deleteExhibitionFeelComment(String id,HttpServletRequest request){
        try {
            exhibitionFeelService.deleteExhibitionFeelComment(id,request.getHeader("Session-Id"));
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("删除展评评论异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "展评转发",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "展评id")
    @GetMapping(value = "/exhibitionFeelForward")
    public ResponseResult exhibitionFeelForward(String id,HttpServletRequest request){
        try {
            exhibitionFeelService.exhibitionFeelForward(id,request.getHeader("Session-Id"));
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("展评转发异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
