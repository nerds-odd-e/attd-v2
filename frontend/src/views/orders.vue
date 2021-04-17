<template lang="pug">
  .flex.justify-center.align-center
    .flex.justify-center.align-center.flex-direction.response
      .flex.justify-center.align-center
        .padding-right-xl Orders
        el-button(type='primary', @click='showAddOrder=true') 录入订单
      .flex.align-center(v-for="(order, index) in list" :key="index")
        .width-200.text-sm {{ order.code }}
        .width-200.text-sm {{ order.productName }}
        .width-200.text-sm {{ order.recipientName }}
        .width-200.text-sm {{ order.recipientMobile }}
        .width-200.text-sm {{ order.recipientAddress }}
        .width-200.text-sm {{ order.status === 'toBeDelivered' ? '待发货' : '' }}
        .width-200.text-sm {{ '￥' + order.total }}
    .flex.justify-center.align-center.absolute.bg-white.flex-direction(v-if="showAddOrder" style="top:100px;z-index:10;")
      div 录入界面
      el-input(v-model="order.code", placeholder="订单号")
      el-input(v-model="order.productName", placeholder="商品名称")
      el-input(v-model="order.total", placeholder="金额")
      el-input(v-model="order.recipientName", placeholder="收件人")
      el-input(v-model="order.recipientMobile", placeholder="电话")
      el-input(v-model="order.recipientAddress", placeholder="地址")
      el-select(v-model="order.status", placeholder="状态")
        el-option.height-50(value="toBeDelivered", label="待发货")
      el-button(@click="addOrder") 提交
</template>

<script>
export default {
  data() {
    return {
      list: [],
      order: {
        code: '',
        productName: '',
        total: '',
        recipientName: '',
        recipientMobile: '',
        recipientAddress: ''
      },
      showAddOrder: false
    }
  },
  async mounted() {
    await this.fetchAll()
  },
  methods: {
    async addOrder() {
      await this.$api.post('api/orders', this.order)
      await this.fetchAll()
      this.showAddOrder = false
    },
    async fetchAll() {
      const response = await this.$api.get('api/orders')
      this.list = response.data
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
