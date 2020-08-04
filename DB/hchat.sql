

DROP TABLE IF EXISTS `tb_chat_record`;

CREATE TABLE `tb_chat_record` (
  `id` bigint NOT NULL auto_increment COMMENT 'id',
  `userid` bigint DEFAULT NULL COMMENT '用户id',
  `friendid` bigint DEFAULT NULL COMMENT '好友id',
  `has_read` int(1) DEFAULT NULL COMMENT '是否已读',
  `createtime` datetime DEFAULT now() COMMENT '聊天时间',
  `has_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  `message` varchar(1024) DEFAULT NULL COMMENT '消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `tb_friend`;

CREATE TABLE `tb_friend` (
  `id` bigint auto_increment NOT NULL,
  `userid` bigint DEFAULT NULL COMMENT '用户id',
  `friends_id` bigint DEFAULT NULL COMMENT '好友id',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT now() COMMENT '添加好友日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK` (`userid`,`friends_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `tb_friend_req`;

CREATE TABLE `tb_friend_req` (
  `id` bigint auto_increment NOT NULL COMMENT 'id',
  `from_userid` bigint DEFAULT NULL COMMENT '请求好友用户id',
  `to_userid` bigint DEFAULT NULL COMMENT '被请求好友用户id',
  `createtime` datetime DEFAULT now() COMMENT '请求时间',
  `message` varchar(255) DEFAULT NULL COMMENT '发送的消息',
  `status` int(1) DEFAULT 0 COMMENT '是否已处理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint auto_increment NOT NULL COMMENT 'ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `pic_small` varchar(255) DEFAULT NULL COMMENT '头像（小尺寸）',
  `pic_normal` varchar(255) DEFAULT NULL COMMENT '头像（正常尺寸）',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `qrcode` varchar(255) DEFAULT NULL COMMENT '二维码',
  `client_id` varchar(255) DEFAULT NULL COMMENT '手机端唯一ID',
  `sign` varchar(1024) DEFAULT NULL COMMENT '签名',
  `createtime` datetime DEFAULT now() COMMENT '注册日期',
  `phone` varchar(255) DEFAULT NULL COMMENT '绑定手机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
