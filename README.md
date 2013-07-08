将RSS内容转为epub格式电子书的工具

# 使用方法
+ rss2epub使用[maven](http://maven.apache.org)构建，请先安装maven。
+ 在rss2pub目录下运行
  ```bash
  mvn package
  ```
  完成项目的编译，此时编译好的rss2epub会放在target目录下，而相关
  依赖会被自动拷贝到target/lib下
+ 工具的使用方法为
  ```bash
  java -cp 依赖jar包 org.codinglabs.rss2epub.RssToEpub 配置文件 输出文件
  ```
  例如在rss2epub目录下执行
  ```bash
  java -cp target/lib/*:target/rss2epub-0.0.1-SNAPSHOT.jar org.codinglabs.rss2epub.RssToEpub book.yml book.epub
  ```
  会在当前目录生成一本名叫book.epub的电子书。

# 配置文件
rss2epub的配置文件使用[YAML](http://www.yaml.org)格式。配置文件结构如下

```bash
title: 电子书标题
author: 电子书作者
feeds:
    - Feed源URL
    - Feed源URL
    ...
    - Feed源URL
```

具体可以参考项目根目录下的book.yml。

# 功能
+ 支持图片
+ 自动生成索引
+ 纯命令行工具，不依赖其它电子书管理工具，方便与其它脚本配合使用

# 依赖
