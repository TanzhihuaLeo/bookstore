<template>
    <div class="container-fluid">

                <div class="row">
                    
                </div>
                <div class="row mt-5 text-center">
                   <div class="col-lg-4">
                       
                   </div>
                   <div class="col-lg-4">
                       <h2>欢迎使用网上书店后台管理系统</h2>
                   </div>
                   <div class="col-lg-4">
                       
                   </div>
                </div>



            <div class="container form-group">
                    <form>
                        <div class="row">
                            <label>用户名：</label>
                            <input v-model="name" @keyup.enter="focusPasswd()" type="text" class="form-control" style="width: 68%">
                        </div>
                        <div class="row mt-5">
                            <label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                            <input v-model="passwd" @keyup.enter="focusLogin()" type="password" id="passwd" class="form-control" style="width: 68%">
                        </div>
                        <div v-if="show" class="row mt-3 text-danger">
                            <label>{{ msg }}</label>
                        </div>
                        <div class="row mt-5">
                            <div class="col col-lg-6">
                                <button type="reset" class="btn btn-sm btn-block">取消</button>
                            </div>
                            <div class="col col-lg-6">
                                <button @click="login()" id="go" type="button" class="btn btn-sm btn-block">登陆</button>
                            </div>
                        </div>
                    </form>
                </div>
    </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return {
        name: '',
        passwd: '',
        msg: '',
        show: false
    }
  },
  methods: {
    focusPasswd(){
        $('#passwd').focus();
    },
    focusLogin(){
        $('#go').focus();
    },
    login(){
        let user = { 'name': this.name, 'password': this.passwd }
        this.axios.post(`${this.host}/login`, user)
        .then((res)=>{
            this.msg = res.data;
            if(this.msg.search('OK')!=0){
                this.show = true;
            }else{
                this.show = false;
                this.router.push({ path: '/manage' })
            }
        })
    }
  }
};
</script>

<style scoped>
.container{
    height: 670px;
    width: 360px;
    padding-top: 200px;
}
</style>


