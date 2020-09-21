package io.github.hashmaparraylist.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link FileSystemResourceLoader} 示例
 *
 *
 * @author
 * @date 2020/9/21
 * @see FileSystemResourceLoader
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/resource/src/main/java/io/github/hashmaparraylist/resource/EncodedFileSystemResourceLoaderDemo.java";
        // 新建一个 FileSystemResourceLoader 的对象
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        // FileSystemResource => WritableResource => Resource
        FileSystemResource fsr = (FileSystemResource) resourceLoader.getResource(currentJavaFilePath);
        EncodedResource er = new EncodedResource(fsr, "UTF-8");
        // 字符输入流
        try (Reader reader = er.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
