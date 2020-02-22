##社区交流
https://github.com/HuangTangWu/comunity

##工具
git

##sql脚本
```sql
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gmt_create` bigint(255) DEFAULT NULL,
  `gmt_modify` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

create table comment
(
	id bigint auto_increment,
	parent_id bigint null,
	type int null,
	commentator int null,
	gmt_create bigint null,
	gmt_modify bigint null,
	like_count bigint default 0 null,
	content varchar(1024) null,
	constraint comment_pk
		primary key (id)
);


```
