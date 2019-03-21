SET FOREIGN_KEY_CHECKS=0;

drop table if exists f_api;
CREATE TABLE `f_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `apiName` varchar(255) DEFAULT NULL COMMENT '名称',
  `apiHost` varchar(255) DEFAULT NULL COMMENT '请求环境',
  `apiUrl` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `apiMethod` varchar(255) DEFAULT NULL COMMENT '请求方法(get/post)',
  `apiHeaders` text COMMENT '请求头',
  `apiParams` text COMMENT '请求参数',
  `apiStatus` int(11) DEFAULT '0' COMMENT '状态(0:启用、1暂停)',
  `remark` text COMMENT '备注',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`apiName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='接口表';




drop table if exists f_api_case;
CREATE TABLE `f_api_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dependCaseId` int(11) DEFAULT NULL COMMENT '前置用例ID',
  `caseName` varchar(255) NOT NULL COMMENT '用例名称',
  `remark` text COMMENT '测试用例描述',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用例新表';

drop table if exists f_api_case_relattion;
CREATE TABLE `f_api_case_relattion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caseId` int(11) DEFAULT NULL COMMENT '用例ID',
  `APIId` int(11) DEFAULT NULL COMMENT '接口ID',
  `caseData` text COMMENT '请求数据',
  `caseAssert` text COMMENT '断言',
  `caseParam` text COMMENT '保存参数',
  `createTime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `updateTime` varchar(255) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `APIId` (`APIId`),
  CONSTRAINT `APIId` FOREIGN KEY (`APIId`) REFERENCES `f_api` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用例与API接口关系表';

drop table if exists f_api_result;
CREATE TABLE `f_api_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `caseId` int(11) NOT NULL COMMENT '用例ID',
  `APIId` int(11) DEFAULT NULL COMMENT '接口ID',
  `setId` int(11) NOT NULL DEFAULT '0' COMMENT '用例集ID(默认0)',
  `startTime` varchar(255) DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `costTime` bigint(11) DEFAULT NULL COMMENT '花费时间',
  `request` text COMMENT '请求内容',
  `statusCode` int(11) DEFAULT NULL COMMENT '状态码',
  `response` text COMMENT '响应结果',
  `batch` bigint(20) DEFAULT NULL COMMENT '运行批次',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `caseid` (`caseId`),
  KEY `setId` (`setId`),
  KEY `APIId` (`APIId`),
  KEY `batch` (`batch`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COMMENT='执行用例结果表';




drop table if exists f_case_result;
CREATE TABLE `f_case_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `caseId` int(11) NOT NULL COMMENT '用例ID',
  `setId` int(11) NOT NULL DEFAULT '0' COMMENT '用例集ID(默认0)',
  `startTime` varchar(255) DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `costTime` bigint(11) DEFAULT NULL COMMENT '花费时间',
  `resultStatus` varchar(20) DEFAULT NULL COMMENT '用例状态:成功(SUCCESS)/失败(FAILURE)/跳过(SKIP)',
  `runEnv` varchar(255) DEFAULT NULL COMMENT '运行环境',
  `batch` bigint(20) DEFAULT NULL COMMENT '执行批次',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `caseid` (`caseId`),
  KEY `setId` (`setId`),
  KEY `batch` (`batch`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='执行用例结果表';



drop table if exists f_case_set;
CREATE TABLE `f_case_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `caseSetName` varchar(255) DEFAULT NULL COMMENT '用例集名称',
  `caseRelation` varchar(500) NOT NULL COMMENT '关联用例',
  `caseCount` int(11) DEFAULT NULL COMMENT '用例数',
  `remark` text COMMENT '备注',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `caseSetName` (`caseSetName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用例集';




drop table if exists f_env_list;
CREATE TABLE `f_env_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `envTitle` varchar(255) DEFAULT NULL COMMENT '环境标题',
  `envDomain` varchar(255) DEFAULT NULL COMMENT '域名(例如：www.xiaoniu88.com)',
  `envIp` varchar(255) DEFAULT NULL COMMENT 'IP地址(例如:172.20.20.211)',
  `createTime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `envTitle` (`envTitle`) USING BTREE,
  KEY `envDomain` (`envDomain`) USING BTREE,
  KEY `envIp` (`envIp`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



drop table if exists f_set_result;
CREATE TABLE `f_set_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `setId` int(11) NOT NULL COMMENT '集合ID',
  `startTime` varchar(255) DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `costTime` bigint(20) DEFAULT NULL COMMENT '消耗时间',
  `runEnv` varchar(255) DEFAULT NULL COMMENT '运行环境',
  `batch` bigint(20) DEFAULT NULL COMMENT '执行批次',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `setId` (`setId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试集结果表';




drop table if exists f_user;
CREATE TABLE `f_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `user` varchar(255) DEFAULT NULL COMMENT '用户名',
  `userPwd` varchar(255) DEFAULT '123456' COMMENT '密码',
  `userName` varchar(255) DEFAULT NULL COMMENT '姓名',
  `userIp` varchar(255) DEFAULT NULL COMMENT '用户IP',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `creatTime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';




drop table if exists f_var;
CREATE TABLE `f_var` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `varName` varchar(255) DEFAULT NULL COMMENT '变量名',
  `varValue` varchar(255) DEFAULT NULL COMMENT '变量值',
  `varType` int(11) DEFAULT NULL COMMENT '变量类型(1:固定变量、2:函数变量)',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `varName` (`varName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='存储变量表';



drop table if exists f_var_temp;
CREATE TABLE `f_var_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `varName` varchar(255) DEFAULT NULL COMMENT '变量名',
  `varRule` varchar(255) DEFAULT NULL COMMENT '变量规则(JSON/Regular/Random)',
  `varValue` varchar(255) DEFAULT NULL COMMENT '变量值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `varName` (`varName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='临时变量表';




SET FOREIGN_KEY_CHECKS=1;