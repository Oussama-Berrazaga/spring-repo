package com.oussama.eshop.product;

import com.oussama.eshop.domain.dto.requests.FindRequest;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @WithMockUser
    @Test
    public void givenAuthenticated_whenSortProducts_thenOk() throws Exception {
        mvc.perform(get("/api/v1/products").queryParam("field", "id")).andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void givenAuthenticated_whenFilterProducts_thenOk() throws Exception {
        FindRequest req = FindRequest.builder()
                .filters(List.of(FindRequest.Filter.builder()
                        .field("name")
                        .operator(
                                FindRequest.Filter.QueryOperator.EQUALS)
                        .value("Product 1")
                        .build()))
                .build();
        String payload = Utils.asJsonString(req);
        mvc.perform(post("/api/v1/products/findDynamic")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.jsonPath("$.count").value("1"))
                .andExpect(status().isOk());

    }
}
