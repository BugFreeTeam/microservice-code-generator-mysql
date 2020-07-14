package com.anjuxing.platform.code.generator;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.anjuxing.platform.code.db.DbCache;
import com.anjuxing.platform.code.entity.CodeModule;
import com.anjuxing.platform.code.entity.DbTable;
import com.anjuxing.platform.code.entity.DbTableColumn;
import com.anjuxing.platform.code.util.CodeUtils;
import com.anjuxing.platform.code.util.FileUtils;
import com.anjuxing.platform.code.util.FreemarkerUtils;
import com.anjuxing.platform.code.view.MainWindow;

public class CodeGeneratorThread extends Thread {
	List<String> tables = null;
	CodeModule cm = null;

	public CodeGeneratorThread(List<String> tables, CodeModule cm) {
		this.tables = tables;
		this.cm = cm;
	}

	public void run() {
	    MainWindow.mainWindow.showProcessPanel();

		if (CodeModule.DATABASE_PERSISTENCE_MYBATIS.equals(cm.getDatabasePersistence())){
			MyBatisGenerator.getInstance().initProject(cm);
		}else if (CodeModule.DATABASE_PERSISTENCE_JPA.equals(cm.getDatabasePersistence())){
			SpringDataJpaGenerator.getInstance().initProject(cm);
		}

		if(tables.size() == 0){
			tables.add(CodeUtils.entityToTable(cm.getEntityName()));
		}
		List<DbTableColumn> columnList = null;
		String tableName = null;
		String tableComment = null;
		CodeModule codeModule = null;
		for (int i = 0, k = tables.size(); i < k; i++) {
			tableName = tables.get(i);
			DbTable dbTable = DbCache.getMysqlDbTable(tableName);
			if (dbTable != null) {
				tableComment = dbTable.getTableComment();
			}
			columnList = DbCache.mysqlDbColumnMap.get(tableName);
			codeModule = cm.clone();
			codeModule.setTableName(tableName);
			codeModule.setTableComment(tableComment);
			codeModule.setColumnList(columnList);
			codeModule.init(tableName);
			new CodeGenerator(codeModule).run();
		}

		// 执行完成
		MainWindow.mainWindow.hideProcessPanel();
		Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, "生成完成\n路径：" + cm.getSaveDir(), "提示", JOptionPane.INFORMATION_MESSAGE);
        /*try {
			Desktop.getDesktop().open(new File(cm.getSaveDir()));
        } catch (Exception e) {
        }*/
	}

}
