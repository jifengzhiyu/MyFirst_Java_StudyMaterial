#  枚举类

```java
/**
 * 一、枚举类的使用
 * 1.枚举类的理解：类的对象只有有限个，确定的。我们称此类为枚举类
 * 2.当需要定义一组常量时，强烈建议使用枚举类
 * 3.如果枚举类中只有一个对象，则可以作为单例模式的实现方式。
 *
 * 二、如何定义枚举类
 * 方式一：jdk5.0之前，自定义枚举类
 * 方式二：jdk5.0，可以使用enum关键字定义枚举类
 *
 * 三、Enum类中的常用方法：
 *    values()方法：返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
 *    valueOf(String str)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。
 *    如不是，会有运行时异常：IllegalArgumentException。
 *    toString()：返回当前枚举类对象常量的名称(枚举类的父类会重写toString())
 *
 * 四、使用enum关键字定义的枚举类实现接口的情况
 *   情况一：实现接口，在enum类中实现抽象方法
 *   情况二：让枚举类的对象分别实现接口中的抽象方法
 */
```

## 自定义枚举类

```java
public class SeasonTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);
    }
}
//自定义枚举类
class Season{
    //1.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器,并给对象属性赋值
    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供当前枚举类的多个对象：public static final的
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }
    
    //4.其他诉求1：提供toString()
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
```

## enum

> enum定义枚举类
>
> enum主要方法
>
> 实现接口的枚举类（正常的实现方式；让每个枚举对象实现接口）

- 与switch case使用

![image-20220506173228023](Pic/image-20220506173228023.png)

- 枚举类

```java
//使用enum关键字枚举类
enum Season1 implements Info{
    //1.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
    SPRING("春天","春暖花开"){
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("夏天","夏日炎炎"){
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    AUTUMN("秋天","秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天","冰天雪地"){
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    //2.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器,并给对象属性赋值
    private Season1(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
//    //4.其他诉求1：提供toString()
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }

//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }
}
```

- main和接口

```java
/**
 * 使用enum关键字定义枚举类
 * 说明：定义的枚举类默认继承于java.lang.Enum类
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        //toString():返回枚举类对象的名称
        System.out.println(summer.toString());//SUMMER

        System.out.println(Season1.class.getSuperclass());
        //class java.lang.Enum

        System.out.println("****************");
        //values():返回所有的枚举类对象构成的数组
        Season1[] values = Season1.values();
        for(int i = 0;i < values.length;i++){
            System.out.println(values[i]);
            values[i].show();
        }

        System.out.println("****************");
        //valueOf(String objName):返回枚举类中对象名是objName的对象。
        Season1 winter = Season1.valueOf("WINTER");
        //如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
        System.out.println(winter);
        winter.show();
    }
}

interface Info{
    void show();
}
```

# Annotation注解

框架理解 = +反射+设计模式

![image-20220506173535268](Pic/image-20220506173535268.png)

```JAVA
/**
 * 注解的使用
 * 1. 理解Annotation:
 * ① jdk 5.0 新增的功能
 *
 * ② Annotation 其实就是代码里的特殊标记, 这些标记可以在编译, 类加载, 运行时被读取, 并执行相应的处理。通过使用 Annotation,
 * 程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
 *
 * ③在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android
 * 中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗
 * 代码和XML配置等。
 /
```

## 常见的Annotation

```java
/**2. Annocation的使用示例
* 示例一：生成文档相关的注解
* 示例二：在编译时进行格式检查(JDK内置的三个基本注解)
    @Override: 限定重写父类方法/实现接口方法, 该注解只能用于方法
    @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
    @SuppressWarnings: 抑制编译器警告

    @SuppressWarnings("unused")
    int num = 10;

 * 示例三：跟踪代码依赖性，实现替代配置文件功能
 /
```

## 自定义Annotation

![image-20220506200508355](Pic/image-20220506200508355.png)

```java
/* 3. 如何自定义注解：参照@SuppressWarnings定义
    * ① 注解声明为：@interface
    * ② 内部定义成员，通常使用value表示
    * ③ 可以指定成员的默认值，使用default定义（有默认值就可以不在使用注解的时候指明成员的值）
    * ④ 如果自定义注解没有成员，表明是一个标识作用。

   如果注解有成员，在使用注解时，需要指明成员的值。
   自定义注解必须配上注解的信息处理流程(使用反射)才有意义。
   自定义注解通过都会指明两个元注解：Retention、Target
   /
```

```java
@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
    String value() default "hello";
}
```

```java
//@MyAnnotation(value="hi")
//@MyAnnotation("abc")//只有一个参数成员且名字是value可以省略value=
//@MyAnnotation//有默认值就可以不在使用注解的时候指明成员的值
class Person{
    private String name;
    private int age;

    public Person() {
    }
    @MyAnnotation
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @MyAnnotation(value="abc")
    public void walk(){
        System.out.println("人走路");
    }
    public void eat(){
        System.out.println("人吃饭");
    }
}
```

## 元注解

![image-20220506201846133](Pic/image-20220506201846133.png)

![image-20220506202058018](Pic/image-20220506202058018.png)

![image-20220506202726750](Pic/image-20220506202726750.png)

- 没有指定哪里都能用

![image-20220506203224617](Pic/image-20220506203224617.png)

