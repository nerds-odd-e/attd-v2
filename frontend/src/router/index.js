import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history', // require service support
  routes: [
    {
      path: '',
      component: () => import('@/views/login')
    },
    {
      path: '/welcome',
      component: () => import('@/views/welcome')
    }
  ]
})
