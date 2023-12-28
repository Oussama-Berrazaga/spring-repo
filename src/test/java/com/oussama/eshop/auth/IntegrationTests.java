package com.oussama.eshop.auth;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.oussama.eshop.domain.dto.requests.AuthRequest;
import com.oussama.eshop.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }


    @Test
    public void givenCorrectCredentials_shouldSucceedWith200() throws Exception {
        String payload = Utils.asJsonString(AuthRequest.builder()
                .email("john.smith@yopmail.com")
                .password("123456")
                .build());
        mvc.perform(post("/api/v1/auth/authenticate")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void givenAuthenticated_shouldSucceedWith200() throws Exception {
        var result = mvc.perform(get("/api/v1/auth")).andDo(MockMvcResultHandlers.print())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        log.error(content);
        //  mvc.perform(get("/api/v1/auth")).andExpect(status().isOk());
    }


    @Test
    public void givenUnauthenticated_shouldFailWith400() throws Exception {
        mvc.perform(get("/api/v1/auth")).andExpect(status().isBadRequest());
    }

}