```java
/*4. jdk 提供的4种元注解
  元注解：对现有的注解进行解释说明的注解
Retention：指定所修饰的 Annotation 的生命周期：SOURCE\CLASS（默认行为）\RUNTIME
       只有声明为RUNTIME生命周期的注解，才能通过反射获取。
Target:用于指定被修饰的 Annotation 能用于修饰哪些程序元素
*******出现的频率较低*******
Documented:表示所修饰的注解在被javadoc解析时，保留下来。
Inherited:被它修饰的 Annotation 将具有继承性。
/
```

```java
@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
    String value() default "hello";
}
```

## 利用反射获取注解信息

```
5,如何获取注解信息：通过发射来进行获取、调用。
前提：要求此注解的元注解Retention中声明的生命周期状态为：RUNTIME.
```

## JDK8新特性

![image-20220507160029782](Pic/image-20220507160029782.png)

### 可重复注解

```java
6.1 可重复注解：① 在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class
              ② MyAnnotation的Target和Retention,@Inherited等元注解与MyAnnotations相同。
```

```java
//jdk 8之前的写法：
//@MyAnnotations({@MyAnnotation(value="hi"),@MyAnnotation(value="hi")})
@MyAnnotation(value="hi")
@MyAnnotation(value="abc")
class Person{}
```

```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
public @interface MyAnnotations {
    MyAnnotation[] value();
}
```

```java
@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
    String value() default "hello";
}
```

### 类型注解

```java
6.2 类型注解：
ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。
```

```java
class Generic<@MyAnnotation T>{
    public void show() throws @MyAnnotation RuntimeException{
        ArrayList<@MyAnnotation String> list = new ArrayList<>();
        int num = (@MyAnnotation int) 10L;
    }
}
```

# 集合框架

**能用集合就不用数组**

一个集合里面可以放各种类型的数据（对象）

```java
/**
 * 一、集合框架的概述
 *
 * 1.集合、数组都是对多个数据进行存储操作的结构，简称Java容器。
 *  说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储（.txt,.jpg,.avi，数据库中）
 *
 * 2.1 数组在存储多个数据方面的特点：
 *      > 一旦初始化以后，其长度就确定了。
 *      > 数组一旦定义好，其元素的类型也就确定了。我们也就只能操作指定类型的数据了。
 *       比如：String[] arr;int[] arr1;Object[] arr2;
 * 2.2 数组在存储多个数据方面的缺点：
 *      > 一旦初始化以后，其长度就不可修改。
 *      > 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率不高。
 *      > 获取数组中实际元素的个数的需求，数组没有现成的属性或方法可用
 *      > 数组存储数据的特点：有序、可重复。对于无序、不可重复的需求，不能满足。
 *
 * 二、集合框架
 *      |----Collection接口：单列集合，用来存储一个一个的对象
 *          |----List接口：存储有序的、可重复的数据。  -->“动态”数组
 *              |----ArrayList、LinkedList、Vector
 *
 *          |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
 *              |----HashSet、LinkedHashSet、TreeSet
 *
 *      |----Map接口：双列集合，用来存储一对(key - value)一对的数据   -->高中函数：y = f(x)
 *              |----HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 /
```

![image-20220507162103862](Pic/image-20220507162103862.png)

![image-20220507164301013](Pic/image-20220507164301013.png)

- List Set是Collection接口的子接口

## Collection接口

![image-20220507165258046](Pic/image-20220507165258046.png)

![image-20220508095516665](Pic/image-20220508095516665.png)

![image-20220508095532909](Pic/image-20220508095532909.png)

**向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals().**

- 基本方法

```java
public class CollectionTest {
    @Test
    public void test1(){
        //Collection常用抽象方法
        Collection coll = new ArrayList();

        //add(Object e):将元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱Integer
        coll.add(new Date());

        //size():获取添加的元素的个数
        System.out.println(coll.size());//4

        //addAll(Collection coll1):将coll1集合中的元素添加到当前的集合中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);

        System.out.println(coll.size());//6
        System.out.println(coll);
        //[AA, BB, 123, Sat May 07 19:22:24 CST 2022, 456, CC]

        //clear():清空集合元素，不是变成NULL
        coll.clear();

        //isEmpty():判断当前集合是否为空,是否有元素
        System.out.println(coll.isEmpty());
        //true
    }
}
```

- 是否包含obj

```java
@Test
public void test1(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new Person("Jerry",20));
    coll.add(new String("Tom"));
    coll.add(false);
    //1.contains(Object obj):判断当前集合中是否包含obj
    //我们在判断时会调用obj对象所在类的equals()。
    System.out.println(coll.contains(123));
    System.out.println(coll.contains(new String("Tom")));//true 比较内容
    ////false -->true 重写自定义类的equals()
    System.out.println(coll.contains(new Person("Jerry",20)));

    //2.containsAll(Collection coll1):判断形参coll1中的所有元素是否都存在于当前集合中。
    Collection coll1 = Arrays.asList(123,4567);
    System.out.println(coll.containsAll(coll1));
}
```

- 移除obj元素

```java
@Test
public void test2(){
    //3.remove(Object obj):从当前集合中移除obj元素。
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new Person("Jerry",20));
    coll.add(new String("Tom"));
    coll.add(false);

    coll.remove(1234);
    System.out.println(coll);

    coll.remove(new Person("Jerry",20));
    System.out.println(coll);

    //4. removeAll(Collection coll1):差集：从当前集合中移除coll1中所有的元素。
    Collection coll1 = Arrays.asList(123,456);
    coll.removeAll(coll1);
    System.out.println(coll);
}
```

