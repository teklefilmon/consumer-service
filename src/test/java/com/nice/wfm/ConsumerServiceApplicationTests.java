package com.nice.wfm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConsumerServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void applicationShouldBeGrantedWhenCreditScoreIsHigh() throws Exception {
        mockMvc.perform(
                    post("/credit-card-applications")
                        .contentType(APPLICATION_JSON)
                        .content("{" +
                                "\"ssn\":\"12-34-5678\"," +
                                "\"cardType\":\"GOLD\"" +
                                "}"
                        ))
                .andExpect(status().isOk())
                .andExpect(content()
                    .json("{" +
                        "\"status\":\"GRANTED\"" +
                    "}"))
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
    }

}
