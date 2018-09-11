<template>
    <div class="container">
        <div class="row form-group">
            <div class="col-lg-4">
                <label for="">分类</label>
                <select v-model="kind" v-on:change="getBooksByKind()" class="form-control" style="width:70%">
                    <option disabled value="">请选择</option>
                    <option v-for="(k,index) in kinds" :key="index">{{ k }}</option>
                </select>
            </div>
            <div class="col col-lg-5">
                <label for="">查看库存低于</label>
                <select v-model="low" @change="look()" class="form-control float-left" style="width:30%">
                    <option disabled value="">请选择</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="20">20</option>
                </select>
                <label for="">的书籍</label>
            </div>
        </div>

        <div class="row mt-3" v-if="books.length==0">
            <div class="col col-lg-1"></div>
            <div class="col col-lg-5">
                <img src="../assets/kindMgr.png" class="img-fluid" alt="无相关信息">
            </div>
            <div class="col col-lg-6">
                <h2 class="mt-5 pt-5" id="msg">选择一个分类吧！</h2>
            </div>
        </div>

        <div class="mt-3">
            <div class="row book-item" v-for="(b,index) in books" :key="index">
                <div class="col-lg-3">
                    <img :src="b.cover" class="img-fluid mb-3" alt="书籍封面">
                </div>
                <div class="col-lg-9">
                    <div class="row">
                        <div class="col-lg-6 col-sm-6">
                            <label class="book-name">{{ b.bookname }}</label>
                        </div>
                        <div class="col-lg-6 col-sm-6 text-muted text-right">
                            <label>编号：</label>
                            <label>{{ b.isbn }}</label>
                        </div>
                    </div>
                    <div class="row mt-1">
                        <div class="col-lg-3 col-sm-5">
                            <label>价格：</label>
                            <label>{{ b.price }}</label>
                        </div>
                        <div class="col-lg-9 col-sm-7">
                            <label>作者：</label>
                            <label>{{ b.author }}</label>
                        </div>
                    </div>
                    <div class="row mt-1">
                        <div class="col-lg-3 col-sm-5">
                            <label>库存：</label>
                            <label>{{ b.storage }}</label>
                        </div>
                        <div class="col-lg-3 col-sm-7">
                            <label>分类：</label>
                            <label>{{ b.kind }}</label>
                        </div>
                        <div class="col-lg-6 col-sm-12">
                            <label>{{ b.publish }}&nbsp;&nbsp;{{ b.publtime }}</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 mt-2">
                            <button @click="getBook(b.bookid)" class="btn btn-block btn-sm" data-toggle="modal" data-target="#modify">
                                修改
                            </button>
                        </div>
                        <div class="col-lg-6 mt-2">
                            <button @click="pullOff(b.bookid,index)" class="btn btn-block btn-sm">下架</button>
                        </div>
                        <div class="modal fade" id="modify" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title col-lg-3">{{ book.bookname }}</h5>
                                        <label class="col-lg-4 text-right">{{ book.isbn }}</label>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        作者：
                                        <input type="text" class="form-control" v-model="author"> 出版社：
                                        <input type="text" class="form-control" v-model="publish"> 出版时间：
                                        <input type="date" class="form-control" v-model="publtime"> 书籍分类：
                                        <input type="text" class="form-control" v-model="kind"> 价格：
                                        <input type="text" class="form-control" v-model="price"> 库存：
                                        <input type="text" class="form-control" v-model="storage"> 简介：
                                        <textarea class="form-control" rows="5" v-model="summary"></textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                        <button @click="save(book.bookid)" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="clearfix"></div>
        <div class="row mt-4 text-center page" v-if="books.length != 0">
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
    name: "KindMgr",
    data() {
        return {
            kinds: [],
            kind: "",
            books: [],
            book: {},
            author: "",
            publish: "",
            publtime: "",
            kind: "",
            price: "",
            storage: "",
            summary: "",
            low: '',
            offset: 0,
            len: 0,
            l: 5,
            prev: true,
            last: false,
            flag: 1
        };
    },
    mounted() {
        // 获取分类
        this.axios.get(`${this.host}/getKind`).then(kinds => {
            this.kinds = kinds.data;
        });
    },
    methods: {
        getBooksByKind() {
            if(this.flag == 2){
                this.offset = 0;
                this.prev = true;
                this.last = false;
            }
            this.axios
                .get(`${this.host}/kindBook`, {
                    params: {
                        k: this.kind,
                        l: this.l,
                        o: this.offset
                    }
                })
                .then(books => {
                    this.books = books.data;
                    console.log("ok");
                });
            this.axios
                .get(`${this.host}/len/kindBook`, {
                    params: {
                        k: this.kind
                    }
                })
                .then(len => {
                    this.len = len.data;
                    if (this.len <= this.l) {
                        this.last = true;
                    }
                });
            this.flag = 1;
        },
        look() {
            if(this.flag == 1){
                this.offset = 0;
                this.prev = true;
                this.last = false;
            }
            this.axios
                .get(`${this.host}/getLowerStorage`, {
                    params: {
                        lo: this.low,
                        l: this.l,
                        o: this.offset
                    }
                })
                .then(books => {
                    this.books = books.data;
                });
            this.axios
                .get(`${this.host}/len/lowerStorage`, {
                    params: {
                        lo: this.low
                    }
                })
                .then(len => {
                    this.len = len.data;
                    if (this.len <= this.l) {
                        this.last = true;
                    }
                });
            this.flag = 2;
        },
        prevPage() {
            if (this.offset > 0) {
                this.offset -= this.l;
                if(this.flag == 1)
                    this.getBooksByKind();
                if(this.flag == 2)
                    this.look();
                this.last = false;
            }
            if (this.offset == 0) {
                this.prev = true;
            }
        },
        lastPage() {
            this.offset += this.l;
            if (this.offset < this.len) {
                if(this.flag == 1)
                    this.getBooksByKind();
                if(this.flag == 2)
                    this.look();
                this.prev = false;
            }
            if (this.offset + this.l >= this.len) {
                this.last = true;
            }
        },
        getBook(id) {
            this.axios.get(`${this.host}/getBook/${id}`).then(book => {
                this.book = book.data;
                this.author = this.book.author;
                this.publish = this.book.publish;
                this.price = this.book.price;
                this.kind = this.book.kind;
                this.publtime = this.book.publtime;
                this.storage = this.book.storage;
                this.summary = this.book.summary;
            });
        },
        save(id) {
            let sbook = {
                bookname: this.book.bookname,
                author: this.author,
                publish: this.publish,
                publtime: this.publtime,
                price: this.price,
                kind: this.kind,
                storage: this.storage,
                summary: this.summary,
                bookid: id
            };
            this.axios.put(`${this.host}/updateBook`, sbook).then(() => {
                console.log("save ok");
            });
        },
        pullOff(id, index) {
            this.axios
                .put(`${this.host}/removeBook/${id}`, {
                    headers: {
                        ContentType: "application/json"
                    }
                })
                .then(() => {
                    this.books.splice(index, 1);
                    this.getBooksByKind();
                    console.log("withdraw ok");
                });
        }
    }
};
</script>
