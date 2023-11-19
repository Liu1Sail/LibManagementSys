<h1>
    <p style="color:brown">
    1.不要直接提交到master分支! ! ! 新建一个分支提交，然后在github提交分支合并申请 ， 防止直接覆盖掉别人的代码.
    <br>
    2.写代码前先拉取一遍保证你的代码处于最新状态 ，否则容易出问题
    <br>
    3.第三方库放在lib！！！
    <br>
    4.把你希望别人用的类写一个短简介放在README下面
    </p>
</h1>

<h2>
类型简介
</h2>


<div>
    <h3>sqlConfig</h3>
    <p>单例类<br> 
        目的 - 全局存储 url , user , password  . 共享mysql配置数据
    </p>
    <h3>UserManager</h3>
    <p>单例类<br>
        目的 - 管理注册 ，登录 ， 注销 用户(  管理Users表(Account表)  )
    </p>
    <h3>User</h3>
    <p>
        目的 - 对表格数据的reClass 存储uid 和 uname 鉴于安全考虑不保存password
    </p>
    <h3>类名(模板)</h3>
    <p>
        简介
    </p>

</div>