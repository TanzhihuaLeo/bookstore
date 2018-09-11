<template>
    <div>
        <!-- 页头 登录 注册  -->
        <v-login></v-login>
        <div class="container">
        <div class="row">
            <div class="col-12">
                <h2>收货信息</h2>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">收货人：</label>
                    <div class="col-sm-10">
                        <input type="text" v-model="recvname" class="form-control" id="inputrecname" placeholder="真实姓名">
                    </div>
                </div>

                <div>
                    <hr>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">收货电话：</label>
                    <div class="col-sm-10">
                        <input type="tel" v-model="recvtel" class="form-control" id="inputrectel" placeholder="电话">
                    </div>
                </div>

                <div>
                    <hr>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">收货地址：</label>
                    <div class="col-sm-10">
                        <input type="text" v-model="recvaddress" class="form-control" id="inputrecaddr" placeholder="地址">
                    </div>
                </div>

                <div>
                    <hr>
                </div>
                
                <div class="form-check">
                  <label class="form-check-label">
                    <input type="checkbox" v-model="checked" class="form-check-input" name="" id="" value="checkedValue" checked>
                    选择默认地址
                  </label>
                </div>
                <p> Time Now: {{timenow}} </p>

                <div>
                    <hr>
                </div>

            </div>
        </div>
        <div class="row">            
            <div class="col-12">
                <h2>订单 :</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th>书名</th>
                            <th>数量</th>
                            <th>单价</th>
                            <th>小计</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="i in caritem" :key="i.bookId">
                            <td scope="row">{{i.bookName}}</td>
                            <td>{{i.quantity}}</td>
                            <td>{{i.price}}</td>
                            <td>{{(i.price*i.quantity).toFixed(2)}}</td>
                        </tr>
                    </tbody>
                </table>
                
            </div>
        </div>

        <div>
            <hr>
        </div>

        <div class="row">
            <div class="col-11">
                <h1>{{total}}元</h1>
            </div>
            <div class="col-1">
                <button type="button" @click="buy()" class="btn btn-primary">付款</button>
            </div>
        </div>

    </div>
    </div>
</template>
<script>
import LoginVue from './Login.vue';
export default {
    name: 'Buy',
    data(){
        return{
            caritem:[],
            username:'',
            userid:'',
            recvname:'',
            recvaddress:'',
            recvtel:'',
            checked:false,
            order:{
                // orderId:'',
                total:'',
                userId:'',
                state:'',
                name:'',
                address:'',
                phone:'',
                time:'',
                itemList:[],
            }
        }
    },
    components:{
        'v-login':LoginVue
    },
    mounted(){
            
            
            this.online=this.$store.getters.online;
            this.username=this.$store.getters.user.username;
            this.userid=this.$store.getters.user.userid;
            this.axios.get(`${this.host}/cart/`+this.userid)
                
            .then((r) => {
                    this.caritem=r.data;
                    console.log(r.data)
                    })
                .catch((error) => {
                    console.log(error)
                    });
        },
    computed: {
        total: function(){
            let totals=0;
            this.caritem.forEach(i => {
                totals+=(i.price*100)*(i.quantity)/100
            });
            totals = totals.toFixed(2);
            return totals;
        },
        timenow: function(){
            let date = new Date();
            let seperator1 = "-";
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            let strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            let currentdate = year + seperator1 + month + seperator1 + strDate;
            return currentdate;
        }
    },
    methods:{
        buy(){
              console.log(this.timenow);
              this.order.time=this.timenow;
              this.order.total=this.total;
              this.order.state = 1;
              this.order.name =this.recvname;
              this.order.phone=this.recvtel;
              this.order.userId = this.userid;
              this.order.address = this.recvaddress;
              this.order.itemList=this.caritem;
              this.axios.post(`${this.host}/orders/create`,this.order)
                .then((r) => {
                        console.log(r)
                        this.caritem=[];
                        alert("购买完成")
                        })
                    .catch((error) => {
                        console.log(error)
                        });

              this.axios.post(`${this.host}/cart/empty`,this.userid)
              .then((r) => {
                        console.log(r)
                        })
                    .catch((error) => {
                        console.log(error)
                        });
          }
    }
}
</script>

