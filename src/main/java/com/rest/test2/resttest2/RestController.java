package com.rest.test2.resttest2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/")
public class RestController {


    @RequestMapping(value = "/create_cookie")
    public String createCookie(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        cookieData(httpServletRequest,httpServletResponse);
        return "Cookie salvato correttamente";
    }

    public void cookieData(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        final String cookieName = "name_cookie";
        final String cookieValue = "value_cookie"; // might be request as Encoded
        final Boolean useSecureCookie = false;
        final int expiryTime = 60 * 60 * 24;
        final String cookiePath = "/";

        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"));
        cookie.setSecure(useSecureCookie);
        cookie.setMaxAge(expiryTime);
        cookie.setPath(cookiePath);
        httpServletResponse.addCookie(cookie);

    }


    @GetMapping(value = "/show_cookie")
    public String getCookie(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        String nome = null;
        String value = null;
        if (null != cookies){
            for (Cookie c:cookies){
                nome = c.getName();
                value = c.getValue();
            }
        }
        return "Dati dei cookie " + nome + " " + value;
    }

    @GetMapping(value = "/delete_cookie")
    public String deleteCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        Cookie[] cookies = httpServletRequest.getCookies();
        if (null != cookies){
            for (Cookie cookie:cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0); //0 does it means delete
                httpServletResponse.addCookie(cookie);
            }
        }

        return "Cookie cancellato";
    }



}
