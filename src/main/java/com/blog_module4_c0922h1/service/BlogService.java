package com.blog_module4_c0922h1.service;

import com.blog_module4_c0922h1.model.Blog;
import com.blog_module4_c0922h1.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public class BlogService {
    @Autowired
    IBlogRepo iBlogRepo;

    public List<Blog> getAll(){
        return (List<Blog>) iBlogRepo.findAll();
    }

    public void save(Blog blog){
        iBlogRepo.save(blog);
    }

    public void delete(int id){
        iBlogRepo.deleteById(id);
    }

    public Blog findById(int id){
        return iBlogRepo.findById(id).get();
    }

}
