# language: zh-CN
@ui-login
功能: 订单

  场景: 订单列表
    假如存在如下订单:
      | code  | productName | total | recipientName | recipientMobile | recipientAddress | status        |
      | SN001 | 衬衫          | 19.99 | 张三            | 13085901735     | 上海市长宁区           | toBeDelivered |
    当查询订单时
    那么显示如下订单
      | SN001 | 衬衫 | ￥19.99 | 张三 | 13085901735 | 上海市长宁区 | 待发货 |

