package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Service;

import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Beans.User;

import java.util.Map;

public interface WxLoginService {
    Map<String,String> wxLogin(String code) throws Exception;

    void addUser(String userJson,String sessionId);

    User findNowUser(String sessionId);
}
