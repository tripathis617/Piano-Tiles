--========================
-- Set the DB Name
--========================

-- General DB Name
-- Local-ENV DB Name
\set db_name 'musictilesdb'


-- USER : config_user

-- DROP USER config_user cascade;

-- User to access config schema
CREATE USER config_user WITH PASSWORD 'config_user';

-- Grants for the config_user
GRANT CONNECT ON DATABASE :DB_NAME TO config_user;
GRANT USAGE ON SCHEMA CONFIG to config_user;
GRANT SELECT ON ALL TABLES IN SCHEMA CONFIG TO config_user;

-- USER : app_user

-- DROP USER app_user cascade;

-- User for the application
CREATE USER app_user WITH PASSWORD 'app_user';

-- Grants for the app_user
GRANT CONNECT ON DATABASE :DB_NAME TO app_user;

GRANT USAGE ON SCHEMA DATA to app_user;
GRANT SELECT,INSERT ON ALL TABLES IN SCHEMA DATA TO app_user;
-- GRANT UPDATE ON ALL TABLES IN SCHEMA DATA TO app_user;
GRANT ALL ON ALL TABLES IN SCHEMA DATA TO app_user;
GRANT all ON ALL SEQUENCES IN SCHEMA DATA TO app_user;

GRANT USAGE ON SCHEMA ACCOUNTS to app_user;
GRANT SELECT,INSERT ON ALL TABLES IN SCHEMA ACCOUNTS TO app_user;
GRANT all ON ALL SEQUENCES IN SCHEMA ACCOUNTS TO app_user;

GRANT USAGE ON SCHEMA TENANT to app_user;
GRANT SELECT ON ALL TABLES IN SCHEMA TENANT TO app_user;
GRANT all ON ALL SEQUENCES IN SCHEMA TENANT TO app_user;

-- USER : monitor_user

-- DROP USER monitor_user cascade;

CREATE USER monitor_user WITH PASSWORD 'monitor_user';

-- GRANT CONNECT ON DATABASE :DB_NAME TO monitor_user;
GRANT CONNECT ON DATABASE :DB_NAME TO monitor_user;
GRANT USAGE ON SCHEMA DATA to monitor_user;
GRANT SELECT ON ALL TABLES IN SCHEMA DATA TO monitor_user;

GRANT USAGE ON SCHEMA ACCOUNTS to monitor_user;
GRANT SELECT ON ALL TABLES IN SCHEMA ACCOUNTS TO monitor_user;

GRANT USAGE ON SCHEMA TENANT to monitor_user;
GRANT SELECT ON ALL TABLES IN SCHEMA TENANT TO monitor_user;


