<template>
  <div id="app" class="bg">
    
    <router-view v-if="isRouterAlive"></router-view>
    <div class="jumbotron mb-0 py-2 bg-dark sticky-bottom">
    <p class="text-light  text-center">Copyright By Qrot</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  provide (){
    return{
      reload:this.reload
    }
  },
  data(){
    return{
      isRouterAlive:true
    }
  },
  methods:{
    reload(){
      this.isRouterAlive=false
      this.$nextTick(function(){
        this.isRouterAlive=true
      })
    }
  }
}

</script>

<style>
    .bg {
    background-image:url('../bjt.jpg');
    /* background-repeat:repeat-x; */
  }
/* #app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */
</style>
