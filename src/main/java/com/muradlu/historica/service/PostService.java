package com.muradlu.historica.service;

import com.muradlu.historica.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);

    List<Post> getAllPosts();

    Post getPostById(long id);

    Post updatePost(Post post, long id);

    void deletePostById(long id);
}
