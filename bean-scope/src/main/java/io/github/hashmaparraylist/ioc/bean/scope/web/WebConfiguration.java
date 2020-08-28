package io.github.hashmaparraylist.ioc.bean.scope.web;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * TODO
 *
 * @author
 * @date 2020/8/25
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {
    
    @Bean
//    @RequestScope
//    @SessionScope
    @ApplicationScope
    public User user() {
        return User.createUser();
    }

}
