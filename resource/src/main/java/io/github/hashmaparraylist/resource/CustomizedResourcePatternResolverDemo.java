package io.github.hashmaparraylist.resource;

import io.github.hashmaparraylist.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自定义 {@link ResourcePatternResolver} 示例
 *
 * @author
 * @date 2020/9/22
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see PathMatcher
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        // 读取当前 package 下的所有 .java 问价
        // *.java
        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/resource/src/main/java/io/github/hashmaparraylist/resource/";
        String locationPattern = currentJavaFilePath + "*.java";
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

        patternResolver.setPathMatcher(new JavaFilePathMatcher());

        Resource[] resources = patternResolver.getResources(locationPattern);

        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    static class JavaFilePathMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
