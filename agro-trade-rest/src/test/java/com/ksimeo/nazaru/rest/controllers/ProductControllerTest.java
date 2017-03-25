package com.ksimeo.nazaru.rest.controllers;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.rest.services.IProductService;
import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Ksimeo on 27.01.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:agro-trade-rest/src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class ProductControllerTest {

    @InjectMocks
    private ProductController pc;

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private IProductService productService;

    private Product testProduct1;
    private Product testProduct2;

    @Before
    public void prepare() {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        testProduct1 = productService.addNewProduct(new Product("Биогумат марки 1", 3.99F));
        testProduct2 = productService.addNewProduct(new Product("Биогумат марки 2", 2.54F));
    }

    @Test
    public void testAddNewProduct() throws Exception {
        Product product = new Product("Биогумат марки 3", 5.21F);
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(product);
        this.mockMvc.perform(post("/addnewproduct").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProductById() throws Exception {
        MvcResult res = mockMvc.perform(get("/getproductbyid/" + testProduct2.getId() + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String data = res.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
                Product dataProduct = mapper.readValue(data, Product.class);
        assertNotNull(dataProduct);
        assertEquals(dataProduct.getId(), testProduct2.getId());
    }

//    @Test
//    public void testDelProduct() throws Exception {
//        this.mockMvc.perform(get("/delproduct/" + testProduct1.getId() + "/"))
//                .andExpect(status().isOk());
//    }

    @Test
    public void testGetAllProducts() throws Exception {
        MvcResult res = this.mockMvc.perform(get("/getallproducts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        String data = res.getResponse().getContentAsString();
        ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().constructCollectionType(List.class, Product.class);
        List<Order> orderList = om.readValue(data, type);
        assertEquals(false, orderList.isEmpty());
    }

//    @After
//    public void completion() {
//
//        productService.deleteAllProducts();
//    }
}
