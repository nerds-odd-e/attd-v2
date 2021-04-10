<template lang="pug">
  .flex.justify-center.align-center.flex-direction.response
    div Orders
    .flex.align-center(v-for="(order, index) in list" :key="index")
      .width-200.text-sm {{ order.code }}
      .width-200.text-sm {{ order.productName }}
      .width-200.text-sm {{ order.recipientName }}
      .width-200.text-sm {{ order.recipientMobile }}
      .width-200.text-sm {{ order.recipientAddress }}
      .width-200.text-sm {{ order.status === 'toBeDelivered' ? '待发货' : '' }}
      .width-200.text-sm {{ '￥' + order.total }}
</template>

<script>
export default {
  data() {
    return {
      list: [{
        code: 'SN001',
        productName: '衬衫',
        recipientName: '张三',
        recipientMobile: '13085901735',
        recipientAddress: '上海市长宁区',
        status: 'toBeDelivered',
        total: 19.99
      }]
    }
  }
}
</script>

<style>
@import "../styles/color-ui.css";
</style>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
@import "../styles/common.styl"
</style>
