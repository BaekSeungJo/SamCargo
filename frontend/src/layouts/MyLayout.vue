<template>
  <!--lHh Lpr lFf-->
  <q-layout view="hHh LpR lff" class="bg-grey-2">
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
          보내고-ADMIN
          <span slot="subtitle">보내고 메인 관리자</span>
        </q-toolbar-title>
        <q-btn
          flat
          color="white"
          @click="$q.fullscreen.toggle()"
          :icon="$q.fullscreen.isActive ? 'fullscreen_exit' : 'fullscreen'"
        >
          <q-tooltip anchor="bottom middle" self="top middle" content-style="font-size: 16px" :offset="[10, 10]">
            <strong>전체화면 보기</strong>
          </q-tooltip>
        </q-btn>

        <q-btn flat @click="signOut" :icon="'exit_to_app'">
          <q-tooltip anchor="bottom middle" self="top middle" content-style="font-size: 16px" :offset="[10, 10]">
            <strong>로그아웃</strong>
          </q-tooltip>
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
      content-class="bg-grey-4"
    >
      <q-list class="text-indigo-10 q-pb-lg">
        <q-item class="q-pa-md">
          <q-item-section avatar>
            <q-avatar rounded>
              <img src="https://cdn.quasar.dev/img/avatar.png">
            </q-avatar>
          </q-item-section>
          <q-item-section class="items-end text-grey-10">
            <q-item-label class="text-subtitle1 text-weight-bolder">Admin account role</q-item-label>
            <q-item-label class="text-subtitle2">J.Minjun</q-item-label>
          </q-item-section>
        </q-item>

        <q-separator color="orange" />

        <!--터미널관리-->
        <q-expansion-item
          class="text-h6 text-weight-bolder"
          expand-separator
          icon="mdi-database"
          label="터미널/그룹 관리"
        >
          <div class="q-ml-md text-grey-8">
            <q-item clickable @click="clickMenu" to="/main/term/" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">터미널</q-item-label>
                <q-item-label caption >터미널 관리</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable @click="clickMenu" to="/main/term_group/" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">터미널 그룹</q-item-label>
                <q-item-label caption >터미널 그룹 관리</q-item-label>
              </q-item-section>
            </q-item>
          </div>
        </q-expansion-item>

        <!--계정관리-->
        <q-expansion-item
          class="text-h6 text-weight-bolder"
          expand-separator
          icon="perm_identity"
          label="계정 관리"
        >
          <div class="q-ml-md text-grey-8">

            <q-item clickable @click="clickMenu" to="/main/account" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">계정</q-item-label>
                <q-item-label caption>운영계정 관리</q-item-label>
              </q-item-section>
            </q-item>

            <q-item clickable @click="clickMenu" to="/main/role" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">권한</q-item-label>
                <q-item-label caption>계정권한 관리</q-item-label>
              </q-item-section>
            </q-item>

          </div>
        </q-expansion-item>

        <!--스케줄관리-->
        <q-expansion-item
          class="text-h6 text-weight-bolder"
          expand-separator
          icon="mdi-calendar-multiple-check"
          label="스케줄 관리"
        >
          <div class="q-ml-md text-grey-8">
            <q-item clickable @click="clickMenu" to="/main/schedule/" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">운행 스케줄</q-item-label>
                <q-item-label caption>운행 스케줄 관리</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable @click="clickMenu" to="/main/car/" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">운행 차량</q-item-label>
                <q-item-label caption>운행 차량 관리</q-item-label>
              </q-item-section>
            </q-item>
          </div>
        </q-expansion-item>

        <!--부가정보관리-->
        <q-expansion-item
          class="text-h6 text-weight-bolder"
          expand-separator
          icon="mdi-cube-send"
          label="부가정보 관리"
        >
          <div class="q-ml-md text-grey-8">
            <q-item clickable @click="clickMenu" to="/main/article" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">물품</q-item-label>
                <q-item-label caption>물품 정보</q-item-label>
              </q-item-section>
            </q-item>
          </div>
        </q-expansion-item>

        <!--접수관리-->
        <q-expansion-item
          class="text-h6 text-weight-bolder"
          expand-separator
          icon="mdi-receipt"
          label="접수 관리"
        >
          <div class="q-ml-md text-grey-8">
            <q-item clickable @click="clickMenu" to="/main/receipt" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">접수</q-item-label>
                <q-item-label caption>접수내역</q-item-label>
              </q-item-section>
            </q-item>
          </div>
        </q-expansion-item>

        <!--보고서관리-->
        <q-expansion-item
          class="text-h6 text-weight-bolder"
          expand-separator
          icon="report"
          label="보고서 관리"
        >
          <div class="q-ml-md text-grey-8">

            <q-item clickable @click="clickMenu" to="/main/term_deadline" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">터미널 그룹 마감</q-item-label>
                <q-item-label caption>터미널 그룹 마감 관리</q-item-label>
              </q-item-section>
            </q-item>

            <q-item clickable @click="clickMenu" to="/main/term_sell" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">터미널별 판매</q-item-label>
                <q-item-label caption>터미널별 판매 내역</q-item-label>
              </q-item-section>
            </q-item>

            <q-item clickable @click="clickMenu" to="/main/route_sell" active-class="menu-choice-link">
              <!--<q-item-section avatar>-->
                <!--<q-icon name="school" />-->
              <!--</q-item-section>-->
              <q-item-section>
                <q-item-label class="text-subtitle1 text-weight-bold">노선별 판매</q-item-label>
                <q-item-label caption>노선별 판매 내역</q-item-label>
              </q-item-section>
            </q-item>
          </div>
        </q-expansion-item>

      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
