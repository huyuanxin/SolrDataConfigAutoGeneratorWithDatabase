# SolrDataConfigAutoGenerator

## 项目介绍

这个项目的目的是为了自动生成apache solr搜索系统的data-config.xml文件，方便进行管理，不需要自己手写大量的entity

## 项目技术栈

SpringBoot version 2.3.4

Thymeleaf  version 3.0.11.RELEASE

Mysql version 5.7

Oracle version 11.2

## 项目特点

* 使用 thymeleaf 模板引擎生成性能较高，经过测试，1502个数据库生成共计20万行的配置文件耗时一分钟左右。
* 可以自定义选择不生成的数据库，表，列。
* 支持Mysql、Oracle（正在完善）

