package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * X Handler 测试示例
 *
 * @author
 * @date 2020/9/22
 */
public class HandlerTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("x:///META-INF/default.properties");
        InputStream  is = url.openStream();
        System.out.println(StreamUtils.copyToString(is, Charset.forName("UTF-8")));
    }
}
