<template>
  <div v-if="!item.hidden" class="menu-wrapper">

    <template>
      <app-link :to="item.path">
        <el-menu-item :index="item.path" :class="{'submenu-title-noDropdown':true}">
          <div> {{ item.meta.title }} </div>
        </el-menu-item>
      </app-link>
    </template>
  </div>
</template>

<script>
import AppLink from './Link'
export default {
  name: 'SidebarItem',
  components: { AppLink },
  props: {
    // route object
    item: {
      type: Object,
      required: true
    }
  }
}
</script>
