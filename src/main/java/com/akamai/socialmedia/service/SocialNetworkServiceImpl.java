package com.akamai.socialmedia.service;

import com.akamai.socialmedia.entity.SocialNetWorkPost;
import com.akamai.socialmedia.exception.RecordNotFoundException;
import com.akamai.socialmedia.mapper.PostDtoMapper;
import com.akamai.socialmedia.models.PostRequest;
import com.akamai.socialmedia.models.PostResponse;
import com.akamai.socialmedia.repository.SocialNetworkRepository;
import com.akamai.socialmedia.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialNetworkServiceImpl implements SocialNetworkService {

    private final SocialNetworkRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostResponse createPost(PostRequest postRequest) {
        SocialNetWorkPost entity = repository.save(PostDtoMapper.of(postRequest));
        PostResponse postResponse = PostDtoMapper.of(entity);
        return postResponse;
    }

    @Override
    public PostResponse viewPostById(Long postId) {
        SocialNetWorkPost requestedPost = getPostById(postId);
        requestedPost.setViewCount(requestedPost.getViewCount()+1);
        return PostDtoMapper.of(repository.save(requestedPost));
    }

    private SocialNetWorkPost getPostById(Long postId) {
        SocialNetWorkPost post = repository.findById(postId)
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));
        return post;
    }

    @Override
    public List<PostResponse> findAllPosts() {
        return repository.findAll().stream()
                .map(PostDtoMapper::of).collect(Collectors.toList());
    }

    @Override
    public PostResponse modifyPost(Long id, PostRequest request) {
        SocialNetWorkPost entity = getPostById(id);
        modelMapper.map(request, entity);
        return PostDtoMapper.of(repository.save(entity));
    }

    @Override
    public void deletePost(Long id) {
        SocialNetWorkPost entity = getPostById(id);
        repository.delete(entity);
    }

    @Override
    public List<PostResponse> getPopularPosts() {
        return repository.highestViewedPosts(PageRequest.of(0,10))
                .stream().map(PostDtoMapper::of).collect(Collectors.toList());
    }
}
