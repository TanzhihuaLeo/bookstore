<template>
    <div class="container">
        <div class="row mt-3" v-if="books.length==0">
            <div class="col col-lg-1"></div>
            <div class="col col-lg-5">
                <img src="../assets/nothing.png" class="img-fluid" alt="无相关信息">
            </div>
            <div class="col col-lg-6">
                <h2 class="mt-5 pt-5" id="msg">请先添加书籍！</h2>
            </div>
        </div>
        <div class="container book-item" v-for="(b, index) in books" :key="index">
            <div class="row">
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
                            <button @click="show()" type="button" class="btn btn-sm btn-block" data-toggle="popover" data-placement="top" :data-content="b.summary">
                                查看简介
                            </button>
                        </div>
                        <div class="col-lg-6 mt-2">
                            <button @click="putBook(b.bookid, index)" type="button" class="btn btn-sm btn-block">上新</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
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
    name: 'PutOnBook',
    data() {
        return{
            books: [],
            offset: 0,
            len: 0,
            l: 5,
            prev: true,
            last: false
        }
    },
    mounted() {
        this.getBook();
        this.getLen();
    },
    methods: {
        getBook(){
            this.axios.get(`${this.host}/getDeleteBook`,{
                params: {
                    l: this.l,
                    o: this.offset
                }
            })
            .then((books) => {
                this.books = books.data;
            });
        },
        getLen(){
            this.axios.get(`${this.host}/len/deleteBook`)
            .then(len=>{
                this.len = len.data;
                if(this.len<=this.l){
                    this.last = true;
                }
            })
        },
        putBook(id, index) {
            this.axios.put(`${this.host}/recoverBook/${id}`, {
                headers: {
                    'ContentType': 'application/json'
                }
            })
            .then(() => {
                this.books.splice(index, 1);
                this.getLen();
                this.getBook();
            })
        },
         prevPage(){
            if(this.offset > 0){
                this.offset -= this.l;
                this.getBook();
                this.last = false;
            }
            if(this.offset==0){
                this.prev = true;
            }
        },
        lastPage(){
            this.offset += this.l;
            if(this.offset < this.len){
                this.getBook();
                this.prev = false;
            }
            if(this.offset + this.l >= this.len){
                this.last = true;
            }
        },
        show(){
            $('[data-toggle="popover"]').popover();
        }
    }
}
</script>  