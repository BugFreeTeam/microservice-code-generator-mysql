[Home](/Home) / [${tableComment}](/${tableName}/${tableName})
### 批量新增${tableComment}

---

#### 1.基本信息
* 请求类型：***HTTP***
* 请求方式：***POST***
* 请求地址：***${requestUrl}/batch***
* 数据类型：***application/json***
* 响应类型：***JSON***

#### 2.请求参数
<#assign defaultValue = "" />
|序号|参数名称|参数类型|长度|可否为空|描述|默认值|
|:--:|:------:|:------:|:------:|:--:|----|:----:|
<#list columnList as column>
<#if column.feildName == "recordPerson">
    <#assign defaultValue = "当前用户" />
<#elseif column.feildName == "recordTime">
    <#assign defaultValue = "当前时间" />
<#elseif column.feildName == "recordDepart">
    <#assign defaultValue = "当前用户部门" />
<#else>
    <#assign defaultValue = "${column.columnDefault}" />
</#if>
<#if column.feildName != "id">
| ${column_index} | ${column.feildName} | ${column.feildType} | <#if column.feildType == "String">${column.characterMaximumLength}</#if> | ${column.isNullable} | ${column.columnComment} | ${defaultValue} |
</#if>
</#list>

#### 3.响应参数
|序号|参数名称|描述|
|:--:|:------:|:------|
|1   |status  |登录状态，0未登录，1正常，2未授权    |
|2   |result  |操作结果，0失败，1成功      |
|3   |data    |返回数据JSON格式     |
|4   |code    |消息编码      |
|5   |message |消息内容      |

#### 4.请求示例
```json
[
    {
    <#list columnList as column>
        <#if column.feildName != "id">
        "${column.feildName}":""<#if column_has_next>,</#if>
        </#if>
    </#list>
    },
    ......
]
```
#### 5.返回示例
* 正确返回示例

```json
{
    "status": "1",
    "result": "1",
    "data": [
        {
        <#list columnList as column>
            "${column.feildName}":""<#if column_has_next>,</#if>
        </#list>
        },
        ......
    ],
    "code": "000000",
    "message": "操作成功"
}
```
* 错误返回示例

```json
{
    "status":"1",
    "result":"0",
    "data":"",
    "code":"000001",
    "message":"操作失败"
}
```
