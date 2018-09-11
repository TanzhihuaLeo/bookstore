<template>
    <div class="container">
        <div class="row form-group">
            <div class="col-lg-4 mt-3">
                <label for="">从</label>
                <input v-model="startTime" @change="getOrders()" type="date" class="form-control" style="width:80%">
            </div>
            <div class="col-lg-4 mt-3">
                <label for="">至</label>
                <input v-model="endTime" @change="getOrders()" type="date" class="form-control" style="width:80%">
            </div>
            <div class="col-lg-4 mt-3">
                <label for="">状态</label>
                <select v-model="state" @change="getOrders()" class="form-control" style="width:60%">
                    <option value="-1">请选择</option>
                    <option value="0">待付款</option>
                    <option value="1">待发货</option>
                    <option value="2">待收货</option>
                    <option value="3">完成</option>
                </select>
            </div>
        </div>

        <div class="row mt-3" v-if="orders.length==0">
            <div class="col col-lg-1"></div>
            <div class="col col-lg-5">
                <img src="../assets/orderMgr.png" class="img-fluid" alt="无相关信息">
            </div>
            <div class="col col-lg-6">
                <h2 class="mt-5 pt-5" id="msg">选择时间查看订单</h2>
            </div>
        </div>

        <div class="container book-item" v-for="(o,index) in orders" :key="index">
            <div class="row">
                <div class="col-lg-4">
                    <label>订单ID：</label>
                    <label>{{ o.orderID }}</label>
                </div>
                <div class="col-lg-4">
                    <label class="text-muted">{{ o.orderTime}}</label>
                </div>
                <div class="col-lg-4">
                    <button type="button" class="btn btn-sm btn-block" data-toggle="modal" data-target="#state">修改状态</button>
                </div>
                <div class="modal fade" id="state" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">修改订单状态</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                待发货=>待收货
                            </div>
                            <div class="modal-footer">
                                <button @click="updataState(o.orderID)" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row order-item" v-for="(item,index) in o.itemList" :key="index">
                <div class="col-lg-3">
                    <img :src="item.bookCover" class="img-fluid" alt="书籍封面">
                </div>
                <div class="col-lg-8">
                    <label class="mt-2 text-primary book-name">{{ item.bookName }}</label>
                    <div class="row">
                        <div class="col-lg-3 col-sm-4">
                            <label>单价：</label>
                            <label>{{ item.itemPrice }}</label>
                        </div>
                        <div class="col-lg-9 col-sm-8">
                            <label>作者：</label>
                            <label>{{ item.bookAuthor }}</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-sm-6">
                            <label>{{ item.bookPublish }}</label>
                        </div>
                        <div class="col-lg-3 col-sm-6">
                            <label>数量：</label>
                            <label>{{ item.itemQuantity }}</label>
                        </div>
                        <div class="col-lg-3 col-sm-6">
                            <label>小计：</label>
                            <label>{{ item.itemSubtotal }}</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-3">
                    <label>总金额：</label>
                    <label>{{ o.orderTotal }}</label>
                </div>
                <div class="col-lg-4">
                    <label>订单状态：</label>
                    <label class="text-danger">{{ states[o.orderState] }}</label>
                </div>
                <div class="col-lg-5">
                    <label>联系方式：</label>
                    <label>{{ o.orderPhone }}</label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <label>收货人：</label>
                    <label>{{ o.orderName }}</label>
                </div>
                <div class="col-lg-9">
                    <label>收货地址：</label>
                    <label>{{ o.orderAddress }}</label>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="row mt-4 text-center page" v-if="orders.length != 0">
            <div class="col col-lg-6">
                <button @click="prevPage()" class="btn btn-sm" v-bind:class="{'disabled':prev}">上一页</button>
            </div>
            <div class="col col-lg-6">
                <button @click="lastPage()" class="btn btn-sm" v-bind:class="{'disabled':last}">下一页</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "OrderMgr",
    data() {
        return {
            startTime: "",
            endTime: "",
            state: "",
            orders: [],
            states: ["待付款", "待发货", "待收货", "完成"],
            offset: 0,
            len: 0,
            l: 5,
            prev: true,
            last: false
        };
    },
    methods: {
        getOrders() {
            this.offset = 0;
            this.prev = true;
            this.last = false;
            this.getAllOrders();
        },
        getAllOrders() {
            this.axios
                .get(`${this.host}/getOrderItem/byTimeState`, {
                    params: {
                        st: this.startTime,
                        e: this.endTime,
                        se: this.state,
                        l: this.l,
                        o: this.offset
                    }
                })
                .then(orders => {
                    this.orders = orders.data;
                    console.log("ok");
                });
            this.axios
                .get(`${this.host}/getOrderLen/byTimeState`, {
                    params: {
                        st: this.startTime,
                        e: this.endTime,
                        se: this.state
                    }
                })
                .then(len => {
                    this.len = len.data;
                    if (this.len <= this.l) {
                        this.last = true;
                    }
                });
        },
        prevPage() {
            if (this.offset > 0) {
                this.offset -= this.l;
                this.getAllOrders();
                this.last = false;
            }
            if (this.offset == 0) {
                this.prev = true;
            }
        },
        lastPage() {
            this.offset += this.l;
            if (this.offset < this.len) {
                this.getAllOrders();
                this.prev = false;
            }
            if (this.offset + this.l >= this.len) {
                this.last = true;
            }
        },
        updataState(id) {
            this.axios.put(`${this.host}/updateOrder/${id}`,{
                headers: {
                    'ContentType': 'application/json'
                }
            }).then(() => {
                console.log("state modify ok");
            });
        }
    }
};
</script>   