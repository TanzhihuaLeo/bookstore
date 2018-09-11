<template>
    <!-- 图标+搜索+购物车 -->
        <div class="jumbotron jumbotron-fluid bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2 col-md-4 col-sm-4 ">
                        <img src="../assets/bj.png" alt="..." class="img-fluid">
                    </div>
                    <div class="col">
                        <div class="input-group mt-3">
                            <input type="text" v-model="search" class="form-control" placeholder="搜索" >
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" v-on:click="tosearch()">搜索</button>
                            </div>
                            <a class="ml-2" href="#">
                                <svg class="icon" aria-hidden="true" data-toggle="modal" data-target="#car">
                                    <use xlink:href="#icon-gouwuche"></use>
                                </svg>
                            </a>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="car" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">购物车</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>书名</th>
                                            <th>单价</th>
                                            <th>数量</th>
                                            <th>删除</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr v-for="i in caritem" :key="i.bookid">
                                            <td>{{i.bookName}}</td>
                                            <td>{{i.price}}</td>
                                            <td>{{i.quantity}}</td>
                                            
                                            <td>
                                                <button type="button" v-on:click="deleteitem(i)" class="btn btn-danger">删除</button>
                                            </td>

                                        </tr>
                                        
                                        
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <h3>总价格:{{total}}元</h3>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                                <a name="" id="" class="btn btn-primary" data-dismiss="modal" @click="buy" role="button" >结账</a>
                                
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
    inject:['reload'],
    name:'Jumbotron',
    data(){
        return{
            search:'',
            caritem:[
                    
                ],
        }
    },
    components:{
        
    },
    mounted(){
        this.axios.get(`${this.host}/cart/`+this.$store.getters.user.userid)
            .then((r) => {
                    this.caritem=r.data;
                    console.log(r.data)
                    })
                .catch((error) => {
                    console.log(error)
                    });
    },
    computed:{
        total: function(){
            let totals=0;
            this.caritem.forEach(i => {
                totals+=i.price*i.quantity
            });
            totals = totals.toFixed(2);
            return totals;
        },
    },
    methods:{
        tosearch: function(){
                this.$router.push({ path: '/search', query: { text:this.search }});
                this.reload()
            },
        deleteitem: function(item){
                this.axios.post(`${this.host}/cart/delete`,item)
                
                .then((r) => {
                    console.log(r.data)
                    this.axios.get(`${this.host}/cart/`+this.$store.getters.user.userid)
                    .then((r) => {
                    this.caritem=r.data;
                        console.log(r.data)
                    })
                    .catch((error) => {
                        console.log(error)
                    });
                        })
                    .catch((error) => {
                        console.log(error)
                    });
                
            },
        buy(){
            this.$router.push({ path: '/buy'});
        }
    }
}
</script>
<style type="text/css">
        .icon {
            /* 通过设置 font-size 来改变图标大小 */
            width: 2em;
            height: 2em;
            /* 图标和文字相邻时，垂直对齐 */
            vertical-align: -0.15em;
            /* 通过设置 color 来改变 SVG 的颜色/fill */
            fill: currentColor;
            /* path 和 stroke 溢出 viewBox 部分在 IE 下会显示
                 normalize.css 中也包含这行 */
            overflow: hidden;
        }
    </style>