- 获取当前集合和coll1集合的交集

```java
@Test
public void test3(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new Person("Jerry",20));
    coll.add(new String("Tom"));
    coll.add(false);

    //5.retainAll(Collection coll1):交集：获取当前集合和coll1集合的交集，并返回给当前集合(直接在当前集合修改)
    Collection coll1 = Arrays.asList(123,456,789);
    coll.retainAll(coll1);
    System.out.println(coll);
    //[123, 456]
    }
```

- equals(Object obj)

```java
@Test
public void test3(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new Person("Jerry",20));
    coll.add(new String("Tom"));
    coll.add(false);

    //6.equals(Object obj):要想返回true，需要当前集合和形参集合的元素都相同。
    //顺序是否需要相同取决于子类 -> 这里ArrayList要求顺序相同
    Collection coll1 = new ArrayList();
    coll1.add(456);
    coll1.add(123);
    coll1.add(new Person("Jerry",20));
    coll1.add(new String("Tom"));
    coll1.add(false);

    System.out.println(coll.equals(coll1));
}
```

- 数组集合相互转换

```java
@Test
public void test4(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new Person("Jerry",20));
    coll.add(new String("Tom"));
    coll.add(false);

    //7.hashCode():返回当前对象的哈希值
    System.out.println(coll.hashCode());

    //8.集合 --->数组：toArray()
    Object[] arr = coll.toArray();
    for(int i = 0;i < arr.length;i++){
        System.out.println(arr[i]);
    }

    //拓展：数组 --->集合:调用Arrays类的静态方法asList()
    List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
    System.out.println(list);

    //装入非对象数组，认为装入一个数组（作为集合的元素）
    //装入对象数组，数组包含几个对象，集合元素就是几个
    List arr1 = Arrays.asList(new int[]{123, 456});
    System.out.println(arr1.size());//1

    List arr2 = Arrays.asList(new Integer[]{123, 456});
    System.out.println(arr2.size());//2
}
```

## Iterator迭代器接口

**lterator仅用于遍历实现Collection接口的集合(Map不用)**

![image-20220508104544327](Pic/image-20220508104544327.png)

### 遍历输出集合

```java
@Test
public void test1(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new Person("Jerry",20));
    coll.add(new String("Tom"));
    coll.add(false);

    Iterator iterator = coll.iterator();
    //方式1
    ////hasNext():判断是否还有下一个元素
    while(iterator.hasNext()){//指针不变，看看下面又没有数据
        //next():①指针下移 ②将下移以后集合位置上的元素返回
        System.out.println(iterator.next());
    }
  
    //方式2
    coll.forEach(System.out::println);
}
```

- 遍历错误方式

```java
@Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //错误方式一：
        //出现越界
//        Iterator iterator = coll.iterator();
//        while((iterator.next()) != null){
//            System.out.println(iterator.next());
//        }

        //错误方式二：
        //集合对象每次调用iterator()方法都得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前。
        //输出第一个元素，死循环
        while (coll.iterator().hasNext()){
            System.out.println(coll.iterator().next());
        }
    }
```

### remove()

```java
//测试Iterator中的remove()
//如果还未调用next(),再调用remove会报IllegalStateException。
//如果在上一次调用 next 方法之后已经调用了 remove 方法，再调用remove会报IllegalStateException。
@Test
public void test3(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new Person("Jerry",20));
    coll.add(new String("Tom"));
    coll.add(false);

    //删除集合中"Tom"
    Iterator iterator = coll.iterator();
    while (iterator.hasNext()){
        Object obj = iterator.next();
        if("Tom".equals(obj)){
            iterator.remove();
        }
    }
    //遍历集合
    iterator = coll.iterator();
    while (iterator.hasNext()){
        System.out.println(iterator.next());
    }
}
```

### foreach增强for循环

![image-202205081107 22125](Pic/image-20220508110722125.png)

```java
@Test
//针对集合
public void test1(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new Person("Jerry",20));
    coll.add(new String("Tom"));
    coll.add(false);

    //for(集合元素的类型 局部变量 : 集合对象)
    //内部仍然调用了迭代器。
    for(Object obj : coll){
        System.out.println(obj);
    }
}
```

```java
@Test
//针对数组
public void test2(){
    int[] arr = new int[]{1,2,3,4,5,6};
    //for(数组元素的类型 局部变量 : 数组对象)
    //数组也是对象
    for(int i : arr){
        System.out.println(i);
    }
}
```

- 易错点

```java
//练习题
    @Test
    public void test3(){
        String[] arr = new String[]{"MM","MM","MM"};

//        //方式一：普通for赋值
//        for(int i = 0;i < arr.length;i++){
//            arr[i] = "GG";
//        }//变成GG

        //方式二：增强for循环
        for(String s : arr){
            s = "GG";//没有变成GG
        }

        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }
    }
```

## Collection子接口一：List

```java
/**List接口框架
*
*    |----Collection接口：单列集合，用来存储一个一个的对象
*          |----List接口：存储有序的、可重复的数据。  -->“动态”数组,替换原有的数组
*              |----ArrayList：作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储
*              |----LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高(一个改，移动一堆)；底层使用双向链表存储
*              |----Vector：作为List接口的古老实现类；线程安全的，效率低；底层使用Object[] elementData存储/
```

