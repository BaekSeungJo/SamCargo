<template>
  <!--lHh Lpr lFf-->
  <q-layout view="hHh LpR lff">
    <q-header bordered v-model="header" :reveal="headerReveal">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          aria-label="Menu"
        >
          <q-icon name="menu" />
        </q-btn>

        <q-toolbar-title slot="default">
          Bonego administrator
          <span slot="subtitle">보내고 메인 관리자</span>
        </q-toolbar-title>

        <q-btn flat @click="signOut">
          <q-icon left name="exit_to_app" />
          로그아웃
        </q-btn>
        <!--<div>Quasar v{{ $q.version }}</div>-->
      </q-toolbar>

      <!--<q-toolbar inset>-->
        <!--<q-breadcrumbs ref="breadcrumbs" v-if="currentUrl('/main/page1')" active-color="white" style="font-size: 16px">-->
          <!--<q-breadcrumbs-el v-if="currentUrl('/main/page1')" label="Home" icon="home" to="/main/page1"/>-->
          <!--<q-breadcrumbs-el v-if="currentUrl('/main/page1/page1_pa')" label="Components" icon="widgets" to="/main/page1/page1_pa" />-->
        <!--</q-breadcrumbs>-->

        <!--<q-breadcrumbs ref="breadcrumbs" v-if="currentUrl('/main/page2')" active-color="white" style="font-size: 16px">-->
          <!--<q-breadcrumbs-el :exact="currentUrl('/main/page2')" label="Home" icon="home" to="/main/page1"/>-->
          <!--<q-breadcrumbs-el label="Components" icon="widgets" to="/main/page1/page1_pa" />-->
        <!--</q-breadcrumbs>-->
      <!--</q-toolbar>-->
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      bordered

      :mini="miniState"
      @mouseover="miniState = false"
      @mouseout="miniState = true"

      show-if-above
      content-class="bg-grey-3"
    >
      <q-list>
        <q-item class="q-pa-md">
          <q-item-section avatar>
            <q-avatar rounded>
              <img src="https://cdn.quasar.dev/img/avatar.png">
            </q-avatar>
          </q-item-section>
          <q-item-section class="items-end">
            <q-item-label>Admin</q-item-label>
            <q-item-label caption>J.Minjun</q-item-label>
          </q-item-section>
        </q-item>

        <q-separator color="orange" />

        <q-expansion-item
          expand-separator
          icon="perm_identity"
          label="Account settings"
          caption="John Doe"
        >
          <div class="q-ml-md">
            <q-item clickable @click="clickMenu" to="/main/page1">
              <q-item-section avatar>
                <q-icon name="school" />
              </q-item-section>
              <q-item-section>
                <q-item-label>Page1</q-item-label>
                <q-item-label caption>page1.dev</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable @click="clickMenu" to="/main/page1_pa">
              <q-item-section avatar>
                <q-icon name="school" />
              </q-item-section>
              <q-item-section>
                <q-item-label>Page1_pa</q-item-label>
                <q-item-label caption>page1_pa.dev</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable @click="clickMenu" to="/main/page1_pb">
              <q-item-section avatar>
                <q-icon name="school" />
              </q-item-section>
              <q-item-section>
                <q-item-label>Page1_pa</q-item-label>
                <q-item-label caption>page1_pa.dev</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable @click="clickMenu" to="/main/page1_pc">
              <q-item-section avatar>
                <q-icon name="school" />
              </q-item-section>
              <q-item-section>
                <q-item-label>Page1_pa</q-item-label>
                <q-item-label caption>page1_pa.dev</q-item-label>
              </q-item-section>
            </q-item>
          </div>
        </q-expansion-item>

        <q-expansion-item
          expand-separator
          icon="signal_wifi_off"
          label="Wifi settings"
        >
          <div class="q-ml-md">
            <q-item clickable @click="clickMenu" to="/main/page2">
            <q-item-section avatar>
              <q-icon name="school" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Page2</q-item-label>
              <q-item-label caption>page2.dev</q-item-label>
            </q-item-section>
          </q-item>
          </div>
        </q-expansion-item>

        <q-item clickable @click="clickMenu" to="/main/page3">
        <q-item-section avatar>
          <q-icon name="school" />
        </q-item-section>
        <q-item-section>
          <q-item-label>Page3</q-item-label>
          <q-item-label caption>page3.dev</q-item-label>
        </q-item-section>
      </q-item>
        <q-item clickable @click="clickMenu" to="/main/page4">
          <q-item-section avatar>
            <q-icon name="school" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Page4</q-item-label>
            <q-item-label caption>page4.dev</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
import { openURL } from 'quasar'
import { mapGetters, mapMutations } from 'vuex'

export default {
  name: 'MyLayout',
  data () {
    return {
      header:true,
      headerReveal:true,
      miniState: true,
      leftDrawerOpen: this.$q.platform.is.desktop
    }
  },
  computed: {
    ...mapGetters([
      'getLayout'
    ])
  },
  methods: {
    ...mapMutations([
      'setLayout'
    ]),
    openURL,
    currentUrl (url) {
      let currentUrl = window.location.pathname
      if (currentUrl === url) {
        return true
      } else {
        return false
      }
    },
    clickMenu () {
    },
    signOut() {
      // this.$router.push('/signIn');
    },
  },
  beforeCreate () {},
  created () {},
  beforeMount () {
    this.setLayout(this)
  },
  mounted () {
    let layout = this.getLayout.$refs.breadcrumbs
    console.log(layout)
  },
  beforeUpdate () {},
  updated () {},
  beforeDestroy () {},
  destroyed () {}
}
</script>

<style>
</style>

<style scoped>
</style>
