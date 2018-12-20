package com.wsi.range;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.wsi.range.model.Range;
import com.wsi.range.service.RangeService;

@SpringBootApplication
public class WSIApplication
{
	private static final Logger log = LoggerFactory.getLogger(WSIApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(WSIApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(RangeService service)
	{
		return (args) ->
		{
			service.deleteAll();
			service.saveOrUpdate(new Range(UUID.fromString("841a2c10-02d8-11e9-a311-fd94feca6183"), 94200, 94299));
			service.saveOrUpdate(new Range(UUID.fromString("841f3520-02d8-11e9-a311-fd94feca6183"), 94226, 94399));
			service.saveOrUpdate(new Range(UUID.fromString("84191aa0-02d8-11e9-a311-fd94feca6183"), 94133, 94133));
			service.saveOrUpdate(new Range(UUID.fromString("841aef60-02d8-11e9-a311-fd94feca6183"), 94600, 94699));
			log.info("The sample data has been generated");
		};
	}
}