![image-20220508113048466](Pic/image-20220508113048466.png)

- List接口中的常用方法

```java
/*
void add(int index, Object ele):在index位置插入ele元素
boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
Object get(int index):获取指定index位置的元素
int indexOf(Object obj):返回obj在集合中首次出现的位置
int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
Object remove(int index):移除指定index位置的元素，并返回此元素
Object set(int index, Object ele):设置指定index位置的元素为ele
List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的子集合

总结：常用方法
增：add(Object obj)
删：remove(int index) / remove(Object obj)
改：set(int index, Object ele)
查：get(int index)
插：add(int index, Object ele)
长度：size()
遍历：① Iterator迭代器方式
     ② 增强for循环
     ③ 普通的循环
     */
```

```java
//遍历
@Test
public void test3(){
    ArrayList list = new ArrayList();
    list.add(123);
    list.add(456);
    list.add("AA");

    //方式一：Iterator迭代器方式
    Iterator iterator = list.iterator();
    while(iterator.hasNext()){
        System.out.println(iterator.next());
    }

    System.out.println("***************");

    //方式二：增强for循环
    for(Object obj : list){
        System.out.println(obj);
    }
}
```

```java
@Test
public void test2(){
    ArrayList list = new ArrayList();
    list.add(123);
    list.add(456);
    list.add("AA");
    list.add(new Person("Tom",12));
    list.add(456);
    //int indexOf(Object obj):返回obj在集合中首次出现的位置。如果不存在，返回-1.
    //找不到返回值一般都是负数
    int index = list.indexOf(4567);
    System.out.println(index);

    //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置。如果不存在，返回-1.
    System.out.println(list.lastIndexOf(456));

    //Object remove(int index):移除指定index位置的元素，并返回此元素
    Object obj = list.remove(0);
    System.out.println(obj);
    System.out.println(list);

    //Object set(int index, Object ele):设置指定index位置的元素为ele
    list.set(1,"CC");
    System.out.println(list);

    //List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的左闭右开区间的子集合
    List subList = list.subList(2, 4);
    System.out.println(subList);
    //[Person{name='Tom', age=12}, 456]
    System.out.println(list);
    //[456, CC, Person{name='Tom', age=12}, 456]
}
```

```java
//插入，获取
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);

        System.out.println(list);

        //void add(int index, Object ele):在index位置插入ele元素
        list.add(1,"BB");
        System.out.println(list);

        //boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
//        list.add(list1);
        System.out.println(list.size());//9

        //Object get(int index):获取指定index位置的元素
        System.out.println(list.get(0));
    }
```

- 区分List中remove(int index)和remove(Object obj)

```java
public class ListExer {
    /*
    区分List中remove(int index)和remove(Object obj)
     */
    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//
    }

    private void updateList(List list) {
        //移除对应角标数据
        list.remove(2);
        //移除指定数据
        list.remove(new Integer(2));
    }
}
```

### ArrayList(代替数组)

```java
2. ArrayList的源码分析：
*   2.1 jdk 7情况下
*      ArrayList list = new ArrayList();//底层创建了长度是10的Object[]数组elementData
*      list.add(123);//elementData[0] = new Integer(123);
*      ...
*      list.add(11);//如果此次的添加导致底层elementData数组容量不够，则扩容。
*      默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
*
*      结论：建议开发中使用带参的构造器：ArrayList list = new ArrayList(int capacity)
*
*   2.2 jdk 8中ArrayList的变化：
*      ArrayList list = new ArrayList();//底层Object[] elementData初始化为{}.并没有创建长度为10的数组
*
*      list.add(123);//第一次调用add()时，底层才创建了长度10的数组，并将数据123添加到elementData[0]
*      ...
*      后续的添加和扩容操作与jdk 7 无异。
*   2.3 小结：jdk7中的ArrayList的对象的创建类似于单例的饿汉式，而jdk8中的ArrayList的对象
*            的创建类似于单例的懒汉式，延迟了数组的创建，节省内存。
```

### LinkedList

```java
3. LinkedList的源码分析：
*      LinkedList list = new LinkedList(); 内部声明了Node类型的first和last属性，默认值为null
*      list.add(123);//将123封装到Node中，创建了Node对象。
*
*      其中，Node定义为：体现了LinkedList的双向链表的说法
*      private static class Node<E> {
            E item;
            Node<E> next;
            Node<E> prev;

            Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
            }
        }
```

- LinkedList双向链表：

![image-20220508114330290](Pic/image-20220508114330290.png)

![image-20220508114440105](Pic/image-20220508114440105.png)

### Vector(不太用)

```java
4. Vector的源码分析：jdk7和jdk8中通过Vector()构造器创建对象时，底层都创建了长度为10的数组。
*      在扩容方面，默认扩容为原来的数组长度的2倍。
  
  栈底层是Vector，先进后出
```

## Collection-子接口二：Set

- Set下面这些实现类的底层就是Map实现类
  - HashSet --- HashMap
  - LinkedHashSet --- LinkedHashMap
  - TreeSet ---- TreeMap

