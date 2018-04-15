package wang.crick.business.haro.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.dictionary.AdviceStateEnum;
import wang.crick.business.haro.core.dictionary.AssemblyCategoryEnum;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;
import wang.crick.business.haro.core.domain.assembly.Assembly;
import wang.crick.business.haro.core.module.advice.AdviceService;
import wang.crick.business.haro.core.module.advice.dto.AdviceDto;
import wang.crick.business.haro.core.module.assembly.AssemblyService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 新闻管理
 * <p>
 * Created by CR on 2015/9/18.
 */
@Controller
@RequestMapping("assembly")
public class AssemblyController extends BaseController {

    @Autowired
    private AssemblyService assemblyService;
    @Autowired
    private AdviceService adviceService;

    @RequestMapping(value = "/{category}/{type}", method = RequestMethod.GET)
    public String view(@PathVariable int category, @PathVariable int type, ModelMap model) {
        AssemblyCategoryEnum categoryEnum = AssemblyCategoryEnum.parse(category);
        model.addAttribute("category", categoryEnum);
        model.addAttribute("entity", assemblyService.findUniqueByType(type));
        switch (categoryEnum) {
            case Service:
                switch (AssemblyTypeEnum.parse(type)) {
                    case Service_advice:
                        AdviceDto adviceDto = new AdviceDto();
                        model.addAttribute("advice",adviceDto);
                        model.addAttribute("adviceList", adviceService.findPaged(adviceDto));
                        model.addAttribute("states", AdviceStateEnum.values());
                        return "advice/adviceList";
                }
                break;
        }
        return "assembly/assembly";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Assembly entity, HttpServletRequest request, ModelMap model) {
        entity.setPublishUser("testUser");
        entity.setPublishTime(new Date());
        if (assemblyService.publish(entity)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @ResponseBody
    public String init(){
        if (assemblyService.init()) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

}
