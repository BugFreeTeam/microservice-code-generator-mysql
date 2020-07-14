package com.anjuxing.platform.code.entity;

import java.util.List;

import com.anjuxing.platform.code.util.CodeUtils;
import com.anjuxing.platform.code.util.CommonUtils;

public class CodeModule implements Cloneable {

	public static final String DATABASE_PERSISTENCE_MYBATIS = "MyBatis";
	public static final String DATABASE_PERSISTENCE_JPA = "SpringDataJpa";

	private boolean createMain;
	private boolean createTest;
	private boolean createDocker;
	private boolean createWiki;
	private boolean createWeb;

	private String groupId;
	private String artifactId;
	private String projectName;
	private String discovery;
	private String servicePort;
	private String requestPath;
	private String requestUrl;

	private String databasePersistence;

	private String database;
	private String dbHost;
	private String dbPort;
	private String dbSchema;
	private String dbEncode;
	private String dbUserName;
	private String dbPassword;

	private String tableName;
	private String tableComment;
	private String packagePath;
	private String domainName;
	private String domainNameLower;
	private String entityName;
	private String entityNameLower;
	private String repositoryName;
	private String repositoryNameLower;
	private String mapperName;
	private String mapperNameLower;
	private String serviceName;
	private String serviceNameLower;
	private String serviceImplName;
	private String controllerName;

	private String saveDir;
	private String projectDir;
	private String dockerDir;
	private String javaDir;
	private String resourcesDir;
	private String testDir;

	private String wikiDir;

	private List<String> tables = null;
	private List<DbTableColumn> columnList = null;

	public boolean isCreateMain() {
		return createMain;
	}

	public void setCreateMain(boolean createMain) {
		this.createMain = createMain;
	}

	public boolean isCreateTest() {
		return createTest;
	}

	public void setCreateTest(boolean createTest) {
		this.createTest = createTest;
	}

	public boolean isCreateDocker() {
		return createDocker;
	}

	public void setCreateDocker(boolean createDocker) {
		this.createDocker = createDocker;
	}

	public boolean isCreateWiki() {
		return createWiki;
	}

	public void setCreateWiki(boolean createWiki) {
		this.createWiki = createWiki;
	}

	public boolean isCreateWeb() {
		return createWeb;
	}

	public void setCreateWeb(boolean createWeb) {
		this.createWeb = createWeb;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDiscovery() {
		return discovery;
	}

	public void setDiscovery(String discovery) {
		this.discovery = discovery;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}

	public String getDatabasePersistence() {
		return databasePersistence;
	}

	public void setDatabasePersistence(String databasePersistence) {
		this.databasePersistence = databasePersistence;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getDbEncode() {
		return dbEncode;
	}

	public void setDbEncode(String dbEncode) {
		this.dbEncode = dbEncode;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainNameLower() {
		return domainNameLower;
	}

	public void setDomainNameLower(String domainNameLower) {
		this.domainNameLower = domainNameLower;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityNameLower() {
		return entityNameLower;
	}

	public void setEntityNameLower(String entityNameLower) {
		this.entityNameLower = entityNameLower;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public String getRepositoryNameLower() {
		return repositoryNameLower;
	}

	public void setRepositoryNameLower(String repositoryNameLower) {
		this.repositoryNameLower = repositoryNameLower;
	}

	public String getMapperName() {
		return mapperName;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public String getMapperNameLower() {
		return mapperNameLower;
	}

	public void setMapperNameLower(String mapperNameLower) {
		this.mapperNameLower = mapperNameLower;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceNameLower() {
		return serviceNameLower;
	}

	public void setServiceNameLower(String serviceNameLower) {
		this.serviceNameLower = serviceNameLower;
	}

	public String getServiceImplName() {
		return serviceImplName;
	}

	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String getProjectDir() {
		return projectDir;
	}

	public void setProjectDir(String projectDir) {
		this.projectDir = projectDir;
	}

	public String getDockerDir() {
		return dockerDir;
	}

	public void setDockerDir(String dockerDir) {
		this.dockerDir = dockerDir;
	}

	public String getJavaDir() {
		return javaDir;
	}

	public void setJavaDir(String javaDir) {
		this.javaDir = javaDir;
	}

	public String getResourcesDir() {
		return resourcesDir;
	}

	public void setResourcesDir(String resourcesDir) {
		this.resourcesDir = resourcesDir;
	}

	public String getTestDir() {
		return testDir;
	}

	public void setTestDir(String testDir) {
		this.testDir = testDir;
	}

	public String getWikiDir() {
		return wikiDir;
	}

	public void setWikiDir(String wikiDir) {
		this.wikiDir = wikiDir;
	}

	public List<String> getTables() {
		return tables;
	}

	public void setTables(List<String> tables) {
		this.tables = tables;
	}

	public List<DbTableColumn> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<DbTableColumn> columnList) {
		this.columnList = columnList;
	}
	
	public void init(String table){
		this.requestPath = CodeUtils.tableToRequestPath(table);
		this.requestUrl += this.requestPath;
		this.entityName = CodeUtils.tableToEntity(table);
		this.entityNameLower = CommonUtils.firstCharToLowerCase(this.entityName);
		this.domainName = this.entityName + "Domain";
		this.domainNameLower = CommonUtils.firstCharToLowerCase(this.domainName);
		this.repositoryName = this.entityName + "Repository";
		this.repositoryNameLower = CommonUtils.firstCharToLowerCase(this.repositoryName);
		this.mapperName = this.entityName + "Mapper";
		this.mapperNameLower = CommonUtils.firstCharToLowerCase(this.mapperName);
		this.serviceName = this.entityName + "Service";
		this.serviceNameLower = CommonUtils.firstCharToLowerCase(this.serviceName);
		this.serviceImplName = this.entityName + "ServiceImpl";
		this.controllerName = this.entityName + "Controller";

		this.projectDir = this.getSaveDir() + this.getArtifactId();
		this.dockerDir = this.projectDir + "/src/main/docker";
		this.javaDir = this.projectDir + "/src/main/java/"+CodeUtils.packageToDir(this.getPackagePath());
		this.resourcesDir = this.projectDir + "/src/main/resources";
		this.testDir = this.projectDir + "/src/test";
		this.wikiDir = this.projectDir + ".wiki";

	}

	public CodeModule clone() {
		CodeModule o = null;
		try {
			o = (CodeModule) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

}
