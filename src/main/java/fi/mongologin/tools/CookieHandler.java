package fi.mongologin.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHandler {
    public static String getCookie(HttpServletRequest request, String cookieName) {
      Cookie cookie[] = request.getCookies();
      if(cookie != null) {
        for(Cookie cok:cookie) {
          if(cok.getName().equals(cookieName)) {
            if(!("".equals(cok.getValue()))) {
              return cok.getValue();
            }
            else {
              return null;
            }
          }
        }
      }
      return null;
    }
    
    public static HttpServletResponse setCookie(HttpServletResponse response, String cookieName, String cookieValue) {
      Cookie cookie = new Cookie(cookieName, cookieValue);
      cookie.setMaxAge(7*24*60*60);
      cookie.setPath("/");
      response.addCookie(cookie);
      return response;
    }
    
    public static HttpServletResponse removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
      Cookie cookie = new Cookie(cookieName, "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
      return response;
    }
  }