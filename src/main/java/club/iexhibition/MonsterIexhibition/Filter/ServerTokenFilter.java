package club.iexhibition.MonsterIexhibition.Filter;

import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

//@WebFilter(filterName = "览集天下第一server",urlPatterns = {"/server/*"})
public class ServerTokenFilter implements Filter {

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${tokenTime}")
    private Long tokenTime;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 每次请求  校验token是否过期
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletResponse.reset();

        ServletOutputStream outputStream = null;

        try {
            //从cookie中获取token
            Map<String, Object> tokenMap = null;

            HttpServletRequest request = (HttpServletRequest) servletRequest;

            //获取请求url
            String requestURI = request.getRequestURI();

            HttpServletResponse response = (HttpServletResponse) servletResponse;

            Cookie[] cookies = request.getCookies();

            outputStream = response.getOutputStream();
            //登录放行  其他拦截
            if("/server/account/login".equals(requestURI)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                if(cookies!=null){
                    for (Cookie cookie : cookies) {
                        if("guess".equals(cookie.getName())){
                            tokenMap = tokenUtil.checkToken(cookie.getValue());
                            break;
                        }
                    }
                }
                if(tokenMap==null||tokenMap.size()<=0){
                    outputStream.println("555");
                }else {
                    if("过期了".equals(tokenMap.get("state"))||"格式错误".equals(tokenMap.get("state"))){
                        outputStream.println("555");
                    }else {
                        //token正常 可以访问  刷新token过期时间
                        Map<String,Object> dataMap = (Map<String, Object>) tokenMap.get("data");
                        Map<String,Object> cTokenMap=new HashMap<>();
                        cTokenMap.put("accountId",dataMap.get("accountId"));
                        cTokenMap.put("endTime",new Date().getTime()+tokenTime);
                        String token = tokenUtil.createToken(cTokenMap);
                        response.setHeader("guess",token);
                        //放行
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                }
            }
        }catch (Exception e){
            System.out.println("aaa");
        }finally {
            outputStream.close();
        }
    }

    @Override
    public void destroy() {

    }
}
