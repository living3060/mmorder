import Vue from 'vue'
import Router from 'vue-router'
import BootstrapVue from 'bootstrap-vue'
import login from '@/components/login'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)

export default new Router({
  routes: [
    {
      path: '/login/',
      name: 'login',
      component: login
    }
  ]
})
