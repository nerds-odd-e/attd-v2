import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout/index'

Vue.use(Router)

export default new Router({
  mode: 'history', // require service support
  routes: [
    {
      path: '',
      component: () => import('@/views/login')
    },
    {
      path: '/home',
      component: Layout,
      redirect: 'home',
      children: [
        {
          path: '/home',
          component: () => import('@/views/home')
        }
      ]
    },
    {
      path: '/orders',
      component: Layout,
      redirect: 'orders',
      children: [
        {
          path: '/orders',
          component: () => import('@/views/orders')
        }
      ]
    }
  ]
})
