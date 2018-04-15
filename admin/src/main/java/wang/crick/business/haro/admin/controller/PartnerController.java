package wang.crick.business.haro.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.dictionary.AssemblyCategoryEnum;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;
import wang.crick.business.haro.core.domain.PartnerProject;
import wang.crick.business.haro.core.domain.PartnerProjectImage;
import wang.crick.business.haro.core.module.partner.dto.PartnerDto;
import wang.crick.business.haro.core.module.partner.partnerproject.PartnerProjectService;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.PartnerProjectImageService;
import wang.crick.business.haro.core.module.partner.partnerprojectimage.dto.PartnerProjectImageDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/partner")
public class PartnerController extends BaseController {

    @Autowired
    private PartnerProjectService partnerProjectService;
    @Autowired
    private PartnerProjectImageService partnerProjectImageService;

    @RequestMapping(value = "/project/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("partnerDto") PartnerDto partnerDto, ModelMap model) {
        model.addAttribute("partnerProjectList", partnerProjectService.findPaged(partnerDto));
        return "partner/partnerProjectList";
    }

    @RequestMapping(value = "/project/listFrag")
    public String listFrag(@ModelAttribute("partnerDto") PartnerDto partnerDto, ModelMap model) {
        model.addAttribute("partnerProjectList", partnerProjectService.findPaged(partnerDto));
        return "partner/partnerProjectListFrag";
    }


    @RequestMapping(value = "/project/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("category", AssemblyCategoryEnum.Partner);
        model.addAttribute("type", AssemblyTypeEnum.Partner_project);
        return "partner/partnerProjectAdd";
    }

    @RequestMapping(value = "/project/save")
    public
    @ResponseBody
    String addSave(PartnerProject entity, HttpServletRequest request) {
        entity.setCreateUser("testCreateUser");
        entity.setCreateTime(new Date());
        if (partnerProjectService.create(entity)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @RequestMapping(value = "/project/delete")
    public
    @ResponseBody
    String projectDelete(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (partnerProjectService.delete(id)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @RequestMapping(value = "/project/image/list", method = RequestMethod.GET)
    public String listImage(@ModelAttribute("partnerProjectImageDto") PartnerProjectImageDto partnerProjectImageDto, ModelMap model) {
        model.addAttribute("partnerProjectImageList", partnerProjectImageService.findPaged(partnerProjectImageDto));
        return "partner/partnerProjectImageList";
    }

    @RequestMapping(value = "/project/image/listFrag")
    public String listImageFrag(@ModelAttribute("partnerProjectImageDto") PartnerProjectImageDto partnerProjectImageDto, ModelMap model) {
        model.addAttribute("partnerProjectImageList", partnerProjectImageService.findPaged(partnerProjectImageDto));
        return "partner/partnerProjectImageListFrag";
    }

    @RequestMapping(value = "/project/image/add", method = RequestMethod.GET)
    public String addImage(@ModelAttribute("partnerProjectImageDto") PartnerProjectImageDto partnerProjectImageDto, ModelMap model) {
        return "partner/partnerProjectImageAdd";
    }

    @RequestMapping(value = "/project/image/save")
    public
    @ResponseBody
    String addSaveImage(PartnerProjectImage entity, HttpServletRequest request) {
        entity.setCreateUser("testCreateUser");
        entity.setCreateTime(new Date());
        if (partnerProjectImageService.create(entity)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @RequestMapping(value = "/project/image/delete")
    public
    @ResponseBody
    String projectImageDelete(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (partnerProjectImageService.delete(id)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

}
