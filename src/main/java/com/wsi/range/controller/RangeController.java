package com.wsi.range.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wsi.range.exeption.RangeException;
import com.wsi.range.model.Range;
import com.wsi.range.model.Response;
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
 
	@RequestMapping(value = "/ranges", method=RequestMethod.GET)
	public ResponseEntity<List<Range>> listRanges()
	{
		log.debug("Listing ALL ranges...");		
		return new ResponseEntity<List<Range>>(service.listAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/ranges/{id}", method=RequestMethod.GET)
	public ResponseEntity<Range> getRangeById(@PathVariable("id") String id) throws RangeException
	{
		log.debug("Getting {} range...", id);
		Optional<Range> range = service.getOne(UUID.fromString(id));
	
		if (range.isPresent())
			return new ResponseEntity<Range>(range.get(), HttpStatus.OK);
		else
			throw new RangeException("Range doesn't exist.");
	}

	@RequestMapping(value = "/ranges/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteRangeById(@PathVariable("id") String id) throws RangeException
	{	
		log.debug("Deleting {} range...", id);
		Optional<Range> range = service.getOne(UUID.fromString(id));
		
		if (range.isPresent())
		{
			service.delete(UUID.fromString(id));
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Range has been deleted."), HttpStatus.OK);
		}
		else
			throw new RangeException("Range to delete doesn't exist.");
	}
	
	@RequestMapping({ "/ranges/deleteall" })
	public void deleteAll()
	{	
		log.debug("Deleting all ranges...");
		service.deleteAll();
	}
	
	@RequestMapping(value = "/ranges/add/{left}/{right}", method = RequestMethod.POST)
	public ResponseEntity<Range> saveRange(@PathVariable("left") int left, @PathVariable("right") int right) throws RangeException
	{	
		if(left >= right)
			throw new RangeException("Invalid range.");
		
		return new ResponseEntity<Range>(service.saveOrUpdate(new Range(UUID.randomUUID(), left, right)), HttpStatus.OK);
	}
	
	@GetMapping({ "/ranges/reduced" })
	public ResponseEntity<List<Range>> listReducedRanges()
	{
		log.debug("Listing REDUCED ranges..."); 
		return new ResponseEntity<List<Range>>(Range.processRanges(service.listAll()), HttpStatus.OK);
	}
}
