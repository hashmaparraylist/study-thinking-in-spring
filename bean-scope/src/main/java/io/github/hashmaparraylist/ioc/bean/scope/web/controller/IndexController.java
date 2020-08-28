package io.github.hashmaparraylist.ioc.bean.scope.web.controller;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 *
 * @author
 * @date 2020/8/25
 */
@Controller
public class IndexController {

    @Autowired private User user;

    @GetMapping("/index.html")
    public  String index(Model model) {
        model.addAttribute("userObject", this.user);
        return "index";
    }
}
