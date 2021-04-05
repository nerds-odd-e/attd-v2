<template>
  <div :class="{'has-logo':false}">
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :collapse="false"
        :background-color="variables.menuBg"
        :unique-opened="false"
        :text-color="variables.menuText"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical">
        <sidebar-item v-for="route in menuRouters" :key="route.path" :item="route" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
export default {
  data: () => {
    return {
      menuRouters: [{
        path: '',
        meta: {
          title: '首页'
        }
      }]
    }
  },
  components: { SidebarItem },
  computed: {
    variables() {
      return variables
    }
  }
}
</script>
