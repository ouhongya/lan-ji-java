package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Controller;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.AddMicroBlog;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlog;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlogComment;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Service.MicroBlogService;
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

@RestController
@RequestMapping(value = "/client/microblog")
@Log4j2
@Api(tags = "WX分享接口")
public class MicroBlogController {

    @Autowired
    private MicroBlogService microBlogService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "查询分享/展评列表", httpMethod = "GET", response = MicroBlog.class)
    @GetMapping(value = "/findMicroBlog")
    public ResponseResult findMicroBlog(ParamBean paramBean, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            PageInfo<MicroBlog> pageInfo = microBlogService.findMicroBlog(paramBean,sessionId);
            return new ResponseResult(CommonCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error("查询分享列表异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过id查询分享详情", httpMethod = "GET", notes = "分页参数是分享评论的分页")
    @ApiImplicitParam(name = "id", value = "分享id")
    @GetMapping(value = "/findMicroBlogById")
    public ResponseResult findMicroBlogById(String id, ParamBean paramBean,HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            MicroBlog microBlog = microBlogService.findMiCroBlogById(id, paramBean,sessionId);
            return new ResponseResult(CommonCode.SUCCESS, microBlog);
        } catch (Exception e) {
            log.error("通过id查询分享详情异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "添加分享", httpMethod = "POST")
    @PostMapping(value = "/addMicroBlog")
    public ResponseResult addMicroBlog(@RequestBody AddMicroBlog addMicroBlog, HttpServletRequest request) {
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            String id = microBlogService.addMicroBlog(addMicroBlog,sessionId);
            return new ResponseResult(CommonCode.SUCCESS,id);
        } catch (Exception e) {
            log.error("添加分享异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "分享评论",httpMethod = "POST")
    @PostMapping(value = "/microBlogComment")
    public ResponseResult microBlogComment(@RequestBody MicroBlogComment microBlogComment,HttpServletRequest request){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            microBlogService.microBlogComment(microBlogComment,sessionId);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("分享评论");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "删除分享评论",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "评论id")
    @GetMapping(value = "/deleteMicroBlogComment")
    public ResponseResult deleteMicroBlogComment(String id){
        try {
            microBlogService.deleteMicroBlogComment(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("删除分享评论异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "分享点赞或取消赞",httpMethod = "GET")
    @ApiImplicitParam(name = "mid",value = "分享id")
    @GetMapping(value = "/microBlogUp")
    public ResponseResult microBlogUp(HttpServletRequest request,String mid){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            microBlogService.microBlogUp(sessionId,mid);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("分享点赞异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SUCCESS);
        }
    }

    @ApiOperation(value = "分享转发",httpMethod = "GET")
    @ApiImplicitParam(name = "mid",value = "分享id")
    @GetMapping(value = "/microBlogForWard")
    public ResponseResult microBlogForWard(HttpServletRequest request,String mid){
        try {
            String sessionId = request.getHeader("Session-Id");
            if(sessionId==null||"".equals(sessionId)){
                return new ResponseResult(CommonCode.SYSTEMBUSY,"SessionId="+sessionId);
            }
            microBlogService.microBlogForWard(sessionId,mid);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("分享转发异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
