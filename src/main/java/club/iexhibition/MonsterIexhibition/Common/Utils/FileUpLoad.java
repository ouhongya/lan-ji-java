package club.iexhibition.MonsterIexhibition.Common.Utils;
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
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 文件上传-腾讯云
 */
@Component
public class FileUpLoad {

    //腾讯云密钥对（1/2）（腾讯云官网个人信息可查看）
   @Value("${secretId}")
   private String  secretId;

    //腾讯云密钥对（2/2）（腾讯云官网个人信息可查看）
   @Value("${secretKey}")
   private String secretKey;


    //腾讯云指定的 地区缩写  （不同地区 缩写不同）
   @Value("${area}")
   private String area;
    /**
     * 上传图片
     * @param inputStream
     * @return  图片url
     */
    public String upload(String bucketName,InputStream inputStream, String fileName) throws IOException {
        //获取文件大小
        int available = inputStream.available();
        //将uuid和时间戳 添加到fileName中 防止文件名重复
        String uuid = UUIDGenerator.uuid();
        fileName=uuid+new Date().getTime()+fileName;
        //用户身份初始化
        COSCredentials cosCredentials = new BasicCOSCredentials(secretId, secretKey);
        //创建地区对象
        Region region = new Region(area);
        //创建ClientConfig 并指定地区
        ClientConfig clientConfig = new ClientConfig(region);
        //生成cos客户端
        COSClient cosClient = new COSClient(cosCredentials,clientConfig);
        //创建objectMetadata对象  用户设置文件的访问信息
        ObjectMetadata objectMetadata = new ObjectMetadata();
        //设置objectMetadata属性
        //文件缓存长度  从流中获取
        objectMetadata.setContentLength(available);
        //缓存控制  no-cache 代表每次缓存需要服务器验证   no-store  代表不缓存
        objectMetadata.setCacheControl("no-cache");
        //设置请求头
        objectMetadata.setHeader("Pragma", "no-cache");
        //设置文件类型
        objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
        //设置外网访问文件的方式    固定写法  inline为直接将文件显示到页面上（一般用于显示图片）
        // attachment弹出下载框 让用户下载  需要指定fileName  例：objectMetadata.setContentDisposition("attachment;filename=哈哈哈");
        objectMetadata.setContentDisposition("inline;filename="+fileName);
        //上传图片
        cosClient.putObject(bucketName, fileName, inputStream, objectMetadata);
        //拼接腾讯云存储的外网访问地址
        String url="https://"+bucketName+".cos."+area+".myqcloud.com/"+fileName;
        //关闭腾讯云连接
        cosClient.shutdown();

        return url;
    }

    /**
     * 通过文件后缀名 返回文件类型
     * @param suffix
     * @return
     */
    private String getContentType(String suffix){
        if (suffix.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (suffix.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("jpg")
                || suffix.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (suffix.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        return "image/jpeg";
    }
}

