<template>
  <div :class="classObj" class="app-wrapper">
    <Sidebar class="sidebar-container" />
    <div :class="{hasTagsView:false}" class="main-container">
      <AppMain />
    </div>
  </div>
</template>

<script>
import { AppMain, Sidebar } from './components'

export default {
  name: 'Layout',
  components: {
    AppMain,
    Sidebar
  },
  computed: {
    classObj() {
      return {
        hideSidebar: false,
        openSidebar: true,
        withoutAnimation: false
      }
    }
  }
}
</script>

<style lang="scss" scoped>
   .app-wrapper {
    position: relative;
    height: 100%;
    width: 100%;
  }
</style>
