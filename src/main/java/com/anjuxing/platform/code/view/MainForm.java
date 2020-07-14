package com.anjuxing.platform.code.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.anjuxing.platform.code.db.DBHelper;
import com.anjuxing.platform.code.db.DbCache;
import com.anjuxing.platform.code.entity.CodeModule;
import com.anjuxing.platform.code.entity.DbTable;
import com.anjuxing.platform.code.entity.DbTableColumn;
import com.anjuxing.platform.code.entity.MysqlDbColumn;
import com.anjuxing.platform.code.generator.CodeGeneratorThread;
import com.anjuxing.platform.code.util.CommonUtils;
import com.anjuxing.platform.code.util.IniReader;
import com.anjuxing.platform.code.util.CodeUtils;

@SuppressWarnings("serial")
public class MainForm extends BasePanel {

	private int width = 900;
	private int height = 600;
	private boolean configInited = false;
	private boolean loadding = false;
	private String config_section = "create_config_info";

	private static JTree tree = null;
	private DefaultMutableTreeNode dbNode;
	private JScrollPane jScrollPane1;
	public static Map<String, Vector<Object>> columnMap = new HashMap<String, Vector<Object>>();

	public MainForm() {
		this.setBackground(Color.white);
		this.setLayout(null);
		initTreeThread(1);
		initCompant();
	}

	public MainForm(int width, int height) {
		this.width = width;
		this.height = height;
		this.setBackground(Color.white);
		this.setLayout(null);
		initTreeThread(1);
		initCompant();
	}

	public List<DbTable> getDbTable() {
		DBHelper.initDbConfig();
		List<DbTable> list = DBHelper.getDbTables(DBHelper.schema);
		DbCache.mysqlDbTableList = list;
		return list;
	}

	public List<?> getTableColumn(String table) {
		DBHelper.initDbConfig();
		if (DBHelper.sections.equals(MySqlSetPanel.sections)) {
			return DBHelper.getTableColumns(DBHelper.schema, table);
		}
		return null;
	}

	public Map<String, List<DbTableColumn>> getTableColumn() {
		DBHelper.initDbConfig();
		if (DBHelper.sections.equals(MySqlSetPanel.sections)) {
			return DBHelper.getMutilTableColumns(DBHelper.schema);
		}
		return null;
	}

	private JTextField groupIdText;
	private JTextField artifactIdText;
	private JTextField packageText;
	private JTextField discoveryText;
	private JTextField requestUrlText;
	private JTextField servicePortText;
	private JRadioButton mybatisRadio;
	private JRadioButton jpaRadio;
	private JCheckBox mainBox;
	private JCheckBox testBox;
	private JCheckBox dockerBox;
	private JCheckBox wikiBox;
	private JCheckBox webBox;

	private JButton seekTableDataBtn;
	private JButton singleCreateBtn;
	private JButton mutilCreateBtn;
	private JButton chooseDirBtn;

	private String chooseTable = null;

	JFileChooser jfc = new JFileChooser();// 文件选择器  
	private JTextField saveDirText;

