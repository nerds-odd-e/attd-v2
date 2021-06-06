# language: zh-CN
@api-login
功能: 订单

  场景: 订单发货
    假如存在如下订单:
      | code  | productName | total | recipientName | recipientMobile | recipientAddress | status        |
      | SN001 | 电脑          | 19999 | 张三            | 13085901735     | 上海市长宁区           | toBeDelivered |
    并且当前时间为"2000-05-10T20:00:00Z"
    当通过API发货订单"SN001"，快递单号为"SF001"
    那么订单"SN001"已发货，发货时间为"2000-05-10T20:00:00Z"，快递单号为"SF001"

#  场景: 订单自动完成
#    假如存在如下订单:
#      | code      | productName | total | recipientName | recipientMobile | recipientAddress | status        | deliveredAt         |
#      | 状态和时间都符合  | 电脑          | 19999 | 张三            | 13085901735     | 上海市长宁区           | delivering    | 2000-05-10 20:00:00 |
#      | 状态不符的时间符合 | 手机          | 29999 | 李四            | 13817817777     | 上海市杨浦区           | toBeDelivered | 2000-05-10 20:00:00 |
#      | 状态符合时间不符合 | iPad        | 39999 | 王五            | 13355557777     | 上海市浦东新区          | delivering    | 2000-05-10 20:00:01 |
#    并且当前时间为"2000-05-25T20:00:00Z"
#    当订单任务运行时
#    那么订单"状态和时间都符合"的状态为"done"
#    那么订单"状态不符的时间符合"的状态为"toBeDelivered"
#    那么订单"状态符合时间不符合"的状态为"delivering"
