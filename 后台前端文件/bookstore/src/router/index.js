import Vue from "vue";
import Router from "vue-router";

import Login from "@/components/Login";
import Manage from "@/components/Manage";
import AddBook from "@/components/AddBook";
import BookMgr from "@/components/BookMgr";
import KindMgr from "@/components/KindMgr";
import OrderMgr from "@/components/OrderMgr";
import PutOnBook from "@/components/PutOnBook";
import CountMgr from "@/components/CountMgr";

Vue.use(Router);

export default new Router({
  linkExactActiveClass: "",
  linkActiveClass: "active",
  routes: [
    {
      path: "/",
      component: Login,
    },
    {
      path: "/manage",
      component: Manage,
      redirect: '/addBook',
      children: [
        {
          path: "/addBook",
          component: AddBook
        },
        {
          path: "/bookManage",
          component: BookMgr
        },
        {
          path: "/kindMgr",
          component: KindMgr
        },
        {
          path: "/orderMgr",
          component: OrderMgr
        },
        {
          path: "/putonBook",
          component: PutOnBook
        },{
          path: "/countMgr",
          component: CountMgr
        }

      ]
    }
  ]
});
