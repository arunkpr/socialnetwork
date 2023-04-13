package com.akamai.socialmedia.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "social_network_post")
public class SocialNetWorkPost extends BaseEntity{
    String author;
    String content;
    @Column(name = "publish_date")
    LocalDateTime postDate;
    Long viewCount;
}