```java
/**
 * 1. Set接口的框架：
 *
 * |----Collection接口：单列集合，用来存储一个一个的对象
 *          |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
 *              |----HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
 *                  |----LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历
 *                                      对于频繁的遍历操作，LinkedHashSet效率高于HashSet.
 *              |----TreeSet：可以按照添加对象的指定属性，进行排序。
 *
 *  1. Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法。
 *
 *  2. 要求：向Set(主要指：HashSet、LinkedHashSet)中添加的数据，其所在的类一定要重写hashCode()和equals()
 *     要求：重写的hashCode()和equals()尽可能保持一致性：相等的对象必须具有相等的散列码(哈希值)
 *      重写两个方法的小技巧：对象中用作 equals() 方法比较的 Field，都应该用来计算 hashCode 值。
 */
```

```java
/*
一、Set：存储无序的、不可重复的数据
以HashSet为例说明：
1. 无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的。

2. 不可重复性：保证添加的元素按照equals()判断时，不能返回true.即：相同的元素只能添加一个。

二、添加元素的过程：以HashSet为例：
    我们向HashSet中添加元素a,首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
    此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为：索引位置），判断
    数组此位置上是否已经有元素：
        如果此位置上没有其他元素，则元素a添加成功。 --->情况1
        如果此位置上有其他元素b(或以链表形式存在的多个元素），则比较元素a与元素b的hash值：
            如果hash值不相同，则元素a添加成功。--->情况2
            如果hash值相同，进而需要调用元素a所在类的equals()方法：
                   equals()返回true,元素a添加失败
                   equals()返回false,则元素a添加成功。--->情况2

    对于添加成功的情况2和情况3而言：元素a 与已经存在指定索引位置上数据以链表的方式存储。
    jdk 7 :元素a放到数组中，指向原来的元素。
    jdk 8 :原来的元素在数组中，指向元素a
    总结：七上八下

    HashSet底层：数组+链表的结构。
 */
```

- 常用方法：Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法。

```java
@Test
public void test1(){
    Set set = new HashSet();
    set.add(456);
    set.add(123);
    set.add(123);
    set.add("AA");
    set.add("CC");
    set.add(new User("Tom",12));
    set.add(new User("Tom",12));
    set.add(129);

    Iterator iterator = set.iterator();
    while(iterator.hasNext()){
        System.out.println(iterator.next());
    }
}
/*
AA
CC
129
456
123
User{name='Tom', age=12}
/
```

![image-20220509151928212](Pic/image-20220509151928212.png)

![image-20220509152325918](Pic/image-20220509152325918.png)

### HashSet

![image-20220509151857645](Pic/image-20220509151857645.png)

```java
二、添加元素的过程：以HashSet为例：
    我们向HashSet中添加元素a,首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
    此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为：索引位置），判断
    数组此位置上是否已经有元素：
        如果此位置上没有其他元素，则元素a添加成功。 --->情况1
        如果此位置上有其他元素b(或以链表形式存在的多个元素），则比较元素a与元素b的hash值：
            如果hash值不相同，则元素a添加成功。--->情况2
            如果hash值相同，进而需要调用元素a所在类的equals()方法：
                   equals()返回true,元素a添加失败
                   equals()返回false,则元素a添加成功。--->情况2

    对于添加成功的情况2和情况3而言：元素a 与已经存在指定索引位置上数据以链表的方式存储。(jdk7)
    jdk8底层 HashSet与HashMap有关.放在key的位置;TreeSet与TreeMap有关；
    
    jdk 7 :元素a放到数组中，指向原来的元素。
    jdk 8 :原来的元素在数组中，指向元素a
    总结：七上八下

    HashSet底层：数组+链表的结构。
```

### LinkedHashSet

```java
//LinkedHashSet的使用
//LinkedHashSet作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个
//数据和后一个数据。
//优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet
@Test
//看上去打印下来是有序的，实际存储是无序的
public void test2(){
    Set set = new LinkedHashSet();
    set.add(456);
    set.add(123);
    set.add(123);
    set.add("AA");
    set.add("CC");
    set.add(new User("Tom",12));
    set.add(new User("Tom",12));
    set.add(129);

    Iterator iterator = set.iterator();
    while(iterator.hasNext()){
        System.out.println(iterator.next());
    }
    /*
    456
    123
    AA
    CC
    User{name='Tom', age=12}
    129
     */
}
```

![image-20220509153743884](Pic/image-20220509153743884.png)

### TreeSet

- 不用hash，不用equals(),用排序

![image-20220509160411535](Pic/image-20220509160411535.png)

- 把大的小的数据分开

```java
/*
1.向TreeSet中添加的数据，要求是相同类的对象。（否则怎么比较？
2.两种排序方式：自然排序（实现Comparable接口） 和 定制排序（Comparator）

3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0.不再是equals().
4.定制排序中，比较两个对象是否相同的标准为：compare()返回0.不再是equals().
 */
```

- 自然排序

```java
//User类节选
//按照姓名从大到小排列,年龄从小到大排列（添加不用equlls(),用这个判断）
    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User user = (User)o;
//            return -this.name.compareTo(user.name);
            int compare = -this.name.compareTo(user.name);
            if(compare != 0){
                return compare;
            }else{
                return Integer.compare(this.age,user.age);
            }
        }else{
            throw new RuntimeException("输入的类型不匹配");
        }
    }
```

```java
@Test
public void test1(){
    TreeSet set = new TreeSet();

    set.add(new User("Tom",12));
    set.add(new User("Jerry",32));
    set.add(new User("Jim",2));
    set.add(new User("Mike",65));
    set.add(new User("Jack",33));
    set.add(new User("Jack",56));

    Iterator iterator = set.iterator();
    while(iterator.hasNext()){
        System.out.println(iterator.next());
    }
    /*
    User{name='Tom', age=12}
    User{name='Mike', age=65}
    User{name='Jim', age=2}
    User{name='Jerry', age=32}
    User{name='Jack', age=33}
    User{name='Jack', age=56}
     */
}
```

