import Vue from 'vue'
import App from './App.vue'
import router from './router'
import api from '@/api'
import Element from 'element-ui'
import Cookies from 'js-cookie'
import '@/styles/index.scss' // global css

Vue.config.productionTip = false

Object.defineProperty(Vue.prototype, '$api', { value: api })

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
