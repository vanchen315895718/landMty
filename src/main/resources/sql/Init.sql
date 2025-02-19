--创建数据库
create
database mty_land;

--用户表
CREATE TABLE ums_member
(
    member_id       bigserial NOT NULL, -- 用户id
    member_code     varchar   NOT NULL, -- 用户code（唯一）
    member_name     varchar NULL,       -- 用户姓名
    member_password varchar NULL,       -- 用户密码
    roles           varchar NULL,       -- 用户角色（多个逗号分隔）
    CONSTRAINT ums_member_member_code_key UNIQUE (member_code),
    CONSTRAINT ums_member_pkey PRIMARY KEY (member_id)
);
COMMENT
ON TABLE ums_member IS '用户表';

-- Column comments

COMMENT
ON COLUMN ums_member.member_id IS '用户id';
COMMENT
ON COLUMN ums_member.member_code IS '用户code（唯一）';
COMMENT
ON COLUMN ums_member.member_name IS '用户姓名';
COMMENT
ON COLUMN ums_member.member_password IS '用户密码';
COMMENT
ON COLUMN ums_member.roles IS '用户角色（多个逗号分隔）';

 ---------------  jwt 记录表

CREATE TABLE IF NOT EXISTS land_mg.jwt_record
(
    id bigint NOT NULL,
    jwt text COLLATE pg_catalog."default" NOT NULL,
    is_deleted integer NOT NULL,
    create_time timestamp with time zone,
    create_by character varying(100) COLLATE pg_catalog."default",
    update_time timestamp with time zone,
    update_by character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT jwt_record_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS land_mg.jwt_record
    OWNER to postgres;