package com.wsi.range.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wsi.range.model.Range;
import com.wsi.range.repository.IRangeRepository;

@Service
public class RangeService implements IRangeService
{
	@Autowired
	private IRangeRepository repository;

	@Autowired
	public RangeService(IRangeRepository repository)
	{		
		this.repository = repository;
	}
	
	@Override
	public List<Range> listAll()
	{
		List<Range> ranges = new ArrayList<Range>();
		repository.findAll().forEach(ranges::add); // cool Java8
		return ranges;
	}

	@Override
	public Optional<Range> getOne(UUID id)
	{
		return repository.findById(id);
	}

	@Override
	public Range saveOrUpdate(Range range)
	{
		repository.save(range);
		return range;
	}

	@Override
	public void delete(UUID id)
	{
		repository.deleteById(id);		
	}

	@Override
	public void deleteAll()
	{
		repository.deleteAll();
	}
}
