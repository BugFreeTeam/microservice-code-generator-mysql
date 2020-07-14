package ${packagePath}.model;

<#list columnList as column>
    <#if column.feildType == "Date">
import java.util.Date;
        <#break>
    </#if>
</#list>

import com.anjuxing.platform.common.base.User;
import com.anjuxing.platform.common.base.ValidateData;
import com.anjuxing.platform.common.crud.CrudModel;
import com.anjuxing.platform.common.util.CodeUtils;
import com.anjuxing.platform.common.util.DateUtils;
import com.anjuxing.platform.common.util.ValidateUtils;
<#list columnList as column>
    <#if column.feildType == "Date">
import com.fasterxml.jackson.annotation.JsonFormat;
        <#break>
    </#if>
</#list>
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ${tableComment}
 */
public class ${entityName} extends CrudModel<${entityName}> {

    private static final long serialVersionUID = 1L;

<#list columnList as column>
    private ${column.feildType} ${column.feildName}; // ${column.columnComment}
</#list>

    /************************************************自定义字段***********************************************/
<#list columnList as column>
    <#if column.feildType == "Date">
    private ${column.feildType} ${column.feildName}Start; // ${column.columnComment}-起始时间
    private ${column.feildType} ${column.feildName}End; // ${column.columnComment}-结束时间
    </#if>
</#list>

    public ${entityName}() {

    }

<#list columnList as column>
    /**
     * 获取${column.columnComment}
     * @return ${column.feildType}
     */
    <#if column.columnType?lower_case == "date">
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    <#elseif column.columnType?lower_case == "timestamp">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    public ${column.feildType} get${column.feildNameUpper}() {
        return ${column.feildName};
    }

    /**
     * 设置${column.columnComment}
     * @param ${column.feildName}
     */
    public void set${column.feildNameUpper}(${column.feildType} ${column.feildName}) {
        this.${column.feildName} = ${column.feildName};
    }

</#list>
<#list columnList as column>
<#if column.feildType == "Date">
    /**
     * 获取${column.columnComment}-起始时间
     * @return ${column.feildType}
     */
    @JsonIgnore
    public ${column.feildType} get${column.feildNameUpper}Start() {
        return ${column.feildName}Start;
    }

    /**
     * 设置${column.columnComment}-起始时间
     * @param ${column.feildName}Start
     */
    @JsonProperty(value="${column.feildName}Start")
    <#if column.columnType?lower_case == "date">
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    <#elseif column.columnType?lower_case == "timestamp">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    public void set${column.feildNameUpper}Start(${column.feildType} ${column.feildName}Start) {
        this.${column.feildName}Start = ${column.feildName}Start;
    }

    /**
     * 获取${column.columnComment}-结束时间
     * @return ${column.feildType}
     */
    @JsonIgnore
    public ${column.feildType} get${column.feildNameUpper}End() {
        return ${column.feildName}End;
    }

    /**
     * 设置${column.columnComment}-结束时间
     * @param ${column.feildName}End
     */
    @JsonProperty(value="${column.feildName}End")
    <#if column.columnType?lower_case == "date">
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    <#elseif column.columnType?lower_case == "timestamp">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    public void set${column.feildNameUpper}End(${column.feildType} ${column.feildName}End) {
        this.${column.feildName}End = ${column.feildName}End;
    }

    </#if>
</#list>
    /********************************************数据校验处理操作***********************************************/

    /**
     * 数据校验，插入数据之前执行，需要手动调用
     * @return
     */
    @Override
    public ValidateData validate() {
        ValidateData valid = new ValidateData();
        StringBuffer msg = new StringBuffer();
        boolean status = true;
<#list columnList as column>
    <#if column.isNullable == "NO">
        if (ValidateUtils.isEmpty(this.${column.feildName})) {
            status = false;
            msg.append("${column.columnComment}不能为空！\n");
        }
    </#if>
    <#if column.feildType == "String">
        if (ValidateUtils.length(this.${column.feildName}) > ${column.characterMaximumLength}) {
            status = false;
            msg.append("${column.columnComment}最大长度为${column.characterMaximumLength}字符!\n");
        }
    </#if>
</#list>
        valid.setStatus(status);
        valid.setMessage(msg.toString());
        return valid;
    }

    /**
     * 插入数据之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(User user) {
        if (ValidateUtils.isEmpty(this.id)) {
            this.id = CodeUtils.getUUID();
        }
<#list columnList as column>
    <#if column.feildName == "status">
        this.status = STATUS_VALID;
    </#if>
    <#if column.feildName == "flag">
        this.flag = FLAG_NORMAL;
    </#if>
    <#if column.feildName == "sortno">
        if (ValidateUtils.isEmpty(this.sortno)) {
            this.sortno = 0;
        }
    </#if>
    <#if column.feildName == "recordTime">
        this.recordTime = DateUtils.getCurrentTime();
    </#if>
</#list>
        if (user != null) {
<#list columnList as column>
    <#if column.feildName == "recordPerson">
            this.recordPerson = user.getId();
    </#if>
    <#if column.feildName == "recordDepart">
            this.recordDepart = user.getDepart();
    </#if>
</#list>
        }
    }

    /**
     * 更新数据之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(User user) {
        if (user != null) {
    <#list columnList as column>
        <#if column.feildName == "recordPerson">
            this.recordPerson = user.getId();
        </#if>
        <#if column.feildName == "recordDepart">
            this.recordDepart = user.getDepart();
        </#if>
        <#if column.feildName == "recordTime">
            this.recordTime = DateUtils.getCurrentTime();
        </#if>
    </#list>
        }
    }

    /**
     * 逻辑删除数据之前执行方法，需要手动调用
     * @param user
     */
    @Override
    public void preCancel(User user) {
        if (user != null) {
    <#list columnList as column>
        <#if column.feildName == "recordPerson">
            this.recordPerson = user.getId();
        </#if>
        <#if column.feildName == "recordDepart">
            this.recordDepart = user.getDepart();
        </#if>
        <#if column.feildName == "recordTime">
            this.recordTime = DateUtils.getCurrentTime();
        </#if>
    </#list>
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName());
        <#list columnList as column>
        sb.append("; ${column.feildName}=" + (${column.feildName} == null ? "null" : ${column.feildName}.toString()));
        </#list>
        return sb.toString();
    }

}