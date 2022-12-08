package com.crewmate.shopapicommon.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 * HttpServletRequest를 재사용하기위한 Wrapper클래스.
 * 내쪽 LoggingAspect로 오기전에 스프링 내부적으로 어디선가 request.getInputStream을 하는것같다
 * 이 wrapper클래스가 없으면 내쪽에서 InputStream의 값이 없어서 읽을 수 없다.
 * @author CREWMATE
 *
 */
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
    
    private ByteArrayOutputStream cachedBytes;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (cachedBytes == null)
            cacheInputStream();

        return new CachedServletInputStream(cachedBytes.toByteArray());
    }

    @Override
    public BufferedReader getReader() throws IOException{
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    
    private void cacheInputStream() throws IOException {
        /* Cache the inputstream in order to read it multiple times. For
         * convenience, I use apache.commons IOUtils
         */
        cachedBytes = new ByteArrayOutputStream();
        IOUtils.copy(super.getInputStream(), cachedBytes);
    }

    
    /* An input stream which reads the cached request body */
    private static class CachedServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream buffer;

        public CachedServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        @Override
        public int read() {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new RuntimeException("Not implemented");
        }
    }
}
