package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Controller;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Beans.TipOffComment;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Service.CommentService;
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

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Log4j2
@Api(tags = "评论接口-后台")
@RequestMapping(value = "/server/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd Hh:mm:ss");

    @ApiOperation(value = "查询被举报过的评论")
    @GetMapping(value = "/findTipOffComment")
    public ResponseResult findTipOffComment(ParamBean paramBean){
        try {
            PageInfo<TipOffComment> pageInfo=commentService.findTipOffComment(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询被举报过的评论");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "处罚被举报次数过多的评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid",value = "评论id"),
            @ApiImplicitParam(name = "type",value = "1展评  2分享")
    })
    @GetMapping(value = "/punish")
    public ResponseResult punish(String cid,String type){
        try {
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("处罚被举报次数过多的评论异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
