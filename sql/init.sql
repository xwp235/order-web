DROP TABLE IF EXISTS "public"."mast_menu";
CREATE TABLE "public"."mast_menu" (
         "id" int8,
         "mm_id" int4 NOT NULL,
         "mm_parent_id" int4,
         "mm_type" int2 NOT NULL,
         "mm_path" varchar(255),
         "mm_method" VARCHAR(10),
         "mm_state" int2,
         "mm_icon" varchar(60),
         "mm_name" jsonb NOT NULL,
         "mm_code" varchar(255),
         "mm_level_chain" varchar(255) NOT NULL,
         "mm_level" int4 NOT NULL,
         "mm_sort" int4 NOT NULL,
         "mm_remark" varchar(255),
         "mm_enable_edit" bool NOT NULL DEFAULT true,
         "mm_enable_delete" bool NOT NULL DEFAULT true,
         "mm_require_auth" bool NOT NULL DEFAULT true,
         "create_time" timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
         "update_time" timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
         PRIMARY KEY ("id"),
         CONSTRAINT "unq_mast_menu_1" UNIQUE ("mm_id"),
         CONSTRAINT "unq_mast_menu_2" UNIQUE ("mm_code"),
         CONSTRAINT "unq_mast_menu_3" UNIQUE ("mm_path","mm_method","mm_code")
);

DROP INDEX IF EXISTS idx_mast_menu_1;
CREATE INDEX idx_mast_menu_1 ON "public"."mast_menu"("mm_parent_id");

COMMENT ON COLUMN "public"."mast_menu"."mm_id" IS '菜单ID';

COMMENT ON COLUMN "public"."mast_menu"."mm_parent_id" IS '父菜单ID，顶级菜单值为NULL';

COMMENT ON COLUMN "public"."mast_menu"."mm_type" IS '菜单类型，菜单=1 按钮=2 链接=3';

COMMENT ON COLUMN "public"."mast_menu"."mm_path" IS '菜单类型为菜单或页面时才需要设置';

COMMENT ON COLUMN "public"."mast_menu"."mm_method" IS '请求方式';

COMMENT ON COLUMN "public"."mast_menu"."mm_state" IS '菜单状态  停用=0 启用=1,菜单类型为菜单或页面时才需要设置';

COMMENT ON COLUMN "public"."mast_menu"."mm_icon" IS '菜单图标，仅在菜单类型为按钮时才需要设置';

COMMENT ON COLUMN "public"."mast_menu"."mm_name" IS '菜单名称，支持多语言:{"ja-JP":"メニュー１","en-US":"Menu1","zh-CN":"菜单1"}';

COMMENT ON COLUMN "public"."mast_menu"."mm_code" IS '每项菜单的唯一标识';

COMMENT ON COLUMN "public"."mast_menu"."mm_level_chain" IS '菜单层级链';

COMMENT ON COLUMN "public"."mast_menu"."mm_level" IS '菜单所在层级';

COMMENT ON COLUMN "public"."mast_menu"."mm_sort" IS '菜单所在层级的排序';

COMMENT ON COLUMN "public"."mast_menu"."mm_remark" IS '菜单备注';

