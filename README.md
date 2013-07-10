[![Build Status](https://travis-ci.org/ericzhang-cn/rss2epub.png)](https://travis-ci.org/ericzhang-cn/rss2epub)

将RSS内容转为epub格式电子书的工具

# 使用方法
+ rss2epub使用[maven](http://maven.apache.org)构建，请先安装maven。
+ 在rss2pub目录下运行

  ```bash
  mvn package
  ```

  完成项目的编译，此时编译好的rss2epub会放在target目录下，而相关依赖会被自动拷贝到target/lib下
+ 工具的使用方法为

  ```bash
  java -cp 依赖jar包 org.codinglabs.rss2epub.RssToEpub 配置文件 输出文件
  ```

  例如在rss2epub目录下执行

  Linux&Mac：

  ```bash
  java -cp target/lib/*:target/rss2epub-0.0.1-SNAPSHOT.jar org.codinglabs.rss2epub.RssToEpub book.yml book.epub
  ```

  Windows：

  ```bash
  java -cp target/lib/*;target/rss2epub-0.0.1-SNAPSHOT.jar org.codinglabs.rss2epub.RssToEpub book.yml book.epub
  ```

  会在当前目录生成一本名叫book.epub的电子书。

# 配置文件
rss2epub的配置文件使用[YAML](http://www.yaml.org)格式。配置文件结构如下

```bash
title: 电子书标题
author: 电子书作者
image: 是否抓取图片
feeds:
    - Feed源URL
    - Feed源URL
    ...
    - Feed源URL
```

具体可以参考项目根目录下的book.yml。

# 功能特点
+ 轻量级，易于使用。
+ 自动处理图片。
+ 自动生成索引。
+ 纯命令行工具，不依赖其它电子书管理工具，方便与其它脚本配合使用。

# 依赖项目
+ [Log4j](http://logging.apache.org/log4j/1.2/) - 日志库
+ [SnakeYAML](https://code.google.com/p/snakeyaml/) - 用于解析YAML文件
+ [Rome](https://rometools.jira.com/wiki/display/ROME/Home) - 用于读取和解析RSS源
+ [Epublib](http://www.siegmann.nl/epublib) - 用于生成epub格式电子书

Maven会自动处理这些依赖，因此你在编译和运行程序时不需显式关心依赖项目。
此处列出仅表示对这些项目的感谢。
