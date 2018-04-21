# Haro-CMS
企业信息展示，分为3个module

1. **admin** 管理人员编辑展示内容
2. **view** 展示企业信息及新闻
3. **core** 业务数据层

![haro_design.png](https://raw.githubusercontent.com/wangyuheng/haro/dev/doc/haro_design.png)

### 历史
原项目为传统spring+jsp项目开发，基于web.xml配置。

### 当前现状
改造为spring boot项目，新版spring boot已不推荐使用jsp，带来很多不便，如： 不能直接运行java， 需要使用maven spring boot 插件运行。

```shell
mvn spring-boot:run
```

admin 和 view 只负责业务渲染与鉴权，业务操作放在core中，方便后期进行前后端分离。
生产环境数据库使用mysql，为了方便演示，开发环境使用H2内嵌数据库。

#### admin 

![haro_admin](https://raw.githubusercontent.com/wangyuheng/haro/dev/doc/haro_admin.png)

#### view

![haro_view](https://raw.githubusercontent.com/wangyuheng/haro/dev/doc/haro_view.png)