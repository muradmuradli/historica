package com.muradlu.historica.service;

import com.muradlu.historica.entity.Post;
import com.muradlu.historica.repository.PostRepository;
import com.muradlu.historica.service.PostService;
import com.muradlu.historica.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        // Get the currently authenticated user's details from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        // Set the user field of the post to the authenticated user
        post.setUser(currentUser);
        Post newPost = postRepository.save(post);
        return newPost;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Post getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return post;
    }

    @Override
    public Post updatePost(Post post, long id) {
        // Check if the currently authenticated user matches the user associated with the post
        if (isCurrentUserAuthorized(post.getUser().getEmail())) {
            Post oldPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException());

            oldPost.setTitle(post.getTitle());
            oldPost.setDescription(post.getDescription());
            oldPost.setContent(post.getContent());
            oldPost.setCategory(post.getCategory());
            Post updatedPost = postRepository.save(oldPost);
            return updatedPost;
        }
        throw new RuntimeException();
    }

    @Override
    public void deletePostById(long id) {
        // Check if the currently authenticated user matches the user associated with the post
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (isCurrentUserAuthorized(post.getUser().getEmail())) {
                postRepository.delete(post);
                return;
            }
        }
        throw new RuntimeException();
    }

    private boolean isCurrentUserAuthorized(String userEmail) {
        // Get the email of the currently authenticated user from the security context
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return currentEmail.equals(userEmail);
    }
}
