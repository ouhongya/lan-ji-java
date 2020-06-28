package club.iexhibition.MonsterIexhibition.Filter;

import club.iexhibition.MonsterIexhibition.Common.Result.CommonCode;
import club.iexhibition.MonsterIexhibition.Common.Result.ResponseResult;
import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
//@WebFilter(filterName = "览集天下第一client",urlPatterns = {"/client/*"})
public class ClientTokenFilter implements Filter {

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${clientTokenTime}")
    private Long clientTokenTime;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletResponse.reset();

        PrintWriter writer  = null;

        try {
            String sessionId=null;
            //从cookie中获取token
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            //response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            //如果请求登录url  放行
            if("/client/wxlogin/wxLogin".equals(request.getRequestURI())||"/client/exhibition/findExhibition".equals(request.getRequestURI())){
                filterChain.doFilter(servletRequest,servletResponse);
            }
             writer=response.getWriter();

             //获取请求头中的Session-Id
                sessionId=request.getHeader("Session-Id");

                if(sessionId==null||"".equals(sessionId)){
                     ResponseResult responseResult = new ResponseResult(CommonCode.TOKEN_TIME_OUT);
                    String json="{\"queue\": {\"code\": "+responseResult.getQueue().getCode()+",\"message\": \""+responseResult.getQueue().getMessage()+"\"},\"data\": null}";
                    writer.print(json);
                    return;
                }



                     //校验sessionId是否过期
                     Map<String, Object> map = tokenUtil.checkToken(sessionId);

                     if("过期了".equals(map.get("state"))||"格式错误".equals(map.get("state"))){
                         //sessionId过期
                         ResponseResult responseResult = new ResponseResult(CommonCode.TOKEN_TIME_OUT);
                         String json="{\"queue\": {\"code\": "+responseResult.getQueue().getCode()+",\"message\": \""+responseResult.getQueue().getMessage()+"\"},\"data\": null}";
                         //String json= "{ queue: { code: "+responseResult.getQueue().getCode()+", message: "+responseResult.getQueue().getMessage()+" }, data: null}";
                         writer.print(json);
                         return;
                     }else {
                         //sessionId状态正常 重新生成session并加入请求头
                         Map<String,Object> tokenMap = (Map<String,Object>)map.get("data");
                         tokenMap.put("endTime",new Date().getTime()+clientTokenTime);
                         String token = tokenUtil.createToken(tokenMap);
                         response.setHeader("Session-Id",token);
                         response.reset();
                         //放行
                         filterChain.doFilter(servletRequest,servletResponse);
                     }
        }catch (Exception e){
            System.out.println("aaa");
        }finally {
            if(writer!=null){
                writer.close();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
