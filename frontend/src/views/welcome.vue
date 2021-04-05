<template lang="pug">
  .dashboard-editor-container
    div Welcome {{userName}}
</template>

<script>
export default {
  data: () => {
    return {
      userName: ''
    }
  },
  mounted() {
    this.userName = this.$route.query.userName
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  // background-color: rgb(240, 242, 245);
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