- 定制排序

```java
@Test
public void test2(){
    Comparator com = new Comparator() {
        //按照年龄从小到大排列,不管其他
        //如果两个对象名字相同，那么是否加到集合里面取决于这两个对象加入的先后顺序
        @Override
        public int compare(Object o1, Object o2) {
            if(o1 instanceof User && o2 instanceof User){
                User u1 = (User)o1;
                User u2 = (User)o2;
                return Integer.compare(u1.getAge(),u2.getAge());
            }else{
                throw new RuntimeException("输入的数据类型不匹配");
            }
        }
    };

    TreeSet set = new TreeSet(com);
    set.add(new User("Tom",12));
    set.add(new User("Jerry",32));
    set.add(new User("Jim",2));
    set.add(new User("Mike",65));
    set.add(new User("Mary",33));
    set.add(new User("Jack",33));
    set.add(new User("Jack",56));

    Iterator iterator = set.iterator();
    while(iterator.hasNext()){
        System.out.println(iterator.next());
    }
}
```

## Map接口

```java
/**
 * 一、Map的实现类的结构：
 *  |----Map:双列数据，存储key-value对的数据   ---类似于高中的函数：y = f(x)
 *         |----HashMap:作为Map的主要实现类；线程不安全的，效率高；存储null的key和value
 *              |----LinkedHashMap:保证在遍历map元素时，可以按照添加的顺序实现遍历。
 *                      原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
 *                      对于频繁的遍历操作，此类执行效率高于HashMap。
 *         |----TreeMap:保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
 *                      底层使用红黑树
 *         |----Hashtable:作为古老的实现类；线程安全的，效率低；不能存储null的key和value
 *              |----Properties:常用来处理配置文件。key和value都是String类型
 *
 *
 *      HashMap的底层：数组+链表  （jdk7及之前）
 *                    数组+链表+红黑树 （jdk 8）
 *
 *
 *  面试题：
 *  1. HashMap的底层实现原理？
 *  2. HashMap 和 Hashtable的异同？
 *  3. CurrentHashMap 与 Hashtable的异同？（暂时不讲）
 
 二、Map结构的理解：
 *    Map中的key:无序的、不可重复的，使用Set存储所有的key(存的是什么Set看用的什么Map HashMap -- HashSet)
 *    ---> key所在的类要重写equals()和hashCode() （以HashMap为例）,TreeMap用比较器
 *
 *    Map中的value:无序的、可重复的，使用Collection存储所有的value
 *    --->value所在的类要重写equals(),因为依托于key，不需要hashcode()
 *
 *    一个键值对：key-value构成了一个Entry对象。
 *    Map中的entry:无序的、不可重复的，使用Set存储所有的entry
```

![image-20220510151902969](Pic/image-20220510151902969.png)

![image-20220510154202078](Pic/image-20220510154202078.png)

- Key不可重复,Value可以重复(键值一对一)
- Entry里面两个属性：key,value
  - Entry无序不可重复（Set）

```java
五、Map中定义的方法：
添加、删除、修改操作：
Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
void putAll(Map m):将m中的所有key-value对存放到当前map中
Object remove(Object key)：移除指定key的key-value对，并返回value
void clear()：清空当前map中的所有数据
元素查询的操作：
Object get(Object key)：获取指定key对应的value
boolean containsKey(Object key)：是否包含指定的key
boolean containsValue(Object value)：是否包含指定的value
int size()：返回map中已经存在的key-value对的个数
boolean isEmpty()：判断当前map是否为空
boolean equals(Object obj)：判断当前map和参数对象obj是否相等
元视图操作的方法：
Set keySet()：返回所有key构成的Set集合
Collection values()：返回所有value构成的Collection集合
Set entrySet()：返回所有key-value对构成的Set集合

*总结：常用方法：
* 添加：put(Object key,Object value)
* 删除：remove(Object key)
* 修改：put(Object key,Object value)
* 查询：get(Object key)
* 长度：size()
* 遍历：keySet() / values() / entrySet()
*
```

- 方法的测试

```java
@Test
public void test1(){
    Map map = new HashMap();
    map.put(null,123);
}
```

```java
@Test
public void test2(){
    Map map = new HashMap();
    map = new LinkedHashMap();
    map.put(123,"AA");
    map.put(345,"BB");
    map.put(12,"CC");

    System.out.println(map);
}
```

```java
/*
    添加、删除、修改操作：
Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
void putAll(Map m):将m中的所有key-value对存放到当前map中
Object remove(Object key)：移除指定key的key-value对，并返回value
void clear()：清空当前map中的所有数据
    */
   @Test
   public void test3(){
       Map map = new HashMap();
       //添加
       map.put("AA",123);
       map.put(45,123);
       map.put("BB",56);
       //修改
       map.put("AA",87);

       System.out.println(map);

       Map map1 = new HashMap();
       map1.put("CC",123);
       map1.put("DD",123);

       map.putAll(map1);

       System.out.println(map);

       //remove(Object key)
       Object value = map.remove("CC");
       System.out.println(value);
       System.out.println(map);

       //clear()
       map.clear();//与map = null操作不同
       System.out.println(map.size());//0
       System.out.println(map);//{}
   }
```

