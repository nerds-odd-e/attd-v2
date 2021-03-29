<template>
  <div>
    <input type="text" placeholder="userName" name="userName"/>
    <input type="password" placeholder="password" name="password"/>
    <input type="button" value="Login" @click="login">
  </div>
</template>

<script>

export default {
  methods: {
    login() {
      this.$router.push('welcome')
    }
  }
}
</script>
