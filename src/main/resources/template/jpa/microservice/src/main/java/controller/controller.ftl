package ${packagePath}.controller;

import com.anjuxing.platform.common.base.BaseController;
import com.anjuxing.platform.common.base.JsonResult;
import com.anjuxing.platform.common.exception.BaseException;
import ${packagePath}.entity.${entityName};
import ${packagePath}.domain.${domainName};
import ${packagePath}.service.${serviceName};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ${tableComment}
 */
@RestController
@RequestMapping(value = "${requestPath}")
public class ${controllerName} extends BaseController {

    private Logger log = LoggerFactory.getLogger(${controllerName}.class);

    @Autowired
    private ${serviceName} ${serviceNameLower};

    /**
     * 根据主键查询数据
     * @param id
     * @return
     * @throws BaseException
     */
    @GetMapping(value = "/{id}")
    public JsonResult get(@PathVariable String id) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        ${entityName} ${entityNameLower} = ${serviceNameLower}.queryById(id);
        jsonResult.setResult(JsonResult.SUCCESS);
        jsonResult.setData(${entityNameLower});
        return jsonResult;
    }

    /**
     * 根据主键查询数据列表
     * @param list
     * @return
     * @throws BaseException
     */
    @PostMapping(value = "/ids")
    public JsonResult getIds(@RequestBody List<${entityName}> list) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        List<String> ids = new ArrayList<String>();
        if (list != null && list.size() > 0){
            for (${entityName} ${entityNameLower} : list){
                ids.add(${entityNameLower}.getId());
            }
        }
        List<${entityName}> resultList = ${serviceNameLower}.queryByIds(ids);
        if (resultList != null && resultList.size() > 0){
            jsonResult.setResult(JsonResult.SUCCESS);
            jsonResult.setData(resultList);
        }
        return jsonResult;
    }

    /**
     * 判断传入的值是否存在
     * @param ${entityNameLower}
     * @return
     * @throws BaseException
     */
    @PostMapping(value = "/exists")
    public JsonResult exists(@RequestBody ${entityName} ${entityNameLower}) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        List<${entityName}> list = ${serviceNameLower}.exists(${entityNameLower});
        if (list != null && list.size() > 0){
            jsonResult.setResult(JsonResult.SUCCESS);
            jsonResult.setData(list);
        }
        return jsonResult;
    }

    /**
     * 根据条件查询数据
     * @param domain
     * @return
     * @throws BaseException
     */
    @PostMapping(value = "/list")
    public JsonResult queryList(@RequestBody ${domainName} domain) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        List<${entityName}> list = ${serviceNameLower}.queryList(domain);
        if (list != null && list.size() > 0){
            jsonResult.setResult(JsonResult.SUCCESS);
            jsonResult.setData(list);
        }
        return jsonResult;
    }

    /**
     * 分页查询数据
     * @param domain
     * @return
     * @throws BaseException
     */
    @PostMapping(value = "/page")
    public JsonResult queryPage(@RequestBody ${domainName} domain) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        Page<${entityName}> page = ${serviceNameLower}.queryPage(domain);
        if (page != null){
            jsonResult.setResult(JsonResult.SUCCESS);
            jsonResult.setData(page);
        }
        return jsonResult;
    }

    /**
     * 新增数据
     * @param ${entityNameLower}
     * @return
     * @throws BaseException
     */
    @PostMapping
    public JsonResult add(@RequestBody ${entityName} ${entityNameLower}) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        ${entityNameLower} = ${serviceNameLower}.save(${entityNameLower}, getUser());
        jsonResult.setResult(JsonResult.SUCCESS);
        jsonResult.setData(${entityNameLower});
        return jsonResult;
    }

    /**
     * 更新数据
     * @param id
     * @param ${entityNameLower}
     * @return
     * @throws BaseException
     */
    @PutMapping(value = "/{id}")
    public JsonResult update(@PathVariable String id, @RequestBody ${entityName} ${entityNameLower}) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        ${entityNameLower} = ${serviceNameLower}.update(id, ${entityNameLower}, getUser());
        jsonResult.setResult(JsonResult.SUCCESS);
        jsonResult.setData(${entityNameLower});
        return jsonResult;
    }

    /**
     * 逻辑删除数据
     * @param id
     * @return
     * @throws BaseException
     */
    @PutMapping(value = "/cancel/{id}")
    public JsonResult cancel(@PathVariable String id) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        boolean result = ${serviceNameLower}.cancel(id, getUser());
        if (result){
            jsonResult.setResult(JsonResult.SUCCESS);
        }
        return jsonResult;
    }

    /**
     * 物理删除数据
     * @param id
     * @return
     * @throws BaseException
     */
    @DeleteMapping(value = "/{id}")
    public JsonResult remove(@PathVariable String id) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        boolean result = ${serviceNameLower}.delete(id);
        if (result){
            jsonResult.setResult(JsonResult.SUCCESS);
        }
        return jsonResult;
    }

    /**=======================================批量操作============================================*/
    /**
     * 批量新增数据
     * @param list
     * @return
     */
    @PostMapping(value = "/batch")
    public JsonResult add(@RequestBody List<${entityName}> list) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        List<${entityName}> resultList = ${serviceNameLower}.save(list, getUser());
        if (resultList != null && resultList.size() > 0){
            jsonResult.setResult(JsonResult.SUCCESS);
            jsonResult.setData(resultList);
        }
        return jsonResult;
    }

    /**
     * 批量更新数据
     * @param list
     * @return
     */
    @PutMapping(value = "/batch")
    public JsonResult update(@RequestBody List<${entityName}> list) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        List<${entityName}> resultList = ${serviceNameLower}.update(list, getUser());
        if (resultList != null && resultList.size() > 0){
            jsonResult.setResult(JsonResult.SUCCESS);
            jsonResult.setData(resultList);
        }
        return jsonResult;
    }

    /**
     * 批量逻辑删除数据
     * @param list
     * @return
     */
    @PutMapping(value = "/batch/cancel")
    public JsonResult cancel(@RequestBody List<${entityName}> list) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        boolean result = ${serviceNameLower}.cancel(list, getUser());
        if (result){
            jsonResult.setResult(JsonResult.SUCCESS);
        }
        return jsonResult;
    }

    /**
     * 批量删除数据
     * @param list
     * @return
     */
    @PostMapping(value = "/batch/delete")
    public JsonResult remove(@RequestBody List<${entityName}> list) throws BaseException{
        JsonResult jsonResult = getJsonResult();
        boolean result = ${serviceNameLower}.delete(list);
        if (result){
            jsonResult.setResult(JsonResult.SUCCESS);
        }
        return jsonResult;
    }

    /**=======================================自定义操作============================================*/

}
