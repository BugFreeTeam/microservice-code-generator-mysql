package ${packagePath}.entity;

import java.io.Serializable;
<#list columnList as column>
    <#if column.feildType == "Date">
import java.util.Date;
        <#break>
    </#if>
</#list>

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.anjuxing.platform.common.base.BaseEntity;
import com.anjuxing.platform.common.util.ValidateUtils;
<#list columnList as column>
    <#if column.feildType == "Date">
import com.fasterxml.jackson.annotation.JsonFormat;
        <#break>
    </#if>
</#list>
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ${tableComment}
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="${tableName}")
public class ${entityName} extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

<#list columnList as column>
    <#if column.feildName == "id">
    @Id
    <#else>
        <#if column.isNullable == "NO">
            <#if column.feildName == "recordPerson" || column.feildName == "recordTime" || column.feildName == "recordDepart">
            <#else>
                <#if column.feildType == "String">
    @NotEmpty(message = "${column.columnComment}不能为空")
                <#else>
    @NotNull(message = "${column.columnComment}不能为空")
                </#if>
            </#if>
        </#if>
        <#if column.feildType == "String">
    @Size(max = ${column.characterMaximumLength}, message = "${column.columnComment}最大长度为${column.characterMaximumLength}字符，最大支持${column.characterMaximumLength / 2}个中文！")
        </#if>
    @Column(name="${column.columnName}")
    </#if>
    private ${column.feildType} ${column.feildName}; // ${column.columnComment}

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

    /**
     * ${tableComment}动态更新
     */
    public ${entityName} dynamicModel(${entityName} ${entityNameLower}, ${entityName} model){
    <#list columnList as column>
        if (ValidateUtils.isNotEmpty(model.get${column.feildNameUpper}()))
            ${entityNameLower}.set${column.feildNameUpper}(model.get${column.feildNameUpper}());
    </#list>
        return ${entityNameLower};
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName());
    <#list columnList as column>
        sb.append("; ${column.feildName}=" + (${column.feildName} == null ? "null" : ${column.feildName}.toString()));
    </#list>
        return sb.toString();
    }
}