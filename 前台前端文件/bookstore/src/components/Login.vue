<template>
    <div>
        <!-- 页头 登录 注册  -->
        <div class="jumbotron jumbotron-fluid py-2 mb-0">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <a href="#/">首页</a>
                        <a href="#">收藏</a>
                        <a href="#">RSS订阅</a>
                    </div>
                    <div class="col">
                    </div>
                    <div class="col-2">
        
                        <a v-if=!online href="#" data-toggle="modal" data-target="#exampleModal">登录</a>
                        <a v-if=!online href="#/register">注册</a>
                        <a v-if=online href="#/userinfo" class="hidden">{{username}}</a>
                        <a v-if=online v-on:click="logout" href="#">下线</a>
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">登录</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-label-group ml-2 mr-2">
                                    <label for="inputEmail">用户名</label>
                                    <input type="text" v-model="username" id="inputEmail" class="form-control" placeholder="用户名" required autofocus>
        
                                </div>
        
                                <div class="form-label-group ml-2 mr-2">
                                    <label for="inputPassword">密码</label>
                                    <input type="password" v-model="userpassword" id="inputPassword" class="form-control" placeholder="密码" required>
        
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" v-on:click="login" data-dismiss="modal">登录</button>
                            </div>
                            </div>
                        </div>
                        </div>        

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:'Login',
    data(){
        return{
            username:'',
            userid:'',
            userpassword:'',
            online:false,
        }
    },
    mounted(){
            this.online=this.$store.getters.online;
            this.userid=this.$store.getters.user.userid;
            this.username=this.$store.getters.user.username;
        },
    methods:{
        login: function(){
                this.axios.get(`${this.host}/user/load`,{
                    params:{
                        n:this.username,
                        p:this.userpassword
                    }
                    })
                .then((r) => {
                    
                    console.log(r.data);
                    if(r.data.id!=null){
                        this.userid=r.data.id;
                        this.username=r.data.name;
                        this.$store.commit('SET_USER', {userid:r.data.id,username:r.data.name,useremail:r.data.email,userphone:r.data.phone,usersex:r.data.sex,password:r.data.password})
                        this.$store.commit('SET_ONLINE', true);
                        this.online = this.$store.getters.online;
                        alert("登录成功")
                            }
                            else{
                                alert("登录失败,用户名或密码错误")
                            }
                        })
                    .catch((error) => {
                        console.log(error);
                        alert("登录失败,用户名或密码错误")
                        });
            },
        logout: function(){
                this.$store.commit('SET_ONLINE', false);
                this.$store.commit('SET_USER', {});
                console.log(this.$store.getters.user);
                this.username=this.$store.getters.user.username;
                this.online = this.$store.getters.online;
            },
    }
}
</script>
