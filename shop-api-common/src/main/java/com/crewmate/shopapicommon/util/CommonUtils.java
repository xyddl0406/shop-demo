package com.crewmate.shopapicommon.util;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {
    public static String getTest() {
        log.info("CommonUtils.getTest!!CommonUtils.getTest!!");
        return "CommonUtils.getTest!!";
    }
    
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
    
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }
    
    public static HttpSession getSession() {
        return getRequest().getSession(true);
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
