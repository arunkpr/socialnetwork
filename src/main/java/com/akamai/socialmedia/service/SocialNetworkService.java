package com.akamai.socialmedia.service;

import com.akamai.socialmedia.models.PostRequest;
import com.akamai.socialmedia.models.PostResponse;

import java.util.List;

public interface SocialNetworkService {
    PostResponse createPost(PostRequest postRequest);

    PostResponse viewPostById(Long id);

    List<PostResponse> findAllPosts();

    PostResponse modifyPost(Long id, PostRequest request);

    void deletePost(Long id);

    List<PostResponse> getPopularPosts();
}
