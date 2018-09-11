<template>
  <div class="container">
    <!-- 头部 -->
    <div class="row">
      <div class="jumbotron jumbotron-fluid col-lg-12 p-2">
        <div class="container">
          <!-- logo -->
          <div class="row">
            <img src="../assets/logo.png" alt="logo" class="img-fluid" style="width:20%;height:10%">
          </div>
          <!-- info welcome -->
          <div class="row">
            <div class="col-lg-10">
              <p class="lead">sale books online</p>
            </div>
            <div class="col-lg-2 text-right">
              <p class="h6">您好，管理员！</p>
            </div>
          </div>
          <hr>
        </div>
      </div>
    </div>

    <div class="row" id="content">
      <!-- 左边列表 -->
      <div class="list-group col-lg-3 col-md-4 text-center" id="list">
        <router-link to="/addBook">添加书籍</router-link>
        <router-link to="/putonBook">书籍上新</router-link>
        <router-link to="/bookManage">书籍管理</router-link>
        <router-link to="/orderMgr">订单管理</router-link>
        <router-link to="/kindMgr">分类管理</router-link>
        <router-link to="/countMgr">统计报表</router-link>
      </div>
      <!-- 右边内容 -->
      <div class="col-lg-9 col-md-8">
        <router-view/>
      </div>
    </div>

    <!-- footer -->
    <footer class="clearfix">
      <p>Copyright 2018 by Qrot</p> 
    </footer>
  </div>
</template>

<script>
export default {
    name: 'Manage'
}    
</script>

