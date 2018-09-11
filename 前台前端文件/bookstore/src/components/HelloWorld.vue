<template>
  <div class="hsello">
    
    <pre>{{msg}}</pre>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted(){
    this.axios.post("http://api.komavideo.com/news/list").then(body =>{
      this.msg = body.data;
      })
      }
}
</script>
