package com.best.hello.controller.XSS;

import com.best.hello.util.Security;
import com.yumchina.architecture.framework.starter.security.utils.YumXssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * XSS漏洞
 *
 * @author nul1
 * @date 2021/6/29
 */

@Api("XSS漏洞")
@RestController
@RequestMapping("/xss/security")
public class XssSecurity {

    static Logger log = LoggerFactory.getLogger(XssSecurity.class);

    @ApiOperation(value = "vul: 反射型XSS")
    @GetMapping("/escapeHtml")
    public static String escapeHtml(String content) {
        log.info("[vul] 反射型XSS：" + content);
        String escapeHtml = YumXssUtil.escapeHtml(content);
        log.info("escapeHtml 转义后=" + escapeHtml);
        return escapeHtml;
    }

    @ApiOperation(value = "vul: 反射型XSS")
    @GetMapping("/javascript")
    public static String javascript(String content) {
        log.info("[vul] 反射型XSS：" + content);
        return YumXssUtil.escapeJavaScript(content);
    }



    @ApiOperation(value = "vul: 反射型XSS")
    @GetMapping("/escapeCss")
    public static String escapeCss(String content) {
        log.info("[vul] 反射型XSS：" + content);
        return YumXssUtil.escapeCss(content);
    }



    @ApiOperation(value = "vul: 反射型XSS")
    @GetMapping("/encodeUrl")
    public static String encodeUrl(String content) {
        log.info("[vul] 反射型XSS：" + content);
        return YumXssUtil.encodeUrl(content);
    }

    @ApiOperation(value = "vul: 反射型XSS")
    @PostMapping("/filterRichText")
    public static String filterRichText(@RequestBody RichTextDTO richTextDTO) {
        log.info("[vul] 反射型XSS：" + richTextDTO);
        String content = HtmlUtils.htmlUnescape(richTextDTO.getContent());

        return YumXssUtil.filterRichTextBoxContent(content);
    }

    @GetMapping("/vul2")
    public static void vul2(String content, HttpServletResponse response) {
        // 修复，设置ContentType类型：response.setContentType("text/plain;charset=utf-8");
        try {
            response.getWriter().println(content);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "safe: 采用实体编码", notes = "采用自带函数HtmlUtils.htmlEscape()来过滤")
    @GetMapping("/escape")
    public static String safe1(String content) {
        log.info("[safe] htmlEscape实体编码：" + content);
        return HtmlUtils.htmlEscape(content);
    }


    @ApiOperation(value = "safe: 过滤特殊字符", notes = "做filterXss方法, 基于转义的方式")
    @GetMapping("/filter")
    public static String safe2(String content) {
        log.info("[safe] xss过滤：" + content);
        return Security.filterXss(content);
    }


    @ApiOperation(value = "safe: 富文本过滤", notes = "采用Jsoup做富文本过滤")
    @GetMapping("/whitelist")
    public static String safe3(String content) {
        Whitelist whitelist = (new Whitelist())
                .addTags("p", "hr", "div", "img", "span", "textarea")  // 设置允许的标签
                .addAttributes("a", "href", "title") // 设置标签允许的属性, 避免如nmouseover属性
                .addProtocols("img", "src", "http", "https")  // img的src属性只允许http和https开头
                .addProtocols("a", "href", "http", "https");
        log.info("[safe] 富文本过滤：" + content);
        return Jsoup.clean(content, whitelist);
    }


    @ApiOperation(value = "safe: ESAPI", notes = "采用ESAPI过滤")
    @GetMapping("/esapi")
    public static String safe4(String content) {
        log.info("[safe] ESAPI：" + content);
        return ESAPI.encoder().encodeForHTML(content);
    }

//    @ApiOperation(value = "safe: ESAPI", notes = "采用ESAPI过滤")
//    @PostMapping("/filterRichText")
//    public static String safe4(@RequestBody Person person) {
//        log.info("[safe] filterRichText：" + person);
//        return person.getName();
//    }

}
