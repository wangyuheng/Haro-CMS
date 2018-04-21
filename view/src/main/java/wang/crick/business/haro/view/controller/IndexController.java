package wang.crick.business.haro.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wang.crick.business.haro.core.dictionary.AssemblyTypeEnum;
import wang.crick.business.haro.core.dictionary.NewsTypeEnum;
import wang.crick.business.haro.core.module.assembly.AssemblyService;
import wang.crick.business.haro.core.module.news.NewsService;
import wang.crick.business.haro.core.module.partner.partnerproject.PartnerProjectService;

@Controller
@RequestMapping(value = {"/", "/index"})
public class IndexController extends BaseHaroController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private AssemblyService assemblyService;
	@Autowired
	private PartnerProjectService partnerProjectService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        preparedCommonModel(model);
		model.addAttribute("companyIntroduction", assemblyService.findUniqueByType(AssemblyTypeEnum.Company_introduction.getKey()));
		model.addAttribute("partnerProjectList", partnerProjectService.findIndex());
		model.addAttribute("companyNews", newsService.findIndex(NewsTypeEnum.Company.getKey()));
		model.addAttribute("industryNews", newsService.findIndex(NewsTypeEnum.Industry.getKey()));
		return "index";
	}
}