## 鼠鼠论坛

## 资料
[Spring 文档](https://spring.io/guides)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[BootStrap](https://v3.bootcss.com/getting-started/)  
[es](https://elasticsearch.cn/explore)  
[H2](https://www.h2database.com/html/main.html)  
[Github OAuth](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)  
[Mybatis Plus](https://baomidou.com/)  
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#what-is-thymeleaf)
## 工具
[Git](https://git-scm.com/download/)  
[Visual Paradigm](https://www.visual-paradigm.com/cn/)

## 脚本
~~~sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID\r\n',
  `account_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  `token` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'cookie信息',
  `gmt_create` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NULL DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;
SET FOREIGN_KEY_CHECKS = 1;
~~~~