package cn.itcast.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StringToDateConverter implements Converter<String, Date> { // S:表示接受的类型，T:表示目标类型
    @Override
    public Date convert(String s) {
        if (s == null) {
            throw new RuntimeException("请传入参数");
        }

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.parse(s);
        } catch (Exception e){
            throw new RuntimeException("字符串转日期失败");
        }
    }
}
