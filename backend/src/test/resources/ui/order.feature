# language: zh-CN
@ui-login
功能: 订单

  场景: 订单列表
    假如存在如下订单:
      | code  | productName | total | recipientName | recipientMobile | recipientAddress | status        |
      | SN001 | 衬衫          | 19.99 | 张三            | 13085901735     | 上海市长宁区           | toBeDelivered |
    当查询订单时
    那么显示如下订单
      | SN001 | 衬衫 | ￥19.99 | 待发货 |

  场景: 录入订单
    当用如下数据录入订单:
      | 订单号   | 商品名称 | 金额    | 收件人 | 电话          | 地址     | 状态  |
      | SN001 | 衬衫   | 19.99 | 张三  | 13085901735 | 上海市长宁区 | 待发货 |
    那么显示如下订单
      | SN001 | 衬衫 | ￥19.99 | 待发货 |
