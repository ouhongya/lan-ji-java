package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Beans.User;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Mapper.UserMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Service.WxLoginService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class WxLoginServiceImpl implements WxLoginService {

    @Value("${AppId}")
    private String appId;

    @Value("${Secret}")
    private String secret;

    @Value("${clientTokenTime}")
    private Long clientTokenTime;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserMapper userMapper;

    /**
     * 微信小程序登录
     * @param code
     * @return
     */
    @Override
    public  Map<String,String> wxLogin(String code) throws Exception {
        //创建存储WX返回值的map
        Map<String,String> wxMap = new HashMap<>();
        URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //请求方式
        urlConnection.setRequestMethod("GET");
        //设置content_type
        urlConnection.setRequestProperty("Content-Type","application/json;charset=utf8");
        //是否允许写出
        urlConnection.setDoOutput(true);
        //是否允许读入
        urlConnection.setDoInput(true);
        //是否使用缓存
        urlConnection.setUseCaches(false);
        //获取WX返回的流
        InputStream inputStream = urlConnection.getInputStream();
        //将流转换为String
        byte[] bytes = new byte[inputStream.available()];

        inputStream.read(bytes);

        String s = new String(bytes);
        //String转json对象
        JSONObject jsonObject = JSONObject.parseObject(s);
        //获取openId
        String openId= (String) jsonObject.get("openid");
        String sessionKey= (String) jsonObject.get("session_key");

        wxMap.put("openId",openId);
        wxMap.put("sessionKey",sessionKey);

        //将openId使用token加密
        Map<String,Object> map = new HashMap<>();
        map.put("openId",openId);
        map.put("endTime",new Date().getTime()+clientTokenTime);
        String token = tokenUtil.createToken(map);

        //查询该用户是否为新用户
        User user=userMapper.findUserById(openId);
        String state;
        if(user!=null){
            if(user.getName()==null||"".equals(user.getName())){
                if(user.getId()==null||"".equals(user.getId())){
                    //新用户  将openId加入数据库
                    userMapper.addOpenId(openId);
                }
                state="1";
            }else {
                //老用户
                state="2";
            }
        }else {
            //新用户  将openId加入数据库
            userMapper.addOpenId(openId);
            state="1";
        }

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("token",token);
        resultMap.put("state",state);
        return resultMap;
    }

    /**
     * 注册完善用户信息
     * @param userJson
     */
    @Override
    public void addUser(String userJson,String sessionId) {
        if(sessionId==null||"".equals(sessionId)){
            return;
        }
        //从sessionId中获取openId
        String openId=tokenUtil.getOpenId(sessionId);
        Map<String,Object> userJsonMap = (Map<String,Object>)JSON.parse(userJson);
        User user = new User();
        //设置用户基本属性
        user.setId(openId);
        user.setAvatar(userJsonMap.get("avatarUrl").toString());
        user.setCity(userJsonMap.get("city").toString());
        user.setCountry(userJsonMap.get("country").toString());
        user.setGender(Integer.parseInt(userJsonMap.get("gender").toString()));
        user.setLanguage(userJsonMap.get("language").toString());
        user.setName(userJsonMap.get("nickName").toString());
        user.setProvince(userJsonMap.get("province").toString());

        userMapper.addUser(user);
    }

    /**
     * 查询当前登录用户
     * @param sessionId
     * @return
     */
    @Override
    public User findNowUser(String sessionId) {
         String openId = tokenUtil.getOpenId(sessionId);
         User user = userMapper.findUserById(openId);
         return user;
    }
}
