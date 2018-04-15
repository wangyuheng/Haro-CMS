package wang.crick.business.haro.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.dictionary.AdviceStateEnum;
import wang.crick.business.haro.core.domain.News;
import wang.crick.business.haro.core.module.advice.AdviceService;
import wang.crick.business.haro.core.module.advice.dto.AdviceDto;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("advice")
public class AdviceController extends BaseController {

    @Autowired(required = false)
    private AdviceService adviceService;

    @RequestMapping("list")
    public String list(@ModelAttribute("advice") AdviceDto adviceDto, ModelMap model) {
        model.addAttribute("states", AdviceStateEnum.values());
        model.addAttribute("adviceList", adviceService.findPaged(adviceDto));
        return "advice/adviceList";
    }

    @RequestMapping("listFrag")
    public String listFrag(@ModelAttribute("advice") AdviceDto adviceDto, ModelMap model) {
        model.addAttribute("adviceList", adviceService.findPaged(adviceDto));
        return "advice/adviceListFrag";
    }

    @RequestMapping(value = "delete")
    public
    @ResponseBody
    String delete(News entity, HttpServletRequest request) {
        String id = request.getParameter("id");
        if (adviceService.delete(id)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }


    @RequestMapping(value = "changeState")
    @ResponseBody
    public String changeState(HttpServletRequest request) {
        String id = request.getParameter("id");
        String state = request.getParameter("state");
        if (adviceService.changeState(Integer.parseInt(state), id)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }


    @RequestMapping("view")
    public String view(HttpServletRequest request, ModelMap model) {
        String id = request.getParameter("id");
        model.addAttribute("advice", adviceService.findUnique(id));
        return "advice/adviceView";
    }
}
