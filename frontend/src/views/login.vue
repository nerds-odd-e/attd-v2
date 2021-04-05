<template lang="pug">
.login-container
  el-form.login-form(
    autocomplete='on'
  )
    .title-container
      h3.title 系统登录
    el-form-item
      el-input(
        v-model='userName',
        placeholder='用户名',
        type='text',
        tabindex='1',
        autocomplete='on'
      )
    el-form-item
      el-input(
        v-model='password',
        placeholder='密码',
        type='password',
        tabindex='2',
        autocomplete='on'
      )
    el-button(
      type='primary',
      style='width: 100%; margin-bottom: 30px;',
      @click.native.prevent='login'
    ) 登录
    .text-yellow.text-bold.text-xxl {{message}}
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
      try {
        const res = await this.$api.post('users/login', this.$data)
        await this.$router.push({
          path: 'home',
          query: {
            userName: res.data.userName
          }
        })
      } catch (e) {
        console.error(e)
        this.message = '无效的用户名或密码'
      }
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #eee;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
    &::first-line {
      color: $light_gray;
    }
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 100%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;
      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #557e92;
$dark_gray: #70a6b2;
$light_gray: #ddd;

.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  background-color: $bg;
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    max-width: 100%;
    padding: 35px 35px 15px 35px;
    margin: 120px auto;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }
}
</style>
