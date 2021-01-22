package io.github.hashmaparraylist.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * {@link TestPropertySource} 测试示例
 *
 * @author
 * @date 2020/11/19
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class)   // Spring 注解驱动测试注解
@TestPropertySource(
        properties = "user.name = Junit测试", // PropertySource(name=Inlined Test Properties)
        locations = "classpath:/META-INF/test.properties"
)
public class TestPropertySourceTest {
    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testUserName() {
        assertEquals("Junit测试", userName);

        MutablePropertySources propertySources = environment.getPropertySources();

        for (PropertySource ps : propertySources) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性 %s\n", ps.getName(), ps.getProperty("user.name"));
        }
    }
}
