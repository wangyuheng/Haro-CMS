package wang.crick.business.haro.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.domain.LayoutInfo;
import wang.crick.business.haro.core.module.layoutinfo.LayoutInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("layoutInfo")
public class LayoutInfoController extends BaseController {

    @Autowired
    private LayoutInfoService layoutInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public String view(ModelMap model) {
        model.addAttribute("layoutInfo", layoutInfoService.findUnique());
        return "layoutinfo/layoutInfo";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(LayoutInfo entity, HttpServletRequest request, ModelMap model) {
        entity.setPublishUser("testUser");
        entity.setPublishTime(new Date());
        if (layoutInfoService.publish(entity)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @RequestMapping(value = "init", method = RequestMethod.GET)
    @ResponseBody
    public String init() {
        if(layoutInfoService.init()) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
}
