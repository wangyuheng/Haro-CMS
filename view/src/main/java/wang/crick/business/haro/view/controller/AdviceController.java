package wang.crick.business.haro.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.crick.business.haro.core.base.mvc.controller.BaseController;
import wang.crick.business.haro.core.domain.Advice;
import wang.crick.business.haro.core.module.advice.AdviceService;

import java.util.Date;

/**
 * 投诉建议
 */
@Controller
@RequestMapping("advice")
public class AdviceController extends BaseController {

    @Autowired
    private AdviceService adviceService;

    @RequestMapping(value="submit", method = RequestMethod.POST)
    @ResponseBody
    private String adviceSubmit(Advice entity){
        entity.setCreateTime(new Date());
        if (adviceService.create(entity)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

}
