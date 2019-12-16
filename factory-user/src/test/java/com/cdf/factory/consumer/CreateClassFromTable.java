package com.cdf.factory.consumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 此方法自动根据表字段生成DO对象，非生产代码
 */
public class CreateClassFromTable {
    private Connection connection;

    /*
    连接数据库
     */
    public void connectDatabase() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/test_oauth?&characterEncoding=utf8&useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
        String userName = "root";
        String password = "123456";
        System.out.println("——开始连接数据库——");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("——数据库连接成功——");
        } catch (Exception e) {
            System.out.println("——数据库连接出现异常——");
            System.out.println(e);
        }
    }

    /*
    JDBC单个查询操作
     */
    public List<String[]> jdbcTest(String tableName) throws Exception {
        //String filePath ="D:\\git\\saas-southeast-fintech\\boss\\src\\main\\java\\com\\rocky\\fintech\\boss\\entity";
        //String fileName = "Dict1" + "DO";
        //String fileSuffix = ".java";
        //String tableName = "tb_dict_partner_business";
        String sql = "select * from " + tableName;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData data = rs.getMetaData();
        List<String[]> columnList = new ArrayList<String[]>();
        while (rs.next()) {
            System.out.println("————————————");
            for (int i = 1; i <= data.getColumnCount(); i++) {
                String columnClassName = data.getColumnClassName(i);
                String columnName = data.getColumnName(i);
                String columnTypeName = data.getColumnTypeName(i);
                //System.out.println(data.getColumnTypeName(i));
                tableName = data.getTableName(i);
                //System.out.println("private " + columnClassName.substring(columnClassName.lastIndexOf(".") + 1) + " " + camelCaseName(columnName) + ";");
                String[] strArray = new String[]{columnName, columnClassName, columnTypeName};
                columnList.add(strArray);
                //System.out.println("<result column=\"" + columnName +"\" property=\"" + camelCaseName(columnName) +"\" jdbcType=\""+ (columnTypeName.equals("BIT") || columnTypeName.equals("INT") ?"INTEGER":columnTypeName) +"\"/>");
            }
            break;
        }
        rs.close();
        pstmt.close();

        return columnList;
    }

    /*
    关闭连接
     */
    public void closeConnection() throws SQLException {
        if (null != connection) {
            connection.close();
        }
    }

    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();

    }

    public static void main(String[] args) throws Exception {
        String serviceName = "user";
        String projectPath = "D:\\stsworkspace2\\factory\\";
        String filePathPrefix = projectPath + "factory-"+serviceName+"\\src\\main\\java\\com\\cdf\\factory\\" + serviceName;
        String fileDOPath = filePathPrefix+ "\\entity";
        String fileMapperPath = filePathPrefix + "\\mapper";
        String controllerPath = filePathPrefix +"\\web";
        String servicePath = filePathPrefix + "\\service";
        Map<String, String> tableMap = new HashMap<>();
        //tableMap.put("tb_dict_partner_business", "Dict12DO");
        //tableMap.put("tb_dict_partner_business_type", "DictTypeDO");
        //tableMap.put("tb_partner", "PartnerDO");
        //tableMap.put("risk_credit_check", "RiskCreditCheckDO");
        System.out.println(fileDOPath);
//        tableMap.put("user_order", "OrderDO");
//        tableMap.put("admin", "AdminDO");
//        tableMap.put("admin_role", "AdminRoleDO");
//        tableMap.put("role", "RoleDO");
//        tableMap.put("role_menu", "RoleMenuDO");
//        tableMap.put("user", "UserDO");
        tableMap.put("role", "RoleDO");
//        tableMap.put("risk_rule_base", "RiskRuleBaseDO");
//        tableMap.put("risk_rule_base_config", "RiskRuleBaseConfigDO");
//        tableMap.put("risk_rule_set", "RiskRuleSetDO");
//        tableMap.put("risk_rule_set_base", "RiskRuleSetBaseDO");
//        tableMap.put("risk_user_level", "RiskUserLevelDO");
//        tableMap.put("risk_user_level_base", "RiskUserLevelBaseDO");
        try {
        	CreateClassFromTable test = new CreateClassFromTable();
            test.connectDatabase();
            tableMap.forEach((key, value) -> {
                try {
                    List<String[]> columnList = test.jdbcTest(key);
                    createDOFile(value, fileDOPath, ".java", columnList);
                    //createMapperFile(value, fileMapperPath, "Mapper.java", columnList);
                    //createMapperXml(value, fileMapperPath, "Mapper.xml", columnList);
                    //createControllerFile(value, "Controller.java", controllerPath);
                    //createServiceFile(value, "Service.java", servicePath);                    
                    createServiceImplFile(value, "ServiceImpl.java", servicePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            test.closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
    }

    private static void createControllerFile(String value, String fileSuffix, String servicePath) throws IOException {
    	File dir = new File(servicePath);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(servicePath + "\\" + value.replaceAll("DO", "") + fileSuffix);
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            
            String object= value.replaceAll("DO", ""); //"Order";
            
            String objectString = value.substring(0, 1).toLowerCase() +  value.substring(1).replaceAll("DO", ""); // "order"
            String service = objectString + "Service"; //"orderService"   
            
            writer = new FileWriter(checkFile, true);
            // 三、向目标文件中写入内容
            writer.append("package "
                            + servicePath.substring(servicePath.indexOf("com")).replaceAll("\\\\", ".")
                            + ";\r\n\n");
            
            writer.append("import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
            		"import org.springframework.web.bind.annotation.DeleteMapping;\r\n" + 
            		"import org.springframework.web.bind.annotation.GetMapping;\r\n" + 
            		"import org.springframework.web.bind.annotation.PathVariable;\r\n" + 
            		"import org.springframework.web.bind.annotation.PostMapping;\r\n" + 
            		"import org.springframework.web.bind.annotation.PutMapping;\r\n" + 
            		"import org.springframework.web.bind.annotation.RequestBody;\r\n" + 
            		"import org.springframework.web.bind.annotation.RequestParam;\r\n" + 
            		"import org.springframework.web.bind.annotation.RestController;\r\n" +
            		"import com.cdf.factory.common.constants.Constants;\r\n" + 
            		"import com.cdf.factory.common.enums.RequestDTO;\r\n" + 
            		"import com.cdf.factory.common.enums.ResponseDTO;\r\n" + 
            		"import com.cdf.factory.common.enums.StatusEnum;\r\n" + 
            		"import com.cdf.factory.user.entity.PageDTO;\r\n" + 
            		"import com.cdf.factory.user.service.RoleService;\r\n" + 
            		"import com.cdf.factory.user.utils.RestResponseUtil;\r\n" + 
            		"import com.github.pagehelper.PageHelper;\r\n");
            
            writer.append("import com.cdf.factory.user.entity."+ value +";\r\n\r\n");
            
            writer.append("@RestController\n"
            		+ "public class " + value.replaceAll("DO", "Controller") + "{\r\n\n");            
                                
            writer.append("	@Autowired\n	" + value.replaceAll("DO", "Service ") + service + ";\r\n\n"); 
            
            writer.append("	@GetMapping(\"/" + objectString + "s\")\n");
            writer.append("	public ResponseDTO<PageDTO<"+ value +" >> list(@RequestParam(value = Constants.Page.PAGE_NUMBER, defaultValue = Constants.Page.PAGE_NUMBER_DEFAULT) int pageNumber, @RequestParam(value = Constants.Page.PAGE_SIZE, defaultValue = Constants.Page.PAGE_SIZE_DEFAULT) int pageSize) {\r\n");
            writer.append("		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, new PageDTO<>(PageHelper.startPage(pageNumber, pageSize)\r\n" + 
            		"                .doSelectPageInfo(() -> this."+ service +".list"+ object +"s(StatusEnum.ENABLE.value()))));\n");
            writer.append("	}\n\n");

            writer.append("	@GetMapping(\"/" + objectString + "s/{id:\\\\d+}\")");
            writer.append("	public ResponseDTO<" + value + ">" +" get(@PathVariable(\"id\") long id) {\n");
            writer.append("		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this." + service + ".get" + object + "(id, StatusEnum.ENABLE.value()));\n");
            writer.append("	}\n\n");
            
            writer.append("	@PostMapping(\"/" + objectString + "s\")\n");
            writer.append("	public ResponseDTO<Boolean> save(@RequestBody RequestDTO<"+ value +"> request) {\n");
            writer.append("		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this." + service + ".save" + object + "(request.getParam()));\n");
            writer.append("	}\n\n");
            
            writer.append("	@PutMapping(\"/" + objectString + "s/{id:\\\\d+}\")\n");
            writer.append("	public ResponseDTO<Boolean> modify(@PathVariable(\"id\") long id, @RequestBody RequestDTO<"+ value +"> request) {\n");
            writer.append("		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this."+ service +".modify" + object + "(request.getParam(), StatusEnum.ENABLE.value()));\n");
            writer.append("	}\n\n");
            
            writer.append("	@DeleteMapping(\"/" + objectString + "s/{id:\\\\d+}\")\n");
            writer.append("	public ResponseDTO<Boolean> remove(@PathVariable(\"id\") long id) {\n");
            writer.append("		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this." + service + ".remove" + object + "(id, StatusEnum.ENABLE.value()));\n");
            writer.append("	}\n");
            
            writer.append("}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
	}

	private static void createServiceFile(String value, String fileSuffix, String servicePath) throws IOException {
    	File dir = new File(servicePath);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(servicePath + "\\" + value.replaceAll("DO", "") + fileSuffix);
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            writer = new FileWriter(checkFile, true);
            // 三、向目标文件中写入内容
            writer.append("package " + servicePath.substring(servicePath.indexOf("com")).replaceAll("\\\\", ".") + ";\r\n\n");

            writer.append("import java.util.List;\n");
            writer.append("import com.cdf.factory.user.entity."+ value +";\n\n");
            
            String object= value.replaceAll("DO", ""); //"Order";            
            String objectString = value.substring(0, 1).toLowerCase() +  value.substring(1).replaceAll("DO", ""); // "order"
            
            writer.append("public interface " + value.replaceAll("DO", "Service") + " {\r\n\n");            
            
            writer.append("	List<" + value +"> list" + object + "s(int status);\n\n");
            writer.append("	"+ value +" get" + object +  "(long id, int status);\n\n");
            writer.append("	boolean save" + object +  "(" + value +  " " + objectString+ ");\n\n");
            writer.append("	boolean modify" + object +  "(" + value +  " " + objectString + ", int status);\n\n");
            writer.append("	boolean remove" + object +  "(long id, int status);\n");
            writer.append("}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
		
	}

	private static void createServiceImplFile(String value, String fileSuffix, String controllerPath) throws IOException {
		File dir = new File(controllerPath);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(controllerPath + "\\" + value.replaceAll("DO", "") + fileSuffix);
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            writer = new FileWriter(checkFile, true);
            // 三、向目标文件中写入内容
            writer.append("package "+ controllerPath.substring(controllerPath.indexOf("com")).replaceAll("\\\\", ".") + ";\r\n\n");
          
            writer.append("import java.util.List;\r\n"+ 
            		"import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
            		"import org.springframework.stereotype.Service;\n");
            writer.append("import com.cdf.factory.user.entity."+ value +";\r\n" + 
            		"import com.cdf.factory.user.mapper." + value.replaceAll("DO", "") + "Mapper;\r\n\n");
            
            writer.append("@Service\n" +
                    "public class " + value.replaceAll("DO", "ServiceImpl implements ") + value.replace("DO", "Service") + " {\r\n\n");            
                     
            //value; //"OrderDO"
            String object= value.replaceAll("DO", ""); //"Order";
            
            String objectString = value.substring(0, 1).toLowerCase() +  value.substring(1).replaceAll("DO", ""); // "order"
            String mapper = objectString + "Mapper"; //"orderMapper"            
            
            writer.append("	@Autowired\n	" +
                     value.replaceAll("DO", "Mapper ") + mapper + ";\r\n\n");             
            
            writer.append("	@Override\n");
            writer.append("	public List<" + value +"> list" + object + "s(int status) {\n");
            writer.append("		return this." + mapper +".select" + object + "List(status);\n");
            writer.append("	}\n\n");
            
            writer.append("	@Override\n");
            writer.append("	public " + value +" get" + object +  "(long id, int status) {\n");
            writer.append("		return this." + mapper + ".select" + object + "(id, status);\n");
            writer.append("	}\n\n");
            
            writer.append("	@Override\n");
            writer.append("	public boolean save" + object +  "(" + value +  " " + objectString + ") {\n");
            writer.append("		return this."+ mapper + ".insert" + object + "(" + objectString + ") > 0;\n");
            writer.append("	}\n\n");
            
            writer.append("	@Override\n");
            writer.append("	public boolean modify" + object +  "(" + value +  " " + objectString + ", int status) {\n");
            writer.append("		return this."+ mapper + ".update" + object + "(" + objectString + ", status) > 0;\n");
            writer.append("	}\n\n");
            
            writer.append("	@Override\n");
            writer.append("	public boolean remove" + object +  "(long id, int status) {\n");
            writer.append("		return this."+ mapper + ".delete" + object + "(id, status) > 0;\n");
            writer.append("	}\n");
            writer.append("}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
		
	}

	private static void createMapperXml(String value, String fileMapperPath, String fileSuffix, List<String[]> columnList) throws IOException {
        File dir = new File(fileMapperPath);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(fileMapperPath + "\\" + value.replaceAll("DO", "") + fileSuffix);
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            // 三、向目标文件中写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            writer = new FileWriter(checkFile, true);

            writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
                    "<mapper namespace=\"" + fileMapperPath.substring(fileMapperPath.indexOf("com")).replaceAll("\\\\", ".")
                    + "." + value.replaceAll("DO", "Mapper") + "\">\n");
            writer.append("    <resultMap id=\"BaseResultMap\" type=\"" + fileMapperPath.substring(fileMapperPath.indexOf("com")).replaceAll("\\\\", ".").replace("mapper", "entity") + "." + value + "\">\n");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < columnList.size(); i++) {
                String[] strings = columnList.get(i);
                if (strings[2].contains("DATETIME")) {
                    strings[2] = "TIMESTAMP";
                }
                if (strings[2].contains("BIT")) {
                    strings[2] = "TINYINT";
                }
                String columnName = strings[0];
                String columnTypeName = strings[2];
                writer.append("        <result column=\"" + columnName + "\" property=\"" + camelCaseName(columnName) + "\" jdbcType=\"" + (columnTypeName.equals("BIT") || columnTypeName.equals("INT") ? "INTEGER" : columnTypeName) + "\"/>\n");
            }
            writer.append(sb.toString());
            writer.append("    </resultMap>\n");
            writer.append("</mapper>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
    }

    private static void createMapperFile(String value, String fileMapperPath, String fileSuffix, List<String[]> columnList) throws IOException {
        File dir = new File(fileMapperPath);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(fileMapperPath + "\\" + value.replaceAll("DO", "") + fileSuffix);
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            // 三、向目标文件中写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            writer = new FileWriter(checkFile, true);
            writer.append(
                    "package "
                            + fileMapperPath.substring(fileMapperPath.indexOf("com")).replaceAll("\\\\", ".")
                            + ";\r\n");

            String object= value.replaceAll("DO", ""); //"Order";
            
            String objectString = value.substring(0, 1).toLowerCase() +  value.substring(1).replaceAll("DO", ""); // "order"
            String mapper = objectString + "Mapper"; //"orderMapper"   
            
            
            writer.append("import java.util.List;\r\n" + 
            		"\r\n" + 
            		"import org.apache.ibatis.annotations.Mapper;\r\n" + 
            		"\r\n" + 
            		"import com.cdf.factory.user.entity." + value + ";\n\n");
            
            writer.append("@Mapper\n" +
                    "public interface " + value.replaceAll("DO", "Mapper") + " {\r\n\n");

            writer.append("	List<"+ value +"> select" + object + "List(int status);\n\n");
            
            writer.append("	" + value + " select" + object + "(long id, int status);\n\n");
            
            writer.append("	int insert"+ object + "(" + value + " " + objectString + ");\n\n");
            
            writer.append("	int update"+ object + "(" + value + " " + objectString + ", int status);\n\n");
            
            writer.append("	int delete"+ object + "(long id, int status);\n\n");
            
            writer.append("}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
    }

    public static void createDOFile(String fileName, String filePath, String fileSuffix, List<String[]> columnList) throws IOException {
        File dir = new File(filePath);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(filePath + "\\" + fileName + fileSuffix);
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            // 三、向目标文件中写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            writer = new FileWriter(checkFile, true);
            writer.append(
                    "package "
                            + filePath.substring(filePath.indexOf("com")).replaceAll("\\\\", ".")
                            + ";\r\n\n");

            StringBuilder importClass = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < columnList.size(); i++) {

                String[] strings = columnList.get(i);
                if (strings[1].contains("Timestamp")) {
                    strings[1] = "java.time.LocalDateTime";
                }
                if (strings[2].contains("BIT")) {
                    strings[1] = "java.lang.Integer";
                }

                System.out.println(i + "=" + strings[0] + "," + strings[1] + "," + strings[2]);
                if(importClass.indexOf(strings[1]) < 0 ) {
                //writer.append("import " + strings[1] + ";\r\n");                
                	importClass.append("import " + strings[1] + ";\r\n");
                }
                sb.append(
                        "   private "
                                + strings[1].substring(strings[1].lastIndexOf(".") + 1)
                                + " "
                                + camelCaseName(strings[0])
                                + ";\r\n");
            }
            
            writer.append(importClass.toString()+ "\n");
            
            writer.append("import lombok.Getter;\n" +
                    "import lombok.Setter;\n\n");
            writer.append("@Getter\n@Setter\n" +
                    "public class " + fileName + " {\r\n");
            writer.append(sb.toString());
            writer.append("}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
    }
}
