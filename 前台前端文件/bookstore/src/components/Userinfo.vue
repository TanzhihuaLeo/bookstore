<template>
    <div>
        <v-login></v-login>
        <div class="container">
        <!-- 资料 -->
        <div class="jumbotron-fluid py-3">
            <h1 class="display-5">个人信息</h1>
        </div>
        <div class="row">
            <div class="col-12">
                <h5>{{user.username}}</h5>
                <p v-show="user.usersex==0">性别: 男</p>
                <p v-show="user.usersex==1">性别: 女</p>
                <p v-show="user.usersex==2">性别: 保密</p>
                <p>电话: {{user.userphone}}</p>
                <p>邮箱: {{user.useremail}}</p>
                <p><button type="button" class="btn btn-warning" data-toggle="modal" data-target="#car">修改</button></p>
                <!-- Modal -->
                    <div class="modal fade" id="car" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">修改</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">邮箱</label>
                                        <input v-model="newuser.email"  type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="请输入邮箱">
                                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">密码</label>
                                        <input v-model="newuser.password"  type="password" class="form-control" id="password" placeholder="请输入密码">
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputPassword1">电话</label>
                                        <input v-model="newuser.phone" type="tel" class="form-control" id="tel" placeholder="请输入手机号码">
                                    </div>

                                    <div class="form-check">
                                        <label class="form-check-label mr-5">
                                            <input v-model="newuser.sex"  type="radio" class="form-check-input" name="sex" id="man" value="0" > 男
                                        </label>
                                        <label class="form-check-label mr-5">
                                            <input v-model="newuser.sex" type="radio" class="form-check-input" name="sex" id="women" value="1" > 女
                                        </label>
                                        <label class="form-check-label mr-5">
                                            <input v-model="newuser.sex" type="radio" class="form-check-input" name="sex" id="secret" value="2" checked> 保密
                                        </label>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn btn-primary" @click="modify" data-dismiss="modal">提交</button>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
        <!-- 订单 -->
        <div class="jumbotron-fluid py-3">
            <h1 class="display-5">订单</h1>
        </div>
        <div class="row">
            <table class="table">
                <thead>
                    <tr>
                        <th>时间</th>
                        <th>单号</th>
                        <th>总价</th>
                        <th>订单项数量</th>
                        <th>删除</th>
                    </tr>
                </thead>
                <tbody >
                    <tr v-for="o in orders" :key="o.orderId">
                        <td scope="row">{{o.time.substr(0,10)}}</td>
                        <td>{{o.orderId}}</td>
                        <td>{{o.total}}</td>
                        <td>
                            <!-- <a v-for="i in o.itemList" :key="i.orderId">{{i.bookid}}:{{i.quantity}}</a> -->
                            <span>{{o.itemList[0].quantity}}</span>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" v-on:click="deleteorder(o)">删除</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- 评论 -->
        <div class="jumbotron-fluid py-3">
            <h1 class="display-5">你的评论</h1>
        </div>
        <div class="row">
            <table class="table">
                <thead>
                    <tr>
                        <th>时间</th>
                        <th>书名</th>
                        <th>评论</th>
                        <th>修改</th>
                        <th>删除</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td scope="row">2018/8/7</td>
                        <td>道德经</td>
                        <td>很好很好很好...</td>
                        <td><button type="button" class="btn btn-primary">修改</button></td>
                        <td><button type="button" class="btn btn-danger">删除</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="input-group">
        <div class="custom-file">
            <input type="file" class="custom-file-input" @change="getFile($event)" id="inputGroupFile04">
            <label class="custom-file-label" for="inputGroupFile04">选择图片</label>
        </div>
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" @click="submitForm($event)" type="button">上传</button>
        </div>
    </div>
    </div>
</template>
<script>
import LoginVue from './Login.vue';
export default {
    name: 'Userinfo',
    components:{
        'v-login':LoginVue
    },
    data(){
        return{
            user:{},
            newuser:{
                id:'',
                password:'',
                sex:'',
                email:'',
                phone:'',
            },
            orders:[],
            comments:[],
        }
    },
    mounted(){
            
            this.user=this.$store.getters.user;
            this.axios.get(`${this.host}/orders/`+this.$store.getters.user.userid)
            .then((r) => {
                    console.log(r.data);
                    this.orders=r.data;
                    })
                .catch((error) => {
                    console.log(error)
                    });
            //评论
            // this.axios.get(`${this.host}/comment/`+this.$store.getters.user.userid)
            // .then((r) => {
            //         console.log(r.data);
            //         this.orders=r.data;
            //         })
            //     .catch((error) => {
            //         console.log(error)
            //         });
        },
    methods:{
        deleteorder(order){
            this.axios.post(`${this.host}/orders/delete`,order)
            .then((r) => {
                    console.log(r.data)
                    this.orders.splice(this.orders.findIndex(i=>i.orderId===order.orderId),1)
                    })
                .catch((error) => {
                    console.log(error)
                    });
        },
        modify(){
            this.newuser.id=this.user.userid
            if(this.newuser.password==''){
                this.newuser.password=this.user.password
            }
            if(this.newuser.sex==''){
                this.newuser.sex=this.user.usersex
            }
            if(this.newuser.email==''){
                this.newuser.email=this.user.useremail
            }
            if(this.newuser.phone==''){
                this.newuser.phone=this.user.userphone
            }
            console.log(this.newuser);
            this.axios.post(`${this.host}/user/update`,this.newuser)
            .then((r) => {
                    console.log(r.data)
                    alert("ok 请重新登录")
                    this.$store.commit('SET_ONLINE', false);
                    this.$store.commit('SET_USER', {});
                    this.$router.push({ path: '/'});
                    })
                .catch((error) => {
                    console.log(error)
                    });
        },
         getFile(event) {
            this.file = event.target.files[0];
            console.log(this.file);
         },
         submitForm(event) {
            event.preventDefault();
            let formData = new FormData();
            formData.append('file', this.file);
 
            let config = {
              headers: {
                'Content-Type': 'multipart/form-data'
              }
            }
 
            this.$http.post(`${this.host}/fileUpload`, formData, config)
            .then(function (response) {
              if (response.status === 200) {
                console.log(response.data);
              }
            })

        }
    }
}
</script>