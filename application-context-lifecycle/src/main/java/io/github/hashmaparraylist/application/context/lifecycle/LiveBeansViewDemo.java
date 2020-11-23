package io.github.hashmaparraylist.application.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.io.IOException;

import static org.springframework.context.support.LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME;

/**
 * {@link LiveBeansView} 示例
 *
 * @author
 * @date 2020/11/23
 * @see LiveBeansView
 */
public class LiveBeansViewDemo {

    public static void main(String[] args) throws IOException {

        // 添加 LiveBeansView 的 ObjectName
        System.setProperty(MBEAN_DOMAIN_PROPERTY_NAME, "io.github.hashmaparraylist");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(LiveBeansViewDemo.class);

        context.refresh();

        System.out.println("按任意键继续:");
        System.in.read();

        context.close();
    }

    /*
        [
          {
            "context": "org.springframework.context.annotation.AnnotationConfigApplicationContext@6267c3bb",
            "parent": null,
            "beans": [
              {
                "bean": "liveBeansViewDemo",
                "aliases": [],
                "scope": "singleton",
                "type": "io.github.hashmaparraylist.application.context.lifecycle.LiveBeansViewDemo",
                "resource": "null",
                "dependencies": []
              }
            ]
          }
        ]
     */
}
