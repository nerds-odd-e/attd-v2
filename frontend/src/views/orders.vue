<template lang="pug">
  .flex.justify-center.align-center
    .flex.justify-center.align-center.flex-direction.response
      .flex.justify-center.align-center
        .padding-right-xl Orders
        el-button(type='primary', @click='showAddOrder=true') Add Order
      .flex.align-center(v-for="(order, index) in list" :key="index")
        .width-200.text-sm {{ order.code }}
        .width-200.text-sm {{ order.productName }}
        .width-200.text-sm {{ order.status === 'toBeDelivered' ? 'To be delivered' : '' }}
        .width-200.text-sm {{ '￥' + order.total }}
    .flex.justify-center.align-center.absolute.bg-white.flex-direction(v-if="showAddOrder" style="top:100px;z-index:10;")
      div Add Order
      el-input(v-model="order.code", placeholder="Order number")
      el-input(v-model="order.productName", placeholder="Product name")
      el-input(v-model="order.total", placeholder="Total")
      el-input(v-model="order.recipientName", placeholder="Recipient name")
      el-input(v-model="order.recipientMobile", placeholder="Recipient mobile")
      el-input(v-model="order.recipientAddress", placeholder="Recipient address")
      el-select(v-model="order.status", placeholder="Status")
        el-option.height-50(value="toBeDelivered", label="To be delivered")
      el-button(@click="addOrder") Submit
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
