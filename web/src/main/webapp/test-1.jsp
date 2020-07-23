<%@ page import="java.util.Properties" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: aohaiyang
  Date: 2019/12/13
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    //资源配置
    static String dbUrl = "jdbc:mysql://120.24.242.184:3306/finebi_saic?useUnicode=true";
    static String dbUser = "root";
    static String dbPasswd = "1234qwer";


    //加载数据
    public static class Tool {

        public static void loadData() {

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            StringBuilder result = new StringBuilder("[");

            try {
                //创建连接
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);

                //查询数据
                preparedStatement = connection.prepareStatement("select tag,reportpath from app_search_function");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    result.append("\"").append(resultSet.getString(1)).append(",").append(resultSet.getString(2)).append("\"");
                }

                //放入缓存
                Cache.setData(result.substring(0, result.length()) + "]");
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                //释放资源
                release(connection, preparedStatement);
            }
        }

        /**
         * 释放资源
         * @param connection
         * @param preparedStatement
         */
        private static void release(Connection connection, PreparedStatement preparedStatement) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class CacheThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println(111111111);
                while (1 > 0) {
                    Tool.loadData();
                    //等待循环 ms
                    sleepTime(60000);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        /**
         * 等待
         * @param time
         */
        private void sleepTime(long time) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 数据缓存
     */
    public static class Cache {

        private volatile static String data;

        static {
            new CacheThread().start();
        }

        public static String getData() {
            if (data == null) {
                Tool.loadData();
            }
            return data;
        }

        public static void setData(String newData) {
            data = newData;
        }
    }
%>

<%=Cache.getData()%>
</body>
</html>
