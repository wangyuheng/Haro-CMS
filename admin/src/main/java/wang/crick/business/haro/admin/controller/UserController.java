package wang.crick.business.haro.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.crick.business.haro.core.base.helper.session.HelperHttpSession;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.module.user.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/password/modify")
    public String modifyPwd(ModelMap model, HttpServletRequest request) {
        model.put("user", HelperHttpSession.getCurrentUser(request));
        return "user/userInfo";
    }
    @RequestMapping(value = "/password/save")
    @ResponseBody
    public String save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) ) {
            if (userService.validExisted(username, password)) {
                if (userService.modifyPassword(newPassword, id)) {
                    return SUCCESS;
                }
            }
        }
        return ERROR;
    }

}
