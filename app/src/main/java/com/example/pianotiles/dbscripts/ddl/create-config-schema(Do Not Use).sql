-- DROP SCHEMA CONFIG CASCADE;

CREATE SCHEMA CONFIG;

-- Drop table
-- Drop table CONFIG.PROPERTIES;

CREATE TABLE CONFIG.PROPERTIES (
	"key" varchar(2048) NOT NULL,
	value text NULL,
	application varchar(128) NOT NULL,
	profile varchar(128) NOT NULL,
	label varchar(128) NOT NULL,
	CONSTRAINT PROPERTIES_pkey PRIMARY KEY (key, application, profile, label)
);
