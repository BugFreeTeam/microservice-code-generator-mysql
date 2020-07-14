package com.anjuxing.platform.code.generator;

import com.anjuxing.platform.code.entity.CodeModule;
import com.anjuxing.platform.code.util.CodeUtils;
import com.anjuxing.platform.code.util.FreemarkerUtils;

public class CodeGenerator {

	CodeModule codeModule = null;

	public CodeGenerator(CodeModule codeModule) {
		this.codeModule = codeModule;
	}

	public void run() {
		if (CodeModule.DATABASE_PERSISTENCE_MYBATIS.equals(codeModule.getDatabasePersistence())){
			MyBatisGenerator.getInstance().createCode(codeModule);
		}else if (CodeModule.DATABASE_PERSISTENCE_JPA.equals(codeModule.getDatabasePersistence())){
			SpringDataJpaGenerator.getInstance().createCode(codeModule);
		}
	}

}
