package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Mapper.UserMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Beans.Integral;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Mapper.IntegralMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Service.IntegralService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.List;
@Transactional
@Service
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private IntegralMapper integralMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenUtil tokenUtil;
    /**
     * 查询积分发放列表
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<Integral> findIntegral(ParamBean paramBean) {
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());
        List<Integral> integrals = integralMapper.findIntegral(paramBean.getSearch());
         PageInfo<Integral> pageInfo = new PageInfo<>(integrals);
         return pageInfo;
    }


    /**
     * 发放积分
     * @param integral
     */
    @Override
    public void giveIntegral(Integral integral, Cookie[] cookies) {
        //从cookie中获取token
        String token=null;
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if("guess".equals(cookie.getName())){
                    token=cookie.getValue();
                }
            }
        }else {
            return;
        }

        if(token!=null){
            //获取accountId
             String uid = tokenUtil.getUidFromToken(token);
             integral.setUid(uid);
        }else {
            return;
        }
        integral.setId(UUIDGenerator.uuid());
        integralMapper.addIntegralJournal(integral);
        //
        if(integral.getTypes()==1){
            //全部用户发放积分
            List<String> ids = userMapper.findAllUserId();

            integralMapper.addUserIntegral(integral.getId(),ids,integral.getNum());
        }
    }
}
