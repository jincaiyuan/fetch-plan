CREATE TABLE `script_detail` (
  `id` int NOT NULL,
  `plan_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '脚本名',
  `plan_path` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '脚本存放路径',
  `biz` int NOT NULL COMMENT '类型',
  `status` int NOT NULL COMMENT '状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modif_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `submit_time` timestamp NULL DEFAULT NULL COMMENT '提交时间',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='脚本信息';