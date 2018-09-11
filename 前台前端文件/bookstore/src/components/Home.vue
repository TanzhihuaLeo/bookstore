<template>
    <div>
        <!-- 页头 登录 注册  -->

        <v-login></v-login>
        <!-- 图标+搜索+购物车 -->
        
        <v-jumbotron></v-jumbotron>
      <div class="container">
            <div class="row">
           
            <!-- 组件 -->
            <v-tabs></v-tabs>
            <!-- 排名推荐部分 -->
            <!-- 标签导航 -->
            
            </div>
      </div>
    
    </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex';
import TabsVue from './Tabs.vue';
import LoginVue from './Login.vue';
import JumbotronVue from './Jumbotron.vue';
    export default {
        name:'Home',
        data(){
            return{
                currentClass:'文学',
                bookclass:[
                    {
                        class:'文学',
                        books:[
                            {id:'',price:'',storage:'',volume:'',delete:'',isbn:'',name:'',author:'',kind:'',kind:'',publish:'',publTime:'',cover:'',score:'',summary:'',commentList:[]}
                        ]
                    },
                    {
                        class:'工学',
                        books:[
                            {id:'',price:'',storage:'',volume:'',delete:'',isbn:'',name:'',author:'',kind:'',kind:'',publish:'',publTime:'',cover:'',score:'',summary:'',commentList:[]}
                        ]
                    },
                    {
                        class:'数学科学',
                        books:[
                            {id:'',price:'',storage:'',volume:'',delete:'',isbn:'',name:'',author:'',kind:'',kind:'',publish:'',publTime:'',cover:'',score:'',summary:'',commentList:[]}
                        ]
                    },
                    {
                        class:'其他',
                        books:[
                            {id:'',price:'',storage:'',volume:'',delete:'',isbn:'',name:'',author:'',kind:'',kind:'',publish:'',publTime:'',cover:'',score:'',summary:'',commentList:[]}
                        ]
                    }
                ],
                car: '',
                classes: [
                    '文学',
                    '工学',
                    '数学科学',
                    '其他',
                ],
            }
        },
        components:{
            'v-tabs':TabsVue,
            'v-login':LoginVue,
            'v-jumbotron':JumbotronVue
        },
        mounted(){
            //分类查看
            this.axios.get(`${this.host}/book/kind`, {
                params: {
                    k: "文学",
                    o: 0,
                }
            })
            .then((r) => {
                    // this.bookclass[0].class="文学"
                    this.bookclass[0].books=r.data
                    })
                .catch((error) => {
                    console.log(error)
                    });
            this.axios.get(`${this.host}/book/kind`, {
                params: {
                    k: "工学",
                    o: 0,
                }
            })
            .then((r) => {
                    this.bookclass[1].books=r.data
                    })
                .catch((error) => {
                    console.log(error)
                    });
            this.axios.get(`${this.host}/book/kind`, {
                params: {
                    k: "数学科学",
                    o: 0,
                }
            })
            .then((r) => {
                    this.bookclass[2].books=r.data
                    })
                .catch((error) => {
                    console.log(error)
                    });
            this.axios.get(`${this.host}/book/kind`, {
                params: {
                    k: "其他",
                    o: 0,
                }
            })
            .then((r) => {
                    this.bookclass[3].books=r.data
                    })
                .catch((error) => {
                    console.log(error)
                    });

        },
        computed:{
          cover: function(){
            let cover='';
            cover = "http://localhost:8080"
            return cover;
            },
        },
        methods:{
            //跳转至详细页面
            // todetail: function(){
            //     this.$router.pus({path:'/detail',query:{bookid:this.bookid}})
            // },
            toinfo: function(id){
                this.$router.push({ path: '/bookinfo', query: { bookid:id }});
            },
            //翻页
            nextpage: function(currentpage){
                this.axios.post("")
                .then((r)=>{
                    this.bookinfosa[currentpage].bookinfos=r
                })
                .catch((error) => console.log(error))
            }
        }
    }
</script>

