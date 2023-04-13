package com.akamai.socialmedia.controller;

import com.akamai.socialmedia.models.PostRequest;
import com.akamai.socialmedia.models.PostResponse;
import com.akamai.socialmedia.service.SocialNetworkService;
import com.akamai.socialmedia.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class SocialNetworkController {

    private final SocialNetworkService socialNetworkService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        return new ResponseEntity<>(socialNetworkService.createPost(postRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("view")
    public ResponseEntity<PostResponse> findPostById(@RequestParam Long postId) {
        return new ResponseEntity<>(socialNetworkService.viewPostById(postId),
                HttpStatus.CREATED);
    }

    @GetMapping("popular")
    public ResponseEntity<List<PostResponse>> getPopularPosts() {
        return new ResponseEntity<>(socialNetworkService.getPopularPosts(),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAllPosts() {
        return new ResponseEntity<>(socialNetworkService.findAllPosts(),
                HttpStatus.CREATED);
    }

    @PutMapping("modify")
    public ResponseEntity<PostResponse> modifyPost(@RequestParam Long id, @RequestBody PostRequest request) {
        return new ResponseEntity<>(socialNetworkService.modifyPost(id, request),
                HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deletePost(Long id) {
        socialNetworkService.deletePost(id);
        return new ResponseEntity<>(MessageUtil.RECORD_DELETED,
                HttpStatus.OK);
    }
}
