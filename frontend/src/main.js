import Vue from 'vue'
import App from './App.vue'
import router from './router'
import api from '@/api'

Vue.config.productionTip = false

Object.defineProperty(Vue.prototype, '$api', { value: api })

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
