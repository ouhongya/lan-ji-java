package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAccount.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAccount.Beans.*;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountService {
    LoginAccount login(String username, String passowrd);

    Integer addAccount(Account account) throws Exception;

    boolean addRole(Role role) throws Exception;

    PageInfo<Account> findAllAccount(ParamBean paramBean) throws Exception;

    List<ParentFunction> findAccountFBByUid(String id, HttpServletRequest request) throws Exception;

    PageInfo<Role> findAllRole(ParamBean paramBean) throws Exception;

    List<ParentFunction> findAllFB() throws Exception;

    void updateAccount(Account account) throws Exception;

    Boolean updateRole(Role role);

    boolean deleteAccount(String uid) throws Exception;

    boolean deleteRole(String id) throws Exception;

    List<ParentFunction> findFBByRoleId(String id) throws Exception;

    List<AccountPower> findAccountPower(HttpServletRequest request) throws Exception;

    void adminR() throws Exception;

    void adminU(String roleId) throws Exception;
}
