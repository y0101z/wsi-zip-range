package com.wsi.range.test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class RangeControllerTest
{
	private MockMvc mock;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup()
	{
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void verifyAllRangesList() throws Exception
	{
		mock.perform(MockMvcRequestBuilders.get("/ranges")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(4)))
		.andDo(print());
	}
	
	@Test
	public void verifyReducedRangesList() throws Exception
	{
		mock.perform(MockMvcRequestBuilders.get("/ranges/reduced")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(3)))
		.andDo(print());
	}
	
	@Test
	public void verifyRangeById() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.get("/ranges/841aef60-02d8-11e9-a311-fd94feca6183")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.left").exists())
		.andExpect(jsonPath("$.right").exists())
		.andExpect(jsonPath("$.id").value("841aef60-02d8-11e9-a311-fd94feca6183"))
		.andExpect(jsonPath("$.left").value(94600))
		.andExpect(jsonPath("$.right").value(94699))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidRangeArgument() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.get("/blahblah")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.error").value(400))
		.andExpect(jsonPath("$.message").value("The request could not be understood. Wrong syntax."))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidRangeId() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.get("/ranges/111aef11-11d1-11e1-a111-fd11feca111")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.error").value(404))
		.andExpect(jsonPath("$.message").value("Range doesn't exist."))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteRange() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.delete("/ranges/delete/841f3520-02d8-11e9-a311-fd94feca6183")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("Range has been deleted."))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidRangeIdToDelete() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.delete("/ranges/222aef22-22d2-22e2-a222-fd22feca222")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.error").value(404))
		.andExpect(jsonPath("$.message").value("Range to delete doesn't exist."))
		.andDo(print());
	}
	
	@Test
	public void verifySaveRange() throws Exception 
	{
		mock.perform(MockMvcRequestBuilders.post("/ranges/add/94546/94600")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.left").exists())
		.andExpect(jsonPath("$.right").exists())
		.andExpect(jsonPath("$.left").value(94546))
		.andExpect(jsonPath("$.right").value(94600))
		.andDo(print());
	}
}
