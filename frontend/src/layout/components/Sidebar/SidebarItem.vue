<template>
  <div v-if="!item.hidden" class="menu-wrapper">

    <template>
      <AppLink :to="item.path">
        <ElMenuItem :index="item.path" :class="{'submenu-title-noDropdown':true}">
          <div> {{ item.meta.title }} </div>
        </ElMenuItem>
      </AppLink>
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
