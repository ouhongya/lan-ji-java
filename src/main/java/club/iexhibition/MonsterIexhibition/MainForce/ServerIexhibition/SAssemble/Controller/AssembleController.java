package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Controller;


import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans.Assemble;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans.AssembleDetails;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Service.AssembleService;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.City;
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
import java.util.List;

@Api(tags = "召集接口-后台")
@Log4j2
@RestController
@RequestMapping(value = "/server/assemble")
public class AssembleController {

    @Autowired
    private AssembleService assembleService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "查询召集",httpMethod = "GET",notes = "排序字段  aas.created_time")
    @GetMapping(value = "/findAllAssemble")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "1 全部召集   2未过期的   3已过期的"),
            @ApiImplicitParam(name = "cityId",value = "城市id  传null=查未绑定展览的")
    })
    public ResponseResult findAllAssemble(ParamBean paramBean,Integer status,String cityId){
        try {
            PageInfo<Assemble> pageInfo=assembleService.findAllAssemble(paramBean,status,cityId);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询所有召集异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询召集详情",httpMethod = "GET")
    @GetMapping(value = "/findAssembleDetails")
    @ApiImplicitParam(name = "assembleId",value = "召集id")
    public ResponseResult findAssembleDetails(String assembleId){
        try {
            AssembleDetails assembleDetails=assembleService.findAssembleDetails(assembleId);
            return new ResponseResult(CommonCode.SUCCESS,assembleDetails);
        }catch (Exception e){
            log.error("查询召集详情异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过展览id查询该展览的召集",httpMethod = "GET",notes = "排序字段   aas.created_time")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "展览id"),
            @ApiImplicitParam(name = "status",value = "1 全部召集   2未过期的   3已过期的")
    })
    @GetMapping(value = "/findAssembleByEid")
    public ResponseResult findAssembleByEid(ParamBean paramBean,String eid,Integer status){
        try {
            PageInfo<Assemble> pageInfo = assembleService.findAssembleByEid(paramBean,eid,status);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("通过展览id查询该展览的召集异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询拥有召集的城市区域")
    @GetMapping(value = "/findCityAreaToAssemble")
    public ResponseResult findCityAreaToAssemble(){
        try {
            List<City>  cities = assembleService.findCityAreaToAssemble();
            return new ResponseResult(CommonCode.SUCCESS,cities);
        }catch (Exception e){
            log.error("查询拥有召集的城市区域异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