INSERT INTO "public"."mast_menu" ("id","mm_id", "mm_parent_id", "mm_type", "mm_method","mm_path", "mm_state", "mm_icon", "mm_name", "mm_code", "mm_level_chain", "mm_level", "mm_sort", "mm_remark", "mm_enable_edit", "mm_enable_delete", "create_time", "update_time")
VALUES
    ( 1, 1, NULL, 1,'get', '/dashboard', 1, 'fa-home', '{"zh_CN": "工作台"}', 'dashboard', '0', 1, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 2, 2, NULL, 1, NULL, NULL, 1, 'fa-cogs', '{"zh_CN": "系统管理"}', 'system', '0', 1, 2, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),

    ( 3, 3, 2, 1, 'get','/user-manage', 1, 'fa-users', '{"zh_CN": "用户管理"}', 'system:users', '0.2', 2, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 4, 4, 3, 2, 'get','/users', 1, NULL, '{"zh_CN": "查看列表"}', 'system:users@list', '0.2.3', 3, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 5, 5, 3, 2, 'get','/users/{id:\d+}', 1, NULL, '{"zh_CN": "查看详情"}', 'system:users@detail', '0.2.3', 3, 2, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 6, 6, 3, 2, 'post','/users/import', 1, NULL, '{"zh_CN": "用户数据导入"}', 'system:users@import', '0.2.3', 3, 3, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 7, 7, 3, 2, 'put','/users', 1, NULL, '{"zh_CN": "编辑"}', 'system:users@update', '0.2.3', 3, 4, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 8, 8, 3, 2, 'delete','/users', 1, NULL, '{"zh_CN": "删除"}', 'system:users@delete', '0.2.3', 3, 5, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 9, 9, 3, 2, 'patch','/users/suspension', 1, NULL, '{"zh_CN": "禁用"}', 'system:users@suspension', '0.2.3', 3, 6, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 10, 10, 3, 2, 'post','/users', 1, NULL, '{"zh_CN": "创建"}', 'system:users@create', '0.2.3', 3, 7, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 11, 11, 3, 2,'put','/users/assignment', 1, NULL, '{"zh_CN": "分配角色"}', 'system:users@assign-roles', '0.2.3', 3, 8, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),

    ( 12, 12, NULL, 1, NULL, NULL, 1, 'fa-tasks', '{"zh_CN": "任务管理"}', 'task', '0', 1, 3, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 13, 13, 12, 1, 'get','/schedule-manage', 1, 'fa-calendar', '{"zh_CN": "定时任务"}', 'task:schedules', '0.12', 2, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 14, 14, 13, 2, 'get','/schedules', 1, NULL, '{"zh_CN": "查看列表"}', 'task:schedules@list', '0.12.13', 3, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 15, 15, 13, 2, 'get','/schedules/{id:\d+}', 1, NULL, '{"zh_CN": "查看详情"}', 'task:schedules@detail', '0.12.13', 3, 2, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 16, 16, 13, 2, 'post','/schedules', 1, NULL, '{"zh_CN": "创建"}', 'task:schedules@create', '0.12.13', 3, 3, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 17, 17, 13, 2, 'put','/schedules', 1, NULL, '{"zh_CN": "编辑"}', 'task:schedules@update', '0.12.13', 3, 4, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 18, 18, 13, 2, 'delete','/schedules', 1, NULL, '{"zh_CN": "删除"}', 'task:schedules@delete', '0.12.13', 3, 5, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 19, 19, 13, 2, 'patch','/schedules/{action:run|resume|pause}', 1, NULL, '{"zh_CN": "变更任务状态[run,resume,pause]"}', 'task:schedules@changeState', '0.12.13', 3, 6, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),

    ( 20, 20, 2, 1, 'get','/menu-manage', 1, 'fa-th-large', '{"zh_CN": "菜单管理"}', 'system:menus', '0.2', 2, 2, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 21, 21, 20, 2, 'get','/menus', 1, NULL, '{"zh_CN": "查看列表"}', 'system:menus@list', '0.2.20', 3, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 22, 22, 20, 2, 'get','/menus/{id:\d+}', 1, NULL, '{"zh_CN": "查看详情"}', 'system:menus@detail', '0.2.20', 3, 2, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 23, 23, 20, 2, 'put','/menus', 1, NULL, '{"zh_CN": "编辑"}', 'system:menus@update', '0.2.20', 3, 3, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 24, 24, 20, 2, 'delete','/menus', 1, NULL, '{"zh_CN": "删除"}', 'system:menus@delete', '0.2.20', 3, 4, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 25, 25, 20, 2, 'patch','/menus/suspension', 1, NULL, '{"zh_CN": "禁用"}', 'system:menus@suspension', '0.2.20', 3, 5, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 26, 26, 20, 2, 'post','/menus', 1, NULL, '{"zh_CN": "创建"}', 'system:menus@create', '0.2.20', 3, 6, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 27, 27, 20, 2, 'get','/menus/form', 1, NULL, '{"zh_CN": "菜单添加页面"}', 'system:menus@to-create', '0.2.20', 3, 7, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 28, 28, 20, 2, 'get','/menus/form/{id:\d+}', 1, NULL, '{"zh_CN": "菜单编辑页面"}', 'system:menus@to-update', '0.2.20', 3, 8, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09');


DROP TABLE IF EXISTS "public"."mast_role";
CREATE TABLE "public"."mast_role" (
      "id" int8,
      "mr_id" varchar(20) NOT NULL,
      "mr_name" jsonb NOT NULL,
      "mr_enable_delete" bool NOT NULL DEFAULT true,
      "mr_enable_edit" bool NOT NULL DEFAULT true,
      "mr_weight" int4 NOT NULL DEFAULT 0,
      "mr_remark" varchar(255),
      "create_time" timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      "update_time" timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      PRIMARY KEY ("id"),
      CONSTRAINT "unq_mast_role_1" UNIQUE ("mr_id"),
      CONSTRAINT "unq_mast_role_2" UNIQUE ("mr_weight")
);

COMMENT ON COLUMN "public"."mast_role"."mr_id" IS '角色ID,角色的唯一标识';
COMMENT ON COLUMN "public"."mast_role"."mr_name" IS '角色名称，支持多语言:{"ja_JP":"スーパー管理者","en_US":"Super Admin","zh_CN":"超级管理员"}';
COMMENT ON COLUMN "public"."mast_role"."mr_enable_delete" IS '是否可以删除角色 true=可以 false=不可以';
COMMENT ON COLUMN "public"."mast_role"."mr_enable_edit" IS '是否可以修改角色信息 true=可以 false=不可以';

INSERT INTO "public"."mast_role" ("id","mr_id", "mr_name", "mr_enable_delete", "mr_enable_edit","mr_weight", "mr_remark", "create_time", "update_time")
VALUES
(1, 'ROLE_ADMIN', '{"ja_JP":"スーパー管理者","en_US":"Super Admin","zh_CN":"超级管理员"}','f','f',1000,NULL,'2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
(2, 'ROLE_MANAGER', '{"ja_JP":"標準管理者","en_US":"Standard Admin","zh_CN":"一般管理员"}','f','f',100,NULL,'2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
(3, 'ROLE_USER', '{"ja_JP":"クライアントユーザー","en_US":"Client User","zh_CN":"客户端用户"}','f','f',1,NULL,'2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
(4, 'ROLE_IMPORT_USER', '{"ja_JP":"導入担当者","en_US":"Import User","zh_CN":"导入人员"}','f','f',10,NULL,'2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09');

DROP TABLE IF EXISTS "public"."mast_account";
CREATE TABLE "public"."mast_account" (
    id int8,
    avatar varchar(50),
    nickname varchar(16),
    username varchar(16),
    mobile varchar(16),
    email varchar(40),
    password varchar(80),
    account_expired bool NOT NULL default false,
    account_locked  bool NOT NULL default false,
    password_expired bool NOT NULL default false,
    mfa_key varchar(255),
    using_mfa bool NOT NULL default true,
    enabled boolean NOT NULL default true,
    create_time timestamptz(6) NOT NULL default current_timestamp(6),
    update_time timestamptz(6) NOT NULL default current_timestamp(6),
    PRIMARY KEY ("id"),
    CONSTRAINT unq_mast_user_nickname UNIQUE (nickname),
    CONSTRAINT unq_mast_user_username UNIQUE (username),
    CONSTRAINT unq_mast_user_username_mobile UNIQUE (username,mobile),
    CONSTRAINT unq_mast_user_username_email UNIQUE (username,email),
    CHECK (COALESCE(username,mobile,email) IS NOT NULL)
);

INSERT INTO "public"."mast_account" ("id","username","password") VALUES
(1, 'admin', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(2, 'manager1','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(3, 'manager2','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(4, 'manager3','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(5, 'user1','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(6, 'user2','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(7, 'user3','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(8, 'user4','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(9, 'import1','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'),
(10, 'import2','{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');


DROP TABLE IF EXISTS "public"."mast_account_mapping_role";
CREATE TABLE "public"."mast_account_mapping_role" (
    account_id int8,
    role_id varchar(20),
    primary key(account_id,role_id)
);

INSERT INTO "public"."mast_account_mapping_role" VALUES
(1, 'ROLE_ADMIN'),
(2,'ROLE_MANAGER'),
(3,'ROLE_MANAGER'),
(4,'ROLE_MANAGER'),
(5,'ROLE_USER'),
(6,'ROLE_USER'),
(7,'ROLE_USER'),
(8,'ROLE_USER'),
(9,'ROLE_IMPORT_USER'),
(10,'ROLE_IMPORT_USER');

DROP TABLE IF EXISTS "public"."mast_role_mapping_permission";
CREATE TABLE "public"."mast_role_mapping_permission" (
      role_id varchar(20),
      permission_key varchar(255),
      primary key(role_id,permission_key)
);

INSERT INTO "public"."mast_role_mapping_permission" VALUES
('ROLE_ADMIN', 'dashboard'),
('ROLE_ADMIN', 'system'),
('ROLE_ADMIN', 'system:users'),
('ROLE_ADMIN', 'system:users@list'),
('ROLE_ADMIN', 'system:users@detail'),
('ROLE_ADMIN', 'system:users@import'),
('ROLE_ADMIN', 'system:users@update'),
('ROLE_ADMIN', 'system:users@delete'),
('ROLE_ADMIN', 'system:users@suspension'),
('ROLE_ADMIN', 'system:users@create'),
('ROLE_ADMIN', 'system:users@assign-roles'),
('ROLE_ADMIN', 'task'),
('ROLE_ADMIN', 'task:schedules'),
('ROLE_ADMIN', 'task:schedules@list'),
('ROLE_ADMIN', 'task:schedules@detail'),
('ROLE_ADMIN', 'task:schedules@create'),
('ROLE_ADMIN', 'task:schedules@update'),
('ROLE_ADMIN', 'task:schedules@delete'),
('ROLE_ADMIN','task:schedules@changeState'),
('ROLE_ADMIN', 'system:menus'),
('ROLE_ADMIN', 'system:menus@list'),
('ROLE_ADMIN', 'system:menus@detail'),
('ROLE_ADMIN', 'system:menus@update'),
('ROLE_ADMIN', 'system:menus@delete'),
('ROLE_ADMIN', 'system:menus@suspension'),
('ROLE_ADMIN', 'system:menus@create'),
('ROLE_ADMIN', 'system:menus@to-create'),
('ROLE_ADMIN', 'system:menus@to-update'),

('ROLE_MANAGER', 'dashboard'),
('ROLE_MANAGER', 'system'),
('ROLE_MANAGER', 'system:users'),
('ROLE_MANAGER', 'system:users@detail'),
('ROLE_MANAGER', 'system:users@list'),
('ROLE_MANAGER', 'system:users@update'),
('ROLE_MANAGER', 'system:users@delete'),
('ROLE_MANAGER', 'system:users@suspension'),
('ROLE_MANAGER', 'system:users@create'),
('ROLE_MANAGER', 'system:users@assign-roles'),
('ROLE_MANAGER', 'task'),
('ROLE_MANAGER', 'task:schedules'),
('ROLE_MANAGER', 'task:schedules@list'),
('ROLE_MANAGER', 'task:schedules@detail'),
('ROLE_MANAGER', 'task:schedules@create'),
('ROLE_MANAGER', 'task:schedules@update'),
('ROLE_MANAGER', 'task:schedules@delete'),
('ROLE_MANAGER', 'task:schedules@changeState'),

('ROLE_MANAGER', 'system:menus'),
('ROLE_MANAGER', 'system:menus@list'),
('ROLE_MANAGER', 'system:menus@detail'),
('ROLE_MANAGER', 'system:menus@update'),
('ROLE_MANAGER', 'system:menus@delete'),
('ROLE_MANAGER', 'system:menus@suspension'),
('ROLE_MANAGER', 'system:menus@create'),
('ROLE_MANAGER', 'system:menus@to-create'),
('ROLE_MANAGER', 'system:menus@to-update'),

('ROLE_IMPORT_USER', 'dashboard'),
('ROLE_IMPORT_USER', 'system'),
('ROLE_IMPORT_USER', 'system:users'),
('ROLE_IMPORT_USER', 'system:users@list'),
('ROLE_IMPORT_USER', 'system:users@detail'),
('ROLE_IMPORT_USER', 'system:users@import');

drop table if exists public.his_client_login_log;
create table public.his_client_login_log (
       username varchar(16),
       session_id varchar(32),
       device varchar(16),
       ip varchar(64),
       account_id int8,
       login_time timestamptz(6) default current_timestamp(6),
       logout_time timestamptz(6),
       is_offline bool default false,
       primary key(username,session_id,device)
);
