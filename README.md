# jarvis-framework-tool
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/a466350665/smart/pulls)
[![GitHub stars](https://img.shields.io/github/stars/a466350665/smart.svg?style=social&label=Stars)](https://github.com/a466350665/smart)
[![GitHub forks](https://img.shields.io/github/forks/a466350665/smart.svg?style=social&label=Fork)](https://github.com/a466350665/smart)

QQ：773995514(提供开发工具和文档下载)

## 简述
    jarvis-framework-tool定位于用当下最流行的技术，为您构建一个易理解、高可用、高扩展性的应用基层，实现快速开发。内置Java基础工具类、Dubbo服务治理、单点登录权限系统(按钮级，权限修改实时生效)、支持分布式的定时任务服务及代码生成器、易用高兼容的bootstrap前端Html模板。

## 组织结构

``` lua
jarvis-framework-tool
├── jarvis-tool-core -- 核心工具类库
├── jarvis-tool-ctypto -- 秘钥算法工具类库
├── jarvis-tool-email -- 邮件工具类库
├── jarvis-tool-extra -- 三方平台工具类库
├── jarvis-tool-http -- HTTP工具类库
├── jarvis-tool-poi -- excel、word工具类库
├── jarvis-tool-redis -- 缓存工具类库


├── smart-demo -- 简单的单点登录Dubbo服务化案例
|    ├── smart-demo-api -- demo远程调用API
|    ├── smart-demo-server -- demo服务化provider
|    ├── smart-demo-web -- demo服务化consumer(单点登录权限拦截器)

```

## 技术选型

### 后端
- JDK：1.8（支持1.7+）
- 项目构建工具：Maven 3.3.3
- MVC框架：SpringMVC 4.2.1.RELEASE
- 核心框架：Spring 4.2.1.RELEASE
- JSON工具：Fastjson 1.2.29
- 定时任务：Quartz 2.2.1
- 数据库连接池：Druid 1.0.15
- 日志管理：SLF4J 1.7.21、Logback 1.1.7

### 前端
- 暂无

### 浏览器兼容
- 暂无

## 数据库模型
![数据库模型](http://img.blog.csdn.net/20170228162027225?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 效果展示

### 代码生成器
![代码生成器](http://img.blog.csdn.net/20170228171253734?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170118203537172?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### Dubbo监控页
![](http://img.blog.csdn.net/20170119151157271?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 单点登录页
![](http://img.blog.csdn.net/20170106172009071?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 权限管理页
![](http://img.blog.csdn.net/20170106172032962?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172050728?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172102416?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 手机浏览器展示
![](http://img.blog.csdn.net/20170106172646403?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172905092?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172915803?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172926694?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
