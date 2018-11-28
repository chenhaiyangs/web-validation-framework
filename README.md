# 对象参数校验和参数容错处理的工具


提供多种参数验证和参数处理使用方式：<br/>
通过注解的形式提供标准javaBean中的字段的参数验证和处理。<br/>
参数验证：当参数不合法，直接抛出ValidationFailException异常。<br/>
参数处理：对于javaBean一些参数，我们可以做前置容错处理：比如字符串转大写，字符串转小写，去空格，去制表符等处理。<br/>

## 快速使用
```xml
     <!-- 参数验证核心工具 -->
    <dependency>
        <artifactId>web-validation-core</artifactId>
        <groupId>com.github.chenhaiyangs</groupId>
        <version>1.0.0</version>
    </dependency>
```
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

#### [@NotEmpty](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/NotEmpty.java)

标注此注解的字段可以为null，不允许为空串和空白串，只验证字符串。非字符串忽略

#### [@NotNullorEmpty](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/NotNullorEmpty.java)

标注此注解的字段不允许为null，也不允许为空串和空白字符串

#### [@StringLength](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/StringLength.java)

标准此注解的字段验证在字段非null时才生效。<br/>
表示字符串的长度范围。两个属性：min，max，每个属性默认值-1,-1表示不验证min或者max <br/>

#### [@AssertBool](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/AssertBool.java)

标准此注解的字段验证在字段非null时才生效。<br/>
表示字段类型必须是布尔值或者是字符串类型的布尔值

#### [@AssertTrue](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/AssertTrue.java)

标准此注解的字段验证在字段非null时才生效。<br/>
表示字段类型必须是布尔值true或者是字符串类型的布尔值true

#### [@AssertFalse](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/AssertFalse.java)

标准此注解的字段验证在字段非null时才生效。<br/>
表示字段类型必须是布尔值false或者是字符串类型的布尔值false

#### [@Pattern](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/Pattern.java)

表示字符串字段必须符合某正则，字段也不允许为null。

#### [@CollectionSize](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/CollectionSize.java)

标准此注解的字段验证在字段非null时才生效。<br/>
如果字段是一个数组，Collection，Map。该字段可以验证这个数组／collection/map的size在min和max之间，min和max默认为-1，-1表示不验证最小或者最大边界

#### [@IntNumber](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/IntNumber.java)
#### [@LongNumber](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/LongNumber.java)
#### [@DoubleNumber](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/DoubleNumber.java)

标准此以上的字段验证在字段非null时才生效。<br/>
表示标注的字段必须是可以转成对应类型的数字。并且数字范围在min和max之间。-1表示不限制最小值或者最大值

#### [@DateStringFormat](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/DateStringFormat.java)

标准此以上的字段验证在字段非null时才生效。<br/>
表示标注的字段必须是指定format类型的时间，比如：
```java
    public class User {
        
        @NotNull
        @DateStringFormat(format="yyyy-MM-dd")
        private String createTime;
    }
```
但是以上验证不是很严格。比如说format=yyyy-MM-dd。但实际上传递 2018-12-12 12:13:55 这样待小时的时间也被认为是合法的。<br/>
如果你要求必须是严格的yyyy-MM-dd类型，建议加上@StringLength(min=10,max=10)来限制长度。

#### [@ValidChild](./web-validation-core/src/main/java/com/web/validation/core/annotation/valid/ValidChild.java)

标准此以上的字段验证在字段非null时才生效。<br/>
默认情况下，该工具只验证对象的一级参数。如果你的javaBean对象的一个字段也是一个javaBean <br/>
你需要在字段前标注@ValidChild 注解告诉工具需要验证子对象，例如：
```java
    public class User {
        
        @NotNull
        @ValidChild
        private Order order;
    }
```
## 参数处理类注解详解：
 参数处理类注解只能处理字符串类型的字段,且字符串不能为null。<br/>
 
#### [@ToLower](./web-validation-core/src/main/java/com/web/validation/core/annotation/process/ToLower.java)
 
 标注此注解的字段会被处理成全小写

#### [@ToUpper](./web-validation-core/src/main/java/com/web/validation/core/annotation/process/ToUpper.java)
 
 标注此注解的字段会被处理成全大写
 
#### [@TrimAll](./web-validation-core/src/main/java/com/web/validation/core/annotation/process/TrimAll.java)
 
 标注此注解的字段会被处理全部的空格，前后中
 
#### [@TrimPrefixAndSuffix](./web-validation-core/src/main/java/com/web/validation/core/annotation/process/TrimPrefixAndSuffix.java)
 
 标注此注解的字段会处理前后空格
 
#### [@TrimTableChar](./web-validation-core/src/main/java/com/web/validation/core/annotation/process/TrimTableChar.java)

标注此注解的字段会处理制表符和空格。比如 \r,\n,\r\n等。

#### [@ProcessChild](./web-validation-core/src/main/java/com/web/validation/core/annotation/process/ProcessChild.java)

默认情况下，该工具只处理对象的一级参数。如果你的javaBean对象的一个字段也是一个javaBean <br/>
你需要在字段前标注@ProcessChild注解告诉工具需要处理子对象，例如：
```java
    public class User {
        
        @ProcessChild
        private Order order;
    }
```



 
 
 

