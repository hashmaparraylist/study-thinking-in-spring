package io.github.hashmaparraylist.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 简单继承 {@link sun.net.www.protocol.x.Handler}
 *
 * @author
 * @date 2020/9/22
 */
public class Handler extends sun.net.www.protocol.x.Handler {

    // -Djava.protocol.handler.pkgs=io.github.hashmaparraylist.resource
    public static void main(String[] args) throws IOException {
        // springx  协议
        URL url = new URL("springx:///META-INF/production.properties");
        InputStream is = url.openStream();
        System.out.println(StreamUtils.copyToString(is, Charset.forName("UTF-8")));
    }
}
