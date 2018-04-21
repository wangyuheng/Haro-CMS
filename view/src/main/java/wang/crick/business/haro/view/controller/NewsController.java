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
import wang.crick.business.haro.core.dictionary.NewsTypeEnum;
import wang.crick.business.haro.core.module.assembly.AssemblyService;
import wang.crick.business.haro.core.module.news.NewsService;
import wang.crick.business.haro.core.module.news.dto.NewsDto;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseHaroController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private AssemblyService assemblyService;

    @RequestMapping(value="search", method=RequestMethod.POST)
    public String search(@ModelAttribute("newsDto")NewsDto newsDto, ModelMap model) {
        preparedCommonModel(model);
        model.addAttribute("entityList", newsService.findPagedSimple(newsDto));
        int category = AssemblyCategoryEnum.News.getKey();
        AssemblyCategoryEnum categoryEnum = AssemblyCategoryEnum.parse(category);
        model.addAttribute("assemblyCategory", categoryEnum);
        model.addAttribute("assemblyList", AssemblyTypeEnum.getAssemblyList(category));
        return "news/newsSearchList";
    }

    @RequestMapping(value = "list/{category}/{type}", method = RequestMethod.GET)
    public String newsList(@PathVariable int category, @PathVariable int type, @ModelAttribute("newsDto")NewsDto newsDto, ModelMap model) {
        preparedCommonModel(model);
        try {
            if (null == AssemblyCategoryEnum.parse(category) || null == AssemblyTypeEnum.parse(type)) {
                category = AssemblyCategoryEnum.News.getKey();
                type = AssemblyTypeEnum.News_company.getKey();
            }
        }catch (Exception e) {
            return "index";
        }
        AssemblyCategoryEnum categoryEnum = AssemblyCategoryEnum.parse(category);
        switch (AssemblyTypeEnum.parse(type)) {
            case News_company:
                newsDto.setType(NewsTypeEnum.Company.getKey());
                break;
            case News_industry:
                newsDto.setType(NewsTypeEnum.Industry.getKey());
                break;
        }
        model.addAttribute("assemblyCategory", categoryEnum);
        model.addAttribute("assemblyList", AssemblyTypeEnum.getAssemblyList(category));
        model.addAttribute("entity", assemblyService.findUniqueByType(type));

        model.addAttribute("entityList", newsService.findPagedSimple(newsDto));
        return "news/newsList";
    }

    @RequestMapping(value = "listFrag")
    public String listFrag(@ModelAttribute("newsDto")NewsDto newsDto, ModelMap model) {
        model.addAttribute("entityList", newsService.findPagedSimple(newsDto));
        return "news/newsListFrag";
    }


    @RequestMapping(value = "view")
    public String view(ModelMap model, HttpServletRequest request) {
        String id = request.getParameter("id");
        model.addAttribute("news", newsService.findUnique(id));
        return "news/newsFrag";
    }

    @RequestMapping(value = "company/{id}")
    public String companyView(ModelMap model, @PathVariable String id) {
        model.addAttribute("news", newsService.findUnique(id));
        return "news/news";
    }

    @RequestMapping(value = "industry/{id}")
    public String industryView(ModelMap model, @PathVariable String id) {
        model.addAttribute("news", newsService.findUnique(id));
        return "news/news";
    }

}
