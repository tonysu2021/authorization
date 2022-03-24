CREATE TABLE tb_permission (
  id character varying(64) NOT NULL,
  parent_id character varying(64) DEFAULT NULL,
  name varchar(64) NOT NULL,
  enname varchar(64) NOT NULL,
  url varchar(255) NOT NULL,
  description varchar(256) DEFAULT NULL,
  created TIMESTAMP WITH TIME ZONE  NOT NULL,
  updated TIMESTAMP WITH TIME ZONE  NOT NULL
);

COMMENT ON TABLE tb_permission IS '權限表';
COMMENT ON COLUMN tb_permission.parent_id IS '父權限';
COMMENT ON COLUMN tb_permission.name IS '權限名稱';
COMMENT ON COLUMN tb_permission.enname IS '權限英文名稱';
COMMENT ON COLUMN tb_permission.url IS '授權路徑';

insert into tb_permission(id,parent_id,name,enname,url,description,created,updated) values
('per37','per0','系統管理','System','/',NULL,NOW(),NOW()),
('per38','per37','用戶管理','SystemUser','/users/',NULL,NOW(),NOW()),
('per39','per38','用戶管理-查看用戶','SystemUser-View','',NULL,NOW(),NOW()),
('per40','per38','用戶管理-新增用戶','SystemUser-Insert','',NULL,NOW(),NOW()),
('per41','per38','用戶管理-編輯用戶','SystemUser-Update','',NULL,NOW(),NOW()),
('per42','per38','用戶管理-刪除用戶','SystemUser-Delete','',NULL,NOW(),NOW()),
('per43','per37','電商-消費者管理','E-Customer','/customer',NULL,NOW(),NOW()),
('per44','per37','微商-聊天室','MB-ChatRoom','',NULL,NOW(),NOW()),
('per45','per37','直播管理','Live-Stream','/live',NULL,NOW(),NOW());

CREATE TABLE tb_role (
  id character varying(64) NOT NULL,
  parent_id character varying(64) DEFAULT NULL,
  name varchar(64) NOT NULL,
  enname varchar(64) NOT NULL,
  description varchar(256) DEFAULT NULL,
  created TIMESTAMP WITH TIME ZONE  NOT NULL,
  updated TIMESTAMP WITH TIME ZONE  NOT NULL
);

COMMENT ON TABLE tb_role IS '角色表';
COMMENT ON COLUMN tb_role.parent_id IS '父角色';
COMMENT ON COLUMN tb_role.name IS '角色名稱';
COMMENT ON COLUMN tb_role.enname IS '角色英文名稱';

insert into tb_role(id,parent_id,name,enname,description,created,updated) values
('role01','role0','超級管理員','ADMIN01',NULL,NOW(),NOW());

CREATE TABLE tb_role_permission (
  id UUID NOT NULL,
  role_id character varying(64) NOT NULL,
  permission_id character varying(64) NOT NULL,
  description varchar(256) DEFAULT NULL,
  created TIMESTAMP WITH TIME ZONE  NOT NULL,
  updated TIMESTAMP WITH TIME ZONE  NOT NULL
);

COMMENT ON TABLE tb_role_permission IS '角色權限表';
COMMENT ON COLUMN tb_role_permission.role_id IS '角色 ID';
COMMENT ON COLUMN tb_role_permission.permission_id IS '權限 ID';

insert into tb_role_permission(id,role_id,permission_id,description,created,updated) values
(random_uuid(),'role01','per37',NULL,NOW(),NOW()),
(random_uuid(),'role01','per38',NULL,NOW(),NOW()),
(random_uuid(),'role01','per39',NULL,NOW(),NOW()),
(random_uuid(),'role01','per40',NULL,NOW(),NOW()),
(random_uuid(),'role01','per41',NULL,NOW(),NOW()),
(random_uuid(),'role01','per42',NULL,NOW(),NOW()),
(random_uuid(),'role01','per43',NULL,NOW(),NOW()),
(random_uuid(),'role01','per44',NULL,NOW(),NOW()),
(random_uuid(),'role01','per45',NULL,NOW(),NOW());

CREATE TABLE tb_user (
  id character varying(64) NOT NULL,
  username varchar(50) NOT NULL,
  password varchar(64) NOT NULL,
  phone varchar(20) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  description varchar(256) DEFAULT NULL,
  created TIMESTAMP WITH TIME ZONE  NOT NULL,
  updated TIMESTAMP WITH TIME ZONE  NOT NULL
);

COMMENT ON TABLE tb_user IS '用戶表';
COMMENT ON COLUMN tb_user.username IS '用戶名';
COMMENT ON COLUMN tb_user.password IS '密碼，加密存儲';
COMMENT ON COLUMN tb_user.phone IS '註冊手機號';
COMMENT ON COLUMN tb_user.email IS '註冊郵箱';

insert into tb_user(id,username,password,phone,email,description,created,updated) values
('admin','admin','$2a$10$WLwpaMP.VUNx8hMpiQmNPeF38UCO7pMh/kRpGg6D0LPxLjw96IGem','','',NULL,NOW(),NOW()),
('user01','tony01','$2a$10$WLwpaMP.VUNx8hMpiQmNPeF38UCO7pMh/kRpGg6D0LPxLjw96IGem','15888888888','shionheartex@gmail.com',NULL,NOW(),NOW());

CREATE TABLE tb_user_role (
  id UUID NOT NULL,
  user_id character varying(64) NOT NULL,
  role_id character varying(64) NOT NULL,
  description varchar(256) DEFAULT NULL,
  created TIMESTAMP WITH TIME ZONE  NOT NULL,
  updated TIMESTAMP WITH TIME ZONE  NOT NULL
);

COMMENT ON TABLE tb_user_role IS '用戶角色表';
COMMENT ON COLUMN tb_user_role.user_id IS '用戶 ID';
COMMENT ON COLUMN tb_user_role.role_id IS '角色 ID';

insert into tb_user_role(id,user_id,role_id,description,created,updated) values
(random_uuid(),'admin','role01',NULL,NOW(),NOW()),
(random_uuid(),'user01','role01',NULL,NOW(),NOW());