-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  7.0.0.4363
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 haro.ha_rwm_news_content 结构
DROP TABLE IF EXISTS `ha_rwm_news_content`;
CREATE TABLE IF NOT EXISTS `ha_rwm_news_content` (
  `news_id` char(36) NOT NULL COMMENT '新闻ID',
  `content` text NOT NULL COMMENT '内容',
  UNIQUE KEY `idx_news_id` (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻内容';

-- 正在导出表  haro.ha_rwm_news_content 的数据：~2 rows (大约)
DELETE FROM `ha_rwm_news_content`;
/*!40000 ALTER TABLE `ha_rwm_news_content` DISABLE KEYS */;
INSERT INTO `ha_rwm_news_content` (`news_id`, `content`) VALUES
	('10830c4a-92f8-4dc2-baa4-d177380e35a6', '            <p>'),
	('37c96424-7f1c-49aa-9a0c-6223785726c6', '5');
/*!40000 ALTER TABLE `ha_rwm_news_content` ENABLE KEYS */;


-- 导出  表 haro.ha_r_assembly 结构
DROP TABLE IF EXISTS `ha_r_assembly`;
CREATE TABLE IF NOT EXISTS `ha_r_assembly` (
  `id` char(36) NOT NULL,
  `type` tinyint(2) DEFAULT NULL COMMENT '类型',
  `category` tinyint(2) DEFAULT NULL COMMENT '类别',
  `content` text COMMENT '内容',
  `publish_time` datetime DEFAULT NULL COMMENT '修改时间',
  `publish_user` char(36) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块';

-- 正在导出表  haro.ha_r_assembly 的数据：~0 rows (大约)
DELETE FROM `ha_r_assembly`;
/*!40000 ALTER TABLE `ha_r_assembly` DISABLE KEYS */;
INSERT INTO `ha_r_assembly` (`id`, `type`, `category`, `content`, `publish_time`, `publish_user`) VALUES
	('05a1ec44-9c71-4ee6-a45b-96230a39921d', 7, 2, NULL, '2015-09-23 17:15:35', NULL),
	('09fe5564-09e5-4f97-8e31-e6a63c547955', 1, 1, NULL, '2015-09-23 17:15:35', NULL),
	('11c21d00-f6ca-4f69-927a-1ec93cb635b4', 20, 7, NULL, '2015-09-23 17:15:35', NULL),
	('1d050a09-2fb1-4e12-88e4-e1bf7971535e', 8, 3, NULL, '2015-09-23 17:15:35', NULL),
	('21a3c2bd-8532-479e-88cd-cf3bcdef4fd5', 17, 6, NULL, '2015-09-23 17:15:35', NULL),
	('3bd6fd3c-21d2-4ee1-b5f0-04e2b4115913', 12, 4, NULL, '2015-09-23 17:15:35', NULL),
	('4b8513bd-17fd-46a2-b26e-0b6ba8c866e4', 21, 8, NULL, '2015-09-23 17:15:35', NULL),
	('4ef18cdd-28e2-44ea-b253-eec76d074b4d', 10, 4, NULL, '2015-09-23 17:15:35', NULL),
	('4fa1ea98-a5e8-48bd-bdaf-0c69612a385a', 24, 8, NULL, '2015-09-23 17:15:35', NULL),
	('53bf31df-778e-48ed-a20f-7abf48d85a8e', 23, 8, NULL, '2015-09-23 17:15:35', NULL),
	('75b3ce16-651f-4f7b-b5e5-7c3cd8ccb6a3', 14, 5, NULL, '2015-09-23 17:15:35', NULL),
	('77954e8f-ca8c-4420-8468-eb36f566a7e2', 3, 1, NULL, '2015-09-23 17:15:35', NULL),
	('80639f5f-108e-48b3-ace4-8d092594cc82', 2, 1, NULL, '2015-09-23 17:15:35', NULL),
	('9de2625f-91f7-4c51-85c5-576c1422fba8', 6, 2, NULL, '2015-09-23 17:15:35', NULL),
	('a39142f2-b160-407b-9137-a9f513ca2fcf', 19, 7, NULL, '2015-09-23 17:15:35', NULL),
	('a8f589f6-9a77-4669-bd8d-32d39571fc8d', 13, 4, NULL, '2015-09-23 17:15:35', NULL),
	('bcfe6f4b-e87c-4301-85b6-67443a45d1e1', 4, 1, NULL, '2015-09-23 17:15:35', NULL),
	('ccc34dab-c4d6-4ce8-82a9-94c4ac5f8dea', 18, 7, NULL, '2015-09-23 17:15:35', NULL),
	('df712269-bc1d-4636-b8be-4e298946c390', 5, 1, NULL, '2015-09-23 17:15:35', NULL),
	('e8ba558e-5f2c-4e31-8e99-48f57ac899d4', 9, 3, NULL, '2015-09-23 17:15:35', NULL),
	('ef1f82fb-1979-4956-8ee7-56ad6696369b', 15, 5, NULL, '2015-09-23 17:15:35', NULL),
	('ef4ed5fc-8785-4e0f-896f-257b4297339f', 11, 4, NULL, '2015-09-23 17:15:35', NULL),
	('f632509f-a638-4431-aacd-045263277705', 16, 6, NULL, '2015-09-23 17:15:35', NULL),
	('ff6624e2-a5ac-4554-82e2-2aa8adf6d1d2', 25, 8, NULL, '2015-09-23 17:15:35', NULL),
	('fffeb6c4-f36a-4e2f-a7cd-6bab5f16572c', 22, 8, NULL, '2015-09-23 17:15:35', NULL);
/*!40000 ALTER TABLE `ha_r_assembly` ENABLE KEYS */;


-- 导出  表 haro.ha_r_layout_info 结构
DROP TABLE IF EXISTS `ha_r_layout_info`;
CREATE TABLE IF NOT EXISTS `ha_r_layout_info` (
  `id` char(36) NOT NULL,
  `hotline` varchar(25) DEFAULT NULL COMMENT '热线电话',
  `address` varchar(100) DEFAULT NULL COMMENT '通讯地址',
  `zip_code` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `copyright` varchar(50) DEFAULT NULL COMMENT '版权信息',
  `icp_no` varchar(20) DEFAULT NULL COMMENT 'ICP',
  `publish_time` datetime DEFAULT NULL COMMENT '修改时间',
  `publish_user` char(36) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='布局元素';

-- 正在导出表  haro.ha_r_layout_info 的数据：~0 rows (大约)
DELETE FROM `ha_r_layout_info`;
/*!40000 ALTER TABLE `ha_r_layout_info` DISABLE KEYS */;
INSERT INTO `ha_r_layout_info` (`id`, `hotline`, `address`, `zip_code`, `telephone`, `copyright`, `icp_no`, `publish_time`, `publish_user`) VALUES
	('a577e4cb-6792-42a8-8f44-2ccfb6a2b84e', '456789', '辽宁省大连市135号', '116011', '222', '大连汉诺工程技术有限公司 版权所有 All rights reserved', '辽ICP备000000号', '2015-09-23 17:51:05', 'testUser');
/*!40000 ALTER TABLE `ha_r_layout_info` ENABLE KEYS */;


-- 导出  表 haro.ha_r_news 结构
DROP TABLE IF EXISTS `ha_r_news`;
CREATE TABLE IF NOT EXISTS `ha_r_news` (
  `id` char(36) NOT NULL,
  `title` varchar(100) NOT NULL COMMENT '标题',
  `author` varchar(50) NOT NULL COMMENT '作者',
  `source` varchar(50) DEFAULT NULL COMMENT '来源',
  `hot_flag` tinyint(4) NOT NULL COMMENT '热门标记',
  `top_flag` tinyint(4) NOT NULL COMMENT '置顶标记',
  `new_flag` tinyint(4) NOT NULL COMMENT '新文章标记',
  `type` tinyint(4) NOT NULL COMMENT '新闻类别',
  `summary` varchar(200) DEFAULT NULL COMMENT '概述',
  `tenant_id` char(36) NOT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` char(36) NOT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user` char(36) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻';

-- 正在导出表  haro.ha_r_news 的数据：~1 rows (大约)
DELETE FROM `ha_r_news`;
/*!40000 ALTER TABLE `ha_r_news` DISABLE KEYS */;
INSERT INTO `ha_r_news` (`id`, `title`, `author`, `source`, `hot_flag`, `top_flag`, `new_flag`, `type`, `summary`, `tenant_id`, `create_time`, `create_user`, `modify_time`, `modify_user`) VALUES
	('10830c4a-92f8-4dc2-baa4-d177380e35a6', '123', '3', NULL, 0, 0, 0, 1, '目击村民卢先生回忆，8月30日凌晨3点50分左右，他正在睡觉，突然听见“轰隆”一声巨响，震得窗户直抖，其以为是外面打雷，“出去之后看了下，满天星星，一回头看见房顶上一米高左右有个火球，（直径）大概四十厘米...', 'testTenantId', '2015-09-18 15:45:15', 'testCreateUser', NULL, NULL),
	('37c96424-7f1c-49aa-9a0c-6223785726c6', '234', '3', NULL, 0, 0, 0, 1, '4', 'testTenantId', '2015-09-18 15:46:45', 'testCreateUser', NULL, NULL);

DROP TABLE IF EXISTS `ha_r_user`;
CREATE TABLE IF NOT EXISTS `ha_r_user` (
 id char(36) not null,
 username varchar(100) not null comment '用户名',
 password varchar(50) not null comment '密码',
 tenant_id char(36) not null comment '租户ID',
 create_time datetime not null comment '创建时间',
 create_user char(36) not null comment '创建人',
 modify_time datetime null comment '修改时间',
 modify_user char(36) null comment '修改人',
 constraint `PRIMARY`
  primary key (id)
);

INSERT INTO ha_r_user (id, username, password, tenant_id, create_time, create_user, modify_time, modify_user) VALUES ('1', 'admin', '123456', '1', '2017-08-15 22:20:59', '1', null, null);

/*!40000 ALTER TABLE `ha_r_news` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
