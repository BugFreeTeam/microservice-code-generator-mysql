package com.anjuxing.platform.code.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.anjuxing.platform.code.db.DBHelper;
import com.anjuxing.platform.code.db.DbCache;
import com.anjuxing.platform.code.entity.CodeModule;
import com.anjuxing.platform.code.entity.DataModule;
import com.anjuxing.platform.code.entity.DbTableColumn;
import com.anjuxing.platform.code.entity.MysqlDbColumn;
import com.anjuxing.platform.code.util.FreemarkerUtils;
import com.anjuxing.platform.code.util.SQLFormatter;

public class TableDataPanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	private String sql = "";
	private String tableName = "";
    private CodeModule codeModule = null;
    private DataModule dataModule = new DataModule();
    private int width = 900, height = 600;

    public TableDataPanel(int width, int height) {
        this.width = width;
        this.height = height;
        initCommpant();
    }

    public void viewTableData(CodeModule codeModule) {
        if (codeModule != null) {
            this.codeModule = codeModule;
            this.tableName = codeModule.getTableName();
            dataModule.setTableName(tableName);
            dataModule.setTableComment(DbCache.getMysqlDbTable(tableName).getTableComment());
            dataModule.setColumnList(DbCache.mysqlDbColumnMap.get(tableName));
            sql = "select " + getColumnSql(tableName) + " from " + tableName + " limit 0, 1000";
            // 执行SQL
            excuteSql();
        } else {
            sql = "";
        }
        sqlArea.setText(sql);
    }

    private String getColumnSql(String tableName){
        String columnSql = "";
        List<DbTableColumn> columnList = DbCache.mysqlDbColumnMap.get(tableName);

        if (columnList != null && columnList.size() > 0){
            for (int i=0;i<columnList.size();i++){
                MysqlDbColumn column = (MysqlDbColumn) columnList.get(i);
                columnSql += column.getColumnName() + ",";
            }
            if (columnSql.endsWith(",")){
                columnSql = columnSql.substring(0, columnSql.length() - 1);
            }
        }else {
            columnSql = "*";
        }
        return columnSql;
    }

    public JTextArea sqlArea;
    public JTextArea infoArea;
    public JTable dataTable;
    public DefaultTableModel model = null;
    public JButton excuteBtn;
    public JButton formatBtn;
    public JButton wikiBtn;
    public JScrollPane tabJsp;
    public JScrollPane infoJsp;

    public void initCommpant() {
        this.setSize(new Dimension(width, height - 30));
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        JButton close = new JButton("<返回");
        close.setBounds(width - 70, 8, 50, 20);
        this.add(close);
        close.addActionListener(new ComActionListener(this));
        
        excuteBtn = new JButton("执行SQL");
        excuteBtn.setBounds(10, 8, 80, 20);
        this.add(excuteBtn);
        excuteBtn.addActionListener(new ComActionListener(this));
        
        formatBtn = new JButton("美化SQL");
        formatBtn.setBounds(10 + 90, 8, 80, 20);
        this.add(formatBtn);
        formatBtn.addActionListener(new ComActionListener(this));

        wikiBtn = new JButton("生成WIKI");
        wikiBtn.setBounds(10 + 90 + 90, 8, 80, 20);
        this.add(wikiBtn);
        wikiBtn.addActionListener(new ComActionListener(this));

        sqlArea = new JTextArea();
        JScrollPane sqlJsp = new JScrollPane(sqlArea);
        //sqlArea.setBackground(new Color(245, 245, 245));
        sqlJsp.setBounds(5, 35, width - 20, 180);
        this.add(sqlJsp);
        
        infoArea = new JTextArea();
        infoArea.setBackground(new Color(245, 245, 245));
        
        infoJsp = new JScrollPane(infoArea);
        infoJsp.setBounds(5, 220, width - 20, height - 260);
        this.add(infoJsp);
        infoJsp.setVisible(false);
        
        dataTable = new JTable(null);
        tabJsp = new JScrollPane(dataTable);
        tabJsp.setBounds(5, 220, width - 20, height - 260);
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.add(tabJsp);

    }

    public void renderTableData(Vector<Vector<Object>> data, Vector<Object> columns) {
        model = new DefaultTableModel(data, columns);
        dataTable.setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        // setDefaultRenderer(Object.class, r);
    }

    public void excuteSql() {
        new Thread() {
            public void run() {
               try {
                    showInfoOrTable(false, "执行中....");
                    Map<String, Object> dataMap = DBHelper.loadTableData(sql);
                    if(dataMap != null){
                        Vector<Vector<Object>> datas = (Vector<Vector<Object>>) dataMap.get("dataSet");
                        Vector<Object> columns = (Vector<Object>) dataMap.get("feildSet");
                        if(datas != null && columns != null){
                            showInfoOrTable(true, "");
                            renderTableData(datas, columns);
                            dataModule.setColumns(columns);
                            dataModule.setDatas(datas);
                        } else {
                            showInfoOrTable(false, "执行成功，无结果...");
                        }
                    } else {
                        showInfoOrTable(false, "执行结果有误");
                    }
                } catch (Exception e) {
                    StringBuffer sb = new StringBuffer();
                    StackTraceElement[] stes = e.getStackTrace();
                    for (int i = 0; i < stes.length; i++) {
                        sb.append("\n" + stes[i].toString());
                    }
                    showInfoOrTable(false, "执行异常：" + e.getMessage() + sb.toString());
                }
            }
        }.start();
    }
    
    public void showInfoOrTable(boolean flag, String msg){
        tabJsp.setVisible(flag);
        infoJsp.setVisible(!flag);
        infoArea.setText(msg);
    }

    public Object[][] ListMapToArray2(List<Map<String, Object>> list) {
        int size1 = list.size(), size2 = 0, index = 0;
        Object[][] data = new Object[size1][];
        Object[] col = null;
        Map<String, Object> map = null;
        for (int i = 0; i < size1; i++) {
            map = list.get(i);
            size2 = map.keySet().size();
            col = new Object[size2];
            index = 0;
            for (Map.Entry<String, Object> ent : map.entrySet()) {
                col[index] = ent.getValue();
                index++;
            }
            data[i] = col;
        }
        return data;
    }

    public void showPanel() {
        //UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(3, 20, 2, 20));
        this.setVisible(true);
    }
    
    private class ComActionListener implements ActionListener {
        private TableDataPanel form = null;

        public ComActionListener(TableDataPanel form) {
            this.form = form;
        }

        public void actionPerformed(ActionEvent e) {
            String com = e.getActionCommand();
            if(com.equals("<返回")){
                form.setVisible(false);
            } else if(com.equals("执行SQL")){
                String text = sqlArea.getText();
                if(DBHelper.sections.equals(MySqlSetPanel.sections)){
                    if(text.indexOf(tableName) == -1){
                        
                    } else if(text.indexOf("limit ") == -1){
                        text = text + " limit 0, 1000";
                    }
                    sql = text;
                }
                excuteSql();
            } else if(com.equals("美化SQL")){
                String sql = sqlArea.getText();
                if(sql != null){
                    sql = new SQLFormatter().format(sql);
                    sqlArea.setText(sql);
                }
            } else if (com.equals("生成WIKI")){
                String dir = codeModule.getWikiDir() + "/data";
                FreemarkerUtils.getInstance().build(dataModule, "/wiki/data/table.ftl",dir,dataModule.getTableName()+".md");
                JOptionPane.showMessageDialog(null, "生成完成\n路径：" + dir, "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}
