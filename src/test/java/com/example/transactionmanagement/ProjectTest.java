package com.example.transactionmanagement;

import com.example.transactionmanagement.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest {

    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void addCategory_ok() throws Exception {
        Category c = new Category();
        c.setCategoryName("Food");
        c.setDescription("expenses related to Food");
        byte[] iJson = toJson(c);
        mockMvc.perform(post("/category/create")
                        .content(iJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addCategory1_ok() throws Exception {
        Category c = new Category();
        c.setCategoryName("Travel");
        c.setDescription("expenses related to Travel");
        byte[] iJson = toJson(c);
        mockMvc.perform(post("/category/create")
                        .content(iJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addCategory2_ok() throws Exception {
        Category c = new Category();
        c.setCategoryName("Sports");
        c.setDescription("expenses related to Sports");
        byte[] iJson = toJson(c);
        mockMvc.perform(post("/category/create")
                        .content(iJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCategory_ok() throws Exception {
        addCategory_ok();
        mockMvc.perform(get("/category/Food")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value("Food"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("expenses related to Food"));
    }

    //	    @Test
//		public void deleteProduct_Ok() throws Exception{
//	    	addCategory2_ok();
//			mockMvc.perform(delete("/category/delete/Food" )).andDo(print())
//	        .andExpect(status().isOk());
//		}
//	    @Test
//	    public void getTransaction_ok() throws Exception{
//	    	 mockMvc.perform(get("/transaction/1")).andDo(print())
//	    	 .andExpect(status().isOk())
//	    	 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//	    	 .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(50))
//	    	 .andExpect(MockMvcResultMatchers.jsonPath("$.category.categoryName").value("Food"))
//	    	 .andExpect(MockMvcResultMatchers.jsonPath("$.category.description").value("expenses related to Food"))
//	    	 .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("beverage"));
//
//	    }
    @Test
    public void addTransaction1_ok() throws Exception {
        addCategory_ok();
        mockMvc.perform(post("/transaction/50/Food/beverage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addTransaction_ok() throws Exception {
        addCategory1_ok();
        mockMvc.perform(post("/transaction/100/Travel/Himalayas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addTransaction2_ok() throws Exception {
        addCategory1_ok();
        mockMvc.perform(post("/transaction/200/Travel/coorg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getTransaction1_ok() throws Exception {
        mockMvc.perform(get("/transaction/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.categoryName").value("Food"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.description").value("expenses related to Food"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("beverage"));

    }

    @Test
    public void getTransaction_ok1() throws Exception {

        mockMvc.perform(get("/transaction/2")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.categoryName").value("Travel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.description").value("expenses related to Travel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("coorg"));

    }


    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        map.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return map.writeValueAsString(r).getBytes();
    }
}
