package com.wsi.range.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.wsi.range.model.Range;

public interface IRangeService
{
	List<Range> listAll();
	Optional<Range> getOne(UUID id);
	Range saveOrUpdate(Range range);
	void delete(UUID id);
	void deleteAll();
}
