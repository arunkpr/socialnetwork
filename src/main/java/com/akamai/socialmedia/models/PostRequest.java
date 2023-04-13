package com.akamai.socialmedia.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PostRequest {

    String author;

    String content;

    LocalDateTime postDate;

    Long viewCount;
}
