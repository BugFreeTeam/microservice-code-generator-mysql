package ${packagePath}.service;

import com.anjuxing.platform.common.exception.BaseException;
import com.anjuxing.platform.common.model.User;
import ${packagePath}.entity.${entityName};
import ${packagePath}.domain.${domainName};
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * ${tableComment}
 */
public interface ${serviceName} {

    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @throws BaseException
     */
    public ${entityName} queryById(String id) throws BaseException;

    /**
     * 根据主键获取数据列表
     * @param list
     * @return
     * @throws BaseException
     */
    public List<${entityName}> queryByIds(List<String> list) throws BaseException;

    /**
     * 根据条件查询数据列表
     * @param domain
     * @return
     * @throws BaseException
     */
    public List<${entityName}> queryList(${domainName} domain) throws BaseException;

    /**
     * 根据条件分页查询数据
     * @param domain
     * @return
     * @throws BaseException
     */
    public Page<${entityName}> queryPage(${domainName} domain) throws BaseException;

    /**
     * 根据条件判断数据是否存在
     * @param ${entityNameLower}
     * @return
     * @throws BaseException
     */
    public List<${entityName}> exists(${entityName} ${entityNameLower}) throws BaseException;

    /**
     * 保存数据
     * @param ${entityNameLower}
     * @param user
     * @return
     * @throws BaseException
     */
    public ${entityName} save(${entityName} ${entityNameLower}, User user) throws BaseException;

    /**
     * 更新数据
     * @param id
     * @param ${entityNameLower}
     * @param user
     * @return
     * @throws BaseException
     */
    public ${entityName} update(String id, ${entityName} ${entityNameLower}, User user) throws BaseException;

    /**
     * 逻辑删除数据
     * @param id
     * @param user
     * @return
     * @throws BaseException
     */
    public boolean cancel(String id, User user) throws BaseException;

    /**
     * 删除数据
     * @param id
     * @return
     * @throws BaseException
     */
    public boolean delete(String id) throws BaseException;

    /**=======================================批量操作============================================*/
    /**
     * 批量保存数据
     * @param list
     * @param user
     * @return
     * @throws BaseException
     */
    public List<${entityName}> save(List<${entityName}> list, User user) throws BaseException;

    /**
     * 批量更新数据
     * @param list
     * @param user
     * @return
     * @throws BaseException
     */
    public List<${entityName}> update(List<${entityName}> list, User user) throws BaseException;

    /**
     * 批量逻辑删除数据
     * @param list
     * @param user
     * @return
     * @throws BaseException
     */
    public boolean cancel(List<${entityName}> list, User user) throws BaseException;

    /**
     * 批量删除数据
     * @param list
     * @return
     * @throws BaseException
     */
    public boolean delete(List<${entityName}> list) throws BaseException;

    /**=======================================自定义操作============================================*/


}
