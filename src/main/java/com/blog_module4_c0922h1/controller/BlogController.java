package com.blog_module4_c0922h1.controller;import com.blog_module4_c0922h1.model.Blog;import com.blog_module4_c0922h1.model.Category;import com.blog_module4_c0922h1.service.BlogService;import com.blog_module4_c0922h1.service.CategoryService;import org.hibernate.Session;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.stereotype.Controller;import org.springframework.util.FileCopyUtils;import org.springframework.web.bind.annotation.*;import org.springframework.web.multipart.MultipartFile;import org.springframework.web.servlet.ModelAndView;import javax.servlet.http.Cookie;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import java.io.File;import java.io.IOException;import java.util.List;@Controllerpublic class BlogController {    @Autowired    BlogService blogService;    @ModelAttribute("categories")    public List<Category> categories(){        return categoryService.getAll();    }    @Autowired    CategoryService categoryService;//    @GetMapping("/blogs")//    public ModelAndView show(@RequestParam(defaultValue = "0") int page,//                             HttpServletResponse response, @CookieValue(defaultValue = "0") int view){//        Cookie cookie = new Cookie("view", ""+ ++view);//        cookie.setMaxAge(60*60);//        response.addCookie(cookie);//        ModelAndView modelAndView = new ModelAndView("showBlog");//        modelAndView.addObject("blogs",blogService.getAll(PageRequest.of(page,1, Sort.by("id"))));//        modelAndView.addObject("view", cookie);//        return modelAndView;//    }    @Autowired    HttpSession httpSession;    @GetMapping("/blogs")    public ModelAndView show(@RequestParam(defaultValue = "0") int page){        if (httpSession.getAttribute("view") == null) {            httpSession.setAttribute("view", 0);        }else {            int view = (int) httpSession.getAttribute("view");            httpSession.setAttribute("view", ++view);        }        ModelAndView modelAndView = new ModelAndView("showBlog");        modelAndView.addObject("blogs",blogService.getAll(PageRequest.of(page,1, Sort.by("id"))));        modelAndView.addObject("view", httpSession.getAttribute("view"));        return modelAndView;    }    @GetMapping("/create")    public ModelAndView showCreate(){        ModelAndView modelAndView = new ModelAndView("create");        return modelAndView;    }    @PostMapping("/create")    public String create(@ModelAttribute Blog blog,@RequestParam MultipartFile fileImg){        String nameFile = fileImg.getOriginalFilename();        try {            FileCopyUtils.copy(fileImg.getBytes(),                    new File("/Users/johntoan98gmail.com/Desktop/Module 4/blog_module4_c0922h1/src/main/webapp/WEB-INF/img/" + nameFile));        } catch (Exception e) {            e.printStackTrace();        }        blog.setImg("/img/" + nameFile);        blogService.save(blog);        return "redirect:/blogs";    }    @GetMapping("/edit/{id}")    public ModelAndView showEdit(@PathVariable int id){        ModelAndView modelAndView = new ModelAndView("edit");        modelAndView.addObject("blog", blogService.findById(id));        return modelAndView;    }    @PostMapping("/edit")    public String edit(@ModelAttribute Blog blog,@RequestParam MultipartFile fileImg){        if (!fileImg.isEmpty()){            try {                FileCopyUtils.copy(fileImg.getBytes(),                        new File("/Users/johntoan98gmail.com/Desktop/Module 4/blog_module4_c0922h1/src/main/webapp/WEB-INF" + blog.getImg()));            } catch (Exception e) {                e.printStackTrace();            }        }        blogService.save(blog);        return "redirect:/blogs";    }    @GetMapping("/delete/{id}")    public String delete(@PathVariable int id){        blogService.delete(id);        return "redirect:/blogs";    }}