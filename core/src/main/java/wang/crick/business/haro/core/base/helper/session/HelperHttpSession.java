package wang.crick.business.haro.core.base.helper.session;

import wang.crick.business.haro.core.base.mvc.entity.BaseUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * session属性存取
 * <p>
 * 避免线路变更导致session变空
 */
public class HelperHttpSession {
    private static DefaultHttpSessionManager httpSessionManager;

    public static boolean setAttribute(HttpServletRequest request, String name, Object value) {
        boolean result = false;
        if (isValidate()) {
            httpSessionManager.setAttribute(request, name, value);
            result = true;
        }
        return result;
    }

    public static Object getAttribute(HttpServletRequest request, String name) {
        Object value = null;
        if (isValidate()) {
            value = httpSessionManager.getAttribute(request, name);
        }
        return value;
    }

    public static boolean setCurrentUser(HttpServletRequest request, BaseUser user){
        boolean result = false;
        if (isValidate()) {
            httpSessionManager.setAttribute(request, "BASE_USER", user);
            result = true;
        }
        return result;
    }

    public static BaseUser getCurrentUser(HttpServletRequest request) {
        BaseUser user = null;
        if (isValidate()){
            user = (BaseUser) httpSessionManager.getAttribute(request, "BASE_USER");
        }
        return user;
    }

    public static boolean clearCurrentUser(HttpServletRequest request) {
        boolean result = false;
        if (isValidate()) {
            httpSessionManager.removeAttribute(request, "BASE_USER");
            result = true;
        }
        return result;
    }


    public static String getCurrentTenantId(HttpServletRequest request) {
        //TODO tenant_id_session
        return "TEST_TENANT_ID";
    }

    public static boolean removeAttribute(HttpServletRequest request, String name) {
        boolean result = false;
        if (isValidate()) {
            httpSessionManager.removeAttribute(request, name);
            result = true;
        }
        return result;
    }

    public static Enumeration<String> getAttributeNames(HttpServletRequest request) {
        Enumeration<String> result = null;
        if (isValidate()) {
            result = httpSessionManager.getAttributeNames(request);
        }
        return result;
    }

    private static boolean isValidate() {
        return httpSessionManager != null;
    }

    public void setHttpSessionManager(DefaultHttpSessionManager httpSessionManager) {
        HelperHttpSession.httpSessionManager = httpSessionManager;
    }
}
