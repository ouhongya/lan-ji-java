package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Controller;


import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.Exhibition;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans.RouteC;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans.RouteR;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Service.SRouteRecommendationService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG
//          佛曰:
//                  写字楼里写字间，写字间里程序员；
//                  程序人员写程序，又拿程序换酒钱。
//                  酒醒只在网上坐，酒醉还来网下眠；
//                  酒醉酒醒日复日，网上网下年复年。
//                  但愿老死电脑间，不愿鞠躬老板前；
//                  奔驰宝马贵者趣，公交自行程序员。
//                  别人笑我忒疯癫，我笑自己命太贱；
//                  不见满街漂亮妹，哪个归得程序员？

@Log4j2
@Api(tags = "线路推荐-后台")
@RestController
@RequestMapping(value = "/server/recommendation")
public class SRouteRecommendationController {

    @Autowired
    private SRouteRecommendationService sRouteRecommendationService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "查询线路推荐",httpMethod = "GET")
    @GetMapping("/findRecom")
    public ResponseResult findRecom(ParamBean paramBean){
        try {
            PageInfo<RouteC>  pageInfo=sRouteRecommendationService.findRecom(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        } catch (Exception e) {
            log.error("查询线路推荐异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过推荐id查询合适的展览",httpMethod = "GET")
    @ApiImplicitParam(name = "rid",value = "推荐id")
    @GetMapping(value = "/findRecomExhibition")
    public ResponseResult findRecomExhibition(String rid,ParamBean paramBean){
        try {
            PageInfo<Exhibition> pageInfo= sRouteRecommendationService.findRecomExhibition(rid,paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("通过推荐id查询展览异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "筛选合适的展览返回WX",httpMethod = "POST")
    @ApiImplicitParam(name = "id",value = "线路推荐id")
    @PostMapping(value = "/optionEnd")
    public ResponseResult optionEnd(String id,@RequestBody List<RouteR> routeRs){
        try {
            sRouteRecommendationService.optionEnd(id,routeRs);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("筛选展览返回WX异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
