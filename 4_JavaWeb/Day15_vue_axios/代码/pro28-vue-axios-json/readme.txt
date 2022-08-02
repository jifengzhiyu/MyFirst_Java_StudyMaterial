1. 回顾：
    Ajax : 异步的JavaScript and XML
    目的： 用来发送异步的请求，然后当服务器给我响应的时候再进行回调操作
    好处： 提高用户体验；局部刷新：降低服务器负担、减轻浏览器压力、减轻网络带宽压力
    开发步骤：
      1) 创建XMLHttpRequest
      2) 调用open进行设置："GET" , URL , true
      3) 绑定状态改变时执行的回调函数 - onreadystatechange
      4) 发送请求 - send()
      5) 编写回调函数，在回调函数中，我们只对XMLHttpRequest的readystate为4的时候感兴趣
                                我们只对XMLHttpRequest的status为200的时候感兴趣

    0: (Uninitialized) the send( ) method has not yet been invoked.
    1: (Loading) the send( ) method has been invoked, request in progress.
    2: (Loaded) the send( ) method has completed, entire response received.
    3: (Interactive) the response is being parsed.
    4: (Completed) the response has been parsed, is ready for harvesting.

    0 － （未初始化）还没有调用send()方法
    1 － （载入）已调用send()方法，正在发送请求
    2 － （载入完成）send()方法执行完成，已经接收到全部响应内容
    3 － （交互）正在解析响应内容
    4 － （完成）响应内容解析完成，可以在客户端调用了

今天内容：
1. Vue
    1) {{}} - 相当于innerText
    2) v-bind:attr 绑定属性值。例如，v-bind:value - 绑定value值
       简写：    :value
    3) v-model 双向绑定
       v-model:value   , 简写  v-model
    4) v-if , v-else , v-show
       v-if和v-else之间不能有其他的节点
       v-show是通过样式表display来控制节点是否显示
    5) v-for 迭代
       v-for={fruit in fruitList}
    6) v-on 绑定事件
    7) 其他：
       - trim:去除首尾空格 , split() , join()
       - watch表示侦听属性
       - 生命周期

2. Axios
   Axios是Ajax的一个框架,简化Ajax操作
   Axios执行Ajax操作的步骤：
   1. 添加并引入axios的js文件

   2-1. 客户端向服务器端异步发送普通参数值
    - 基本格式： axios().then().catch()
    - 示例：
      axios({
        method:"POST",
        url:"....",
        params:{
            uname:"lina",
            pwd:"ok"
        }
      })
      .then(function(value){})          //成功响应时执行的回调        value.data可以获取到服务器响应内容
      .catch(function(reason){});       //有异常时执行的回调          reason.response.data可以获取到响应的内容

                                                                   reason.message / reason.stack 可以查看错误的信息

    2-2. 客户端向服务器发送JSON格式的数据
    - 什么是JSON
      JSON是一种数据格式
      XML也是一种数据格式
      XML格式表示两个学员信息的代码如下：
      <students>
        <student sid="s001">
            <sname>jim</sname>
            <age>18</age>
        </student>
        <student sid="s002">
            <sname>tom</sname>
            <age>19</age>
        </student>
      </students>
      JSON格式表示两个学员信息的代码如下：
      [{sid:"s001",age:18},{sid:"s002",age:19}]
    - JSON表达数据更简洁，更能够节约网络带宽
    - 客户端发送JSON格式的数据给服务器端
    1) 客户端中params需要修改成：  data:
    2) 服务器获取参数值不再是 request.getParameter()...
       而是：
       StringBuffer stringBuffer = new StringBuffer("");
       BufferedReader bufferedReader = request.getReader();
       String str = null ;
       while((str=bufferedReader.readLine())!=null){
           stringBuffer.append(str);
       }
       str = stringBuffer.toString() ;

    3) 我们会发现 str的内容如下：
       {"uname":"lina","pwd":"ok"}

    -  服务器端给客户端响应JSON格式的字符串，然后客户端需要将字符串转化成js Object
