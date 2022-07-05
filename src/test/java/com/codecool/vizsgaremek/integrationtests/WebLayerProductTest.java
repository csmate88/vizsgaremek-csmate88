package com.codecool.vizsgaremek.integrationtests;

import com.codecool.vizsgaremek.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WebLayerProductTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clean() {
        TestUtil.resetDatabase();
    }

    @Test
    void getRequestProduct_ReturnsJSON() throws Exception{
        mockMvc.perform(get("/product")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getRequest_ReturnsAListOfAllProducts() throws Exception{
        mockMvc.perform(get("/product").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(11)));
    }

    @Test
    void findProduct_ReturnsSpecificProduct_GivenId() throws Exception{
        mockMvc.perform(get("/product/4")).andExpect(status().isOk()).andExpect(content().json("{\"id\":4,\"name\":\"Bacon\",\"description\":\"Meat\"}"));
    }

    @Test
    void getRequestProduct_ReturnsNotFoundNot_WhenIndexOutOfBounds() throws Exception{
        mockMvc.perform(get("/product/100").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    void findProductByIdAndDelete_ProductNotFoundAfterDelete() throws Exception{
        mockMvc.perform(get("/product/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":3,\"name\":\"Energy Drink\",\"description\":\"Sugar and caffeine\"}"));
        mockMvc.perform(delete("/product/3"));
        mockMvc.perform(get("/product/3")).andExpect(status().isNotFound());
    }

    @Test
    void saveProduct_ReturnSavedProduct() throws Exception {
    }

    @Test
    void UpdateProductChanges() throws Exception{

    }
}
