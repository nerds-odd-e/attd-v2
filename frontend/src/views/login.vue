<template>
  <div>
    <input type="text" placeholder="userName" v-model="userName"/>
    <input type="password" placeholder="password" v-model="password"/>
    <input type="button" value="Login" @click="login">
  </div>
</template>

<script>

export default {
  data: () => {
    return {
      userName: '',
      password: ''
    }
  },
  methods: {
    async login() {
      const res = await this.$api.post('users/login', this.$data)
      this.$router.push({
        path: 'welcome',
        query: {
          userName: res.data.userName
        }
      })
    }
  }
}
</script>
