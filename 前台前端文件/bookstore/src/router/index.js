import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import News from '@/components/News'
import About from '@/components/About'
import Home from '@/components/Home'
import Register from '@/components/Register'
import Search from '@/components/Search'
import Tabs from '@/components/Tabs'
import Bookinfo from '@/components/Bookinfo'
import Buy from '@/components/Buy'
import Userinfo from '@/components/Userinfo'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/about/:uid/:name',
      name: 'About',
      component: About,
      props:true,
    },
    {
      path: '/news',
      name: 'News',
      component: News
    },

    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/search',
      name: 'Search',
      component: Search,
      children: [
        {
          path: '/search',
          component: Login
        },
      ]
    },
    {
      path: '/Bookinfo',
      name: 'Bookinfo',
      component: Bookinfo
    },
    {
      path: '/buy',
      name: 'Buy',
      component: Buy
    },
    {
      path: '/userinfo',
      name: 'Userinfo',
      component: Userinfo
    },
    // {
    //   path: '/Login',
    //   name: 'Login',
    //   component: Login
    // }
  ]
  
})
