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
public class GetJavaDOFromMysqlDatabase {
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
        String serviceName = "consumer";
        String projectPath = "D:\\stsworkspace2\\factory\\";
        String filePath = projectPath
                + "factory-consumer\\src\\main\\java\\com\\cdf\\factory\\" + serviceName + "\\entity";
        //String fileMapperPath = projectPath + serviceName + "\\src\\main\\java\\com\\rocky\\fintech\\" + serviceName + "\\mapper";
        Map<String, String> tableMap = new HashMap<>();
        //tableMap.put("tb_dict_partner_business", "Dict12DO");
        //tableMap.put("tb_dict_partner_business_type", "DictTypeDO");
        //tableMap.put("tb_partner", "PartnerDO");
        //tableMap.put("risk_credit_check", "RiskCreditCheckDO");
        System.out.println(filePath);
        tableMap.put("sale_order", "OrderDO");
//        tableMap.put("risk_rule_base", "RiskRuleBaseDO");
//        tableMap.put("risk_rule_base_config", "RiskRuleBaseConfigDO");
//        tableMap.put("risk_rule_set", "RiskRuleSetDO");
//        tableMap.put("risk_rule_set_base", "RiskRuleSetBaseDO");
//        tableMap.put("risk_user_level", "RiskUserLevelDO");
//        tableMap.put("risk_user_level_base", "RiskUserLevelBaseDO");
        try {
        	GetJavaDOFromMysqlDatabase test = new GetJavaDOFromMysqlDatabase();
            test.connectDatabase();
            //tableMap.forEach((key, value) -> {
                try {
                    List<String[]> columnList = test.jdbcTest("sale_order");
                    createDOFile("OrderDO", filePath, ".java", columnList);
                    //createMapperFile(value, fileMapperPath, "Mapper.java", columnList);
                    //createMapperXml(value, fileMapperPath, "Mapper.xml", columnList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
           // });
            test.closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

            //           StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < columnList.size(); i++) {
//                String[] strings = columnList.get(i);
//                if (strings[1].contains("Timestamp")) {
//                    strings[1] = "java.util.LocalDateTime";
//                }
//                if (strings[2].contains("BIT")) {
//                    strings[1] = "java.util.Integer";
//                }
//                writer.append("import " + strings[1] + ";\r\n");
//                sb.append(
//                        "   private "
//                                + strings[1].substring(strings[1].lastIndexOf(".") + 1)
//                                + " "
//                                + camelCaseName(strings[0])
//                                + ";\r\n");
//            }
            writer.append("@Mapper\n" +
                    "public interface " + value.replaceAll("DO", "Mapper") + "{\r\n");
            //writer.append(sb.toString());
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
                            + ";\r\n");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < columnList.size(); i++) {

                String[] strings = columnList.get(i);
                if (strings[1].contains("Timestamp")) {
                    strings[1] = "java.time.LocalDateTime";
                }
                if (strings[2].contains("BIT")) {
                    strings[1] = "java.util.Integer";
                }

                System.out.println(i + "=" + strings[0] + "," + strings[1] + "," + strings[2]);
                writer.append("import " + strings[1] + ";\r\n");
                sb.append(
                        "   private "
                                + strings[1].substring(strings[1].lastIndexOf(".") + 1)
                                + " "
                                + camelCaseName(strings[0])
                                + ";\r\n");
            }
            writer.append("import lombok.Getter;\n" +
                    "import lombok.Setter;\n");
            writer.append("@Getter\n@Setter\n" +
                    "public class " + fileName + "{\r\n");
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
