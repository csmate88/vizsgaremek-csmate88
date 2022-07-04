package com.codecool.vizsgaremek.integrationtests;


import com.codecool.vizsgaremek.entity.Customer;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clean(){
        Flyway flyway=Flyway.configure().envVars().load();
        flyway.clean();
        flyway.migrate();
    }


    @Test
    void getRequest_ReturnsJSON() throws Exception{
        mockMvc.perform(get("/customer")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/order")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/product")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getRequest_ReturnsAListOfAllCustomers() throws Exception{
        mockMvc.perform(get("/customer").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(5)));
    }
    @Test
    void getRequest_ReturnsAListOfAllOrders() throws Exception{
        mockMvc.perform(get("/order").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(6)));
    }
    @Test
    void getRequest_ReturnsAListOfAllProducts() throws Exception{
        mockMvc.perform(get("/product").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(11)));
    }

    @Test
    void findOrder_ReturnsSpecificOrder_GivenId() throws Exception{
        mockMvc.perform(get("/order/6")).andExpect(status().isOk()).andExpect(content().json("{\"id\":6,\"products\":[{\"id\":6,\"name\":\"Paper sheet\",\"description\":\"A plain sheet to write your thoughts\"}],\"orderTime\":\"2022-05-10T10:40:00\"}"));
    }

    @Test
    void findCustomer_ReturnsSpecificCustomer_GivenId() throws Exception{
        mockMvc.perform(get("/customer/5")).andExpect(status().isOk()).andExpect(content().json("{\"id\":5,\"name\":\"Tim Doe\",\"email\":\"tim.doe@email.fr\",\"telephoneNumber\":\"+36337689130\",\"address\":\"3000 Hatvan Puszta tér 5.\",\"orders\":[]}"));
    }

    @Test
    void findProduct_ReturnsSpecificProduct_GivenId() throws Exception{
        mockMvc.perform(get("/product/4")).andExpect(status().isOk()).andExpect(content().json("{\"id\":4,\"name\":\"Bacon\",\"description\":\"Meat\"}"));
    }

    @Test
    void getRequest_ReturnsNotFoundNot_WhenIndexOutOfBounds() throws Exception{
        mockMvc.perform(get("/customer/100").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
        mockMvc.perform(get("/product/100").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
        mockMvc.perform(get("/order/100").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    void findOrders_ReturnsListOfOrder_BeforeDate() throws Exception{
        ResultActions resultActions=mockMvc.perform(get("/order"));
        mockMvc.perform(get("/order/date?date=2021-05-10"))
                .andExpect(status().isOk()).andExpect(content().json("[{\"id\":4,\"products\":[{\"id\":4,\"name\":\"Bacon\",\"description\":\"Meat\"},{\"id\":10,\"name\":\"Energy Drink\",\"description\":\"Sugar and caffeine\"}],\"orderTime\":\"2020-05-30T18:45:00\"},{\"id\":5,\"products\":[{\"id\":5,\"name\":\"Mouse\",\"description\":\"Not the animal\"},{\"id\":11,\"name\":\"Paper sheet\",\"description\":\"A plain sheet to write your thoughts\"}],\"orderTime\":\"2021-04-09T12:20:00\"}]"));
    }





}
