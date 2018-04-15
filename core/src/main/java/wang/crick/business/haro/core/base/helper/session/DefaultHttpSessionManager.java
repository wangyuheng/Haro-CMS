package wang.crick.business.haro.core.base.helper.session;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class DefaultHttpSessionManager {

    public boolean setAttribute(HttpServletRequest request, String name, Object value) {
        request.getSession().setAttribute(name, value);
        return true;
    }

    public Object getAttribute(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }

    public boolean removeAttribute(HttpServletRequest request, String name) {
        request.getSession().removeAttribute(name);
        return true;
    }

    public Enumeration<String> getAttributeNames(HttpServletRequest request) {
        return request.getSession().getAttributeNames();
    }
}
