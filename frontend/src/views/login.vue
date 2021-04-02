<template>
  <div>
    <input type="text" placeholder="userName" v-model="userName"/>
    <input type="password" placeholder="password" v-model="password"/>
    <input type="button" value="Login" @click="login">
    <div>{{message}}</div>
  </div>
</template>

<script>

export default {
  data: () => {
    return {
      userName: '',
      password: '',
      message: ''
    }
  },
  methods: {
    async login() {
      try{
        const res = await this.$api.post('users/login', this.$data)
        this.$router.push({
          path: 'welcome',
          query: {
            userName: res.data.userName
          }
        })
      }
      catch(e){
        this.message = '无效的用户名或密码'
      }
    }
  }
}
</script>
