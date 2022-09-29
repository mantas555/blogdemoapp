package org.codeacademy.mantas.blogapp.service;

import org.codeacademy.mantas.blogapp.model.Comment;
import org.codeacademy.mantas.blogapp.model.Post;

import java.nio.file.attribute.UserPrincipal;
import java.util.Collection;
import java.util.Optional;


public interface CommentService {

    Comment save(Comment comment);

    void delete(Comment comment);


    Optional<Comment> getById(Long id);

}
