package wang.crick.business.haro.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.dictionary.AssemblyCategoryEnum;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;

@Controller
public class IndexController extends BaseController {

    @GetMapping("index")
    public String index(ModelMap model) {
        model.addAttribute("companies", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Company.getKey()));
        model.addAttribute("cultures", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Culture.getKey()));
        model.addAttribute("newsList", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.News.getKey()));
        model.addAttribute("businesses", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Business.getKey()));
        model.addAttribute("partners", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Partner.getKey()));
        model.addAttribute("technologies", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Technology.getKey()));
        model.addAttribute("talents", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Talent.getKey()));
        model.addAttribute("services", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Service.getKey()));
        return "index/index";
    }

    @GetMapping(value = "index/home")
    public String home() {
        return "index/home";
    }

}