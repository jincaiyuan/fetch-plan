CREATE TABLE `plan_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(50) NOT NULL,
  `submit_time` timestamp NOT NULL,
  `real_submit_time` timestamp NOT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `type` tinyint NOT NULL,
  `cron` varchar(30) DEFAULT NULL,
  `plan_status` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;