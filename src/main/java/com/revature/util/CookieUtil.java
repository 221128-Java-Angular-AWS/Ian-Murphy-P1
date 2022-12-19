package com.revature.util;

import com.revature.exceptions.RoleIncorrectException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

    public static final String USER_ID_COOKIE = "userId";
    public static final String USER_TYPE_COOKIE = "userType";

    public static Integer getUserIdCookie(HttpServletRequest req) {
        String userIdStr = getCookie(req, USER_ID_COOKIE);
        if(userIdStr != null) {
            return Integer.parseInt(userIdStr);
        }
        return null;
    }

    public static String getUserTypeCookie(HttpServletRequest req) {
        return getCookie(req, USER_TYPE_COOKIE);
    }

    public static boolean verifyUserType(HttpServletRequest req, String userType) throws RoleIncorrectException {
        if (userType != null && userType.equals(getCookie(req, USER_TYPE_COOKIE))) {
            return true;
        }
        throw new RoleIncorrectException("Incorrect role");
    }

    private static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie1 = cookies[i];
            if (cookie1.getName().equals(name)) {
                return cookie1.getValue();
            }/*from  w w w .j a v  a2  s  .co  m*/
        }
        return null;
    }
}
