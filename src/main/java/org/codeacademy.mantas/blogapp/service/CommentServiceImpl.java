package org.codeacademy.mantas.blogapp.service;


import org.codeacademy.mantas.blogapp.model.Comment;
import org.codeacademy.mantas.blogapp.model.Post;
import org.codeacademy.mantas.blogapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }
}
