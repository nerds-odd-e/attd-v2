# language: zh-CN
功能: 登录

  场景: 登录成功
    假如存在用户名为"joseph"和密码为"123"的用户
    当以用户名为"joseph"和密码为"123"登录时
    那么"joseph"登录成功

  场景: 登录失败
    假如存在用户名为"joseph"和密码为"123"的用户
    当以用户名为"joseph"和密码为"incorrect-password"登录时
    那么登录失败的错误信息是"无效的用户名或密码"
