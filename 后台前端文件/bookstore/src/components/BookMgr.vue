<template>
    <div class="clearfix container">
        <form class="input-group mt-3">
            <input v-model="keyWords" type="text" class="form-control" placeholder="书籍名称或者书籍作者">
            <div class="input-group-append">
                <button @click="search()" class="btn" type="submit">搜索</button>
            </div>
        </form>

        <div class="row mt-3" v-if="books.length==0">
            <div class="col col-lg-1"></div>
            <div class="col col-lg-5">
                <img src="../assets/nothing.png" class="img-fluid" alt="无相关信息">
            </div>
            <div class="col col-lg-6">
                <h2 class="mt-5 pt-5" id="msg">没有任何东西哦</h2>
            </div>
        </div>

        <div class="mt-3">
            <div class="row book-item" v-for="(b, index) in books" :key="index">
                <div class="col-lg-3">
                    <img :src="b.cover" class="img-fluid" alt="书籍封面">
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
                            <label>分类：</label>
                            <label>{{ b.kind }}</label>
                        </div>
                        <div class="col-lg-3 col-sm-5">
                            <label>库存：</label>
                            <label>{{ b.storage }}</label>
                        </div>
                        <div class="col-lg-6 col-sm-12">
                            <label>{{ b.publish }} &nbsp;&nbsp;{{ b.publtime }}</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 mt-2">
                            <button @click="getComments(b.bookid)" data-toggle="modal" data-target="#comment" class="btn btn-block btn-sm">查看评论</button>
                        </div>
                        <div class="modal fade" id="comment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">{{ b.bookname }}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="container" style="min-height: 300px">
                                        <p v-if="comments.length==0" class="text-center" style="line-height: 300px">暂无任何评论</p>
                                        <div class="modal-body book-item" v-for="(c, index) in comments" :key="index">
                                            <div class="row">
                                                <div class="col col-lg-12" id="comment">
                                                    <div style="float: left">{{ c.userName }}&nbsp;{{ c.userID }}</div>
                                                    <div class="text-right">订单号：{{ c.orderID }}</div>
                                                    <p>&nbsp;&nbsp;{{ c.commContent }}</p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col col-lg-6">评分：{{ c.commScore }}</div>
                                                <div class="col col-lg-6">{{ c.commTime }}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 mt-2">
                            <button @click="modifyBook(b.bookid)" class="btn btn-block btn-sm" data-toggle="modal" data-target="#modify">
                                修改
                            </button>
                        </div>
                        <div class="col-lg-4 mt-2">
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
                                        <button @click="saveBook(book.bookid,index)" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
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
    name: "BookMgr",
    data() {
        return {
            books: [],
            keyWords: "",
            book: {},
            author: "",
            publish: "",
            publtime: "",
            kind: "",
            price: "",
            storage: "",
            summary: "",
            offset: 0,
            len: 0,
            l: 5,
            prev: true,
            last: false,
            comments: []
        };
    },
    methods: {
        search() {
            this.offset = 0;
            this.prev = true;
            this.last = false;
            this.searchBook();
        },
        searchBook() {
            this.axios
                .get(`${this.host}/selectBook`, {
                    params: {
                        t: this.keyWords,
                        l: this.l,
                        o: this.offset
                    }
                })
                .then(books => {
                    this.books = books.data;
                });
            this.axios
                .get(`${this.host}/len/selectBook`, {
                    params: {
                        t: this.keyWords,
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
                this.searchBook();
                this.last = false;
            }
            if (this.offset == 0) {
                this.prev = true;
            }
        },
        lastPage() {
            this.offset += this.l;
            if (this.offset < this.len) {
                this.searchBook();
                this.prev = false;
            }
            if (this.offset + this.l >= this.len) {
                this.last = true;
            }
        },
        modifyBook(id) {
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
        saveBook(id, index) {
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
                    this.searchBook();
                    console.log("withdraw ok");
                });
        },
        getComments(id) {
            this.axios
                .get(`${this.host}/getComments`, {
                    params: {
                        id: id,
                        l: 5,
                        o: 0
                    }
                })
                .then(comments => {
                    this.comments = comments.data;
                    console.log(this.comments);
                });
        }
    }
};
</script>

<style scoped>
#comment p{
    word-wrap: break-word;
}
</style>

