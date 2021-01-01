package cn.itcast.utils;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDate implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder webDataBinder) {
        // TODO Auto-generated method stub
        //转换日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }
}
