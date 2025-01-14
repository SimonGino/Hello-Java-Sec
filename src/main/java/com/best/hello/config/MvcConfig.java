package com.best.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index/xss").setViewName("xss");
        registry.addViewController("/index/xss/escapeHtml").setViewName("escapeHtml");
        registry.addViewController("/index/xss/escapeJavaScript").setViewName("escapeJavaScript");
        registry.addViewController("/index/xss/escapeCss").setViewName("escapeCss");
        registry.addViewController("/index/xss/encodeUrl").setViewName("encodeUrl");
        registry.addViewController("/index/xss/filterRichText").setViewName("filterRichText");
        registry.addViewController("/index/xss/global/escapeHtml").setViewName("globalEscapeHtml");
        registry.addViewController("/index/xss/global/filterRichText").setViewName("globalFilterRichText");
//        registry.addViewController("/index/rce").setViewName("rce");
//        registry.addViewController("/index/spel").setViewName("spel");
//        registry.addViewController("/index/ssti").setViewName("ssti");
//        registry.addViewController("/index/sqli/jdbc").setViewName("sqli_jdbc");
//        registry.addViewController("/index/sqli/mybatis").setViewName("sqli_mybatis");
//        registry.addViewController("/index/ssrf").setViewName("ssrf");
//        registry.addViewController("/index/traversal").setViewName("traversal");
//        registry.addViewController("/index/xxe").setViewName("xxe");
//        registry.addViewController("/index/deserialize").setViewName("deserialize");
//        registry.addViewController("/index/redirect").setViewName("redirect");
//        registry.addViewController("/index/actuator").setViewName("actuator");
//        registry.addViewController("/index/idor").setViewName("idor");
//        registry.addViewController("/index/upload").setViewName("upload");
//        registry.addViewController("/index/xstream").setViewName("xstream");
//        registry.addViewController("/index/fastjson").setViewName("fastjson");
//        registry.addViewController("/index/xff").setViewName("xff");
//        registry.addViewController("/index/unauth").setViewName("unauth");
//        registry.addViewController("/index/jackson").setViewName("jackson");
//        registry.addViewController("/index/log4j").setViewName("log4j");
//        registry.addViewController("/index/jndi").setViewName("jndi");
//        registry.addViewController("/index/csrf").setViewName("csrf");
//        registry.addViewController("/index/dos").setViewName("dos");
//        registry.addViewController("/index/cors").setViewName("cors");
//        registry.addViewController("/index/captcha").setViewName("captcha_vul");
//        registry.addViewController("/index/swagger").setViewName("swagger");
//        registry.addViewController("/index/jwt").setViewName("jwt");
//        registry.addViewController("/index/xpath").setViewName("xpath");

    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/ldap", "/login", "/css/**", "/js/**", "/img/**", "/Unauth/**", "/captcha");
    }
}
