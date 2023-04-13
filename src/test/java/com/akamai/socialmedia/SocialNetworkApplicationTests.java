package com.akamai.socialmedia;

import com.akamai.socialmedia.models.PostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class SocialNetworkApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @Test
    void integrationTest() throws Exception {

       String request = mapper.writeValueAsString(getPostRequestStub());
       MvcResult mvcResultCreate = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

       assertThat(mvcResultCreate.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
       assertThat(mvcResultCreate.getResponse().getContentAsString().contains("Dan Brown"));

        MvcResult mvcResultFindAllPost = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        assertThat(mvcResultFindAllPost.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(mvcResultFindAllPost.getResponse().getContentAsString().contains("Dan Brown"));

        MvcResult mvcResultFindPostById = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/view?postId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        assertThat(mvcResultFindPostById.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(mvcResultFindPostById.getResponse().getContentAsString().contains("Dan Brown"));

        MvcResult mvcResultFindGetPopularPosts = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/popular")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        assertThat(mvcResultFindGetPopularPosts.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(mvcResultFindGetPopularPosts.getResponse().getContentAsString().contains("Dan Brown"));

        MvcResult mvcResultFindModify = mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v1/modify?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        assertThat(mvcResultFindModify.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(mvcResultFindModify.getResponse().getContentAsString().contains("Dan Brown"));
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
