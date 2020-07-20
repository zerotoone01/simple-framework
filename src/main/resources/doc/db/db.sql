create table `tb_head_line`(
    `line_id` int(100) NOT NULL AUTO_INCREMENT COMMENT '头条id',
    `line_name` varchar(100) DEFAULT NULL COMMENT '头条名称',
    `line_link` varchar(200) NOT NULL COMMENT '头条链接',
    `line_img` varchar(100) NOT NULL COMMENT '头条图片',
    `priority` int(2) DEFAULT NULL COMMENT '展示优先级',
    `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '可用状态',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `last_edit_time` datetime DEFAULT NULL COMMENT '最近编辑时间',
    PRIMARY KEY(`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1  DEFAULT CHARSET=utf8

create table `tb_shop_category`(
    `shop_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺类别id',
    `shop_category_name` varchar(100) DEFAULT NULL COMMENT '店铺类别名称',
    `shop_category_desc` varchar(1000) NOT NULL COMMENT '店铺类别描述',
    `shop_category_img` varchar(2000) NOT NULL COMMENT '店铺类别图片地址',
    `priority` int(2) NOT NULL DEFAULT '0'  COMMENT '店铺类别展示优先级',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `last_edit_time` datetime DEFAULT NULL COMMENT '最近编辑时间',
    `parent_id` int(11) DEFAULT NULL COMMENT '店铺类别的父级类别'
    PRIMARY KEY(`shop_category_id`),
    KEY `fk_shop_category_self`(`parent_id`),
    CONSTRAINT `fk_shop_category_self`  FOREIGN KEY(`parent_id`),
    REFERENCES `tb_shop_category`(`shop_category_id`)

) ENGINE=InnoDB AUTO_INCREMENT=1  DEFAULT CHARSET=utf8