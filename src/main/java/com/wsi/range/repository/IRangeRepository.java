package com.wsi.range.repository;

import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import com.wsi.range.model.Range;

@Repository
public interface IRangeRepository extends CassandraRepository<Range, UUID>
{
}
