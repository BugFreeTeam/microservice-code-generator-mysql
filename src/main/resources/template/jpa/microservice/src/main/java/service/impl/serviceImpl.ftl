package ${packagePath}.service.impl;

import com.anjuxing.platform.common.exception.BaseException;
import com.anjuxing.platform.common.jpa.Criteria;
import com.anjuxing.platform.common.jpa.Restrictions;
import com.anjuxing.platform.common.model.User;
import com.anjuxing.platform.common.util.CodeUtils;
import com.anjuxing.platform.common.util.DateUtils;
import com.anjuxing.platform.common.util.ValidateUtils;
import ${packagePath}.entity.${entityName};
import ${packagePath}.domain.${domainName};
import ${packagePath}.repository.${repositoryName};
import ${packagePath}.service.${serviceName};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ${tableComment}
 */
@Service
@Transactional(readOnly = true, rollbackFor = BaseException.class)
public class ${serviceImplName} implements ${serviceName}{

    private Logger log = LoggerFactory.getLogger(${serviceImplName}.class);

    @Autowired
    private ${repositoryName} ${repositoryNameLower};

    /**
     * 根据主键查询数据
     * @param id
     * @return
     */
    @Override
    public ${entityName} queryById(String id) throws BaseException{
        ${entityName} ${entityNameLower} = null;
        try {
            ${entityNameLower} = ${repositoryNameLower}.findOne(id);
            if (${entityNameLower} != null){
                return ${entityNameLower};
            }else {
                throw new BaseException("", id + "数据不存在");
            }
        }catch (Exception e){
            throw new BaseException("", e.getMessage());
        }
    }

