package sun.net.www.protocol.x;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * X 协议 {@link URLConnection} 实现
 *
 * @author
 * @date 2020/9/22
 */
public class XURLConnection extends URLConnection {

    private final ClassPathResource resource;

    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    protected XURLConnection(URL url) {
        super(url);
        this.resource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }

    public InputStream getInputStream() throws IOException {
        return this.resource.getInputStream();
    }


}
