# 泛型

- 标签，限制类型

![image-20220514163248460](Pic/image-20220514163248460.png)

```java
/**
 * 泛型的使用
 * 1.jdk 5.0新增的特性
 *
 * 2.在集合中使用泛型：
 *  总结：
 *  ① 集合接口或集合类在jdk5.0时都修改为带泛型的结构。
 *  ② 在实例化集合类时，可以指明具体的泛型类型
 *  ③ 指明完以后，在集合类或接口中凡是定义类或接口时，内部结构（比如：方法、构造器、属性等）使用到类的泛型的位置，都指定为实例化的泛型类型。
 *    比如：add(E e)  --->实例化以后：add(Integer e)
 *  ④ 注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，拿包装类替换
 *  ⑤ 如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型。
 *
 * 3.如何自定义泛型结构：泛型类、泛型接口；泛型方法。见 GenericTest1.java
 */
```

## 在集合中使用泛型

```java
//在集合中使用泛型的情况：以ArrayList为例
    @Test
    public void test2(){
       ArrayList<Integer> list =  new ArrayList<Integer>();

        list.add(78);
        list.add(87);
        list.add(99);
        list.add(65);
        //编译时，就会进行类型检查，保证数据的安全
//        list.add("Tom");

        //方式一：
//        for(Integer score : list){
//            //避免了强转操作
//            int stuScore = score;
//            System.out.println(stuScore);
//        }
        //方式二：
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            int stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }
```

```java
//在集合中使用泛型的情况：以HashMap为例
    @Test
    public void test3(){
//        Map<String,Integer> map = new HashMap<String,Integer>();
        //jdk7新特性：类型推断
        Map<String,Integer> map = new HashMap<>();

        map.put("Tom",87);
        map.put("Jerry",87);
        map.put("Jack",67);

        //泛型的嵌套
        Set<Map.Entry<String,Integer>> entry = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();

        while(iterator.hasNext()){
            Map.Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + "----" + value);
        }
    }
```

## 自定义泛型结构

![image-20220515180409637](Pic/image-20220515180409637.png)

![image-20220515181622884](Pic/image-20220515181622884.png)

![image-20220515181635159](Pic/image-20220515181635159.png)

![image-20220515181926473](Pic/image-20220515181926473.png)

### 自定义泛型类,泛型方法

```java
public class Order<T> {
    String orderName;
    int orderId;

    //类的内部结构就可以使用类的泛型
    T orderT;

    public Order(){
        //编译不通过
//        T[] arr = new T[10];
        //编译通过
        T[] arr = (T[]) new Object[10];
    }

    public Order(String orderName,int orderId,T orderT){
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    //如下的三个方法都不是泛型方法
    public T getOrderT(){
        return orderT;
    }

    public void setOrderT(T orderT){
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    //静态方法中不能使用类的泛型。(静态方法中不能用对象，不能用类的属性)
//    public static void show(T orderT){
//        System.out.println(orderT);
//    }

    //泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。
    //换句话说，泛型方法所属的类是不是泛型类都没有关系。
    //泛型方法，可以声明为静态的。原因：泛型参数是在调用方法时确定的。并非在实例化类时确定。
    public static <E>  List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for(E e : arr){
            list.add(e);
        }
        return list;
    }
}
```

其子类：

```java
public class SubOrder extends Order<Integer> {//SubOrder:不是泛型类
    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for(E e : arr){
            list.add(e);
        }
        return list;
    }
}

public class SubOrder1<T> extends Order<T> {//SubOrder1<T>:仍然是泛型类
}
```

使用:

```java
@Test
public void test1(){
    //如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
    //要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
    Order order = new Order();
    order.setOrderT(123);
    order.setOrderT("ABC");

    //建议：实例化时指明类的泛型
    Order<String> order1 = new Order<String>("orderAA",1001,"order:AA");

    order1.setOrderT("AA:hello");
}
```

```java
@Test
public void test2(){
    SubOrder sub1 = new SubOrder();
    //由于子类在继承带泛型的父类时，指明了泛型类型。则实例化子类对象时，不再需要指明泛型。
    sub1.setOrderT(1122);

    SubOrder1<String> sub2 = new SubOrder1<>();
    sub2.setOrderT("order2...");
}
```

```java
@Test
    public void test3(){
        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        //泛型不同的引用不能相互赋值。
//        list1 = list2;
    }
```

```java
//测试泛型方法
@Test
public void test4(){
    Order<String> order = new Order<>();
    Integer[] arr = new Integer[]{1,2,3,4};
    //泛型方法在调用时，指明泛型参数的类型。
    List<Integer> list = order.copyFromArrayToList(arr);

    System.out.println(list);
}
```

## 泛型与异常

```java
//异常类不能声明为泛型类
//public class MyException<T> extends Exception{
//}
```

```java
 public void show(){
        //编译不通过
        //泛型不能与异常有关
//        try{
//        }catch(T t){
//        }
    }
```

## 泛型在继承上的表现

```java
/*
    1. 泛型在继承方面的体现
      虽然类A是类B的父类，但是G<A> 和G<B>二者不具备子父类关系，二者是并列关系。
       补充：类A是类B的父类，A<G> 是 B<G> 的父类
     */
    @Test
    public void test1(){
        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;
        
        List<Object> list1 = null;
        List<String> list2 = new ArrayList<String>();
        //此时的list1和list2的类型不具有子父类关系
        //编译不通过
//        list1 = list2;
    }

@Test
    public void test2(){
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;

        list1 = list3;
        list2 = list3;
    }
```



## 通配符的使用

### 一般使用

![image-20220515204753884](Pic/image-20220515204753884.png)

```java
/*
    2. 通配符的使用
       通配符：?
       类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
     */
    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list = list1;
        list = list2;

        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list = list3;
        //添加(写入)：对于List<?>就不能向其内部添加数据。
        //除了添加null之外。
//        list.add("DD");
//        list.add('?');

        list.add(null);

        //获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);
    }

public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }
```

### 有限制的通配符

![image-20220515204828934](Pic/image-20220515204828934.png)



## 泛型的应用

### 比较

```java
public class Employee implements Comparable<Employee>{
  //省略代码
  ...............................
  //指明泛型时的写法
    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }
}
```

```java
public class MyDate implements Comparable<MyDate>{
 @Override
    public int compareTo(MyDate m) {
        //比较年
        int minusYear = this.getYear() - m.getYear();
        if(minusYear != 0){
            return minusYear;
        }
        //比较月
        int minusMonth = this.getMonth() - m.getMonth();
        if(minusMonth != 0){
            return minusMonth;
        }
        //比较日
        return this.getDay() - m.getDay();
    } 
}
```

```java
TreeSet<Employee> set = new TreeSet<>(new Comparator<Employee>() {
    //使用泛型以后的写法
    @Override
    public int compare(Employee o1, Employee o2) {
        MyDate b1 = o1.getBirthday();
        MyDate b2 = o2.getBirthday();

        return b1.compareTo(b2);
    }
```

### 泛型嵌套

![image-20220515212255934](Pic/image-20220515212255934.png)