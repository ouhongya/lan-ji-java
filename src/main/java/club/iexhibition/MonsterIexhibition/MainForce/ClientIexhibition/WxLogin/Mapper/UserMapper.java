package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Beans.User;

import java.util.List;

public interface UserMapper {
    String findIdByOpenId(String openId);

    void addOpenId(String openId);

    void addUser(User user);

    User findUserById(String openId);

    List<String> findAllUserId();
}