    /**
     * 根据主键获取数据列表
     * @param list
     * @return
     */
    @Override
    public List<${entityName}> queryByIds(List<String> list) throws BaseException {
        try {
            return ${repositoryNameLower}.findAll(list);
        }catch (Exception e){
            log.error("根据主键获取数据列表失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
    }

    /**
     * 根据条件查询数据列表
     * @param domain
     * @return
     */
    @Override
    public List<${entityName}> queryList(${domainName} domain) throws BaseException {
        try {
            Criteria<${entityName}> criteria = new Criteria<${entityName}>();
        <#list columnList as column>
            <#if column.feildType == "String" && column.characterMaximumLength gt 50>
            criteria.add(Restrictions.like("${column.feildName}", domain.get${column.feildNameUpper}()));
            <#elseif column.feildType == "Date">
            criteria.add(Restrictions.equal("${column.feildName}", domain.get${column.feildNameUpper}()));
            criteria.add(Restrictions.gte("${column.feildName}", domain.get${column.feildNameUpper}Start()));
            criteria.add(Restrictions.lte("${column.feildName}", domain.get${column.feildNameUpper}End()));
            <#else>
            criteria.add(Restrictions.equal("${column.feildName}", domain.get${column.feildNameUpper}()));
            </#if>
        </#list>

            return ${repositoryNameLower}.findAll(criteria, domain.getSort());
        }catch (Exception e){
            log.error("根据条件查询数据列表失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
    }

    /**
     * 根据条件分页查询数据
     * @param domain
     * @return
     */
    @Override
    public Page<${entityName}> queryPage(${domainName} domain) throws BaseException {
        try {
            Pageable pageable = new PageRequest(domain.getPage(), domain.getSize(), domain.getSort());
            Criteria<${entityName}> criteria = new Criteria<${entityName}>();
        <#list columnList as column>
            <#if column.feildType == "String" && column.characterMaximumLength gt 50>
            criteria.add(Restrictions.like("${column.feildName}", domain.get${column.feildNameUpper}()));
            <#elseif column.feildType == "Date">
            criteria.add(Restrictions.equal("${column.feildName}", domain.get${column.feildNameUpper}()));
            criteria.add(Restrictions.gte("${column.feildName}", domain.get${column.feildNameUpper}Start()));
            criteria.add(Restrictions.lte("${column.feildName}", domain.get${column.feildNameUpper}End()));
            <#else>
            criteria.add(Restrictions.equal("${column.feildName}", domain.get${column.feildNameUpper}()));
            </#if>
        </#list>

            return ${repositoryNameLower}.findAll(criteria, pageable);
        }catch (Exception e){
            log.error("根据条件分页查询数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
    }

    /**
     * 根据条件判断数据是否存在
     * @param ${entityNameLower}
     * @return
     */
    @Override
    public List<${entityName}> exists(${entityName} ${entityNameLower}) throws BaseException {
        try {
            return ${repositoryNameLower}.findAll(Example.of(${entityNameLower}));
        }catch (Exception e) {
            log.error("根据条件判断数据是否存在失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
    }

    /**
     * 保存数据
     * @param ${entityNameLower}
     * @param user
     * @return
     * @throws BaseException
     */
    @Override
    @Transactional
    public ${entityName} save(${entityName} ${entityNameLower}, User user) throws BaseException {
        try {
            if (${entityNameLower} != null){
                ${entityNameLower}.setId(CodeUtils.getUUID());
                ${entityNameLower}.setFlag(1);
                ${entityNameLower}.setStatus(1);
        <#list columnList as column>
            <#if column.feildName == "recordTime">
                ${entityNameLower}.setRecordTime(DateUtils.getCurrentTime());
                ${entityNameLower}.setRecordDepart(user.getDepart());
                ${entityNameLower}.setRecordPerson(user.getId());
                <#break>
            </#if>
        </#list>

                ${entityNameLower} = ${repositoryNameLower}.save(${entityNameLower});
                log.info("保存数据成功：" + ${entityNameLower}.getId());
            }
        }catch (Exception e){
            log.error("保存数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
        return ${entityNameLower};
    }

    /**
     * 更新数据
     * @param id
     * @param ${entityNameLower}
     * @param user
     * @return
     * @throws BaseException
     */
    @Override
    @Transactional
    public ${entityName} update(String id, ${entityName} ${entityNameLower}, User user) throws BaseException {
        try {
            if (${entityNameLower} != null){
                ${entityName} updateEntity = queryById(id);
                if (updateEntity != null){
            <#list columnList as column>
                <#if column.feildName == "recordTime">
                    ${entityNameLower}.setRecordTime(DateUtils.getCurrentTime());
                    ${entityNameLower}.setRecordDepart(user.getDepart());
                    ${entityNameLower}.setRecordPerson(user.getId());
                    <#break>
                </#if>
            </#list>

                    ${entityNameLower} = ${repositoryNameLower}.save(updateEntity.dynamicModel(updateEntity, ${entityNameLower}));
                    log.info("更新数据成功：" + id);
                }else {
                    throw new BaseException("", id + "数据不存在");
                }
            }
        }catch (Exception e){
            log.error("更新数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
        return ${entityNameLower};
    }
    /**
     * 逻辑删除数据
     * @param id
     * @param user
     * @return
     * @throws BaseException
     */
    @Override
    @Transactional
    public boolean cancel(String id, User user) throws BaseException {
        boolean result = false;
        try {
            ${entityName} ${entityNameLower} = queryById(id);
            if (${entityNameLower} != null){
                ${entityNameLower}.setFlag(0);
        <#list columnList as column>
            <#if column.feildName == "recordTime">
                ${entityNameLower}.setRecordTime(DateUtils.getCurrentTime());
                ${entityNameLower}.setRecordDepart(user.getDepart());
                ${entityNameLower}.setRecordPerson(user.getId());
                <#break>
            </#if>
        </#list>

                ${repositoryNameLower}.save(${entityNameLower});
                result = true;
                log.info("逻辑删除数据成功：" + ${entityNameLower}.getId());
            }else {
                throw new BaseException("", id);
            }
        }catch (Exception e){
            log.info("逻辑删除数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
        return result;
    }

    /**
     * 删除数据
     * @param id
     * @return
     * @throws BaseException
     */
    @Override
    @Transactional
    public boolean delete(String id) throws BaseException {
        boolean result = false;
        try {
            if (ValidateUtils.isNotEmpty(id)){
                ${repositoryNameLower}.delete(id);
                result = true;
                log.info("删除数据成功：" + id);
            }
        }catch (Exception e){
            log.error("删除数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
        return result;
    }

    /**=======================================批量操作============================================*/
    /**
     * 批量保存数据
     * @param list
     * @return
     */
    @Override
    @Transactional
    public List<${entityName}> save(List<${entityName}> list, User user) throws BaseException {
        List<${entityName}> resultList = new ArrayList<${entityName}>();
        try {
            if (list != null && list.size() > 0){
                for (${entityName} ${entityNameLower} : list){
                    if (${entityNameLower} != null){
                        ${entityNameLower}.setId(CodeUtils.getUUID());
                        ${entityNameLower}.setFlag(1);
                        ${entityNameLower}.setStatus(1);
                <#list columnList as column>
                    <#if column.feildName == "recordTime">
                        ${entityNameLower}.setRecordTime(DateUtils.getCurrentTime());
                        ${entityNameLower}.setRecordDepart(user.getDepart());
                        ${entityNameLower}.setRecordPerson(user.getId());
                        <#break>
                    </#if>
                </#list>
                        ${entityNameLower} = ${repositoryNameLower}.save(${entityNameLower});
                        resultList.add(${entityNameLower});
                    }
                }
                log.info("批量保存数据成功：" + list.toString());
            }
        }catch (Exception e){
            log.error("批量保存数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
        return resultList;
    }

    /**
     * 批量更新数据
     * @param list
     * @return
     */
    @Override
    @Transactional
    public List<${entityName}> update(List<${entityName}> list, User user) throws BaseException {
        List<${entityName}> resultList = new ArrayList<${entityName}>();
        try {
            if (list != null && list.size() > 0){
                for (${entityName} ${entityNameLower} : list){
                    if (${entityNameLower} != null && ValidateUtils.isNotEmpty(${entityNameLower}.getId())){
                        ${entityName} updateEntity = queryById(${entityNameLower}.getId());
                        if (updateEntity != null){
                    <#list columnList as column>
                        <#if column.feildName == "recordTime">
                            ${entityNameLower}.setRecordTime(DateUtils.getCurrentTime());
                            ${entityNameLower}.setRecordDepart(user.getDepart());
                            ${entityNameLower}.setRecordPerson(user.getId());
                            <#break>
                        </#if>
                    </#list>

                            ${entityNameLower} = ${repositoryNameLower}.save(updateEntity.dynamicModel(updateEntity, ${entityNameLower}));
                            resultList.add(${entityNameLower});
                        }else {
                            throw new BaseException("", ${entityNameLower}.getId() + "更新的数据不存在");
                        }
                    }
                }
                log.info("批量更新数据成功：" + list.toString());
            }
        }catch (Exception e){
            log.error("批量更新数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
        return resultList;
    }

    /**
     * 批量逻辑删除数据
     * @param list
     * @return
     */
    @Override
    @Transactional
    public boolean cancel(List<${entityName}> list, User user) throws BaseException {
        boolean result = false;
        try {
            if (list != null && list.size() > 0){
                for (${entityName} model : list){
                    if (ValidateUtils.isNotEmpty(model.getId())){
                        ${entityName} ${entityNameLower} = queryById(model.getId());
                        if (${entityNameLower} != null){
                            ${entityNameLower}.setFlag(0);
                    <#list columnList as column>
                        <#if column.feildName == "recordTime">
                            ${entityNameLower}.setRecordTime(DateUtils.getCurrentTime());
                            ${entityNameLower}.setRecordDepart(user.getDepart());
                            ${entityNameLower}.setRecordPerson(user.getId());
                            <#break>
                        </#if>
                    </#list>
                            ${repositoryNameLower}.save(${entityNameLower});
                        }else {
                            throw new BaseException("", model.getId() + "逻辑删除数据不存在");
                        }
                    }
                }
                result = true;
                log.info("批量逻辑删除数据成功：" + list.toString());
            }
        }catch (Exception e){
            log.error("批量逻辑删除数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
        return result;
    }



    /**
     * 批量删除数据
     * @param list
     * @return
     */
    @Override
    @Transactional
    public boolean delete(List<${entityName}> list) throws BaseException {
        boolean result = false;
        try {
            if (list != null && list.size() > 0){
                ${repositoryNameLower}.deleteInBatch(list);
                result = true;
                log.info("批量删除数据成功：" + list.toString());
            }
        }catch (Exception e){
            log.error("批量删除数据失败：" + e.getMessage());
            throw new BaseException("", e.getMessage());
        }
        return result;
    }

    /**=======================================自定义操作============================================*/
}
