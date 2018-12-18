package com.williamssonoma.zipcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.wsi.range.model.Range;
import java.util.UUID;
import org.assertj.core.api.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WSIApplicationTests
{

	@Test
	public void testGetRangeById() throws Exception
	{
		Range range = new Range(UUID.fromString("841a2c10-02d8-11e9-a311-fd94feca6183"), 94200, 94299 );
	}

}
