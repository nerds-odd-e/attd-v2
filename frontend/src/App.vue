<template>
  <div id="app">
    <input type="text" placeholder="userName" name="userName"/>
    <input type="password" placeholder="password" name="password"/>
    <input type="button" :value="buttonClicked ? 'Clicked' : 'Login'" @click="buttonClicked=true">
  </div>
</template>

<script>

export default {
  name: 'app',
  data: () => {
    return {
      buttonClicked: false
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
