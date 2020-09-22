package io.github.hashmaparraylist.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * {@link Resource} 工具类
 *
 * @author Sebastian Qu
 * @date 2020/9/22
 */
public interface ResourceUtils {

    static String getContent(Resource resource) {
        try {
            return getContent(resource, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getContent(Resource resource, String encoding) throws IOException {
        EncodedResource er = new EncodedResource(resource, encoding);
        // 字符输入流
        try (Reader reader = er.getReader()) {
            return IOUtils.toString(reader);
        }
    }
}
