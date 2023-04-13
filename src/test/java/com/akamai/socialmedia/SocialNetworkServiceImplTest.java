package com.akamai.socialmedia;

import com.akamai.socialmedia.entity.SocialNetWorkPost;
import com.akamai.socialmedia.models.PostRequest;
import com.akamai.socialmedia.models.PostResponse;
import com.akamai.socialmedia.repository.SocialNetworkRepository;
import com.akamai.socialmedia.service.SocialNetworkServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc

public class SocialNetworkServiceImplTest {

    @Mock
    SocialNetworkRepository repository;
    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    SocialNetworkServiceImpl socialNetworkServiceImpl;
    @BeforeEach
    public void setUp() {
        when(repository.save(any())).thenReturn(getSocialNetworkService());
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(getSocialNetworkService()));
    }

    @Test
    public void createPostTest() {

          PostResponse response = socialNetworkServiceImpl.createPost(getPostRequestStub());
          assertThat(response.getAuthor()).isEqualTo("Stubbed Author");
          assertThat(response.getViewCount()).isEqualTo(0l);
          assertThat(response.getContent()).isEqualTo("Stubbed Content");
    }

    @Test
    public void viewPostByIdTest() {
        PostResponse postResponse = socialNetworkServiceImpl.viewPostById(anyLong());
        assertThat(postResponse.getAuthor()).isEqualTo("Stubbed Author");
        assertThat(postResponse.getViewCount()).isEqualTo(0l);
        assertThat(postResponse.getContent()).isEqualTo("Stubbed Content");
    }

    @Test
    public void findAllPostsTest() {
        when(repository.findAll()).thenReturn(Arrays.asList(getSocialNetworkService()));
        List<PostResponse> responseList = socialNetworkServiceImpl.findAllPosts();
        assertThat(responseList.size()).isEqualTo(1);
        assertThat(responseList.get(0).getAuthor()).isEqualTo("Stubbed Author");
        assertThat(responseList.get(0).getViewCount()).isEqualTo(0l);
        assertThat(responseList.get(0).getContent()).isEqualTo("Stubbed Content");
    }
    @Test
    public void deletePost(){
        doNothing().when(repository).delete(any());
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(getSocialNetworkService()));
        socialNetworkServiceImpl.deletePost(1l);
    }

    private SocialNetWorkPost getSocialNetworkService() {
        return SocialNetWorkPost.builder()
                .author("Stubbed Author")
                .content("Stubbed Content")
                .viewCount(0l)
                .postDate(LocalDateTime.now())
                .build();
    }

    private PostRequest getPostRequestStub() {
        return PostRequest.builder()
                .postDate(LocalDateTime.now())
                .content("My first Post")
                .viewCount(0l)
                .author("Dan Brown")
                .build();
    }
}
