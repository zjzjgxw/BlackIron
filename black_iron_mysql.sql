/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50638
 Source Host           : localhost:3306
 Source Schema         : gxw_store

 Target Server Type    : MySQL
 Target Server Version : 50638
 File Encoding         : 65001

 Date: 20/12/2020 10:40:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for area_code_2020
-- ----------------------------
DROP TABLE IF EXISTS `area_code_2020`;
CREATE TABLE `area_code_2020` (
  `code` bigint(12) unsigned NOT NULL COMMENT '区划代码',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '名称',
  `level` tinyint(1) NOT NULL COMMENT '级别1-5,省市县镇村',
  `pcode` bigint(12) DEFAULT NULL COMMENT '父级区划代码',
  PRIMARY KEY (`code`),
  KEY `name` (`name`),
  KEY `level` (`level`),
  KEY `pcode` (`pcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for gxw_admin
-- ----------------------------
DROP TABLE IF EXISTS `gxw_admin`;
CREATE TABLE `gxw_admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL COMMENT '登录账号',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `is_admin` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0为普通员工，1为管理员',
  `email` varchar(255) NOT NULL COMMENT '邮箱地址',
  `logo_img` varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
  `status` tinyint(4) NOT NULL COMMENT '状态，0为可用，1为禁用',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_u_index` (`account`),
  KEY `name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Table structure for gxw_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `gxw_admin_role`;
CREATE TABLE `gxw_admin_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台管理系统角色表';

-- ----------------------------
-- Table structure for gxw_admin_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `gxw_admin_role_permission_relation`;
CREATE TABLE `gxw_admin_role_permission_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_permission_id_u_index` (`role_id`,`permission_id`) USING BTREE,
  KEY `permission_id_index` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='管理角色权限关系表';

-- ----------------------------
-- Table structure for gxw_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `gxw_admin_role_relation`;
CREATE TABLE `gxw_admin_role_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL COMMENT '管理员id',
  `admin_role_id` int(11) NOT NULL COMMENT '管理角色角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_id_roll_id_u_index` (`admin_id`,`admin_role_id`),
  KEY `roll_id_index` (`admin_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='管理角色关系表';

-- ----------------------------
-- Table structure for gxw_business
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business`;
CREATE TABLE `gxw_business` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '公司名称',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `logo_img` varchar(255) DEFAULT '' COMMENT '头像地址',
  `scale` int(11) NOT NULL DEFAULT '0' COMMENT '规模大小',
  `info` varchar(255) DEFAULT '' COMMENT '公司描述',
  `province` varchar(30) DEFAULT NULL COMMENT '省份',
  `city` varchar(128) DEFAULT NULL COMMENT '城市',
  `county` varchar(128) DEFAULT NULL COMMENT '县',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态，0待审核，1审核通过，2审核拒绝',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商户表';

-- ----------------------------
-- Table structure for gxw_business_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_advertisement`;
CREATE TABLE `gxw_business_advertisement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(10) unsigned NOT NULL DEFAULT '0',
  `img_url` varchar(500) NOT NULL COMMENT '图片地址',
  `url` varchar(500) NOT NULL COMMENT '跳转地址',
  `index_no` int(11) NOT NULL DEFAULT '0' COMMENT '越小越靠前',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `business_index` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='广告图表';

-- ----------------------------
-- Table structure for gxw_business_banner
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_banner`;
CREATE TABLE `gxw_business_banner` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(10) unsigned NOT NULL DEFAULT '0',
  `img_url` varchar(500) NOT NULL COMMENT '图片地址',
  `url` varchar(500) NOT NULL COMMENT '跳转地址',
  `index_no` int(11) NOT NULL DEFAULT '0' COMMENT '越小越靠前',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `business_index` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='banner 表';

-- ----------------------------
-- Table structure for gxw_business_department
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_department`;
CREATE TABLE `gxw_business_department` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(11) NOT NULL COMMENT '商户id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '部门名称',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `business_id_index` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Table structure for gxw_business_navigation
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_navigation`;
CREATE TABLE `gxw_business_navigation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(10) unsigned NOT NULL DEFAULT '0',
  `img_url` varchar(500) NOT NULL COMMENT '图片地址',
  `url` varchar(500) NOT NULL COMMENT '跳转地址',
  `index_no` int(11) NOT NULL DEFAULT '0' COMMENT '越小越靠前',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `business_index` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='导航表';

-- ----------------------------
-- Table structure for gxw_business_role
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_role`;
CREATE TABLE `gxw_business_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(11) NOT NULL COMMENT '商户id',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `business_id_index` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表\n';

-- ----------------------------
-- Table structure for gxw_business_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_role_permission_relation`;
CREATE TABLE `gxw_business_role_permission_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_permission_id_u_index` (`role_id`,`permission_id`) USING BTREE,
  KEY `permission_id_index` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

-- ----------------------------
-- Table structure for gxw_business_staff
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_staff`;
CREATE TABLE `gxw_business_staff` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(11) NOT NULL COMMENT '商户id',
  `account` varchar(255) NOT NULL COMMENT '员工登录账号',
  `password` varchar(255) NOT NULL COMMENT '员工登录密码',
  `name` varchar(255) NOT NULL COMMENT '员工姓名',
  `is_admin` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0为普通员工，1为管理员',
  `email` varchar(255) NOT NULL COMMENT '邮箱地址',
  `logo_img` varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
  `status` tinyint(4) NOT NULL COMMENT '状态，0为可用，1为禁用',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_u_index` (`account`),
  KEY `name_index` (`name`),
  KEY `business_id` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
-- Table structure for gxw_business_staff_department_relation
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_staff_department_relation`;
CREATE TABLE `gxw_business_staff_department_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_staff_id` int(11) NOT NULL COMMENT '员工id',
  `business_department_id` int(11) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `staff_id_department_id_u_index` (`business_staff_id`,`business_department_id`),
  KEY `department_id_index` (`business_department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='员工部门关系表';

-- ----------------------------
-- Table structure for gxw_business_staff_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `gxw_business_staff_role_relation`;
CREATE TABLE `gxw_business_staff_role_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_staff_id` int(11) NOT NULL COMMENT '员工id',
  `business_role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `staff_id_roll_id_u_index` (`business_staff_id`,`business_role_id`),
  KEY `roll_id_index` (`business_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='员工角色关系表';

-- ----------------------------
-- Table structure for gxw_city
-- ----------------------------
DROP TABLE IF EXISTS `gxw_city`;
CREATE TABLE `gxw_city` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` bigint(12) NOT NULL,
  `name` varchar(128) NOT NULL COMMENT '名字',
  `pcode` bigint(12) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=512 DEFAULT CHARSET=utf8mb4 COMMENT='市';

-- ----------------------------
-- Table structure for gxw_county
-- ----------------------------
DROP TABLE IF EXISTS `gxw_county`;
CREATE TABLE `gxw_county` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` bigint(12) NOT NULL,
  `name` varchar(128) NOT NULL COMMENT '名字',
  `pcode` bigint(12) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4096 DEFAULT CHARSET=utf8mb4 COMMENT='县';

-- ----------------------------
-- Table structure for gxw_coupon
-- ----------------------------
DROP TABLE IF EXISTS `gxw_coupon`;
CREATE TABLE `gxw_coupon` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `staff_id` int(10) unsigned NOT NULL COMMENT '创建人',
  `business_id` int(11) NOT NULL COMMENT '商户id',
  `name` varchar(255) NOT NULL COMMENT '活动名称',
  `content` varchar(255) NOT NULL COMMENT '活动描述',
  `price` int(12) unsigned NOT NULL COMMENT '抵扣金额，单位分',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `mode` tinyint(4) NOT NULL COMMENT '0为全场，1为指定商品',
  `target_price` int(12) unsigned NOT NULL COMMENT '需要满足的价格，单位分',
  `num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '剩余张数',
  `delete_flag` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券';

-- ----------------------------
-- Table structure for gxw_coupon_product
-- ----------------------------
DROP TABLE IF EXISTS `gxw_coupon_product`;
CREATE TABLE `gxw_coupon_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) NOT NULL COMMENT '优惠券id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_copupon_id_product_id` (`coupon_id`,`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券指定的商品';

-- ----------------------------
-- Table structure for gxw_coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `gxw_coupon_user`;
CREATE TABLE `gxw_coupon_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '领取人id',
  `order_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '对应订单_id',
  `coupon_id` int(10) unsigned NOT NULL COMMENT '优惠券id',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '0未使用，1已使用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
  `use_at` datetime DEFAULT NULL COMMENT '使用时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_coupon_id_unqiue` (`user_id`,`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券领取使用情况表';

-- ----------------------------
-- Table structure for gxw_discount
-- ----------------------------
DROP TABLE IF EXISTS `gxw_discount`;
CREATE TABLE `gxw_discount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `staff_id` int(10) unsigned NOT NULL COMMENT '创建人',
  `business_id` int(11) NOT NULL COMMENT '商户优惠活动',
  `name` varchar(255) NOT NULL COMMENT '活动名称',
  `content` varchar(255) NOT NULL COMMENT '活动描述',
  `discount` int(255) unsigned NOT NULL COMMENT '优惠百分比',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `mode` tinyint(4) NOT NULL COMMENT '0为全场，1为指定商品',
  `delete_flag` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='限时折扣活动\n';

-- ----------------------------
-- Table structure for gxw_discount_product
-- ----------------------------
DROP TABLE IF EXISTS `gxw_discount_product`;
CREATE TABLE `gxw_discount_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `discount_id` int(11) NOT NULL COMMENT '折扣id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_discount_id_product_id` (`discount_id`,`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COMMENT='限制折扣指定的商品';

-- ----------------------------
-- Table structure for gxw_express
-- ----------------------------
DROP TABLE IF EXISTS `gxw_express`;
CREATE TABLE `gxw_express` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '快递名称',
  `word` varchar(20) NOT NULL DEFAULT '' COMMENT '缩写',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for gxw_fans
-- ----------------------------
DROP TABLE IF EXISTS `gxw_fans`;
CREATE TABLE `gxw_fans` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `fans_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '粉丝用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_fans_u_index` (`user_id`,`fans_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='粉丝关系表';

-- ----------------------------
-- Table structure for gxw_idol
-- ----------------------------
DROP TABLE IF EXISTS `gxw_idol`;
CREATE TABLE `gxw_idol` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `idol_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '偶像用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_idol_u_index` (`user_id`,`idol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='偶像关系表';

-- ----------------------------
-- Table structure for gxw_order
-- ----------------------------
DROP TABLE IF EXISTS `gxw_order`;
CREATE TABLE `gxw_order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(11) NOT NULL COMMENT '商户id',
  `business_name` varchar(255) NOT NULL COMMENT '商户名称',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `order_code` varchar(20) NOT NULL COMMENT '订单号',
  `price` int(11) NOT NULL COMMENT '实付金额',
  `original_price` int(11) unsigned NOT NULL COMMENT '总订单价格',
  `express_price` int(11) NOT NULL COMMENT '快递价格',
  `express_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '快递公司，1为顺丰，2为圆通',
  `express_code` varchar(30) NOT NULL DEFAULT '' COMMENT '快递单号',
  `receiver` varchar(255) NOT NULL DEFAULT '' COMMENT '收件人',
  `telphone` varchar(15) NOT NULL DEFAULT '' COMMENT '联系电话',
  `county` varchar(50) NOT NULL DEFAULT '' COMMENT '县',
  `city` varchar(20) NOT NULL DEFAULT '' COMMENT '市',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `address` varchar(255) NOT NULL COMMENT '收件人地址',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态，1为待支付，2为待发货，3为已发货，4为待评价，5.已完成， 6为申请退款，7为同意退款，8拒绝退款',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pay_at` datetime DEFAULT NULL COMMENT '支付时间',
  `send_at` datetime DEFAULT NULL COMMENT '发货时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COMMENT='订单表\n';

-- ----------------------------
-- Table structure for gxw_order_item
-- ----------------------------
DROP TABLE IF EXISTS `gxw_order_item`;
CREATE TABLE `gxw_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned NOT NULL COMMENT '订单id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名称',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `cover_url` varchar(255) NOT NULL COMMENT '商品封面',
  `specification_id` int(11) DEFAULT NULL COMMENT '规格id',
  `first_specification_name` varchar(50) DEFAULT NULL COMMENT '第一个规格名称',
  `first_specification_value` varchar(50) DEFAULT NULL COMMENT '第一个规格值',
  `second_specification_name` varchar(50) DEFAULT NULL COMMENT '第二个规格名称',
  `second_specification_value` varchar(50) DEFAULT NULL COMMENT '第二个规格值',
  `product_snap` text NOT NULL COMMENT '商品快照信息',
  `price` int(12) unsigned NOT NULL COMMENT '实际价格',
  `stock_type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '1为拍下减库存，2为支付减库存',
  `original_price` int(12) unsigned NOT NULL COMMENT '原价',
  `num` int(10) unsigned NOT NULL COMMENT '购买数量',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COMMENT='订单项目详情';

-- ----------------------------
-- Table structure for gxw_order_refund
-- ----------------------------
DROP TABLE IF EXISTS `gxw_order_refund`;
CREATE TABLE `gxw_order_refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `refund_reason` varchar(255) NOT NULL DEFAULT '' COMMENT '退款理由',
  `refuse_reason` varchar(255) NOT NULL DEFAULT '' COMMENT '拒绝理由',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单退款记录';

-- ----------------------------
-- Table structure for gxw_permission
-- ----------------------------
DROP TABLE IF EXISTS `gxw_permission`;
CREATE TABLE `gxw_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '权限类型，1为商户，2为系统后台',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `path` varchar(255) NOT NULL COMMENT '接口路径',
  `method` varchar(10) NOT NULL COMMENT '调用方式，GET/POST等',
  `info` varchar(255) NOT NULL DEFAULT '' COMMENT '权限描述',
  `group_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '权限组id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `group_id_index` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for gxw_permission_group
-- ----------------------------
DROP TABLE IF EXISTS `gxw_permission_group`;
CREATE TABLE `gxw_permission_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '组名',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1为商户权限，2为系统权限',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='权限组';

-- ----------------------------
-- Table structure for gxw_product_category
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_category`;
CREATE TABLE `gxw_product_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(11) NOT NULL COMMENT '商户id',
  `name` varchar(255) NOT NULL COMMENT '类目名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父类目id',
  `show_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否显示在首页，0为不现实，1为显示',
  `img_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片路径',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识，1为删除，0 为正常',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gxw_product_category_attribute
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_category_attribute`;
CREATE TABLE `gxw_product_category_attribute` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '类目id',
  `name` varchar(20) NOT NULL COMMENT '属性名称',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_category_name` (`category_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='类目属性';

-- ----------------------------
-- Table structure for gxw_product_category_attribute_options
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_category_attribute_options`;
CREATE TABLE `gxw_product_category_attribute_options` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `attribute_id` int(11) NOT NULL COMMENT '类目属性id',
  `content` varchar(20) NOT NULL COMMENT '属性值',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_attribute_id` (`attribute_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='类目属性值';

-- ----------------------------
-- Table structure for gxw_product_category_specification
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_category_specification`;
CREATE TABLE `gxw_product_category_specification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '类目id',
  `name` varchar(20) NOT NULL COMMENT '规格名称',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_category_name` (`category_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='类目规格';

-- ----------------------------
-- Table structure for gxw_product_category_specification_options
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_category_specification_options`;
CREATE TABLE `gxw_product_category_specification_options` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `specification_id` int(11) NOT NULL COMMENT '规格id',
  `content` varchar(20) NOT NULL COMMENT '规格选项名称',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_category_name` (`specification_id`,`content`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='类目规格选项';

-- ----------------------------
-- Table structure for gxw_product_comment
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_comment`;
CREATE TABLE `gxw_product_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(10) unsigned NOT NULL COMMENT '商户id',
  `product_id` int(10) unsigned NOT NULL COMMENT '商品id',
  `order_id` int(10) unsigned NOT NULL COMMENT '订单id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `cover_url` varchar(500) NOT NULL DEFAULT '' COMMENT '用户头衔地址',
  `content` varchar(500) NOT NULL COMMENT '评论内容',
  `score` int(11) NOT NULL COMMENT '分数',
  `type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '1为好评，2为中评，3为差评',
  `img_url` varchar(500) DEFAULT '' COMMENT '图片地址',
  `delete_flag` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='商品评论表';

-- ----------------------------
-- Table structure for gxw_product_detail
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_detail`;
CREATE TABLE `gxw_product_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(11) NOT NULL COMMENT '商户id',
  `category_id` int(10) unsigned NOT NULL COMMENT '类目id',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `mode` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '交易模式，1为普通，2为预付',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `cover_url` varchar(255) NOT NULL DEFAULT '' COMMENT '封面图',
  `video_url` varchar(255) NOT NULL DEFAULT '' COMMENT '视频地址',
  `stock_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '库存模式，1为拍下减少库存，2为付款减库存',
  `status_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1为上架中，2为下架中',
  `delete_flag` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `sale_time` datetime DEFAULT NULL COMMENT '预售时间',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='商品详情';

-- ----------------------------
-- Table structure for gxw_product_detail_attribute
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_detail_attribute`;
CREATE TABLE `gxw_product_detail_attribute` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `detail_id` int(11) unsigned NOT NULL COMMENT '商品id',
  `name` varchar(20) NOT NULL COMMENT '属性名称',
  `content` varchar(20) NOT NULL COMMENT '属性值',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_detail_id` (`detail_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='商品属性';

-- ----------------------------
-- Table structure for gxw_product_detail_desc_img
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_detail_desc_img`;
CREATE TABLE `gxw_product_detail_desc_img` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `detail_id` int(10) unsigned NOT NULL COMMENT '商品详情id',
  `img_url` varchar(250) NOT NULL COMMENT '文件id',
  `index_no` int(10) unsigned NOT NULL COMMENT '文件次序',
  PRIMARY KEY (`id`),
  KEY `idx_detail_id` (`detail_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='商品描述图片';

-- ----------------------------
-- Table structure for gxw_product_detail_main_img
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_detail_main_img`;
CREATE TABLE `gxw_product_detail_main_img` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `detail_id` int(10) unsigned NOT NULL COMMENT '商品详情id',
  `img_url` varchar(250) NOT NULL COMMENT '文件id',
  `index_no` int(10) unsigned NOT NULL COMMENT '文件次序',
  PRIMARY KEY (`id`),
  KEY `idx_detail_id` (`detail_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='商品主图';

-- ----------------------------
-- Table structure for gxw_product_recommend
-- ----------------------------
DROP TABLE IF EXISTS `gxw_product_recommend`;
CREATE TABLE `gxw_product_recommend` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(10) unsigned NOT NULL,
  `product_id` int(10) unsigned NOT NULL,
  `index_no` int(11) NOT NULL COMMENT '排序，越大越靠前',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `recommend_unqiue` (`business_id`,`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='商品推荐表';

-- ----------------------------
-- Table structure for gxw_province
-- ----------------------------
DROP TABLE IF EXISTS `gxw_province`;
CREATE TABLE `gxw_province` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` bigint(12) NOT NULL,
  `name` varchar(128) NOT NULL COMMENT '名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='省份';

-- ----------------------------
-- Table structure for gxw_stock_info
-- ----------------------------
DROP TABLE IF EXISTS `gxw_stock_info`;
CREATE TABLE `gxw_stock_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL COMMENT '商品id',
  `currency_code` varchar(3) NOT NULL COMMENT '币种代码',
  `price` int(12) NOT NULL COMMENT '售价',
  `express_price` int(12) unsigned NOT NULL DEFAULT '0' COMMENT '快递费',
  `last_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '剩余库存',
  `sale_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总销量',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='库存供应商表';

-- ----------------------------
-- Table structure for gxw_stock_sale_record
-- ----------------------------
DROP TABLE IF EXISTS `gxw_stock_sale_record`;
CREATE TABLE `gxw_stock_sale_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL COMMENT '商品id',
  `order_id` int(10) unsigned NOT NULL COMMENT '订单id',
  `num` int(10) unsigned NOT NULL COMMENT '购买数量',
  `stock_specification_id` int(11) DEFAULT NULL COMMENT '库存规格id',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='库存销售记录';

-- ----------------------------
-- Table structure for gxw_stock_specification
-- ----------------------------
DROP TABLE IF EXISTS `gxw_stock_specification`;
CREATE TABLE `gxw_stock_specification` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `stock_info_id` int(10) unsigned NOT NULL COMMENT '库存id',
  `first_name` varchar(255) NOT NULL DEFAULT '' COMMENT '规格名称',
  `first_value` varchar(255) NOT NULL DEFAULT '' COMMENT '规格值',
  `second_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `second_value` varchar(255) DEFAULT NULL COMMENT '规格值',
  `delete_flag` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_stock_value` (`stock_info_id`,`first_value`,`second_value`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='库存详情';

-- ----------------------------
-- Table structure for gxw_stock_specification_detail
-- ----------------------------
DROP TABLE IF EXISTS `gxw_stock_specification_detail`;
CREATE TABLE `gxw_stock_specification_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `stock_specification_id` int(10) unsigned NOT NULL COMMENT '库存规格id',
  `price` int(12) unsigned NOT NULL DEFAULT '0' COMMENT '价格',
  `last_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '剩余库存',
  `sku` varchar(255) NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_stock_info_id` (`stock_specification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='库存详情';

-- ----------------------------
-- Table structure for gxw_user
-- ----------------------------
DROP TABLE IF EXISTS `gxw_user`;
CREATE TABLE `gxw_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商户id',
  `account` varchar(255) NOT NULL COMMENT '登录账号',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `name` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户昵称',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `tel` varchar(20) NOT NULL DEFAULT '' COMMENT '手机',
  `open_id` varchar(50) NOT NULL DEFAULT '' COMMENT '微信open_id',
  `union_id` varchar(50) NOT NULL DEFAULT '' COMMENT '微信unionID',
  `profile_url` varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
  `idol_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '关注者数量',
  `fans_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '粉丝数量',
  `birthday` date DEFAULT NULL,
  `vip_flag` tinyint(4) NOT NULL COMMENT '0表示非会员，1表示会员',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `consume_price` int(12) unsigned NOT NULL DEFAULT '0' COMMENT '总消费金额',
  `point` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户积分',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_u_index` (`account`),
  UNIQUE KEY `tel_u_index` (`tel`) USING BTREE,
  KEY `open_id_idex` (`open_id`) USING BTREE,
  KEY `union_id_index` (`union_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2022 DEFAULT CHARSET=utf8 COMMENT='C端用户表';

-- ----------------------------
-- Table structure for gxw_user_vip
-- ----------------------------
DROP TABLE IF EXISTS `gxw_user_vip`;
CREATE TABLE `gxw_user_vip` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(11) NOT NULL COMMENT '商户id',
  `level` int(11) unsigned NOT NULL COMMENT '会员等级',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '会员称呼',
  `free_express` tinyint(4) unsigned NOT NULL COMMENT '0为不包邮，1为包邮',
  `discount` int(11) unsigned NOT NULL DEFAULT '100' COMMENT '会员折扣',
  `coupon_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '0为不赠送优惠券，其他表示需要赠送的优惠券id',
  `consume_price` int(12) unsigned NOT NULL COMMENT '消费金额条件，单位分',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='会员等级';

-- ----------------------------
-- View structure for area_index_2020
-- ----------------------------
DROP VIEW IF EXISTS `area_index_2020`;
CREATE ALGORITHM=UNDEFINED DEFINER=`tos`@`%` SQL SECURITY DEFINER VIEW `area_index_2020` AS select `a`.`code` AS `code`,`e`.`name` AS `province`,`d`.`name` AS `city`,`c`.`name` AS `county`,`b`.`name` AS `town`,`a`.`name` AS `villagetr` from ((((`area_code_2020` `a` join `area_code_2020` `b` on(((`a`.`level` = 5) and (`b`.`level` = 4) and (`a`.`pcode` = `b`.`code`)))) join `area_code_2020` `c` on((`b`.`pcode` = `c`.`code`))) join `area_code_2020` `d` on((`c`.`pcode` = `d`.`code`))) join `area_code_2020` `e` on((`d`.`pcode` = `e`.`code`))) order by `a`.`code`;

SET FOREIGN_KEY_CHECKS = 1;
