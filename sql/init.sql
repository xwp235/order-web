DROP TABLE IF EXISTS "public"."mast_menu";
CREATE TABLE "public"."mast_menu" (
                                     "id" int8,
                                     "mm_id" int4 NOT NULL,
                                     "mm_parent_id" int4,
                                     "mm_type" int2 NOT NULL,
                                     "mm_path" varchar(255),
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
                                     "create_time" timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                     "update_time" timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                     PRIMARY KEY ("id"),
                                     CONSTRAINT "unq_mast_menu_1" UNIQUE ("mm_id"),
                                     CONSTRAINT "unq_mast_menu_2" UNIQUE ("mm_code"),
                                     CONSTRAINT "unq_mast_menu_3" UNIQUE ("mm_path")
);

DROP INDEX IF EXISTS idx_mast_menu_1;
CREATE INDEX idx_mast_menu_1 ON "public"."mast_menu"("mm_parent_id");

COMMENT ON COLUMN "public"."mast_menu"."mm_id" IS '菜单ID';

COMMENT ON COLUMN "public"."mast_menu"."mm_parent_id" IS '父菜单ID，顶级菜单值为NULL';

COMMENT ON COLUMN "public"."mast_menu"."mm_type" IS '菜单类型，菜单=1 按钮=2 页面=3';

COMMENT ON COLUMN "public"."mast_menu"."mm_path" IS '菜单类型为菜单或页面时才需要设置';

COMMENT ON COLUMN "public"."mast_menu"."mm_state" IS '菜单状态  停用=0 启用=1,菜单类型为菜单或页面时才需要设置';

COMMENT ON COLUMN "public"."mast_menu"."mm_icon" IS '菜单图标，仅在菜单类型为按钮时才需要设置';

COMMENT ON COLUMN "public"."mast_menu"."mm_name" IS '菜单名称，支持多语言:{"ja-JP":"メニュー１","en-US":"Menu1","zh-CN":"菜单1"}';

COMMENT ON COLUMN "public"."mast_menu"."mm_code" IS '每项菜单的唯一标识';

COMMENT ON COLUMN "public"."mast_menu"."mm_level_chain" IS '菜单层级链';

COMMENT ON COLUMN "public"."mast_menu"."mm_level" IS '菜单所在层级';

COMMENT ON COLUMN "public"."mast_menu"."mm_sort" IS '菜单所在层级的排序';

COMMENT ON COLUMN "public"."mast_menu"."mm_remark" IS '菜单备注';

INSERT INTO "public"."mast_menu" ("id","mm_id", "mm_parent_id", "mm_type", "mm_path", "mm_state", "mm_icon", "mm_name", "mm_code", "mm_level_chain", "mm_level", "mm_sort", "mm_remark", "mm_enable_edit", "mm_enable_delete", "create_time", "update_time")
VALUES
    ( 1, 1, NULL, 1, '/dashboard', 1, 'DashboardOutlined', '{"zh_CN": "工作台"}', 'dashboard', '0', 1, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 2, 2, NULL, 1, NULL, 1, 'ProductOutlined', '{"zh_CN": "系统管理"}', 'system', '0', 1, 2, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 3, 3, 2, 1, '/user-manage', 1, 'UserOutlined', '{"zh_CN": "用户管理"}', 'system:user', '0.2', 2, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 4, 4, 3, 2, NULL, 1, NULL, '{"zh_CN": "查看"}', 'system:user@read', '0.2.3', 3, 1, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 5, 5, 3, 2, NULL, 1, NULL, '{"zh_CN": "用户数据导入"}', 'system:user@import', '0.2.3', 3, 2, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 6, 6, 3, 2, NULL, 1, NULL, '{"zh_CN": "编辑"}', 'system:user@update', '0.2.3', 3, 3, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 7, 7, 3, 2, NULL, 1, NULL, '{"zh_CN": "删除"}', 'system:user@delete', '0.2.3', 3, 4, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 8, 8, 3, 2, NULL, 1, NULL, '{"zh_CN": "禁用"}', 'system:user@active', '0.2.3', 3, 5, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
    ( 9, 9, 3, 2, NULL, 1, NULL, '{"zh_CN": "分配角色"}', 'system:user@assign-roles', '0.2.3', 3, 6, NULL, 'f', 'f','2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09');


DROP TABLE IF EXISTS "public"."mast_role";
CREATE TABLE "public"."mast_role" (
      "id" int8,
      "mr_id" varchar(20) NOT NULL,
      "mr_name" jsonb NOT NULL,
      "mr_enable_delete" bool NOT NULL DEFAULT true,
      "mr_enable_edit" bool NOT NULL DEFAULT true,
      "mr_remark" varchar(255),
      "create_time" timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      "update_time" timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      PRIMARY KEY ("id"),
      CONSTRAINT "unq_mast_role_1" UNIQUE ("mr_id")
);

COMMENT ON COLUMN "public"."mast_role"."mr_id" IS '角色ID,角色的唯一标识';
COMMENT ON COLUMN "public"."mast_role"."mr_name" IS '角色名称，支持多语言:{"ja-JP":"スーパー管理者","en-US":"Super Admin","zh-CN":"超级管理员"}';
COMMENT ON COLUMN "public"."mast_role"."mr_enable_delete" IS '是否可以删除角色 true=可以 false=不可以';
COMMENT ON COLUMN "public"."mast_role"."mr_enable_edit" IS '是否可以修改角色信息 true=可以 false=不可以';

INSERT INTO "public"."mast_role" ("id","mr_id", "mr_name", "mr_enable_delete", "mr_enable_edit", "mr_remark", "create_time", "update_time")
VALUES
(1, 'ADMIN', '{"ja-JP":"スーパー管理者","en-US":"Super Admin","zh-CN":"超级管理员"}','f','f',NULL,'2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
(2, 'MANAGER', '{"ja-JP":"標準管理者","en-US":"Standard Admin","zh-CN":"一般管理员"}','f','f',NULL,'2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
(3, 'USER', '{"ja-JP":"クライアントユーザー","en-US":"Client User","zh-CN":"客户端用户"}','f','f',NULL,'2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09'),
(4, 'IMPORT_USER', '{"ja-JP":"導入担当者","en-US":"Import User","zh-CN":"导入人员"}','f','f',NULL,'2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09');

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
    created_time timestamptz(6) NOT NULL default current_timestamp(6),
    updated_time timestamptz(6) NOT NULL default current_timestamp(6),
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

