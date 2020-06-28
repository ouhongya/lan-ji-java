package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CUpload;

import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.Common.Utils.FileUpLoad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
@Log4j2
@Api(tags = "图片上传-WX")
public class CUpload {

    @Autowired
    private FileUpLoad fileUpLoad;

    //存储桶名称
    @Value("${wxBucketName}")
    private String wxBucketName;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @ApiOperation(value = "图片上传",httpMethod = "POST")
    @PostMapping(value = "/upload")
    public ResponseResult upload(@RequestParam(name = "file")MultipartFile multipartFile){
//        try {
//            String name=null;
//            String url = null;
//            //使用apache组件获取content-type为multiParFile/form-data时的参数
//            //创建工厂
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            //创建apache获取文件的对象
//            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
//            //将request转换为requestContext
//            ServletRequestContext requestContext = new ServletRequestContext(request);
//            //获取所有文件
//            List<FileItem> fileItems = servletFileUpload.parseRequest(requestContext);
//            //fileItem  getName=文件名称   getFieldName=参数的key  getInputStream=文件流   当content-type为multiParFile/form-data时 所有参数都会转为二进制流的方式向后端传递
//        for (FileItem fileItem : fileItems) {
//                if(!"file".equals(fileItem.getFieldName())){
//                    InputStream inputStream = fileItem.getInputStream();
//                    StringBuilder stringBuilder = new StringBuilder();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    while ((name=bufferedReader.readLine())!=null){
//                        stringBuilder.append(name);
//                    }
//                    String s = stringBuilder.toString();
//                     System.out.println(s);
//                } else {
//                     url = fileUpLoad.upload(wxBucketName, fileItem.getInputStream(), fileItem.getName());
//                }
//            }
//            return new ResponseResult(CommonCode.SUCCESS,url);
//        }catch (Exception e){
//            log.error("图片上传异常-wx");
//            log.error(e);
//            log.error(sf.format(new Date()));
//            return new ResponseResult(CommonCode.SYSTEMBUSY);
//        }
        try {
            String url = fileUpLoad.upload(wxBucketName, multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            return new ResponseResult(CommonCode.SUCCESS,url);
        }catch (Exception e){
            log.error("图片上传异常-wx");
            log.error(e);
            log.error(sf.format(new Date()));
            return new ResponseResult(CommonCode.SYSTEMBUSY);
        }
    }
}
