<template lang="pug">
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
