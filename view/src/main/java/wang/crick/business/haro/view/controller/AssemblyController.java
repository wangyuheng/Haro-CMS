package wang.crick.business.haro.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wang.crick.business.haro.core.dictionary.AssemblyCategoryEnum;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;
import wang.crick.business.haro.core.module.assembly.AssemblyService;

@Controller
@RequestMapping("/assembly")
public class AssemblyController extends BaseHaroController {

    @Autowired
    private AssemblyService assemblyService;

    @RequestMapping(value = "{category}/{type}", method = RequestMethod.GET)
    public String dispatch(@PathVariable int category, @PathVariable int type, ModelMap model) {
        preparedCommonModel(model);
        try {
            if (null == AssemblyCategoryEnum.parse(category) || null == AssemblyTypeEnum.parse(type)) {
                return "index";
            }
        }catch (Exception e) {
            return "index";
        }
        AssemblyCategoryEnum categoryEnum = AssemblyCategoryEnum.parse(category);
        model.addAttribute("assemblyCategory", categoryEnum);
        model.addAttribute("assemblyList", AssemblyTypeEnum.getAssemblyList(category));
        model.addAttribute("entity", assemblyService.findUniqueByType(type));
        switch (categoryEnum) {
            case Service:
                switch (AssemblyTypeEnum.parse(type)) {
                    case Service_advice:
                        return "service/serviceAdvice";
                }
                break;
        }
        return "assembly/assembly";
    }

}