```java
/*
元素查询的操作：
Object get(Object key)：获取指定key对应的value
boolean containsKey(Object key)：是否包含指定的key
boolean containsValue(Object value)：是否包含指定的value
int size()：返回map中key-value对的个数
boolean isEmpty()：判断当前map是否为空
boolean equals(Object obj)：判断当前map和参数对象obj是否相等
    */
   @Test
   public void test4(){
       Map map = new HashMap();
       map.put("AA",123);
       map.put(45,123);
       map.put("BB",56);
       // Object get(Object key)
       System.out.println(map.get(45));
       //containsKey(Object key)
       boolean isExist = map.containsKey("BB");
       System.out.println(isExist);

       isExist = map.containsValue(123);
       System.out.println(isExist);

       map.clear();

       System.out.println(map.isEmpty());
   }
```

- 遍历

```java
/*
元视图操作的方法：
Set keySet()：返回所有key构成的Set集合
Collection values()：返回所有value构成的Collection集合
Set entrySet()：返回所有key-value对构成的Set集合
    */
   @Test
   public void test5(){
       Map map = new HashMap();
       map.put("AA",123);
       map.put(45,1234);
       map.put("BB",56);

       //遍历所有的key集：keySet()
       Set set = map.keySet();
           Iterator iterator = set.iterator();
           while(iterator.hasNext()){
               System.out.println(iterator.next());
       }
       System.out.println();
       //遍历所有的value集：values()
       Collection values = map.values();
       for(Object obj : values){
           System.out.println(obj);
       }
       //一一对应关系
       System.out.println();
       
       //遍历所有的key-value
       //方式一：entrySet()
       Set entrySet = map.entrySet();
       Iterator iterator1 = entrySet.iterator();
       while (iterator1.hasNext()){
           Object obj = iterator1.next();
           //entrySet集合中的元素都是entry
           Map.Entry entry = (Map.Entry) obj;
           System.out.println(entry.getKey() + "---->" + entry.getValue());
       }
       System.out.println();
       //方式二：
       Set keySet = map.keySet();
       Iterator iterator2 = keySet.iterator();
       while(iterator2.hasNext()){
           Object key = iterator2.next();
           Object value = map.get(key);
           System.out.println(key + "=====" + value);
       }
   }
```

### HashMap

```java
三、HashMap的底层实现原理？以jdk7为例说明：
*      HashMap map = new HashMap():
*      在实例化以后，底层创建了长度是16的一维数组Entry[] table。
*      ...可能已经执行过多次put...
*      map.put(key1,value1):
*      首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry数组中的存放位置。
*      如果此位置上的数据为空，此时的key1-value1添加成功。 ----情况1
*      如果此位置上的数据不为空，(意味着此位置上存在一个或多个数据(以链表形式存在)),比较key1和已经存在的一个或多个数据
*      的哈希值：
*              如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功。----情况2
*              如果key1的哈希值和已经存在的某一个数据(key2-value2)的哈希值相同，继续比较：调用key1所在类的equals(key2)方法，比较：
*                      如果equals()返回false:此时key1-value1添加成功。----情况3
*                      如果equals()返回true:使用value1替换value2。
*
*       补充：关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储。
*
*      在不断的添加过程中，会涉及到扩容问题，当超出临界值(且要存放的位置非空)时，扩容。默认的扩容方式：扩容为原来容量的2倍，并将原有的数据复制过来。
```

```java
jdk8 相较于jdk7在底层实现方面的不同：
*      1. new HashMap():底层没有创建一个长度为16的数组，懒汉式初始化
*      2. jdk 8底层的数组是：Node[],而非Entry[]
*      3. 首次调用put()方法时，底层创建长度为16的数组
*      4. jdk7底层结构只有：数组+链表。jdk8中底层结构：数组+链表+红黑树。
*         4.1 形成链表时，七上八下（jdk7:新的元素指向旧的元素。jdk8：旧的元素指向新的元素）
          4.2 当数组的某一个索引位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度 > 64时，此时此索引位置上的所数据改为使用红黑树存储。
  
  DEFAULT_INITIAL_CAPACITY : HashMap的默认容量，16
 *      DEFAULT_LOAD_FACTOR：HashMap的默认加载因子：0.75
 *      threshold：扩容的临界值，=容量*填充因子：16 * 0.75 => 12(提前扩容，为了让链表少一些 )
 *      TREEIFY_THRESHOLD：Bucket中链表长度大于该默认值，转化为红黑树:8
 *      MIN_TREEIFY_CAPACITY：桶中的Node被树化时最小的hash表容量:64
```

![image-20220511160348933](Pic/image-20220511160348933.png)

#### LinkedHashMap

```java
四、LinkedHashMap的底层实现原理（了解）
*      源码中：
*      static class Entry<K,V> extends HashMap.Node<K,V> {
            Entry<K,V> before, after;//能够记录添加的元素的先后顺序
            Entry(int hash, K key, V value, Node<K,V> next) {
               super(hash, key, value, next);
            }
        }
```

### TreeMap

```java
//向TreeMap中添加key-value，要求key必须是由同一个类创建的对象(否则怎么排序)
//因为要按照key进行排序：自然排序 、定制排序
```

