package wang.crick.business.haro.admin.controller;

import wang.crick.business.haro.core.base.helper.session.HelperHttpSession;
import wang.crick.business.haro.core.base.mvc.entity.BaseUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationFilter implements Filter {
    private final List<String> excludePaths = new ArrayList<String>();
    private String loginPath;

    public void destroy() {

    }

    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String encoding = "utf-8";
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
        BaseUser user = HelperHttpSession.getCurrentUser(req);
        if (user == null) {
            String requestURI = req.getRequestURI().substring(
                    req.getContextPath().length() + 1);
            if (checkPathPermission(requestURI)) {
                chain.doFilter(request, response);
                return;
            } else {
                resp.sendRedirect(req.getContextPath() + "/" + loginPath);
                return;
            }
        }
        chain.doFilter(request, response);

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        loginPath = filterConfig.getInitParameter("loginPath");
        if (loginPath == null || loginPath.isEmpty()) {
            throw new ServletException(" the initParameter redirectPath cann't be not null!");
        }
        excludePaths.add(loginPath);
        String excludePath = filterConfig.getInitParameter("excludePaths");
        if (excludePath != null && !excludePath.isEmpty()) {
            String[] values = excludePath.split(",");
            Collections.addAll(excludePaths, values);
        }
    }

    private boolean checkPathPermission(String currentPath) {
        boolean success = false;
        // 出于效率的考虑，此处仅仅列出少数不受保护的资源则直接可以访问,此处需要进一步处理
        // 如果是对网站类等，需要访问大量不受保护的资源则，需要采用基于antPatcher或者正则类的
        // 则需要重新设置于定义
        if (excludePaths != null) {
            for (String excludeItem : excludePaths) {
                if (excludeItem.equals(currentPath)) {
                    success = true;
                    break;
                }
            }
        }
        return success;
    }
}
