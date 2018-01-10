package com.libproject.elibrary.controller;

import com.libproject.elibrary.config.WebApplicationContextConfig;
import com.libproject.elibrary.security.SecurityConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebApplicationContextConfig.class, SecurityConfiguration.class})
@WebAppConfiguration
public class AbstractIntegrationTest {

    @Autowired
    WebApplicationContext applicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).apply(springSecurity()).build();
    }
}
