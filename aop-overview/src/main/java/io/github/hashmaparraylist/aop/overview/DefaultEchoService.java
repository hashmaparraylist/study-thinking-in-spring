package io.github.hashmaparraylist.aop.overview;

/**
 * 默认 {@link EchoService} 实现
 *
 * @author
 * @date 2021/1/7
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return "[ECHO] " + message;
    }
}
