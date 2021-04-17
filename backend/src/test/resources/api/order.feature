# language: zh-CN
@api-login
功能: 订单

  场景: 订单列表
    假如存在如下订单:
      | code  | productName | total | recipientName | recipientMobile | recipientAddress | status        |
      | SN001 | 电脑          | 19999 | 张三            | 13085901735     | 上海市长宁区           | toBeDelivered |
    并且存在订单"SN001"的订单项:
      | itemName | price | quantity |
      | MacBook  | 19999 | 1        |
      | TouchPad | 0     | 1        |
    当API查询订单时
    那么返回如下订单
    """
      [{
        "code": "SN001",
        "productName": "电脑",
        "total": 19999,
        "recipientName": "张三",
        "recipientMobile": "13085901735",
        "recipientAddress": "上海市长宁区",
        "status": "toBeDelivered",
        "lines": [{
          "itemName": "MacBook",
          "price": 19999,
          "quantity": 1
        }, {
          "itemName": "TouchPad",
          "price": 0,
          "quantity": 1
        }]
      }]
    """
