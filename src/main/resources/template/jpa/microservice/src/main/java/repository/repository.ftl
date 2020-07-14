package ${packagePath}.repository;

import ${packagePath}.entity.${entityName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ${tableComment}
 */
public interface ${repositoryName} extends JpaRepository<${entityName}, String>, JpaSpecificationExecutor<${entityName}>  {

}