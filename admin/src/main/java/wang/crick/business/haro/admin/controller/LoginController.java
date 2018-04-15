package wang.crick.business.haro.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import wang.crick.business.haro.core.base.helper.session.HelperHttpSession;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.dictionary.AssemblyCategoryEnum;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;
import wang.crick.business.haro.core.domain.User;
import wang.crick.business.haro.core.module.user.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("login")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(User login, HttpServletRequest request, ModelMap model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            User user = userService.login(username, password);
            if (null != user) {
                HelperHttpSession.setCurrentUser(request, user);
                model.addAttribute("companies", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Company.getKey()));
                model.addAttribute("cultures", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Culture.getKey()));
                model.addAttribute("newsList", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.News.getKey()));
                model.addAttribute("businesses", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Business.getKey()));
                model.addAttribute("partners", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Partner.getKey()));
                model.addAttribute("technologies", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Technology.getKey()));
                model.addAttribute("talents", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Talent.getKey()));
                model.addAttribute("services", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Service.getKey()));
                return "index/index";
            } else {
                model.put("message", "用户名或者密码有误，请重新输入！");
            }
        }
        return "index/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HelperHttpSession.clearCurrentUser(request);
        return "index/login";
    }
}
