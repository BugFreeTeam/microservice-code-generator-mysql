package ${packagePath}.domain;

import java.io.Serializable;
<#list columnList as column>
    <#if column.feildType == "Date">
import java.util.Date;
        <#break>
    </#if>
</#list>

import com.anjuxing.platform.common.base.BaseDomain;
<#list columnList as column>
    <#if column.feildType == "Date">
import com.fasterxml.jackson.annotation.JsonFormat;
        <#break>
    </#if>
</#list>

/**
 * ${tableComment}
 */
public class ${domainName} extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

<#list columnList as column>
    private ${column.feildType} ${column.feildName}; // ${column.columnComment}
</#list>

    /**=======================================自定义字段============================================*/
<#list columnList as column>
    <#if column.feildType == "Date">
    private ${column.feildType} ${column.feildName}Start; // ${column.columnComment}-起始时间
    private ${column.feildType} ${column.feildName}End; // ${column.columnComment}-结束时间
    </#if>
</#list>

    public ${domainName}() {

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
    <#if column.columnType?lower_case == "date">
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    <#elseif column.columnType?lower_case == "timestamp">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    public ${column.feildType} get${column.feildNameUpper}Start() {
        return ${column.feildName}Start;
    }

    /**
     * 设置${column.columnComment}-起始时间
     * @param ${column.feildName}Start
     */
    public void set${column.feildNameUpper}Start(${column.feildType} ${column.feildName}Start) {
        this.${column.feildName}Start = ${column.feildName}Start;
    }

    /**
     * 获取${column.columnComment}-结束时间
     * @return ${column.feildType}
     */
    <#if column.columnType?lower_case == "date">
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    <#elseif column.columnType?lower_case == "timestamp">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    public ${column.feildType} get${column.feildNameUpper}End() {
        return ${column.feildName}End;
    }

    /**
     * 设置${column.columnComment}-结束时间
     * @param ${column.feildName}End
     */
    public void set${column.feildNameUpper}End(${column.feildType} ${column.feildName}End) {
        this.${column.feildName}End = ${column.feildName}End;
    }
    </#if>
</#list>
}