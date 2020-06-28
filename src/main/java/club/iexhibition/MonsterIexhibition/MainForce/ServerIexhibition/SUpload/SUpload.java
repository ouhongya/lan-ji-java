package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUpload;


import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.Common.Utils.FileUpLoad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Api(tags = "图片上传-后台")
@RestController
@RequestMapping(value = "/server")
public class SUpload {

    @Autowired
    private FileUpLoad fileUpLoad;

    //存储桶名称
    @Value("${bucketName}")
    private String bucketName;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @ApiOperation(value = "图片上传")
    @PostMapping(value = "/upload")
    public ResponseResult upload(@RequestParam(name = "file") MultipartFile multipartFile){
        try {
            String url = fileUpLoad.upload(bucketName,multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            if(url!=null){
                return new ResponseResult(CommonCode.SUCCESS,url);
            }else {
                return new ResponseResult(CommonCode.FAILED);
            }
        }catch (Exception e){
            log.error("图片上传异常-后台");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