	public void initCompant() {
		int left = 300, labelWidth = 80, labelTextHeight = 25, top = 40, textWidth = 150;

		// 按钮层
		seekTableDataBtn = new JButton("查看表数据");
		seekTableDataBtn.setBounds(left, 5, textWidth, labelTextHeight);
		add(seekTableDataBtn);
		seekTableDataBtn.setEnabled(false);

		singleCreateBtn = new JButton("生成选择的表");
		singleCreateBtn.setBounds(left + textWidth + 10, 5, textWidth, labelTextHeight);
		add(singleCreateBtn);
		singleCreateBtn.setEnabled(false);

		mutilCreateBtn = new JButton("生成全部表");
		mutilCreateBtn.setBounds(left + (textWidth + 10) * 2, 5, textWidth, labelTextHeight);
		add(mutilCreateBtn);

		JLabel tipLabel = new JLabel("基于SpringCloud分布式微服务项目代码构建工具");
		tipLabel.setBounds(left, top, 450, labelTextHeight);
		add(tipLabel);

		JLabel groupIdLabel = new JLabel("GroupID");
		groupIdLabel.setBounds(left, top + ((labelTextHeight + 10) * 1), labelWidth, labelTextHeight);
		add(groupIdLabel);
		groupIdText = new JTextField("");
		groupIdText.setName("groupId");
		groupIdText.setBounds(left + labelWidth, top + ((labelTextHeight + 10) * 1), textWidth * 2, labelTextHeight);
		add(groupIdText);
		JLabel groupIdTipLabel = new JLabel("例：com.anjuxing.platform");
		groupIdTipLabel.setBounds(left + labelWidth + textWidth * 2 + 10, top + ((labelTextHeight + 10) * 1), labelWidth * 2, labelTextHeight);
		add(groupIdTipLabel);
		
		JLabel artifactIdLabel = new JLabel("ArtifactID");
		artifactIdLabel.setBounds(left, top + ((labelTextHeight + 10) * 2), labelWidth, labelTextHeight);
		add(artifactIdLabel);
		artifactIdText = new JTextField("");
		artifactIdText.setName("artifactId");
		artifactIdText.setBounds(left + labelWidth, top + ((labelTextHeight + 10) * 2), textWidth * 2, labelTextHeight);
		add(artifactIdText);
		JLabel artifactIdTipLabel = new JLabel("例：microservice-platform");
		artifactIdTipLabel.setBounds(left + labelWidth + textWidth * 2 + 10, top + ((labelTextHeight + 10) * 2), labelWidth * 2, labelTextHeight);
		add(artifactIdTipLabel);

		JLabel packageLabel = new JLabel("Package");
		packageLabel.setBounds(left, top + ((labelTextHeight + 10) * 3), labelWidth, labelTextHeight);
		add(packageLabel);
		packageText = new JTextField("");
		packageText.setName("packagePath");
		packageText.setBounds(left + labelWidth, top + ((labelTextHeight + 10) * 3), textWidth * 2, labelTextHeight);
		add(packageText);

		JLabel discoveryLabel = new JLabel("Discovery");
		discoveryLabel.setBounds(left, top + ((labelTextHeight + 10) * 4), labelWidth, labelTextHeight);
		add(discoveryLabel);
		discoveryText = new JTextField("http://");
		discoveryText.setName("projectName");
		discoveryText.setBounds(left + labelWidth, top + ((labelTextHeight + 10) * 4), textWidth * 2, labelTextHeight);
		add(discoveryText);

		JLabel requestUrlLabel = new JLabel("RequestPath");
		requestUrlLabel.setBounds(left, top + ((labelTextHeight + 10) * 5), labelWidth, labelTextHeight);
		add(requestUrlLabel);
		requestUrlText = new JTextField("");
		requestUrlText.setName("requestUrl");
		requestUrlText.setBounds(left + labelWidth, top + ((labelTextHeight + 10) * 5), labelWidth * 2 - 10, labelTextHeight);
		add(requestUrlText);

		JLabel servicePortLabel = new JLabel("ServicePort");
		servicePortLabel.setBounds(left + labelWidth * 3, top + ((labelTextHeight + 10) * 5), labelWidth, labelTextHeight);
		add(servicePortLabel);
		servicePortText = new JTextField("8001");
		servicePortText.setName("servicePort");
		servicePortText.setBounds(left + labelWidth * 4, top + ((labelTextHeight + 10) * 5), labelWidth - 20, labelTextHeight);
		add(servicePortText);

		JLabel radioTipLabel = new JLabel("选择数据库持久层：");
		radioTipLabel.setBounds(left, top + ((labelTextHeight + 10) * 6), 200, labelTextHeight);
		add(radioTipLabel);
		mybatisRadio = new JRadioButton("MyBatis", true);
		mybatisRadio.setBounds(left + 120, top + ((labelTextHeight + 10) * 6), 100, labelTextHeight);
		add(mybatisRadio);
		jpaRadio = new JRadioButton("SpringDataJpa");
		jpaRadio.setBounds(left + 220, top + ((labelTextHeight + 10) * 6), 150, labelTextHeight);
		add(jpaRadio);
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(mybatisRadio);
		radioGroup.add(jpaRadio);

		JLabel boxTipLabel = new JLabel("选择需要生成的微服务项目代码：");
		boxTipLabel.setBounds(left, top + ((labelTextHeight + 10) * 7), 450, labelTextHeight);
		add(boxTipLabel);

		mainBox = new JCheckBox("Main");
		mainBox.setBounds(left, top + ((labelTextHeight + 10) * 8), labelWidth, labelTextHeight);
		add(mainBox);
		mainBox.setSelected(true);
		JLabel mainLabel = new JLabel("生成微服务接口源代码");
		mainLabel.setBounds(left + labelWidth + 10, top + ((labelTextHeight + 10) * 8), textWidth * 2, labelTextHeight);
		add(mainLabel);

		testBox = new JCheckBox("Test");
		testBox.setBounds(left, top + ((labelTextHeight + 10) * 9), labelWidth, labelTextHeight);
		add(testBox);
		testBox.setSelected(true);
		JLabel testLabel = new JLabel("生成微服务接口测试代码");
		testLabel.setBounds(left + labelWidth + 10, top + ((labelTextHeight + 10) * 9), textWidth * 2, labelTextHeight);
		add(testLabel);

		dockerBox = new JCheckBox("Docker");
		dockerBox.setBounds(left, top + ((labelTextHeight + 10) * 10), labelWidth, labelTextHeight);
		add(dockerBox);
		dockerBox.setSelected(true);
		JLabel dockerLabel = new JLabel("生成微服务Docker镜像配置文件");
		dockerLabel.setBounds(left + labelWidth + 10, top + ((labelTextHeight + 10) * 10), textWidth * 2, labelTextHeight);
		add(dockerLabel);

		wikiBox = new JCheckBox("Wiki");
		wikiBox.setBounds(left, top + ((labelTextHeight + 10) * 11), labelWidth, labelTextHeight);
		add(wikiBox);
		wikiBox.setSelected(true);
		JLabel wikiLabel = new JLabel("生成微服务项目Wiki文档");
		wikiLabel.setBounds(left + labelWidth + 10, top + ((labelTextHeight + 10) * 11), textWidth * 2, labelTextHeight);
		add(wikiLabel);

		webBox = new JCheckBox("Web");
		webBox.setBounds(left, top + ((labelTextHeight + 10) * 12), labelWidth, labelTextHeight);
		add(webBox);
		webBox.setSelected(true);
		JLabel webLabel = new JLabel("生成微服务项目Web页面源代码");
		webLabel.setBounds(left + labelWidth + 10, top + ((labelTextHeight + 10) * 12), textWidth * 2, labelTextHeight);
		add(webLabel);

		JLabel saveDirLabel = new JLabel("保存位置");
		saveDirLabel.setBounds(left, top + ((labelTextHeight + 10) * 13), labelWidth, labelTextHeight);
		add(saveDirLabel);
		saveDirText = new JTextField("D:/code/");
		saveDirText.setName("saveDir");
		saveDirText.setBounds(left + labelWidth, top + ((labelTextHeight + 10) * 13), textWidth * 2, labelTextHeight);
		saveDirText.setEditable(false);
		add(saveDirText);
		chooseDirBtn = new JButton("选择位置");
		chooseDirBtn.setBounds(left + labelWidth + textWidth * 2 + 10, top + ((labelTextHeight + 10) * 13), textWidth - 50, labelTextHeight);
		add(chooseDirBtn);
		chooseDirBtn.addActionListener(new BtnActionListener());
		
		groupIdText.addKeyListener(new InputKeyListener());
		artifactIdText.addKeyListener(new InputKeyListener());
		packageText.addKeyListener(new InputKeyListener());
		discoveryText.addKeyListener(new InputKeyListener());
		servicePortText.addKeyListener(new InputKeyListener());
		requestUrlText.addKeyListener(new InputKeyListener());

		mainBox.addActionListener(new BoxActionListener());
		testBox.addActionListener(new BoxActionListener());
		dockerBox.addActionListener(new BoxActionListener());
		wikiBox.addActionListener(new BoxActionListener());
		webBox.addActionListener(new BoxActionListener());

		seekTableDataBtn.addActionListener(new BtnActionListener());
		singleCreateBtn.addActionListener(new BtnActionListener());
		mutilCreateBtn.addActionListener(new BtnActionListener());

		configInited = false;
	}

