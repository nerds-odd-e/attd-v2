
<template>
  <!-- eslint-disable vue/require-component-is -->
  <Component v-bind="linkProps(to)">
    <slot />
  </Component>
</template>

<script>
export default {
  props: {
    to: {
      type: String,
      required: true
    }
  },
  methods: {
    linkProps(url) {
      return {
        is: 'router-link',
        to: url
      }
    }
  }
}
</script>
