package com.ksimeo.nazaru.rest.controllers;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.rest.services.IUserService;
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

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Ksimeo on 26.01.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:agro-trade-rest/src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class UserControllerTest {

    @InjectMocks
    private UserController uc;

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private IUserService userService;

    private User testUser1;
    private User testUser2;

    @Before
    public void prepare() {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        testUser1 = userService.addUser(new User("Nazar", "Samarchuk", true));
        testUser2 = userService.addUser(new User("Maksim", "MDC-Design", true));
    }

    @Test
    public void testAddNewUser() throws Exception {

        User testUser = new User("Vasya", "#12345", false);
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(testUser);
        this.mockMvc.perform(post("/addnewuser").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testDelUserById() throws Exception {

        this.mockMvc.perform(get("/deluserbyid/" + testUser2.getId() + "/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserById() throws Exception {

        this.mockMvc.perform(get("/getuserbyid/" + testUser1.getId() + "/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserByName() throws Exception {

        MvcResult res = this.mockMvc.perform(get("/getuserbyname/" + testUser1.getLogin() + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String data = res.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        User dataUser = mapper.readValue(data, User.class);
        assertNotNull(dataUser);
        assertEquals(dataUser.getLogin(), testUser1.getLogin());
    }

    @Test
    public void testIsExistLogin() throws Exception {

        MvcResult res = this.mockMvc.perform(get("/getuserbyname/" + "Genya" + "/"))
                .andExpect(status().isOk())
                .andReturn();
        String data = res.getResponse().getContentAsString();
        assertEquals("", data);
    }

    @Test
    public void testGetAllUsers() throws Exception {

        MvcResult res = this.mockMvc.perform(get("/getallusers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        String data = res.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
        List<Order> orderList = mapper.readValue(data, type);
        assertEquals(false, orderList.isEmpty());
    }

//    @After
//    public void completion() {
//
//        userService.deleteAllUsers();
//    }

}
