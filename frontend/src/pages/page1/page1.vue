<template>
  <q-page class="flex flex-center">
    <img class=" " alt="Quasar logo" src="~assets/quasar-logo-full.svg">
  </q-page>
</template>

<style>
</style>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Page1',
  data () {
    return {
    }
  },
  computed: {
    ...mapGetters([
      'getLayout'
    ])
  },
  beforeCreate () {},
  created () {},
  beforeMount () {
    // let breadcrumbs = this.getLayout.$refs.breadcrumbs
  },
  mounted () {
  },
  beforeUpdate () {},
  updated () {},
  beforeDestroy () {},
  destroyed () {}
}
</script>
