/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : practice_erp

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2019-10-29 00:13:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33155F659F657E` (`parentId`)
) ENGINE=InnoDB AUTO_INCREMENT=707 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统菜单', '-', '0');
INSERT INTO `menu` VALUES ('100', '商品管理', '-', '1');
INSERT INTO `menu` VALUES ('101', '供应商', '/supplier_list.action', '100');
INSERT INTO `menu` VALUES ('102', '商品类别', '/category_list.action', '100');
INSERT INTO `menu` VALUES ('103', '商品列表', '/product_list.action', '100');
INSERT INTO `menu` VALUES ('200', '采购管理', '-', '1');
INSERT INTO `menu` VALUES ('201', '采购订单', '/order_buyList.action', '200');
INSERT INTO `menu` VALUES ('202', '采购审核', '/order_buyAuditList.action', '200');
INSERT INTO `menu` VALUES ('300', '销售管理', '-', '1');
INSERT INTO `menu` VALUES ('400', '商品运输', '-', '1');
INSERT INTO `menu` VALUES ('401', '任务指派', '/transport_taskList.action', '400');
INSERT INTO `menu` VALUES ('402', '任务进度', '/transport_myTaskList.action', '400');
INSERT INTO `menu` VALUES ('500', '仓库管理', '-', '1');
INSERT INTO `menu` VALUES ('501', '库存查询', '/storageDetail_list.action', '500');
INSERT INTO `menu` VALUES ('502', '入库', '/order_warehousingList.action', '500');
INSERT INTO `menu` VALUES ('503', '库存操作日志', '/operationDetail_list.action', '500');
INSERT INTO `menu` VALUES ('600', '报表中心', '-', '1');
INSERT INTO `menu` VALUES ('601', '采购报表', '/bill_buyBillList.action', '600');
INSERT INTO `menu` VALUES ('700', '基础维护', '-', '1');
INSERT INTO `menu` VALUES ('701', '部门维护', '/department_list.action', '700');
INSERT INTO `menu` VALUES ('702', '员工维护', '/employee_list.action', '700');
INSERT INTO `menu` VALUES ('703', '角色维护', '/role_list.action', '700');
INSERT INTO `menu` VALUES ('704', '资源维护', '/resource_list.action', '700');
INSERT INTO `menu` VALUES ('705', '菜单维护', '/menu_list.action', '700');
INSERT INTO `menu` VALUES ('706', '仓库维护', '/warehouse_list.action', '700');
