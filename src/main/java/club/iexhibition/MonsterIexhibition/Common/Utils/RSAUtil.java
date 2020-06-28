package club.iexhibition.MonsterIexhibition.Common.Utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


@Component
public class RSAUtil {


    @Value("${pubKey}")
    private String publicKey;
    @Value("${priKey}")
    private String privateKey;

    /**
     * base64加密
     * @param bytes
     * @return
     */
    private String baseEncode(byte[] bytes) throws UnsupportedEncodingException {
          return new String(Base64.getMimeEncoder().encode(bytes),"utf-8");
    }

    /**
     * base64解密
     * @param str
     * @return
     */
//    public String baseDecode(byte[] bytes) throws UnsupportedEncodingException {
//          return new String(Base64.getMimeDecoder().decode(bytes),"utf-8");
//    }

    private byte[] baseDecode(String str){
        return Base64.getMimeDecoder().decode(str);
    }


    /**
     * 生成随机密钥对
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private void getKeys() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //获取RSA算法的密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器  指定密钥长度为96-1024    new SecureRandom()是security中生成随机数的类  生成的随机数在byte数组中
        keyPairGenerator.initialize(1024,new SecureRandom());
        //生成随机密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
       // KeyPair keyPair1 = keyPairGenerator.genKeyPair();
        //获取公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //获取私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //将公钥私钥解析为字符串  并通过Base64加密
        String publicKeyStr = baseEncode(publicKey.getEncoded());
        String privateKeyStr = baseEncode(privateKey.getEncoded());
    }


    /**
     * RSA公钥加密
     * @param str
     * @return
     */
    public String rsaEncode(String str) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        //base64解密公钥
        byte[] pubKeyBytes = baseDecode(publicKey);
        //通过解密后的公钥字符串 获取公钥对象
        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubKeyBytes));
        //RSA加密  构建密码对象（加密解密都用它  传不同的参数）
        Cipher cipher = Cipher.getInstance("RSA");
        //初始化密码对象
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        //加密
        byte[] enStrBytes = cipher.doFinal(str.getBytes("utf-8"));
        String s = baseEncode(enStrBytes);
        return s;
    }

    /**
     * RSA私钥解密
     * @param str
     * @return
     */
    public String rsaDecode(String str) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //将被base64加密的字符串解密
        byte[] strBytes = baseDecode(str);
        //base64解密私钥
        byte[] priKeyBytes = baseDecode(privateKey);
        //获取私钥对象
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(priKeyBytes));
        //解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] deStrBytes = cipher.doFinal(strBytes);
        return new String(deStrBytes);
    }


}
