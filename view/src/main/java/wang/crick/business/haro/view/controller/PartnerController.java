package wang.crick.business.haro.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wang.crick.business.haro.core.dictionary.AssemblyCategoryEnum;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;
import wang.crick.business.haro.core.module.assembly.AssemblyService;
import wang.crick.business.haro.core.module.partner.dto.PartnerDto;
import wang.crick.business.haro.core.module.partner.partnerproject.PartnerProjectService;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.PartnerProjectImageService;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.dto.PartnerProjectImageDto;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/partner")
public class PartnerController extends BaseHaroController {

    @Autowired
    private PartnerProjectService partnerProjectService;
    @Autowired
    private PartnerProjectImageService partnerProjectImageService;
    @Autowired
    private AssemblyService assemblyService;

    @RequestMapping(value = "project/list/{category}/{type}", method = RequestMethod.GET)
    public String newsList(@PathVariable int category, @PathVariable int type, @ModelAttribute("partnerDto")PartnerDto partnerDto, ModelMap model) {
        partnerDto.setPageSize(12);
        preparedCommonModel(model);
        try {
            if (null == AssemblyCategoryEnum.parse(category) || null == AssemblyTypeEnum.parse(type)) {
                category = AssemblyCategoryEnum.Partner.getKey();
                type = AssemblyTypeEnum.Partner_project.getKey();
            }
        }catch (Exception e) {
            return "index";
        }
        AssemblyCategoryEnum categoryEnum = AssemblyCategoryEnum.parse(category);

        model.addAttribute("assemblyCategory", categoryEnum);
        model.addAttribute("assemblyList", AssemblyTypeEnum.getAssemblyList(category));
        model.addAttribute("entity", assemblyService.findUniqueByType(type));
        switch (AssemblyTypeEnum.parse(type)) {
            case Partner_project:
                model.addAttribute("entityList", partnerProjectService.findPaged(partnerDto));
                break;
            case Partner_contact:
                break;
        }
        return "partner/partnerProjectList";
    }

    @RequestMapping(value = "project/listFrag")
    public String listFrag(@ModelAttribute("partnerDto")PartnerDto partnerDto, ModelMap model) {
        partnerDto.setPageSize(12);
        model.addAttribute("entityList", partnerProjectService.findPaged(partnerDto));
        return "partner/partnerProjectListFrag";
    }


    @RequestMapping(value = "project/image/listFrag")
    public String view(@ModelAttribute("partnerProjectImageDto")PartnerProjectImageDto partnerProjectImageDto, ModelMap model, HttpServletRequest request) {
        model.addAttribute("entityList", partnerProjectImageService.findPaged(partnerProjectImageDto));
        return "partner/partnerProjectImageListFrag";
    }


    @RequestMapping(value = "project/image/index")
    public String imageIndex(@ModelAttribute("partnerProjectImageDto")PartnerProjectImageDto partnerProjectImageDto, ModelMap model) {
        preparedCommonModel(model);
        int category = AssemblyCategoryEnum.Partner.getKey();
        int type = AssemblyTypeEnum.Partner_project.getKey();

        AssemblyCategoryEnum categoryEnum = AssemblyCategoryEnum.parse(category);

        model.addAttribute("assemblyCategory", categoryEnum);
        model.addAttribute("assemblyList", AssemblyTypeEnum.getAssemblyList(category));
        model.addAttribute("entity", assemblyService.findUniqueByType(type));
        switch (AssemblyTypeEnum.parse(type)) {
            case Partner_project:
                model.addAttribute("entityList", partnerProjectImageService.findPaged(partnerProjectImageDto));
                break;
            case Partner_contact:
                break;
        }
        return "partner/partnerProjectImageIndex";
    }
//
//    @RequestMapping(value = "company/{id}")
//    public String companyView(ModelMap model, @PathVariable String id) {
//        model.addAttribute("news", newsService.findUnique(id));
//        return "news/news";
//    }
//
//    @RequestMapping(value = "industry/{id}")
//    public String industryView(ModelMap model, @PathVariable String id) {
//        model.addAttribute("news", newsService.findUnique(id));
//        return "news/news";
//    }

}
