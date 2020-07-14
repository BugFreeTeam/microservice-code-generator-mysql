package com.anjuxing.platform.code.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anjuxing.platform.code.entity.DbTable;
import com.anjuxing.platform.code.entity.DbTableColumn;

public class DbCache {

	public static List<DbTable> mysqlDbTableList = new ArrayList<DbTable>();

	public static Map<String, List<DbTableColumn>> mysqlDbColumnMap = new HashMap<String, List<DbTableColumn>>();
	
	public static DbTable getMysqlDbTable(String tableName){
		DbTable dbTable = null;
		if (mysqlDbTableList != null && mysqlDbTableList.size() > 0) {
			for (DbTable table : mysqlDbTableList) {
				if (tableName.equals(table.getTableName())) {
					dbTable = table;
					break;
				}
			}
		}
		return dbTable;
		
	}

}
