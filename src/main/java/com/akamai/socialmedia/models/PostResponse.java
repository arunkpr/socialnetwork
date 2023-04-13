package com.akamai.socialmedia.models;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse{
    String author;
    String content;
    LocalDateTime postDate;
    Long viewCount;
}
