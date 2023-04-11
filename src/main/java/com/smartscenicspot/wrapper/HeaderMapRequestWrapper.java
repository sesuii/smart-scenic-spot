package com.smartscenicspot.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/11 14:35
 **/
public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {

    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    private Map<String, String> headerMap = new HashMap<>(16);

    public void addHeader(String key, String value) {
        headerMap.put(key, value);
    }

    public void removeHeader(String key) {
        super.removeAttribute(key);
        headerMap.remove(key);
    }

    @Override
    public String getHeader(String key) {
        String headerValue = super.getHeader(key);
        if (headerMap.containsKey(key)) {
            headerValue = headerMap.get(key);
        }
        return headerValue;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headerMap.keySet());
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if(headerMap.containsKey(name)) {
            values.add(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }

}
