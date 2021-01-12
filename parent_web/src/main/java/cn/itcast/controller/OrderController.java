package cn.itcast.controller;

import cn.itcast.service.IOrderService;
import com.github.pagehelper.PageInfo;
import itcast.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

    @RequestMapping(value = "/findAll.do")
    @Secured({"ROLE_ADMIN"})
    public ModelAndView findAll(@RequestParam(name = "page" , required = true, defaultValue = "1") int page,
                                @RequestParam(name = "size", required = true, defaultValue = "5") int size) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Order> orders = iOrderService.findAll(page, size);

        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo<>(orders);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("orders-page-list");
        return modelAndView;
    }

    @RequestMapping(value = "/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) int id) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        Order order = iOrderService.findById(id);
        modelAndView.addObject("orders", order);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
