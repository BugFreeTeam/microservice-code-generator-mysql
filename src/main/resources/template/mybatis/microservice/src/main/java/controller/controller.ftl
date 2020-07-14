package ${packagePath}.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anjuxing.platform.common.crud.CrudController;
import ${packagePath}.model.${entityName};
import ${packagePath}.service.${serviceName};

/**
 * ${tableComment}
 */
@RestController
@RequestMapping(value = "${requestPath}")
public class ${controllerName} extends CrudController<${serviceName}, ${entityName}> {

    private Logger log = LoggerFactory.getLogger(${controllerName}.class);

    @Autowired
    private ${serviceName} ${serviceNameLower};


}

