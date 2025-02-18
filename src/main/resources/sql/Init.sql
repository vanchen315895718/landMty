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