package com.blog_module4_c0922h1.controller;

import com.blog_module4_c0922h1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("/blog")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("showBlog");
        modelAndView.addObject("blogs",blogService.getAll());
        return modelAndView;
    }
}
