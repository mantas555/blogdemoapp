package org.codeacademy.mantas.blogapp.controller;


import org.codeacademy.mantas.blogapp.model.BlogUser;
import org.codeacademy.mantas.blogapp.model.Post;
import org.codeacademy.mantas.blogapp.service.BlogUserService;
import org.codeacademy.mantas.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@SessionAttributes("post")
public class PostController {

    private final PostService postService;
    private final BlogUserService blogUserService;

    @Autowired
    public PostController(PostService postService, BlogUserService blogUserService) {
        this.postService = postService;
        this.blogUserService = blogUserService;
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal) {


//        // gauname prisijungusio vartotojo varda
        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);
        // jei postas egzistuoja ikeliama i modeli
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            // tikrina ar prisijunges vartotojas, posto autorius
            if (authUsername.equals(post.getUser().getUsername())) {
                model.addAttribute("isOwner", true);
            }
            return "post";
        } else {
            return "error";
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/createNewPost")
    public String createNewPost(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }


        Optional<BlogUser> optionalBlogUser = this.blogUserService.findByUsername(authUsername);

        if (optionalBlogUser.isPresent()) {
            Post post = new Post();
            post.setUser(optionalBlogUser.get());
            model.addAttribute("post", post);
            return "postForm";
        } else {
            return "error";
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/createNewPost")
    public String createNewPost(@Valid @ModelAttribute Post post, BindingResult bindingResult, SessionStatus sessionStatus) {
        System.err.println("POST post: " + post); // testavimui
        if (bindingResult.hasErrors()) {
            System.err.println("Post did not validate");
            return "postForm";
        }

        this.postService.save(post);
        System.err.println("SAVE post: " + post);
        sessionStatus.setComplete();
        return "redirect:/post/" + post.getId();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("editPost/{id}")
    public String editPost(@PathVariable Long id, Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }





        Optional<Post> optionalPost = this.postService.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // tikrina ar prisijunges vartotojas, posto autorius
            if (authUsername.equals(post.getUser().getUsername())) {
                model.addAttribute("post", post);
                System.err.println("EDIT post: " + post);
                return "postForm";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id); // for testing debugging purposes
                return "error";
            }
        } else {
            System.err.println("Could not find a post by id: " + id);
            return "error";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id, Principal principal) {


        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        // ieskoti posta pagal id
        Optional<Post> optionalPost = this.postService.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // tikrina ar prisijunges vartotojas, posto autorius
            if (authUsername.equals(post.getUser().getUsername())) {
                // jei taip, tai istrina is duomenu bazes
                this.postService.delete(post);
                System.err.println("DELETED post: " + post);
                return "redirect:/";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id);
                return "error";
            }
        } else {
            System.err.println("Could not find a post by id: " + id);
            return "error";
        }
    }

}
