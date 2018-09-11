// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.js'
import './assets/font/iconfont.js'
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from './store/index.js';
Vue.use(VueAxios, axios)
Vue.config.productionTip = false
Vue.prototype.host = 'http://127.0.0.1:8080'
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
/* eslint-disable no-new */
// router.push('Home')
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
