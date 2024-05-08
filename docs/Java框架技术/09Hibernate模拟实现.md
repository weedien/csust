## 任务要求

掌握Hibernate ORM核心原理，完成如下任务：

（1）建立一个配置文件Student.properties，格式如下：

com.csust.Student STUDENT

key id:ID:increment

property name:NAME

property age:AGE

（2）解析配置文件，自动生成SQL语句查询指定ID的学生对象

SELECT ID,NAME,AGE FROM STUDENT WHERE ID=?

（3）在JDBCTemplate的基础上封装核心组件MyHibernate，实现Session接口中的方法load(Class clazz,Serializable serializable)
，该方法将查找serializable（如serializable=1）对应的数据，并自动将数据封装为一个Class标识的对象

（4）将任务的主要原理、代码及运行截图形成一个word文档提交

## 主要原理

解析配置文件中数据库表的配置信息，并根据用户的请求生成对应的SQL语句。调用JDBCTemplate的queryForObject方法进行查询操作。

## 核心代码

- 配置文件解析类

```java
import lombok.Data;  
  
import java.io.*;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
import java.util.Properties;  
  
@Data  
public class ConfigParser {  
  
    private Map<String, Object> configs;  
  
    private String tableName;  
  
    private String primaryKey;  
  
    private List<String> columns;  
  
    public ConfigParser(String configPath) {  
        columns = new ArrayList<>();  
        parseConfigFile(configPath);  
    }  
  
    private void parseConfigFile(String configFilePath) {  
  
        String realFilePath = this.getClass().getClassLoader().getResource(configFilePath).getFile();  
        if (realFilePath == null) {  
            throw new IllegalArgumentException("File not found");  
        }  
        try (BufferedReader reader = new BufferedReader(new FileReader(realFilePath))) {  
            String line;  
            while ((line = reader.readLine()) != null) {  
                if (line.startsWith("cn.weedien.csust")) {  
                    tableName = line.split("=")[1];  
                } else if (line.startsWith("key-")) {  
                    primaryKey = line.split("=")[1];  
                } else if (line.startsWith("property-")) {  
                    columns.add(line.split("=")[1]);  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public String generateQuery(String column) {  
        String base = "select " + primaryKey + ", " +String.join(", ", columns) + " from " + tableName + " where ";  
        if (column.equals(primaryKey)) {  
            return base + primaryKey + " = ?";  
        }  
  
        if (columns.contains(column)) {  
            return base + column + " = ?";  
        } else {  
            throw new IllegalArgumentException("Column not found");  
        }  
    }  
  
    public static void main(String[] args) {  
        ConfigParser config = new ConfigParser("Student.properties");  
        System.out.println(config);  
        System.out.println(config.generateQuery("id"));  
    }  
}
```

- MyHibernate

```java
import java.io.Serializable;  
  
public class MyHibernate {  
  
    public <T> T load(Class<T> clazz, Serializable serializable) {  
       ConfigParser config = new ConfigParser("Student.properties");  
  
       String query = config.generateQuery("id");  
  
        return JDBCTemplate.queryForObject(query, clazz, serializable);  
    }  
  
    public static void main(String[] args) {  
        MyHibernate h = new MyHibernate();  
        StudentDO student =  h.load(StudentDO.class, 1);  
        System.out.println(student);  
    }  
}
```

## 运行结果

根据id查询学生信息：
![image.png](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/20240404195543.png)
