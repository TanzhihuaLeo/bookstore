import Vue from 'vue'
import Vuex from "vuex"
import App from './App'
import router from './router'

import axios from 'axios'
import echarts from 'echarts';
import $ from 'jquery'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'
import './assets/commen.css'

Vue.use(Vuex)
Vue.prototype.axios = axios
Vue.prototype.$echarts = echarts 
Vue.prototype.router = router
Vue.prototype.host = 'http://127.0.0.1:8080/api/1'
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
