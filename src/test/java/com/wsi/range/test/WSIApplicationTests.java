package com.wsi.range.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wsi.range.model.Range;
import java.util.UUID;
import org.assertj.core.api.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WSIApplicationTests
{
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetRangeById() throws Exception
	{
//		Range range = new Range(UUID.fromString("841a2c10-02d8-11e9-a311-fd94feca6183"), 94200, 94299);
//		this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/1"))
//        .andExpect(status().isOk())
//        .andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}
