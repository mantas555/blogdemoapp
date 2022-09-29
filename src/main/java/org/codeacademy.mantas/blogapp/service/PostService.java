package org.codeacademy.mantas.blogapp.service;



import org.codeacademy.mantas.blogapp.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostService {

    Optional<Post> getById(Long id);

    Collection<Post> getAll();

    Post save(Post post);

    void delete(Post post);
}