	public void initTreeThread(int flag) {
	    if(loadding){
	        /*int count = dbNode.getChildCount();
	        if(count > 0){
	        }*/
	        return;
	    }
		new Thread() {
			public void run() {
			    loadding = true;
			    MainWindow.mainWindow.showProcessPanel();
				initTree();
				MainWindow.mainWindow.hideProcessPanel();
				loadding = false;
			}
		}.start();
	}

	public void initTree() {

		// 创建没有父节点和子节点、但允许有子节点的树节点，并使用指定的用户对象对它进行初始化。
		// public DefaultMutableTreeNode(Object TreeObject)
		if (jScrollPane1 != null) {
		    jScrollPane1.removeAll();
		    this.remove(jScrollPane1);
		}

		List<DbTable> data = getDbTable();
		int tableSize = -1;
		if (data != null) {
			tableSize = data.size();
		}

		dbNode = new DefaultMutableTreeNode(new User(DBHelper.schema));

		DbTable table = null;
		DefaultMutableTreeNode node1 = null;
		for (int i = 0; i < tableSize; i++) {
			table = data.get(i);
			node1 = new DefaultMutableTreeNode(new User(table.getTableName()));
			dbNode.add(node1);
		}

		tree = new JTree(dbNode);

		jScrollPane1 = new JScrollPane();
		jScrollPane1.getViewport().add(tree);
		jScrollPane1.setBounds(5, 5, 290, height - 45);
		this.add(jScrollPane1);

		// 添加选择事件
		tree.addTreeSelectionListener(new JTreeSelectionListener());

		if (tableSize > 0) {
			new Thread(new LoadColumnThread()).start();
		} else if (tableSize == -1) {
			new Thread(new AlertThread()).start();
		}
	}