import { openURL } from 'quasar'
import { AppFullscreen } from 'quasar'
import { mapGetters, mapMutations } from 'vuex'

export default {
  name: 'MyLayout',
  data () {
    return {
      header:true,
      headerReveal:true,
      miniState: true,

      _refreshTokenTimer: null,

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
    /**
     * click-listener callback method for side menu item
     * **/
    clickMenu () {
      //do something
    },
    /**
     * timeout 처리를 위한 각종 이벤트 리스터 등록
     * **/
    setup() {
      window.addEventListener("click", this.resetTimer, false);
      window.addEventListener("mousemove", this.resetTimer, false);
      window.addEventListener('touchmove', this.resetTimer, false)
      window.addEventListener("mousedown", this.resetTimer, false);
      window.addEventListener("keyup", this.resetTimer, false);
      window.addEventListener("dragend", this.resetTimer, false);
      window.addEventListener("keypress", this.resetTimer, false);
      window.addEventListener("DOMMouseScroll", this.resetTimer, false);
      window.addEventListener("wheel", this.resetTimer, false);
      window.addEventListener("touchcancel", this.resetTimer, false);
      window.addEventListener("touchend", this.resetTimer, false);
      window.addEventListener("MSPointerMove", this.resetTimer, false);

      // timeout timer
      this.startTimer();
    },
    /**
     * timeout 처리를 위해 등록한 각종 이벤트 리스터 제거
     * **/
    cleanup(){
      window.removeEventListener("click", this.resetTimer);
      window.removeEventListener("mousemove", this.resetTimer);
      window.removeEventListener('touchmove', this.resetTimer)
      window.removeEventListener("mousedown", this.resetTimer);
      window.removeEventListener("keyup", this.resetTimer);
      window.removeEventListener("dragend", this.resetTimer);
      window.removeEventListener("keypress", this.resetTimer);
      window.removeEventListener("DOMMouseScroll", this.resetTimer);
      window.removeEventListener("wheel", this.resetTimer);
      window.removeEventListener("touchcancel", this.resetTimer);
      window.removeEventListener("touchend", this.resetTimer);
      window.removeEventListener("MSPointerMove", this.resetTimer);
    },
    /**
     * Timeout을 위한 timer
     * **/
    startTimer() {
      var intervalMillisec = (60 * 1000) * 30; // 30분
      // wait 2 seconds before calling goInactive
      this._refreshTokenTimer = setTimeout(this.goInactive, intervalMillisec);
      // console.log('startTimer :' + intervalMillisec);
    },
    /**
     * application timeout을 위한 기존 timer reset
     * **/
    resetTimer(e) {
      this.clearTimer();
      this.goActive();
    },
    /**
     * Idle상태의 application이 시간 active될 시간을 초과한 경우 Logout
     * **/
    goInactive() {
      this.signOut();
    },
    /**
     * timeout timer 시작
     * **/
    goActive() {
      this.startTimer();
    },
    /**
     * 등록된 timer 제거
     * **/
    clearTimer() {
      if (Boolean(this._refreshTokenTimer)) {
        clearTimeout(this._refreshTokenTimer);
      }
    },
    /**
     * Logout 처리
     * **/
    signOut() {
      this.clearTimer();
      this.$router.push('/');
    },
  },
  beforeCreate () {},
  created () {},
  beforeMount () {
    this.setLayout(this)
  },
  mounted () {
    // let layout = this.getLayout.$refs.breadcrumbs;
    // console.log(layout);

    // FIXME. 테스트시 인증에 의한 접근제한으로 주석처리후 사용
    this.setup();
  },
  beforeUpdate () {},
  updated () {},
  beforeDestroy () {
    this.cleanup();
  },
  destroyed () {}
}
</script>

<style>
</style>

<style lang="stylus">

</style>

<style scoped>
</style>
