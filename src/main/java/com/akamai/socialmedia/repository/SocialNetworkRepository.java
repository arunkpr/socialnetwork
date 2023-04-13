package com.akamai.socialmedia.repository;

import com.akamai.socialmedia.entity.SocialNetWorkPost;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialNetworkRepository extends JpaRepository<SocialNetWorkPost, Long> {

    @Query("from SocialNetWorkPost order by viewCount DESC")
    List<SocialNetWorkPost> highestViewedPosts(PageRequest pageable);
}
