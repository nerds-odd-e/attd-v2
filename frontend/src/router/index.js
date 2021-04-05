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
      redirect: 'dashboard',
      children: [
        {
          path: '/dashboard',
          component: () => import('@/views/welcome'),
          name: 'Dashboard',
          meta: { title: 'dashboard', icon: 'dashboard', affix: true }
        }
      ]
    }
  ]
})
