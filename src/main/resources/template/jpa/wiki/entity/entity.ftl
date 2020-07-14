[Home](/Home)
#### ${tableComment}接口文档

#### 接口信息列表
<#--1. [根据主键查询${tableComment}](/${tableName}/${tableName}_get) **GET** ${requestUrl}/{id}
2. [根据主键集合批量查询${tableComment}](/${tableName}/${tableName}_ids) **POST** ${requestUrl}/ids
3. [根据条件判断${tableComment}是否存在](/${tableName}/${tableName}_exists) **POST** ${requestUrl}/exists
4. [根据条件查询${tableComment}列表](/${tableName}/${tableName}_list) **POST** ${requestUrl}/list
5. [根据条件分页查询${tableComment}](/${tableName}/${tableName}_page) **POST** ${requestUrl}/page
6. [新增${tableComment}](/${tableName}/${tableName}_add) **POST** ${requestUrl}
7. [更新${tableComment}](/${tableName}/${tableName}_update) **PUT** ${requestUrl}/{id}
8. [逻辑删除${tableComment}](/${tableName}/${tableName}_cancel) **POST** ${requestUrl}/cancel
9. [物理删除${tableComment}](/${tableName}/${tableName}_delete) **DELETE** ${requestUrl}/{id}
10. [批量新增${tableComment}](/${tableName}/${tableName}_batch_add) **POST** ${requestUrl}/batch
11. [批量更新${tableComment}](/${tableName}/${tableName}_batch_update) **PUT** ${requestUrl}/batch
12. [批量逻辑删除${tableComment}](/${tableName}/${tableName}_batch_cancel) **POST** ${requestUrl}/batch/cancel
13. [批量物理删除${tableComment}](/${tableName}/${tableName}_batch_delete) **POST** ${requestUrl}/batch/delete-->

| 序号 | 接口名称 | 请求方式 | 请求地址 |
|:--:|:-------|:------:|:-------|
| 1 |[根据主键查询${tableComment}](/${tableName}/${tableName}_get) | **GET** | ${requestUrl}/{id} |
| 2 |[根据主键集合批量查询${tableComment}](/${tableName}/${tableName}_ids) | **POST** | ${requestUrl}/ids |
| 3 |[根据条件判断${tableComment}是否存在](/${tableName}/${tableName}_exists) | **POST** | ${requestUrl}/exists |
| 4 |[根据条件查询${tableComment}列表](/${tableName}/${tableName}_list) | **POST** | ${requestUrl}/list |
| 5 |[根据条件分页查询${tableComment}](/${tableName}/${tableName}_page) | **POST** | ${requestUrl}/page |
| 6 |[新增${tableComment}](/${tableName}/${tableName}_add) | **POST** | ${requestUrl} |
| 7 |[更新${tableComment}](/${tableName}/${tableName}_update) | **PUT** | ${requestUrl}/{id} |
| 8 |[逻辑删除${tableComment}](/${tableName}/${tableName}_cancel) | **PUT** | ${requestUrl}/cancel/{id} |
| 9 |[物理删除${tableComment}](/${tableName}/${tableName}_delete) | **DELETE** | ${requestUrl}/{id} |
| 10 |[批量新增${tableComment}](/${tableName}/${tableName}_batch_add) | **POST** | ${requestUrl}/batch |
| 11 |[批量更新${tableComment}](/${tableName}/${tableName}_batch_update) | **PUT** | ${requestUrl}/batch |
| 12 |[批量逻辑删除${tableComment}](/${tableName}/${tableName}_batch_cancel) | **PUT** | ${requestUrl}/batch/cancel |
| 13 |[批量物理删除${tableComment}](/${tableName}/${tableName}_batch_delete) | **POST** | ${requestUrl}/batch/delete |

#### 实体信息
|序号|字段名称|字段类型|数据长度|字段描述|示例|
|:--:|:------:|:------:|:------:|:-------|----|
<#list columnList as column>
| ${column_index+1} | ${column.feildName} | ${column.feildType} | <#if column.feildType == "String">${column.characterMaximumLength}</#if> | ${column.columnComment} | <#if column.columnType?lower_case == "date">`yyyy-MM-dd`<#elseif column.columnType?lower_case == "timestamp">`yyyy-MM-dd HH:mm:ss`</#if> |
</#list>
