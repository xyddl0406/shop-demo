package com.crewmate.shopauth.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonUtils {
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
    
    public static HttpSession getSession() {
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
        
        return httpSession;
    }
    
    public static String sha512(String str, String salt) {
        String SHA = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(salt.getBytes("UTF-8"));
    
            byte byteData[] = md.digest(str.getBytes("UTF-8"));
            SHA = new String(Base64.encodeBase64(byteData), "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            //log.error("NoSuchAlgorithmException : {}", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            //log.error("UnsupportedEncodingException : {}", e.getMessage());
        } finally {
            if(StringUtils.isEmpty(SHA)){
                SHA = null;
            }
        }
        
        return SHA;
    }
}
