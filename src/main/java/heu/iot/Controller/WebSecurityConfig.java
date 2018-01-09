package heu.iot.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/17 19:24
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    /**
     * 登录session key
     */
    public final static String ID = "user";
    public final static String NAME = "name";
    public final static String LEVEL = "level";
    public final static String PIC = "pic";
    public final static String StudentTop4="studenttop4";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            String url = request.getRequestURL().toString();
            HttpSession session = request.getSession();
            //登陆控制
            if (session.getAttribute(ID) != null) {
            //登陆域控制
                if (session.getAttribute(LEVEL).toString().equals("2") && !url.matches("(.*)student(.*)")) {
                    response.sendRedirect("/login");
                    return false;
                }
                return true;
            }


            // 跳转登录
            url = "/login";
            response.sendRedirect(url);
            return false;
        }
    }
}
