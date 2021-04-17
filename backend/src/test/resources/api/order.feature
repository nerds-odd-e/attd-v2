# language: zh-CN
  @api-login
功能: 订单

  场景: 订单列表
    假如存在如下订单:
      | code  | productName | total | recipientName | recipientMobile | recipientAddress | status        |
      | SN001 | 衬衫          | 19.99 | 张三            | 13085901735     | 上海市长宁区           | toBeDelivered |
    当API查询订单时
    那么返回如下订单
    """
      [{
        "code": "SN001",
        "productName": "衬衫",
        "total": 19.99,
        "recipientName": "张三",
        "recipientMobile": "13085901735",
        "recipientAddress": "上海市长宁区",
        "status": "toBeDelivered"
      }]
    """
