package com.luo.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request){
        String language=request.getParameter("l");

        Locale locale=Locale.getDefault(); //没有就使用默认的语言
        if(!StringUtils.isEmpty(language)){
            //zh_CN
            String[] spilt=language.split("_");
            //国家 地区
            locale = new Locale(spilt[0],spilt[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
