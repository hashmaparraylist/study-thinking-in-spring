package io.github.hashmaparraylist.aop.overview;

/**
 * 静态代理示例
 *
 * @author
 * @date 2021/1/7
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello, world");
    }

}
