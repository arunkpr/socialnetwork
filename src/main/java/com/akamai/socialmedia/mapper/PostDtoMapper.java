package com.akamai.socialmedia.mapper;

import com.akamai.socialmedia.entity.SocialNetWorkPost;
import com.akamai.socialmedia.models.PostRequest;
import com.akamai.socialmedia.models.PostResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PostDtoMapper {
    public static SocialNetWorkPost of (PostRequest postRequest) {
        SocialNetWorkPost entity = new SocialNetWorkPost();
        BeanUtils.copyProperties(postRequest, entity);
        return entity;
    }

    public static PostResponse of (SocialNetWorkPost entity) {
        PostResponse response = new PostResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
