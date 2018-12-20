package com.wsi.range.controller;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wsi.range.model.Range;
import com.wsi.range.service.RangeService;

@RestController
public class RangeController
{
	@Autowired
	private RangeService service;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	public String home() 
	{
		log.debug("Just testing...");
		return "Just testing!";
	}
 
	@GetMapping({ "/ranges" })
	public List<Range> listRanges()
	{
		log.debug("Listing ALL ranges...");
		List<Range> ranges = service.listAll();
		return ranges;
	}

	@RequestMapping({ "/ranges/{id}" })
	public Range getRange(@PathVariable String id) throws Exception
	{
		log.debug("Getting {} range...", id);
		return service.getOne(UUID.fromString(id)).orElseThrow(() -> new java.lang.Exception());
	}

	@RequestMapping({ "/ranges/delete/{id}" })
	public void delete(@PathVariable String id)
	{	
		log.debug("Deleting {} range...", id);
		service.delete(UUID.fromString(id));
	}
	
	@RequestMapping({ "/ranges/deleteall" })
	public void deleteAll()
	{	
		log.debug("Deleting all ranges...");
		service.deleteAll();
	}
	
	@RequestMapping({ "/ranges/add/{left}/{right}" })
	public Range add(@PathVariable Integer left, @PathVariable Integer right)
	{	
		Range range = new Range(UUID.randomUUID(), left, right);
		log.debug("Adding range: {} ...", range.toString());
		service.saveOrUpdate(range);
		return range;
	}
	
	@GetMapping({ "/ranges/reduced" })
	public List<Range> listReducedRanges()
	{
		log.debug("Listing REDUCED ranges..."); 
		return Range.processRanges(service.listAll());
	}
}
