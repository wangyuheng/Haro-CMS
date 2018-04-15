package wang.crick.business.haro.admin.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import wang.crick.business.haro.core.base.helper.session.HelperHttpSession;
import wang.crick.business.haro.core.base.mvc.entity.BaseUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BaseUser user = HelperHttpSession.getCurrentUser(request);
        if (null != user) {
            return super.preHandle(request, response, handler);
        } else {
            response.sendRedirect("/login/login");
            return false;
        }
    }
}
