package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Beans.Integral;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.Cookie;

public interface IntegralService {
    PageInfo<Integral> findIntegral(ParamBean paramBean);

    void giveIntegral(Integral integral, Cookie[] cookies);
}
