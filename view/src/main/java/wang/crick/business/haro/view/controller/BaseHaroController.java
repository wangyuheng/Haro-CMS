package wang.crick.business.haro.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.dictionary.AssemblyCategoryEnum;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;
import wang.crick.business.haro.core.module.layoutinfo.LayoutInfoService;

public abstract class BaseHaroController extends BaseController {

    @Autowired
    private LayoutInfoService layoutInfoService;

    protected void preparedCommonModel(ModelMap model){
        model.addAttribute("layoutInfo", layoutInfoService.findUnique());
        model.addAttribute("companies", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Company.getKey()));
        model.addAttribute("cultures", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Culture.getKey()));
        model.addAttribute("newsList", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.News.getKey()));
        model.addAttribute("businesses", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Business.getKey()));
        model.addAttribute("partners", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Partner.getKey()));
        model.addAttribute("technologies", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Technology.getKey()));
        model.addAttribute("talents", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Talent.getKey()));
        model.addAttribute("services", AssemblyTypeEnum.getAssemblyList(AssemblyCategoryEnum.Service.getKey()));
    }
}
