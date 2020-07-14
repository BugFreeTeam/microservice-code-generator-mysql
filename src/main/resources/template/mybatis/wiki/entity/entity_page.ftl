[Home](/Home) / [${tableComment}](/${tableName}/${tableName})
### 根据条件分页查询${tableComment}

---

#### 1.基本信息
* 请求类型：***HTTP***
* 请求方式：***POST***
* 请求地址：***${requestUrl}/page***
* 数据类型：***application/json***
* 响应类型：***JSON***

#### 2.请求参数
<#assign num = 0 />
|序号|参数名称|参数类型|长度|可否为空|查询类型|描述|默认值|示例|
|:--:|:------:|:------:|:------:|:--:|:------:|----|:----:|----|
<#list columnList as column>
<#if column.feildType == "String" && column.characterMaximumLength gt 50>
| <#assign num = num + 1 />${num } | ${column.feildName} | ${column.feildType} | <#if column.feildType == "String">${column.characterMaximumLength}</#if> | YES | `like` | ${column.columnComment} |  |  |
<#elseif column.feildType == "Date">
| <#assign num = num + 1 />${num } | ${column.feildName} | ${column.feildType} |  | YES | `=` | ${column.columnComment} |  | <#if column.columnType?lower_case == "date">`yyyy-MM-dd`<#elseif column.columnType?lower_case == "timestamp">`yyyy-MM-dd HH:mm:ss`</#if> |
| <#assign num = num + 1 />${num } | ${column.feildName}Start | ${column.feildType} |  | YES | `>=` | ${column.columnComment}-起始时间 |  | <#if column.columnType?lower_case == "date">`yyyy-MM-dd`<#elseif column.columnType?lower_case == "timestamp">`yyyy-MM-dd HH:mm:ss`</#if> |
| <#assign num = num + 1 />${num } | ${column.feildName}End | ${column.feildType} |  | YES | `<=` | ${column.columnComment}-结束时间 |  | <#if column.columnType?lower_case == "date">`yyyy-MM-dd`<#elseif column.columnType?lower_case == "timestamp">`yyyy-MM-dd HH:mm:ss`</#if> |
<#else>
| <#assign num = num + 1 />${num } | ${column.feildName} | ${column.feildType} | <#if column.feildType == "String">${column.characterMaximumLength}</#if> | YES | `=` | ${column.columnComment} |  |  |
</#if>
</#list>
| <#assign num = num + 1 />${num } | page | Integer | | YES |  |页码 | 1 |  |
| <#assign num = num + 1 />${num } | size | Integer | | YES |  |每页显示大小 | 20 |  |
| <#assign num = num + 1 />${num } | order | String | | YES |  |排序（多个字段排序以“,”分隔） | id desc |  |

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
{
    "page":"1",
    "size":"20",
    "order":"id desc"
}
```
#### 5.返回示例
* 正确返回示例

```json
{
    "status": "1",
    "result": "1",
    "data": {
        "content": [
            {
            <#list columnList as column>
                "${column.feildName}":""<#if column_has_next>,</#if>
            </#list>
            },
            ......
        ],
        "totalPages": 2,
        "totalElements": 22,
        "last": true,
        "first": false,
        "size": 20,
        "number": 1
    },
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
