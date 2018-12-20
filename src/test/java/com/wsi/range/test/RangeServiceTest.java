package com.wsi.range.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.wsi.range.model.Range;
import com.wsi.range.repository.IRangeRepository;
import com.wsi.range.service.RangeService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RangeServiceTest
{
	@Mock
	private IRangeRepository repository;

	@InjectMocks
	private RangeService service;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllRanges()
	{
		List<Range> ranges = new ArrayList<Range>();
		ranges.add(new Range(UUID.fromString("841a2c10-02d8-11e9-a311-fd94feca6183"), 94200, 94299));
		ranges.add(new Range(UUID.fromString("841f3520-02d8-11e9-a311-fd94feca6183"), 94226, 94399));
		ranges.add(new Range(UUID.fromString("84191aa0-02d8-11e9-a311-fd94feca6183"), 94133, 94133));
		ranges.add(new Range(UUID.fromString("841aef60-02d8-11e9-a311-fd94feca6183"), 94600, 94699));
		when(repository.findAll()).thenReturn(ranges);
		List<Range> result = service.listAll();
		assertEquals(4, result.size());
	}

	@Test
	public void testGetRangeById()
	{
		UUID id = UUID.fromString("841aef60-02d8-11e9-a311-fd94feca6183");
		int left = 94600;
		int right = 94699;		
		Range range = new Range(id, left, right);
		when(repository.findById(id)).thenReturn(Optional.of(range));		
		Optional<Range> result = service.getOne(id);
		assertEquals(id, result.get().getId());
		assertEquals(left, result.get().getLeft().intValue()); // result.get() because of Optional
		assertEquals(right, result.get().getRight().intValue());
	}

	@Test
	public void testSaveRange()
	{
		UUID id = UUID.fromString("52b6b042-b615-4665-9390-3cd48e77de76");
		int left = 55555;
		int right = 88888;	
		Range range = new Range(id, left, right); 
		when(repository.save(range)).thenReturn(range);
		Range result = service.saveOrUpdate(range);
		assertEquals(id, result.getId());
		assertEquals(left, result.getLeft().intValue());
		assertEquals(right, result.getRight().intValue());
	}

	@Test
	public void testDeleteRange()
	{
		UUID id = UUID.fromString("52b6b042-b615-4665-9390-3cd48e77de76");		
		Range range = new Range(id, 55555, 88888); 
		service.delete(id);
		verify(repository, times(1)).delete(range);
	}
}
