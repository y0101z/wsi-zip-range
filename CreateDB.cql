CREATE KEYSPACE IF NOT EXISTS WSI WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

CREATE TABLE wsi.ranges
(id uuid,
"left" int,
"right" int,
PRIMARY KEY (id))

insert into wsi.ranges (id, left, right) values (now(),94133,94133);
insert into wsi.ranges (id, left, right) values (now(),94200,94299);
insert into wsi.ranges (id, left, right) values (now(),94600,94699);
insert into wsi.ranges (id, left, right) values (now(),94226,94399);