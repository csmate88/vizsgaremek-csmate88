package com.codecool.vizsgaremek.integrationtests;

import com.codecool.vizsgaremek.TestUtil;
import org.flywaydb.core.Flyway;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WebLayerOrderTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clean() {
        TestUtil.resetDatabese();
    }

    @Test
    void getRequestOrder_ReturnsJSON() throws Exception{
        mockMvc.perform(get("/order")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void getRequest_ReturnsAListOfAllOrders() throws Exception{
        mockMvc.perform(get("/order").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(6)));
    }

    @Test
    void findOrder_ReturnsSpecificOrder_GivenId() throws Exception{
        mockMvc.perform(get("/order/6")).andExpect(status().isOk()).andExpect(content().json("{\"id\":6,\"products\":[{\"id\":6,\"name\":\"Paper sheet\",\"description\":\"A plain sheet to write your thoughts\"}],\"orderTime\":\"2022-05-10T10:40:00\"}"));
    }

    @Test
    void getRequestOrder_ReturnsNotFoundNot_WhenIndexOutOfBounds() throws Exception{
        mockMvc.perform(get("/order/100").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
    @Test
    void findOrders_ReturnsListOfOrder_BeforeDate() throws Exception{
        mockMvc.perform(get("/order/date?date=2021-05-10"))
                .andExpect(status().isOk()).andExpect(content().json("[{\"id\":4,\"products\":[{\"id\":4,\"name\":\"Bacon\",\"description\":\"Meat\"},{\"id\":10,\"name\":\"Energy Drink\",\"description\":\"Sugar and caffeine\"}],\"orderTime\":\"2020-05-30T18:45:00\"},{\"id\":5,\"products\":[{\"id\":5,\"name\":\"Mouse\",\"description\":\"Not the animal\"},{\"id\":11,\"name\":\"Paper sheet\",\"description\":\"A plain sheet to write your thoughts\"}],\"orderTime\":\"2021-04-09T12:20:00\"}]"));
    }

    @Test
    void findOrderByIdAndDelete_OrderNotFoundAfterDelete() throws Exception{
        mockMvc.perform(get("/order/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":3,\"products\":[{\"id\":3,\"name\":\"Energy Drink\",\"description\":\"Sugar and caffeine\"},{\"id\":9,\"name\":\"USB cable 2m\",\"description\":\"Cable with 2m length\"}],\"orderTime\":\"2022-07-01T03:45:00\"}"));
        mockMvc.perform(delete("/order/3"));
        mockMvc.perform(get("/order/3")).andExpect(status().isNotFound());
    }

    @Test
    void saveOrder_ReturnSavedOrder() throws Exception {
        mockMvc.perform(post("/order").content("{\"customerId\":5,\"products\":[{\"name\":\"Paper sheet\",\"description\": \"A plain sheet to write your thoughts\"}]}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(7)))
                .andExpect(jsonPath("$.products[0].id", equalTo(12)))
                .andExpect(jsonPath("$.products[0].name", equalTo("Paper sheet")))
                .andExpect(jsonPath("$.products[0].description", equalTo("A plain sheet to write your thoughts")));
    }

    @Test
    void UpdateOrderChanges() throws Exception{
        mockMvc.perform(get("/order/6").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":6,\"products\":[{\"id\":6,\"name\":\"Paper sheet\",\"description\":\"A plain sheet to write your thoughts\"}],\"orderTime\":\"2022-05-10T10:40:00\"}"));
        mockMvc.perform(put("/order").content("{\"id\": 6,\"customerId\":2,\"products\": [{\"name\": \"Bacon\",\"description\": \"Meat\"}]}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.id",equalTo(6)))
                .andExpect(jsonPath("$.products[0].id", equalTo(12)))
                .andExpect(jsonPath("$.products[0].name", equalTo("Bacon")))
                .andExpect(jsonPath("$.products[0].description", equalTo("Meat")));
    }
}
