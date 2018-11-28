# 对象参数校验和参数容错处理的工具


提供多种参数验证和参数处理使用方式：<br/>
通过注解的形式提供标准javaBean中的字段的参数验证和处理。<br/>
参数验证：当参数不合法，直接抛出ValidationFailException异常。<br/>
参数处理：对于javaBean一些参数，我们可以做前置容错处理：比如字符串转大写，字符串转小写，去空格，去制表符等处理。<br/>

## 参数验证的主体

只针对javaBean进行参数验证（javaBean要提供字段的get/set），不能直接处理原生数据类型。所有注解类型都是注解在字段级别。
例如如下代码，用来生明对javaBean中具体参数的参数验证逻辑：

```java
    public class User {
        @NotNull
        private String userId;
    
        @IntNumber(min = 6,max = 12)
        private int age;
        
        @DateStringFormat(format = "yyyy-MM-dd")
        @StringLength(min = 10,max = 10)
        private String timeStemp;
    }
```
## 参数验证类注解详解

在com.web.validation.core.annotation.valid包下包含所有的验证相关的注解：<br/>
除了@ValidChild注解，其他注解都具有两个公共属性code，默认为''。message,默认为 field %s validate error，rule : xxx <br/>
注解上的code和message可以自己设置，在参数验证失败抛出的异常ValidationFailException中能够获取code和message。xxx表示每个注解的注解名称 <br/>
message的设置 如果添加了 %s ，%s会自动映射到出错的字段上。<br/>
下面注解的讲解不会讲解公共属性。<br/>

#### [@NotNull](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/NotNull.java)

标注此注解的字段不允许为null，但允许为空

