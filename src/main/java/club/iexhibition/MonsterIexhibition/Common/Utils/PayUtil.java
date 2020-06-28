package club.iexhibition.MonsterIexhibition.Common.Utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Component
public class PayUtil {
    /**
     * 拼接签名需要的字符串
     * @param map
     * @return
     */
    public String mapStr(Map<String,String> map){
        String data = "";
        for (String s : map.keySet()) {
            data+=s+"="+map.get(s)+"&";
        }
        return data;
    }

    /**
     * MD5加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String md5(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//
//        md.update(data.getBytes());
        String s = DigestUtils.md5DigestAsHex(data.getBytes("utf-8"));

        return s;
    }
}
