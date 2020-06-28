package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Controller;


import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.BigStar;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.BigStarAlbum;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.BigStarShop;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans.BigStarShopClass;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Service.BigStarService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/server/bigstar")
@Api(tags = "大咖接口-后台")
public class SBigStarController {

    @Autowired
    private BigStarService bigStarService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "添加大咖",httpMethod = "POST")
    @PostMapping(value = "/addBigStar")
    public ResponseResult addBigStar(@RequestBody BigStar bigStar){
        try {
            if(bigStarService.addBigStar(bigStar)){
                return new ResponseResult(CommonCode.SUCCESS);
            }else {
                return new ResponseResult(CommonCode.CANNOT_OPERATION_BIGSTAR);
            }
        }catch (Exception e){
            log.error("添加大咖异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑大咖",httpMethod = "POST")
    @PostMapping(value = "/updateBigStar")
    public ResponseResult updateBigStar(@RequestBody BigStar bigStar){
        try {
            if(bigStarService.updateBigStar(bigStar)){
                return new ResponseResult(CommonCode.SUCCESS);
            }else {
                return new ResponseResult(CommonCode.CANNOT_OPERATION_BIGSTAR);
            }
        }catch (Exception e){
            log.error("编辑大咖异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询所有大咖",httpMethod = "GET")
    @GetMapping(value = "、findBigStar")
    public ResponseResult findBigStar(ParamBean paramBean){
        try {
            PageInfo<BigStar> pageInfo=bigStarService.findBigStar(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询大咖异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过id查询大咖")
    @ApiImplicitParam(name = "bid",value = "大咖id")
    @GetMapping(value = "/findBigStarById")
    public ResponseResult findBigStarById(String bid){
        try {
            BigStar bigStar=bigStarService.findBigStarById(bid);
            return new ResponseResult(CommonCode.SUCCESS,bigStar);
        }catch (Exception e){
            log.error("通过id查询大咖异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "删除大咖",httpMethod = "GET")
    @ApiImplicitParam(name = "bid",value = "大咖id")
    @GetMapping(value = "/deleteBigStar")
    public ResponseResult deleteBigStar(String bid){
        try {
            bigStarService.deleteBigStar(bid);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("删除大咖异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "添加大咖相册",httpMethod = "POST")
    @PostMapping(value = "/addBigStarAlbum")
    public ResponseResult addBigStarAlbum(String bid, @RequestBody List<BigStarAlbum> bigStarAlbums){
        try {
            bigStarService.addBigStarAlbum(bid,bigStarAlbums);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("添加大咖相册异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑大咖相册",httpMethod = "POST")
    @PostMapping(value = "/updateBigStarAlbum")
    public ResponseResult updateBigStarAlbum(@RequestBody BigStarAlbum bigStarAlbum){
        try {
            bigStarService.updateBigStarAlbum(bigStarAlbum);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("编辑大咖相册异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "删除大咖相册",httpMethod = "GET")
    @ApiImplicitParam(name = "bid",value = "相册id")
    @GetMapping(value = "/deleteBigStarAlbum")
    public ResponseResult deleteBigStarAlbum(String bid){
        try {
            bigStarService.deleteBigStarAlbum(bid);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("删除大咖相册异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询大咖相册",httpMethod = "GET")
    @GetMapping(value = "/findBigStarAlbum")
    @ApiImplicitParam(name = "bid",value = "大咖id")
    public ResponseResult findBigStarAlbum(String bid){
        try {
            List<BigStarAlbum> urls=bigStarService.findBigStarAlbum(bid);
            return new ResponseResult(CommonCode.SUCCESS,urls);
        }catch (Exception e){
            log.error("查询大咖相册");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "添加大咖商品分类",httpMethod = "POST")
    @ApiImplicitParam(name = "name",value = "分类名称")
    @PostMapping(value = "/addBigStarShopClass")
    public ResponseResult addBigStarShopClass(String name){
        try {
            if(bigStarService.addBigStarShopClass(name)){
                return new ResponseResult(CommonCode.SUCCESS);
            }else {
                return new ResponseResult(CommonCode.CANNOT_CLASS);
            }
        }catch (Exception e){
            log.error("添加大咖商品分类异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑大咖商品分类")
    @PostMapping(value = "/updateBigStarShopClass")
    public ResponseResult updateBigStarShopClass(@RequestBody BigStarShopClass bigStarShopClass){
        try {
            if(bigStarService.updateBigStarShopClass(bigStarShopClass)){
                return new ResponseResult(CommonCode.SUCCESS);
            }else {
                return new ResponseResult(CommonCode.CANNOT_CLASS);
            }
        }catch (Exception e){
            log.error("编辑大咖商品分类异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "删除大咖商品分类",httpMethod = "GET")
    @ApiImplicitParam(name = "bid",value = "分类id")
    @GetMapping(value = "/deleteBigStarShopClass")
    public ResponseResult deleteBigStarShopClass(String bid){
        try {
            if(bigStarService.deleteBigStarShopClass(bid)){
                return new ResponseResult(CommonCode.SUCCESS);
            }else {
                return new ResponseResult(CommonCode.CANNOT_DELETE_CLASS);
            }

        }catch (Exception e){
            log.error("删除大咖商品分类异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询大咖商品分类",httpMethod = "GET")
    @GetMapping(value = "/findBigStarShopClass")
    public ResponseResult findBigStarShopClass(ParamBean paramBean){
        try {
            PageInfo<BigStarShopClass> pageInfo = bigStarService.findBigStarShopClass(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询大咖商品分类异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "添加大咖商品",httpMethod = "POST")
    @PostMapping(value = "/addBigStarShop")
    public ResponseResult addBigStarShop(@RequestBody BigStarShop bigStarShop){
        try {
            bigStarService.addBigStarShop(bigStarShop);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("添加大咖商品异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "编辑大咖商品",httpMethod = "POST")
    @PostMapping(value = "/updateBigStarShop")
    public ResponseResult updateBigStarShop(@RequestBody BigStarShop bigStarShop){
        try {
            bigStarService.updateBigStarShop(bigStarShop);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("编辑大咖商品异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "大咖商品上下架",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bid",value = "商品id"),
            @ApiImplicitParam(name = "status",value = "1上架  0下架")
    })
    @PostMapping(value = "/signUDBigStarShop")
    public ResponseResult signUDBigStarShop(String bid,Integer status){
        try {
            bigStarService.signUDBigStarShop(bid,status);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("大咖商品上下架异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "删除大咖商品",httpMethod = "GET")
    @ApiImplicitParam(name = "bid",value = "商品id")
    @GetMapping(value = "/deleteBigStarShop")
    public ResponseResult deleteBigStarShop(String bid){
        try {
            bigStarService.deleteBigStarShop(bid);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("删除大咖商品异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "查询大咖商品",httpMethod = "GET")
    @GetMapping(value = "/findBigStarShop")
    public ResponseResult findBigStarShop(ParamBean paramBean){
        try {
            PageInfo<BigStarShop> pageInfo = bigStarService.findBigStarShop(paramBean);
            return new ResponseResult(CommonCode.SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("查询大咖商品异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }

    @ApiOperation(value = "通过id查询商品信息和规格")
    @ApiImplicitParam(name = "bid",value = "商品id")
    @GetMapping(value = "/findBigStarShopById")
    public ResponseResult findBigStarShopById(String bid){
        try {
            BigStarShop bigStarShop = bigStarService.findBigStarShopById(bid);
            return new ResponseResult(CommonCode.SUCCESS,bigStarShop);
        }catch (Exception e){
            log.error("通过id查询商品信息和规格异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }


    @ApiOperation(value = "将某个大咖设为本期大咖",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "大咖id")
    @GetMapping(value = "/setSuperBigStar")
    public ResponseResult setSuperBigStar(String id){
        try {
            bigStarService.setSuperBigStar(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("将某个大咖设为明星大咖异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SUCCESS);
        }
    }

    @ApiOperation(value = "修改大咖排序",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "大咖id"),
            @ApiImplicitParam(name = "sort",value = "排序值")
    })
    @GetMapping(value = "/updateBigStarSort")
    public ResponseResult updateBigStarSort(String id,int sort){
        try {
            bigStarService.updateBigStarSort(id,sort);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            log.error("修改大咖排序异常");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
