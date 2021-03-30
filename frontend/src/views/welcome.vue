<template>
  <div>Welcome {{userName}}</div>
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
