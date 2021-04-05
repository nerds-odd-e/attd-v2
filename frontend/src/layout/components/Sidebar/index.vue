<template>
  <div :class="{'has-logo':false}">
    <ElScrollbar wrap-class="scrollbar-wrapper">
      <ElMenu
        :collapse="false"
        :background-color="variables.menuBg"
        :unique-opened="false"
        :text-color="variables.menuText"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical">
        <SidebarItem v-for="route in menuRouters" :key="route.path" :item="route" />
      </ElMenu>
    </ElScrollbar>
  </div>
</template>

<script>
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
export default {
  components: { SidebarItem },
  data: () => {
    return {
      menuRouters: [{
        path: 'home',
        meta: {
          title: '首页'
        }
      }, {
        path: 'orders',
        meta: {
          title: '订单'
        }
      }]
    }
  },
  computed: {
    variables() {
      return variables
    }
  }
}
</script>
