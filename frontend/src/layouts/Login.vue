<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <q-layout>
    <q-page>
      <div class="flex justify-center items-center">
        <!-- Login form box-->
        <div class="row justify-center items-center" style="width: 40vw; height: 100vh;">
          <div class="">
            <section class=" q-mb-xl">
              <span class="flex text-h2 text-weight-bolder full-width flex-center">보내고 SYSTEM</span>
              <span class="flex text-h5 text-weight-bolder full-width flex-center">터미널 허브 화물 운송 서비스</span>
            </section>

            <section class=" q-pa-xl">
              <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
              <q-input
                filled
                v-model="name"
                label="아이디"
                hint="아이디를 입력하세요."
                lazy-rules
                :rules="[ val => val && val.length > 0 || '입력된 아이디가 없습니다.']"
              />

              <q-input
                v-model="password"
                filled :type="isPwd ? 'password' : 'text'"
                label="패스워드"
                hint="패스워드를 입력하세요."
                :rules="[ val => val && val.length > 0 || '입력된 패스워드가 없습니다.']"
              >
                <template v-slot:append>
                  <q-icon
                    :name="isPwd ? 'visibility_off' : 'visibility'"
                    class="cursor-pointer"
                    @click="isPwd = !isPwd"
                  />
                </template>
              </q-input>

              <q-checkbox v-model="accept" label="나는 라이센스 및 약관에 동의 합니다." />

              <div>
                <q-btn label="Submit" type="submit" color="primary"/>
                <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
              </div>
            </q-form>
            </section>
          </div>
        </div>

        <!-- Intro img box -->
        <div class="flex justify-center items-center"
             style="
             width: 60vw;
             height: 100vh;
             backgroundImage: url('statics/plane.jpg');
             background-size: cover;
             background-repeat: no-repeat;
             background-position: center;"
        >
          <img class="flex-center col-1" src="statics/app-logo-128x128.png" style="width: 4vw;"/>
        </div>

      </div>
    </q-page>
  </q-layout>
</template>

<script>
  import { LocalStorage, SessionStorage } from 'quasar'

    export default {
        name: "Login",
      data () {
        return {
          name: null,
          password: '',
          isPwd: true,

          age: null,

          accept: false
        }
      },
      methods: {
        onSubmit () {
          if (this.accept !== true) {
            this.$q.notify({
              color: 'red-5',
              textColor: 'white',
              icon: 'fas fa-exclamation-triangle',
              message: '먼저 라이센스 및 약관에 동의하십시오.'
            });
          }
          else {
            this.$q.notify({
              color: 'green-4',
              textColor: 'white',
              icon: 'fas fa-check-circle',
              message: 'Submitted'
            });

            this.$router.push('/main/term/');
          }
        },

        onReset () {
          this.name = null;
          this.password = null;
          this.accept = false;
        }
      }
    }
</script>

<style scoped>

</style>
