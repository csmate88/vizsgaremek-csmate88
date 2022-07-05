package com.codecool.vizsgaremek.integrationtests;


import com.codecool.vizsgaremek.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WebLayerCustomerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clean() {
        TestUtil.resetDatabase();
    }


    @Test
    void getRequestCustomer_ReturnsJSON() throws Exception{
        mockMvc.perform(get("/customer")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getRequest_ReturnsAListOfAllCustomers() throws Exception{
        mockMvc.perform(get("/customer").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(5)));
    }


    @Test
    void findCustomer_ReturnsSpecificCustomer_GivenId() throws Exception{
        mockMvc.perform(get("/customer/5")).andExpect(status().isOk()).andExpect(content().json("{\"id\":5,\"name\":\"Tim Doe\",\"email\":\"tim.doe@email.fr\",\"telephoneNumber\":\"+36-33-7689130\",\"address\":\"3000 Hatvan Puszta tér 5.\",\"orders\":[]}"));
    }



    @Test
    void getRequestCustomer_ReturnsNotFoundNot_WhenIndexOutOfBounds() throws Exception{
        mockMvc.perform(get("/customer/100").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    void findCustomerByIdAndDelete_CustomerNotFoundAfterDelete() throws Exception{
        mockMvc.perform(get("/customer/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":3,\"name\":\"Joe Doe\",\"email\":\"john.doe@email.it\",\"telephoneNumber\":\"+36229999999\",\"address\":\"5000 Szolnok Valami utca 12.\",\"orders\":[{\"id\":4,\"products\":[{\"id\":4,\"name\":\"Bacon\",\"description\":\"Meat\"},{\"id\":10,\"name\":\"Energy Drink\",\"description\":\"Sugar and caffeine\"}],\"orderTime\":\"2020-05-30T18:45:00\"}]}"));
        mockMvc.perform(delete("/customer/3"));
        mockMvc.perform(get("/customer/3")).andExpect(status().isNotFound());
    }

    @Test
    void saveCustomer_ReturnSavedCustomer() throws Exception {
        mockMvc.perform(post("/customer").content("{\"name\":\"Brazil Kakas\",\"email\":\"egyemil@comail.com\",\"telephoneNumber\":\"+36-30-1112223\",\"address\":\"1111 Budapest\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":6,\"name\":\"Brazil Kakas\",\"email\":\"egyemil@comail.com\",\"telephoneNumber\":\"+36-30-1112223\",\"address\":\"1111 Budapest\",\"orders\":[]}"));
    }

    @Test
    void UpdateCustomerChanges() throws Exception{
        mockMvc.perform(put("/customer").content("{\"id\": 5,\"name\": \"Tim Doe\",\"email\": \"tim.doe@new.mail\",\"telephoneNumber\": \"+36-33-7689130\",\"address\": \"3000 Hatvan Puszta tér 5.\",\"orders\": []}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email",equalTo("tim.doe@new.mail")));
    }



}
