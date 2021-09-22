CREATE DATABASE auth_db;
    
\c auth_db;

/** Account Management */
CREATE USER auth_user WITH PASSWORD '1qaz2wsx';
    
GRANT CONNECT ON DATABASE auth_db TO auth_user; 

/* Initalise uuid module */
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

create table oauth_code (
  code VARCHAR(256), authentication bytea
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);

INSERT INTO public.oauth_client_details (client_id,resource_ids,client_secret,"scope",authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES
('admin',NULL,'$2a$10$WLwpaMP.VUNx8hMpiQmNPeF38UCO7pMh/kRpGg6D0LPxLjw96IGem','read','password,refresh_token',NULL,NULL,3600,3600,NULL,'false'),
('clientapp',NULL,'$2a$10$OitkHmhB93tEb/ZABzKNNumP0NHYxmBxrptarK4/VFqqSACFGYZJe','read','password,refresh_token',NULL,NULL,3600,3600,NULL,'false'),
('livestream',NULL,'$2a$10$OitkHmhB93tEb/ZABzKNNumP0NHYxmBxrptarK4/VFqqSACFGYZJe','read','password,refresh_token',NULL,NULL,3600,3600,NULL,'false');