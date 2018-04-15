package wang.crick.business.haro.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.dictionary.NewsTypeEnum;
import wang.crick.business.haro.core.domain.News;
import wang.crick.business.haro.core.module.news.NewsService;
import wang.crick.business.haro.core.module.news.dto.NewsDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 新闻管理
 * <p>
 * Created by CR on 2015/9/18.
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/company/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("news") NewsDto newsDto, ModelMap model) {
        newsDto.setType(NewsTypeEnum.Company.getKey());
        model.addAttribute("newsList", newsService.findPaged(newsDto));
        return "news/newsList";
    }

    @RequestMapping(value = "/company/listFrag")
    public String listFrag(@ModelAttribute("news") NewsDto newsDto, ModelMap model) {
        newsDto.setType(NewsTypeEnum.Company.getKey());
        model.addAttribute("newsList", newsService.findPaged(newsDto));
        return "news/newsListFrag";
    }


    @RequestMapping(value = "/company/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return "news/newsAdd";
    }

    @RequestMapping(value = "/company/save")
    public
    @ResponseBody
    String addSave(News entity, HttpServletRequest request) {
        entity.setCreateUser("testCreateUser");
        entity.setCreateTime(new Date());
        entity.setType(NewsTypeEnum.Company.getKey());
        if (newsService.create(entity)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }


    @RequestMapping(value = "/company/delete")
    public
    @ResponseBody
    String delete(News entity, HttpServletRequest request) {
        String id = request.getParameter("id");
        if (newsService.delete(id)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }


    @RequestMapping(value = "/industry/list", method = RequestMethod.GET)
    public String industryList(@ModelAttribute("news") NewsDto newsDto, ModelMap model) {
        newsDto.setType(NewsTypeEnum.Industry.getKey());
        model.addAttribute("newsList", newsService.findPaged(newsDto));
        return "news/newsIndustryList";
    }

    @RequestMapping(value = "/industry/listFrag")
    public String industryListFrag(@ModelAttribute("news") NewsDto newsDto, ModelMap model) {
        newsDto.setType(NewsTypeEnum.Industry.getKey());
        model.addAttribute("newsList", newsService.findPaged(newsDto));
        return "news/newsIndustryListFrag";
    }


    @RequestMapping(value = "/industry/add", method = RequestMethod.GET)
    public String industryAdd(ModelMap model) {
        model.addAttribute("newsTypes", NewsTypeEnum.values());
        return "news/newsIndustryAdd";
    }

    @RequestMapping(value = "/industry/save")
    public
    @ResponseBody
    String industryAddSave(News entity, HttpServletRequest request) {
        entity.setCreateUser("testCreateUser");
        entity.setCreateTime(new Date());
        entity.setType(NewsTypeEnum.Industry.getKey());
        if (newsService.create(entity)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @RequestMapping(value = "/industry/delete")
    public
    @ResponseBody
    String industryDelete(News entity, HttpServletRequest request) {
        String id = request.getParameter("id");
        if (newsService.delete(id)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

}
