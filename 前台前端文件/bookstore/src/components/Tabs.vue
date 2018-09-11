<template>
    <div class="col-md-12 col-sm-12 col-lg-12">

                <div class="row">
            <nav class="nav nav-pills nav-justified col">
                    <div v-for="c in classes" :key='c' class="col">
                        <a class="nav-item nav-link" v-bind:class="[ { active: currentClass === c }]" v-on:click="currentClass = c">{{c}}</a>
                    </div>
            </nav>
        </div>
                <table class="table">
                    
                    <thead>
                        <tr>
                            <th>排名</th>
                            <th>书名</th>
                            <th>作者</th>
                            <th v-show="currentClass==='销量'">销量</th>
                            <th v-show="currentClass==='评分'">评分</th>
                            <th v-show="currentClass==='新品'">价格</th>
                            <th >查看</th>
                        </tr>
                    </thead>
                    <tbody v-if="currentClass==='销量'">
                        <tr v-for="(i,index) in bookinfox" :key="index">
                            <td>{{index+1}}</td>
                            <td>{{i.name}}</td>
                            <td>{{i.author}}</td>
                            <td>{{i.volume}}</td>
                            <td>
                                <button type="button" @click="toinfo(i.id)" class="btn btn-primary">查看</button>
                            </td>
                        </tr>
                        
                    </tbody>
                    <tbody v-if="currentClass==='评分'">
                        <tr v-for="(i,index) in bookinfox1" :key="index">
                            <td>{{index+1}}</td>
                            <td>{{i.name}}</td>
                            <td>{{i.author}}</td>
                            <td>{{i.score}}</td>
                            <td>
                                <button type="button" @click="toinfo(i.id)" class="btn btn-primary">查看</button>
                            </td>
                        </tr>
                        
                    </tbody>
                    <tbody v-if="currentClass==='新品'">
                        <tr v-for="(i,index) in bookinfox2" :key="index">
                            <td>{{index+1}}</td>
                            <td>{{i.name}}</td>
                            <td>{{i.author}}</td>
                            <td>{{i.price}}</td>
                            <td>
                                <button type="button" @click="toinfo(i.id)" class="btn btn-primary">查看</button>
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
                
    </div>
</template>
<script>
export default {
    name:'Tabs',
    data(){
        return{
            currentClass:'销量',
            classes:['销量','评分','新品'],
            bookinfox:[

                    {name: '道德经', author: '老子', price: 10.1},
                    { name: '论语', author: '孔子',price: 10.2},
                    { name: '道德经1', author: '老子一', price: 10.1 },
                    { name: '论语1', author: '孔子一', price: 10.2 },
                    { name: '道德经2', author: '老子二', price: 10.1 },

                ],
                bookinfox1:[

                    {name: '道德经3', author: '老子', price: 10.1},
                    { name: '论语3', author: '孔子',price: 10.2},
                    { name: '道德经一', author: '老子一', price: 10.1 },
                    { name: '论语一', author: '孔子一', price: 10.2 },
                    { name: '道德经二1', author: '老子二', price: 10.1 },

                ],
                bookinfox2:[

                    {name: '道德经二', author: '老子', price: 10.1},
                    { name: '论语二', author: '孔子',price: 10.2},
                    { name: '道德经一', author: '老子一', price: 10.1 },
                    { name: '论语一', author: '孔子一', price: 10.2 },
                    { name: '道德经二2', author: '老子二', price: 10.1 },

                ],
        }
    },
    mounted(){
        // 排名
            this.axios.get(`${this.host}/book/score`)
                .then((r) => {
                        this.bookinfox=r.data
                        })
                    .catch((error) => {
                        console.log(error)
                        });
        this.axios.get(`${this.host}/book/volume`)
                .then((r) => {
                        this.bookinfox1=r.data
                        })
                    .catch((error) => {
                        console.log(error)
                        });
        this.axios.get(`${this.host}/book/time`)
                .then((r) => {
                        this.bookinfox2=r.data
                        })
                    .catch((error) => {
                        console.log(error)
                        })
    },
    methods:{
        toinfo: function(id){
                this.$router.push({ path: '/bookinfo', query: { bookid:id }});
            },
    }
}
</script>