	class JTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (node == null)
				return;
			int count = node.getChildCount();
			Object object = node.getUserObject();
			if (object != null) { // node.isLeaf() //node.isRoot()
				User User = (User) object;
				if (User != null) {
					String table = User.toString();
					int level = node.getLevel();
					if (level == 0) { // 数据库层
						chooseTable = null;
						seekTableDataBtn.setEnabled(false);
						singleCreateBtn.setEnabled(false);
						/*entityNameText.setEnabled(false);
						entityNameText.setText("");*/
						setCompTooltip();
						if (count == 0) {
							initTreeThread(1);
						}
					} else if (level == 1) { // 表层
						int selnum = tree.getSelectionCount();
						chooseTable = table;
						seekTableDataBtn.setEnabled(selnum == 1);
						singleCreateBtn.setEnabled(true);
						/*entityNameText.setEnabled(true);
						entityNameText.setText(CodeUtils.tableToEntity(table));*/
						setCompTooltip();
						if (count == 0) {
							initTableFeild(node, table);
						}
					} else if (level == 2) { // 字段层
						int selnum = tree.getSelectionCount();
						chooseTable = table;
						seekTableDataBtn.setEnabled(false);
						singleCreateBtn.setEnabled(false);
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
						/*entityNameText.setText(CodeUtils.tableToEntity(parent.getUserObject().toString()));*/
					}
				}
			}
		}
	}

	public void renderTableFeild(DefaultMutableTreeNode top, String table, List<?> columns) {
		if (DBHelper.sections.equals(MySqlSetPanel.sections)) {
			if (columns != null) {
				MysqlDbColumn colum = null;
				DefaultMutableTreeNode node1 = null;
				String title = "";
				Vector<Object> vector = new Vector<Object>();
				for (int i = 0, k = columns.size(); i < k; i++) {
					colum = (MysqlDbColumn) columns.get(i);
					title = colum.getColumnName() + " - " + colum.getColumnType();
					node1 = new DefaultMutableTreeNode(new User(title));
					top.add(node1);
					vector.add(colum.getColumnName());
				}
				if (!columnMap.containsKey(table)) {
					columnMap.put(table, vector);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void initTableFeild(DefaultMutableTreeNode top, String table) {
		if (DBHelper.sections.equals(MySqlSetPanel.sections)) {
			List<MysqlDbColumn> columns = (List<MysqlDbColumn>) getTableColumn(table);
			if (columns != null) {
				MysqlDbColumn colum = null;
				DefaultMutableTreeNode node1 = null;
				String title = "";
				Vector<Object> vector = new Vector<Object>();
				// String[] column = new String[columns.size()];
				for (int i = 0, k = columns.size(); i < k; i++) {
					colum = columns.get(i);
					title = colum.getColumnName() + " - " + colum.getColumnType();
					node1 = new DefaultMutableTreeNode(new User(title));
					top.add(node1);
					// column[i] = colum.getColumnName();
					vector.add(colum.getColumnName());
				}
				if (!columnMap.containsKey(table)) {
					columnMap.put(table, vector);
				}
			}
		}
	}

	class LoadColumnThread implements Runnable {

		public void run() {
			Map<String, List<DbTableColumn>> tabelMap = getTableColumn();
			if (tabelMap != null) {
				DbCache.mysqlDbColumnMap = tabelMap;
				List<?> comulnList = null;
				int size = dbNode.getChildCount();
				DefaultMutableTreeNode node;
				TreePath tpath = null;
				User user = null;
				for (int i = 0; i < size; i++) {
					node = (DefaultMutableTreeNode) dbNode.getChildAt(i);
					user = (User) node.getUserObject();
					if (user != null) {
						comulnList = tabelMap.get(user.toString());
						if (comulnList != null) {
							renderTableFeild(node, user.toString(), comulnList);
						}
					}
					tpath = new TreePath(node);
					tree.expandPath(tpath);
					tree.collapsePath(tpath);
				}
			}
			JScrollBar sBar = jScrollPane1.getVerticalScrollBar(); // 得到JScrollPane中的JScrollBar
			sBar.setValue(sBar.getMaximum()); // 设置JScrollBar的位置到最后
			sBar.setValue(sBar.getMinimum()); // 设置JScrollBar的位置到最前
		}

	}

	public void setCompTooltip() {
		/*changeTips("entityPackage", entityPackageText.getText());
		changeTips("repositoryPackage", repositoryPackageText.getText());
		changeTips("servicePackage", servicePackageText.getText());
		changeTips("controllerPackage", controlerPackageText.getText());*/
	}

	public void changeTips(String name, String value) {
		/*String entity = entityNameText.getText();
		if (entity == null || entity.length() < 1) {
			entity = "[实体类名称]";
		}
		if (name.equals("entityPackage")) {
			entityNameText.setToolTipText(value + "." + entity + ".java");
		} else if (name.equals("repositoryPackage")) {
			repositoryBox.setToolTipText(value + "." + entity + "Repository.java");
		} else if (name.equals("servicePackage")) {
			serviceBox.setToolTipText(value + "." + entity + "Service.java");
		} else if (name.equals("controllerPackage")) {
			controllerBox.setToolTipText(value + "." + entity + "Controller.java");
		}*/
	}

	public void changeConfig(String tag) {
		if (configInited) {
			IniReader reader = IniReader.getIniReader();
			reader.replaceActiveCfg(config_section, tag);
			//initValue(reader.getConfig(config_section, tag));
		}
	}

	public void startCreate(boolean isall) {
		List<String> tables = null;
		if (validateForm()){
			if (isall) {
				tables = getAllTable(dbNode);
			} else {
				tables = getSelectedTable();
			}
			if (tables != null) {
				CodeModule ca = getCodeModule(tables);
				if (ca != null) {
					new CodeGeneratorThread(tables, ca).start();
				}
			}
		}
	}

	public boolean validateForm(){
		boolean result = true;
		String msg = "";
		if ("".equals(groupIdText.getText().trim())){
			result = false;
			msg += "GroupID不能为空！\n";
		}
		if ("".equals(artifactIdText.getText().trim())){
			result = false;
			msg += "ArtifactID不能为空！\n";
		}
		if ("".equals(packageText.getText().trim())){
			result = false;
			msg += "Package不能为空！\n";
		}
		if ("".equals(discoveryText.getText().trim()) || "http://".equals(discoveryText.getText().trim())){
			result = false;
			msg += "Discovery不能为空！\n";
		}
		if ("".equals(requestUrlText.getText().trim())){
			result = false;
			msg += "RequestPath不能为空！\n";
		}
		if ("".equals(servicePortText.getText().trim())){
			result = false;
			msg += "ServicePort不能为空！\n";
		}
		if (!result){
			JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.WARNING_MESSAGE);
		}
		return result;
	}

	public CodeModule getCodeModule(List<String> tables) {
		CodeModule cm = new CodeModule();
		cm.setTables(tables);

		cm.setDatabase(DBHelper.schema);
		cm.setDbHost(DBHelper.ip);
		cm.setDbPort(DBHelper.port);
		cm.setDbSchema(DBHelper.schema);
		cm.setDbUserName(DBHelper.userName);
		cm.setDbPassword(DBHelper.pwd);
		cm.setDbEncode(DBHelper.encode);

		cm.setGroupId(groupIdText.getText());
		cm.setArtifactId(artifactIdText.getText());
		cm.setPackagePath(packageText.getText());
		cm.setProjectName(CodeUtils.getProjectName(cm.getArtifactId()));
		cm.setRequestUrl(requestUrlText.getText());
		cm.setDiscovery(discoveryText.getText());
		cm.setServicePort(servicePortText.getText());

		if (mybatisRadio.isSelected()){
			cm.setDatabasePersistence(CodeModule.DATABASE_PERSISTENCE_MYBATIS);
		}else {
			cm.setDatabasePersistence(CodeModule.DATABASE_PERSISTENCE_JPA);
		}

		cm.setEntityName("");
		cm.setEntityNameLower("");
		cm.setRequestPath("");
		cm.setTableComment("");
		cm.setCreateMain(mainBox.isSelected());
		cm.setCreateTest(testBox.isSelected());
		cm.setCreateDocker(dockerBox.isSelected());
		cm.setCreateWiki(wikiBox.isSelected());
		cm.setCreateWeb(webBox.isSelected());
		cm.setSaveDir((CommonUtils.isBlank(saveDirText.getText()) ? "D:/code/" : saveDirText.getText())+"/");

		return cm;
	}

	// 获取所有表
	@SuppressWarnings("unchecked")
	public List<String> getAllTable(DefaultMutableTreeNode node) {
		List<String> tableList = new ArrayList<String>();
		Enumeration<DefaultMutableTreeNode> children = node.children();
		User user = null;
		while (children.hasMoreElements()) {
			DefaultMutableTreeNode child = children.nextElement();
			if (!child.isLeaf()) { // 是否叶子节点
				if (child.getLevel() == 1) { // 表
					user = (User) child.getUserObject();
					if (user != null) {
						tableList.add(user.toString());
					}
				}
			}
		}
		return tableList;
	}

	// 获取所有选中的表
	public List<String> getSelectedTable() {
		List<String> tableList = new ArrayList<String>();
		TreePath[] treePaths = tree.getSelectionPaths();
		if (treePaths != null) {
			DefaultMutableTreeNode node = null;
			User user = null;
			for (int i = 0; i < treePaths.length; i++) {
				node = (DefaultMutableTreeNode) treePaths[i].getLastPathComponent();
				if (node != null) {
					if (node.getLevel() == 1) { // 表
						user = (User) node.getUserObject();
						if (user != null) {
							tableList.add(user.toString());
						}
					}
				}
			}
		}
		return tableList;
	}

	class AlertThread implements Runnable {
		public void run() {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "未检测到有效配置，数据库表加载失败", "提示", JOptionPane.ERROR_MESSAGE);
		}
	}

	class InputKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) { // 按下

		}

		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) { // 放开
			JTextField textFeild = (JTextField) e.getComponent();
			String name = textFeild.getName();
			//changeTips(name, textFeild.getText());
			if ("groupId".equals(name)){
				String groupId = groupIdText.getText();
				packageText.setText(groupId + "." + DBHelper.schema.replaceAll("_", "."));
				requestUrlText.setText("/" + DBHelper.schema.replaceAll("_", "/"));
			}
		}
	}

	class BoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JCheckBox box = (JCheckBox) e.getSource();
			String name = box.getText();
			/*if (name.equals("生成Entity")) {
				entityPackageText.setEnabled(box.isSelected());
				entityPackageText.setText("");
			} else if (name.equals("生成Repository")) {
				repositoryPackageText.setEnabled(box.isSelected());
				repositoryPackageText.setText("");
			} else if (name.equals("生成Service")) {
				servicePackageText.setEnabled(box.isSelected());
				servicePackageText.setText("");
			} else if (name.equals("生成Controller")) {
				controlerPackageText.setEnabled(box.isSelected());
				controlerPackageText.setText("");
			}*/
		}
	}

	class BtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String comm = e.getActionCommand();
			if (comm.equals("查看表数据")) {
				if (!"".equals(artifactIdText.getText().trim())){
					CodeModule cm = new CodeModule();
					cm.setTableName(chooseTable);
					cm.setArtifactId(artifactIdText.getText());
					cm.setSaveDir(saveDirText.getText());
					cm.init(chooseTable);
					MainWindow.mainWindow.showTableDataPanel(cm);
				}else {
					JOptionPane.showMessageDialog(null, "ArtifactId不参为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (comm.equals("生成全部表")) {
				startCreate(true);
			} else if (comm.equals("生成选择的表")) {
				startCreate(false);
			} else if (comm.equals("选择位置")) {
				String dir = saveDirText.getText();
				if(CommonUtils.isBlank(dir)){
					jfc.setCurrentDirectory(new File("D://code//"));// 文件选择器的初始目录定为D盘 
				} else {
					jfc.setCurrentDirectory(new File(dir));
				}
				jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
		        int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
		        if (state == 1) {  
		            return;  
		        } else {  
		            File f = jfc.getSelectedFile();// f为选择到的目录  
		            String path = f.getAbsolutePath();
		            saveDirText.setText(path.replaceAll("\\\\", "/") + "/");  
		        }
			}
		}
	}

	class User {
		private String name;

		public User(String n) {
			name = n;
		}

		public String toString() {
			return name;
		}
	}

}
