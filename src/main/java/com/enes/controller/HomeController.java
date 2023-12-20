package com.enes.controller;

import com.enes.dto.response.PostResponseDto;
import com.enes.repository.entity.Post;
import com.enes.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class HomeController {

    private final PostService postService;

    @GetMapping("/homepage/{userId}")
    public ModelAndView getHomePage(@PathVariable Long userId) {
        List<PostResponseDto> posts = postService.findAllByUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("homepage");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }
}
