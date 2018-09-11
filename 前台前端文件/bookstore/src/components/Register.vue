<template>
    <div>
        <v-login></v-login>

    <!-- 图标+搜索 -->
    <div class="jumbotron jumbotron-fluid bg-light">
        <div class="container">
            <div class="row">
                <div class="col-lg-2 col-md-4 col-sm-4 ">
                    <img src="../assets/bj.png" alt="..." class="img-fluid">
                </div>

            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-12">

                    <div class="form-group">
                        <label for="exampleInputEmail1">邮箱</label>
                        <input v-model="registerinfo.email"  type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="请输入邮箱">
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">用户名</label>
                        <input v-model="registerinfo.name"  type="text" class="form-control" id="name" placeholder="请输入昵称">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">密码</label>
                        <input v-model="registerinfo.password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">电话</label>
                        <input v-model="registerinfo.phone" type="tel" class="form-control" id="tel" placeholder="请输入手机号码">
                    </div>
                    <!-- <div class="form-group">
                        <label for="exampleInputPassword1">收货人</label>
                        <input v-model="recvname" type="text" class="form-control" id="rcvname" placeholder="请输入收货人姓名">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">收货地址</label>
                        <input v-model="recvaddress" type="text" class="form-control" id="rcvaddress" placeholder="请输入收货地址">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">收货电话</label>
                        <input v-model="recvphone" type="tel" class="form-control" id="rcvtel" placeholder="请输入收货电话">
                    </div> -->

                    <div class="form-check">
                        <label class="form-check-label mr-5">
                            <input  v-model="registerinfo.sex" type="radio" class="form-check-input" name="sex" id="man" value="0" > 男
                        </label>
                        <label class="form-check-label mr-5">
                            <input v-model="registerinfo.sex" type="radio" class="form-check-input" name="sex" id="women" value="1" > 女
                        </label>
                        <label class="form-check-label mr-5">
                            <input v-model="registerinfo.sex" type="radio" class="form-check-input" name="sex" id="secret" value="2" checked> 保密
                        </label>
                    </div>

                    <div>
                        <hr>
                    </div>

                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">
                             我已阅读并接受
                            <a href="#">版权说明和隐私保护条款</a>
                        </label>
                    </div>

                    <div>
                        <hr>
                    </div>

                    <button type="" class="btn btn-primary btn-block" v-on:click="regist()">确认注册</button>

                     <div>
                        <hr>
                    </div>


            </div>
        </div>
    </div>
    </div>
</template>
<script>
import LoginVue from './Login.vue';
    export default{
        name: 'Regiser',
        components:{
            'v-login':LoginVue
        },
        data(){
            return{
                user:'',
                username:'',
                userpassword:'',
                userphone:'',
                usersex:'',
                useremail:'',
                recvname:'',
                registerinfo:{
                    name:'',
                    password:'',
                    phone:'',
                    sex:2,    //男
                    email:'',
                    userHead:'',
                    
                }
            }
        },
        methods: {
                //注册
                regist: function() {
                    
                    this.axios.post(`${this.host}/user`, this.registerinfo)           
                        .then((r) => {
                            console.log(r);
                            alert("注册成功")
                            this.$router.push('/')
                            })
                        .catch((error) => {
                            alert("注册失败")
                            console.log(error)
                        })
                },
            }
    }
</script>
