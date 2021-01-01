package cn.itcast.controller;

import cn.itcast.service.IProductService;
import itcast.domain.Product;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = iProductService.findAll();
        modelAndView.addObject("productList", products);
        modelAndView.setViewName("product-list");
        return modelAndView;
    };

    @RequestMapping(value = "/saveProduct.do")
    public String saveProduct(Product product) throws Exception{
        Integer result = iProductService.saveProduct(product);
        if (result > 0){
            return "redirect:findAll.do";
        }

        return "error";
    }

    @RequestMapping(value = "/editProduct.do")
    public String editProduct(Product product) throws Exception{
        Integer result = iProductService.editProduct(product);
        if (result > 0){
            return "redirect:findAll.do";
        }

        return "error";
    }

    @RequestMapping(value = "/deleteProduct.do")
    public String deleteProduct(@RequestParam(name = "productIds[]", required = true) Integer[] productIds) throws Exception{
//    public String deleteProduct(HttpServletRequest httpServletRequest) throws Exception{ //Integer[] productIds

//        Integer[] productIds = (Integer[])httpServletRequest.getAttribute("productIds");
        String ids = StringUtils.join(productIds, ",");
        Integer result = iProductService.deleteProduct(ids);
        if (result > 0){
            return "redirect:findAll.do";
        }

        return "error";
    }

    @RequestMapping(value = "/editDetail.do")
    public ModelAndView editDetail(@RequestParam(name = "id", required = true) Integer id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Product product = iProductService.findById(id);
        mv.addObject("product", product);
        mv.setViewName("product-edit");
        return mv;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        //转换日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }
}
