package io.github.hashmaparraylist.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link FileSystemResource} 示例
 *
 *
 * @author
 * @date 2020/9/21
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = System.getProperty("user.dir") + "/resource/src/main/java/io/github/hashmaparraylist/resource/EncodedFileSystemResourceDemo.java";
        File currentJavaFile = new File(currentJavaFilePath);
        // FileSystemResource => WritableResource => Resource
        FileSystemResource fsr = new FileSystemResource(currentJavaFile);
        EncodedResource er = new EncodedResource(fsr, "UTF-8");
        // 字符输入流
        Reader reader = er.getReader();
        System.out.println(IOUtils.toString(reader));
    }
}
