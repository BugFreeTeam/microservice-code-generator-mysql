package ${packagePath}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anjuxing.platform.common.crud.CrudServiceImpl;
import com.anjuxing.platform.common.exception.ServiceException;
import ${packagePath}.mapper.${mapperName};
import ${packagePath}.model.${entityName};
import ${packagePath}.service.${serviceName};

/**
 * ${tableComment}
 */
@Service("${serviceNameLower}")
@Transactional(readOnly = true, rollbackFor = ServiceException.class)
public class ${serviceImplName} extends CrudServiceImpl<${mapperName}, ${entityName}> implements ${serviceName} {

    @Autowired
    private ${mapperName} ${mapperNameLower};


}