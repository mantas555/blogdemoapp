package org.codeacademy.mantas.blogapp.controller;


import org.codeacademy.mantas.blogapp.model.Authority;
import org.codeacademy.mantas.blogapp.model.BlogUser;
import org.codeacademy.mantas.blogapp.model.Comment;
import org.codeacademy.mantas.blogapp.model.Post;
import org.codeacademy.mantas.blogapp.service.BlogUserService;
import org.codeacademy.mantas.blogapp.service.CommentService;
import org.codeacademy.mantas.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;



@Controller
@SessionAttributes("comment")
public class CommentController {

    private final PostService postService;
    private final BlogUserService blogUserService;
    private final CommentService commentService;

    @Autowired
    public CommentController(PostService postService, BlogUserService blogUserService, CommentService commentService) {
        this.postService = postService;
        this.blogUserService = blogUserService;
        this.commentService = commentService;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<BlogUser> optionalBlogUser = this.blogUserService.findByUsername(authUsername);
        // iesko komentaro pagal id
        Optional<Post> postOptional = this.postService.getById(id);

        if (postOptional.isPresent() && optionalBlogUser.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(postOptional.get());
            comment.setUser(optionalBlogUser.get());
            model.addAttribute("comment", comment);
            System.err.println("GET comment/{id}: " + comment + "/" + id);
            return "commentForm";
        } else {
            System.err.println("Could not find a post by id: " + id + " or user by logged in username: " + authUsername); // for testing debugging purposes
            return "error";
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/comment")
    public String validateComment(@Valid @ModelAttribute Comment comment, BindingResult bindingResult, SessionStatus sessionStatus) {
        System.err.println("POST comment: " + comment);
        if (bindingResult.hasErrors()) {
            System.err.println("Comment did not validate");
            return "commentForm";
        } else {
            this.commentService.save(comment);
            System.err.println("SAVE comment: " + comment);
            sessionStatus.setComplete();
            return "redirect:/post/" + comment.getPost().getId();
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("commentEdit/{id}")
    public String editComment(@PathVariable Long id, Model model, Principal principal, Authority authority) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }



        Optional<Comment> optionalComment = this.commentService.getById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            // tikrina ar prisijunges vartotojas, komentaro autorius
            if (authUsername.equals(comment.getUser().getUsername())) {
                model.addAttribute("comment", comment);
                model.addAttribute("isOwner", true);
                System.err.println("EDIT comment: " + comment);
                return "commentEdit";
            } else {
                System.err.println("Current User has no permissions to edit comment on post by id: " + id); // for testing debugging purposes
                return "error";
            }
        } else {
            System.err.println("Could not find a comment by id: " + id);
            return "error";
        }
    }

    @GetMapping("/deleteComment/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deleteComment(@PathVariable Long id, Principal principal) {


        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        // suranda komentara pagal id
        Optional<Comment> optionalComment = this.commentService.getById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            // tikrina ar prisijunges vartotojas yra komentaro autorius
            if (authUsername.equals(comment.getUser().getUsername())) {
                // jei taip, tai panaikinamas komentaras
                this.commentService.delete(comment);
                System.err.println("DELETED Comment: " + comment);
                return "redirect:/";
            } else {
                System.err.println("Current User has no permissions to delete comment on post by id: " + id); // test
                return "error";
            }
        } else {
            System.err.println("Could not find a comment by id: " + id);
            return "error";
        }
    }
}
