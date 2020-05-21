CBoard
============
[![JDK](https://img.shields.io/badge/JDK-1.8+-blue.svg)](https://www.oracle.com/technetwork/java/javase/overview/index.html)
[![SpringBoot](https://img.shields.io/badge/SpringBoot-2.1.x-green.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

[中文](Readme_zh.md)  

CBoard is not only a analysis platform that supports interactive multi-dimensional report design and data analysis for user, but also a BI product development platform for developer.
Powered by [Shanhai Chuguo Information and Technology Co., Ltd.](http://www.chuguotech.com/)
- **Normal User** can analysis your data or design a report by simple drag and drop operation
- **Developer** can extend any type of your data datasource that you can connect by Java program

<div style="text-align:center">
  <img class="img-responsive" src="https://user-images.githubusercontent.com/6037522/42865027-ef625120-8a9a-11e8-9982-50630902d263.png"  />
</div>

## Who is using CBoard
If you wish your company's logo show on our home page, please [sign in here](https://github.com/TuiQiao/CBoard/issues/122) provide a logo pic with transparent background
<div style="text-align:center">
  <img class="img-responsive" src="https://user-images.githubusercontent.com/6037522/42865039-fd217ad4-8a9a-11e8-9762-d6ded70dc843.png"  />
</div>

## Architecture
Light weight architecture, common technology stack, **self designed multi-dimension engine**, clear optimization rule, small application running on your big data stack!
<div style="text-align:center">
  <img class="img-responsive" src="https://user-images.githubusercontent.com/6037522/42865071-0c268402-8a9b-11e8-81b3-53fe8020bb17.png"  />
</div>


## Features

* Simple and beautiful interface and layout
* Interactive, drag-and-drop OLAP classisc report development experience
* One dataset, multiple report widgets. Maximize reuse query result
* Cube level data refresh / realtime chart for quick query
* Role Based Access Control
* More than 20 chart types and dashboard insight or email report
* Multiple types data source connection
  * Support JDBC data connection (Almost all kinds database that has implemented JDBC protocal)
  * Support Native Elasticsearch connection for version 1.x, 2.x, 5.x
  * Support Native Kylin connection for version 1.6
* Lightweight architecture and concise source code, the entire project does not rely on any third-party multi-dimensional analysis tools, if you are struggling in Mondrain, CBoard could be a nice alternative for you.
* Easy to implement your own DataProvider to connect any data source. Even for expensive commercial BI suite, it's not possible to connect all the NOSQL and NewSQL data source in the era of big data. Due to the schema of NOSQL are various, such as hash tables, arrays, trees, maps, etc., different people using the same NoSQL products designed Schema may vary widely. The user who most familiar with their storage schema are the user themselves. And it's easy to find a Java programmers to code a Class to connect their own data source. So we leave this part of work to the end user with an easy extended data connection framework in CBoard

### Community Edition V.S. Enterprise Edition

Features | Community Edition | Enterprise Edition
---|:---:|:---:
Multiple kinds of data source plugins | :white_check_mark: | :white_check_mark: 
Drag-and-drop self-service multidimensional analysis |:white_check_mark: | :white_check_mark: 
More than 20 kinds of chart type |:white_check_mark: | :white_check_mark: + 明细表 + GIS中国地图
Dashboard |:white_check_mark: | :white_check_mark: 
Near-realtime data refresh |:white_check_mark: | :white_check_mark: 
Dashboard Layout | Layout by row and column, view and design are seperated | Free layout and live preview
Dashboard parameter | Common filter | Add date range filter, checkbox, searchable dropdown selector filter, keyword input filter
Cockpit Dashboard | :x: | :white_check_mark: 
Chart link | Design for developers, simple support based on raw data columns |Design for data analyst, complete chart linkage mechanism to support linkage to datasets, billboards
Inline chart | :x: All charts must first be saved and managed in the **Widget Config** design area. | Support insert inline chart in dashboard.
Send report email |:white_check_mark: | :white_check_mark: 
Regroup dimension members | :x: | :white_check_mark: 
Cusomize sort dimension members  | :x: | :white_check_mark: 
Chart tunning | Simple supported | More powerful and easy to use
Detail data table | :x: | :white_check_mark: 
GIS Map chart on detail data | :x: | :white_check_mark: 
Data Security | Chart level Control | :star::star: Data cell level control and support role based data access control
Folder based resource managment system | :x: Only support virtual path based folder | OS likly file system solution. ACL can be managed by folder
Advantage Cross Table | :x: | :star::star: Supports advanced calculations such as year-on-year, aspect ratio, percentage, totals, subtotals, cell conditional styles 
Dashboard iframe integeration  | :x: | Iframe external system integration with parameter control 
SDK integeration Support | :x: | :white_check_mark: 
Front-end Technology Stack | AngularJS | VueJS + ES6 + Webpack
Professional Technology Support | :x: | :white_check_mark: Escort your production environment
Road Map | :x: Maintenance-oriented | Customer-oriented Road map 


> More enterprise features please access our homepage: [上海楚果信息技术有限公司](http://www.chuguotech.com/)


## Issues

If you like our product, you can start from our community version. With the support of commercialization of products, we will do our best to maintain the stability of the community version.
In future, without affecting the company's business, it will gradually open more basic development infrastructure.
Any bugs or question please feel free to post at Github[Issue system](https://github.com/TuiQiao/CBoard/issues)

> To create a new issue, follow these steps [click](.github/ISSUE_TEMPLATE/ISSUE_TEMPLATE.md)  
  To create a new pull request, follow these steps [click](.github/ISSUE_TEMPLATE/PULL_REQUEST_TEMPLATE.md)

## :books: More Document
- [:cn: 帮助文档](http://peter_zhang921.gitee.io/cboard_docsify/#/zh-cn/)
- [:uk: Document](https://tuiqiao.github.io/CBoardDoc/#/en-us/)


## Gitter 
[![Gitter](https://badges.gitter.im/tuiqiao_cboard/community.svg)](https://gitter.im/tuiqiao_cboard/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)