```java
//向TreeMap中添加key-value，要求key必须是由同一个类创建的对象(否则怎么排序)
//因为要按照key进行排序：自然排序 、定制排序
//自然排序
@Test
public void test1(){
    TreeMap map = new TreeMap();
    User u1 = new User("Tom",23);
    User u2 = new User("Jerry",32);
    User u3 = new User("Jack",20);
    User u4 = new User("Rose",18);

    map.put(u1,98);
    map.put(u2,89);
    map.put(u3,76);
    map.put(u4,100);

    Set entrySet = map.entrySet();
    Iterator iterator1 = entrySet.iterator();
    while (iterator1.hasNext()){
        Object obj = iterator1.next();
        Map.Entry entry = (Map.Entry) obj;
        System.out.println(entry.getKey() + "---->" + entry.getValue());
    }
}
```

```java
//定制排序
@Test
public void test2(){
    TreeMap map = new TreeMap(new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            if(o1 instanceof User && o2 instanceof User){
                User u1 = (User)o1;
                User u2 = (User)o2;
                return Integer.compare(u1.getAge(),u2.getAge());
            }
            throw new RuntimeException("输入的类型不匹配！");
        }
    });
    User u1 = new User("Tom",23);
    User u2 = new User("Jerry",32);
    User u3 = new User("Jack",20);
    User u4 = new User("Rose",18);

    map.put(u1,98);
    map.put(u2,89);
    map.put(u3,76);
    map.put(u4,100);

    Set entrySet = map.entrySet();
    Iterator iterator1 = entrySet.iterator();
    while (iterator1.hasNext()){
        Object obj = iterator1.next();
        Map.Entry entry = (Map.Entry) obj;
        System.out.println(entry.getKey() + "---->" + entry.getValue());
    }
}
```

```java
public class User implements Comparable{
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("User equals()....");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() { //return name.hashCode() + age;
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    //按照姓名从大到小排列,年龄从小到大排列
    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User user = (User)o;
//            return -this.name.compareTo(user.name);
            int compare = -this.name.compareTo(user.name);
            if(compare != 0){
                return compare;
            }else{
                return Integer.compare(this.age,user.age);
            }
        }else{
            throw new RuntimeException("输入的类型不匹配");
        }
    }
}
```

### Hashtable

#### Properties

![image-20220511221906541](Pic/image-20220511221906541.png)

![image-20220511222213363](Pic/image-20220511222213363.png)



![image-20220511223056787](Pic/image-20220511223056787.png)

- 钩上之后再做配置文件 
- .properties文件

```java
//不要加空格
name=Tom宋红康
password=abc123
```

```java
public class PropertiesTest {
    //Properties:常用来处理配置文件。key和value都是String类型
    public static void main(String[] args)  {
        FileInputStream fis = null;
        try {
            Properties pros = new Properties();

            fis = new FileInputStream("jdbc.properties");
            pros.load(fis);//加载流对应的文件

            String name = pros.getProperty("name");
            String password = pros.getProperty("password");

            System.out.println("name = " + name + ", password = " + password);
            //name = Tom宋红康, password = abc123
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

## Collections工具类

![image-20220511224323842](Pic/image-20220511224323842.png)

![image-20220512150718318](Pic/image-20220512150718318.png)

- **ArrayList,HashMap可能使用同步控制**

```java
/*
reverse(List)：反转 List 中元素的顺序
shuffle(List)：对 List 集合元素进行随机排序
sort(List)：根据元素的自然顺序对指定 List 集合元素按升序排序
sort(List，Comparator)：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
swap(List，int， int)：将指定 list 集合中的 i 处元素和 j 处元素进行交换

Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素
Object max(Collection，Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最大元素
Object min(Collection)
Object min(Collection，Comparator)
int frequency(Collection，Object)：返回指定集合中指定元素的出现次数
void copy(List dest,List src)：将src中的内容复制到dest中
boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替换 List 对象的所有旧值
 */

   Collections:操作Collection、Map的工具类
 *
 * 面试题：Collection 和 Collections的区别？
```

```java
@Test
    public void test1(){
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(765);
        list.add(765);
        list.add(-97);
        list.add(0);

        System.out.println(list);

//        Collections.reverse(list);
//        Collections.shuffle(list);
//        Collections.sort(list);
//        Collections.swap(list,1,2);
        int frequency = Collections.frequency(list, 123);

        System.out.println(list);
        System.out.println(frequency);
    }
```

```java
@Test
    public void test2(){
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);

        //报异常：IndexOutOfBoundsException("Source does not fit in dest")
//        List dest = new ArrayList();
//        Collections.copy(dest,list);
        //正确的：
        List dest = Arrays.asList(new Object[list.size()]);
        System.out.println(dest.size());// == list.size(); = 5
        Collections.copy(dest,list);

        System.out.println(dest);
        /*
        Collections 类中提供了多个 synchronizedXxx() 方法，
        该方法可使将指定集合包装成线程同步的集合，从而可以解决
        多线程并发访问集合时的线程安全问题
         */
        //返回的list1即为线程安全的List
        List list1 = Collections.synchronizedList(list);
    }
```

数据结构与算法分析]ava语言描述（第2版）[美]卡拉罗(Carrano,F.M.) 著金名，等译
大话数据结构（作者：程杰著）⊙



- 栈：先进后出（一摞书 ）
- 队列：先进先出





![image-20220512154005169](Pic/image-20220512154005169.png)