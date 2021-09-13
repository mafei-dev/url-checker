package com.mafei.urlchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class TestController implements ApplicationListener<WebServerInitializedEvent> {
    private static final double val = new Random().nextDouble();
    int aPort;
    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public Object index() throws UnknownHostException {
        Map<String, Object> data = new HashMap<>();
        data.put("aPort", aPort);
        data.put("val", val);
        data.put("getRequestURI", request.getRequestURI());
        data.put("getRequestURL", request.getRequestURL());
        data.put("getPathInfo", request.getPathInfo());
        data.put("getHostAddress-remote", InetAddress.getLoopbackAddress().getHostAddress());
        data.put("getHostName-remote", InetAddress.getLoopbackAddress().getHostName());


        data.put("getHostAddress-local", InetAddress.getLocalHost().getHostAddress());
        data.put("getHostName-local", InetAddress.getLocalHost().getHostName());

        return data;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        aPort = event.getWebServer().getPort();
    }
}
