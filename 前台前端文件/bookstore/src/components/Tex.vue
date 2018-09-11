<template>
    <div>
        <button v-on:click="search"></button>
    </div>
</template>
<script>
export default {
    name: 'Tex',
    data(){
        return{
            userid='老李',
            userid='3',
            userpassword='123456',
            bookclass:[
                    {
                        class: '经典',
                        bookinfos: [
                            {name: '道德经', author: '老子', price: 10.1},
                            { name: '论语', author: '孔子',price: 10.2},
                            { name: '道德经一', author: '老子一', price: 10.1 },
                            { name: '论语一', author: '孔子一', price: 10.2 },
                            { name: '道德经二', author: '老子二', price: 10.1 },
                        ]
                    },
                    {
                        class: '小说',
                        bookinfos: [
                            { name: '天龙八部', author: '金庸 ', price: 100.1 },
                            { name: '小凤传奇', author: '古龙', price: 10.2 },
                            { name: '大唐双龙传', author: '黄易', price: 10.1 },
                            { name: '卧虎藏龙', author: '王度庐', price: 10.2 },
                        ]
                    },
                ],
            bookrank:[],
            pages:[1,1,1,1],
            registerinfo:{
                    username:'老王',
                    userpassword:'123456',
                    userphone:'158',
                    usersex:'0',    //男
                    useremail:'158@163.com',
                    recvname:'王百五',
                    recvaddress:'林科大电子楼',
                    recvphone:'15858558',
                },
            bookallinfo:{
                id: 1004,
                price: 28.7,
                storage: 45,
                volume: 75,
                score: 86,
                delete: 0,
                isbn: "9787548059974",
                name: "道德经",
                author: "(春秋)，老子",
                publish: "江西美术出版社",
                kind: "文学",
                summary: "全书分为上、下两篇。上篇称《道经》，下篇为《德经》。《道经》讲述的是宇宙根本，道出了天地万物变化的玄机，揭示了阴阳变幻的微妙。《德经》讲述的是处世方略。道出了人事的进退之术。本书在忠于原著的基础上，详细注解并翻译原文，并针对每章内容分别从为人之道、从政之道、经商之道等三大方向列举了大量妙趣横生的古今中外案例，力求呈现出多方位的立体化效果，使读者能够更好体会和感悟两千多年前的哲人圣典。",
                publTime: null,
                cover: null,
                commentList: [
                    {
                        commId: 3,
                        score: 4.9,
                        content: "道德经真的很不错，不愧是老子的书",
                        time: "2018-07-27T09:55:49.000+0000",
                        bookId: 1004,
                        orderId: 203642,
                        userId: 20150703
                    }
                ]
            }
        }
    },
    mounted(){
        this.axios.get("")
        .then((r) =>{
            this.bookclass=r.bookClass;
            this.bookrank = r.bookrank;
        })
        .catch((error)=>console.log(error))
        ;
        this.axios.get("localhost:8080/book/"+this.bookid,)
                .then((r) => {
                        console.log(r);
                        this.bookallinfo=r;
                        console.log(this.bookallinfo)
                        })
                    .catch((error) => {
                        console.log(error)
                        });
        
    },
    methods:{
        pagechange: function(){
            this.axios.post("",{
            pages:this.pages[1],
            class: '1'
            })
            .then((r)=>{
                this.bookclass[1]=r
            })
            .catch((error)=>console.log(error))
            },
        regist: function() {
                    console.log(
                        this.registerinfo
                        );
                    
                    this.axios.post('/user', {
                        user_name: this.registerinfo.username,
                        user_password: this.registerinfo.userpassword,
                        user_phone: this.registerinfo.userphone,
                        user_sex:this.registerinfo.usersex,
                        user_email:this.registerinfo.useremail,
                        recv_name:this.registerinfo.recvname,
                        recv_address:this.registerinfo.recvaddress,
                        recv_phone:this.registerinfo.recvphone,
                    })
                    .then((r) => {
                        console.log(r);
                        alert("ok,注册成功")
                        })
                    .catch((error) => {
                        console.log(error)
                    })
                },
        login: function(){
                this.axios.post("",{
                    user_name:this.username,
                    user_password:this.userpassword
                })
                .then((r) => {
                        console.log(r)//判断是否登录什么原因登录失败
                        })
                .catch((error) => {
                        console.log(error)
                    });
            },

        getbookinfo:function(){
                this.axios.post("",{
                bookid:1004,
            })
            .then((r) => {
                console.log(r)
                })
            .catch((error) => {
                console.log(error)
                });
            },
        getuserinfo: function(){
            this.axios.post("",{
                user_id:this.userid,
            })
            .then((r) => {
                console.log(r)
                })
            .catch((error) => {
                console.log(error)
            });
            },
        deleteoder: function(){
            this.axios.post("",{
                order_id:1,
            })
            .then((r) => {
                console.log(r)
                })
            .catch((error) => {
                console.log(error)
                });
            },
        deletecomment: function(){
            this.axios.post("",{
                comment_id:1,
            })
            .then((r) => {
                console.log(r)
                })
            .catch((error) => {
                console.log(error)
                });
            },
        modifyuserinfo: function(){
            this.axios.post("",{
                user_id:1,
                user_password: '1234567',
                user_email: 'modify@qq.com',
                userphone:'1258',
                user_sex:'1',    //男
                recvname:'王百五',
                recvaddress:'林科大电子楼714',
                recvphone:'158585588',
            })
            .then((r) => {
                console.log(r)
                })
            .catch((error) => {
                console.log(error)
                });
            },
        addcomment: function(){
            this.axios.post("",{
                user_id: 1,
                book_id: 1004,
                commentscore: 4.8,
                commentcontent:`道德经真的很不错，老子的书`,
                commenttime:'2018-08-13 09:55:49',
                })
            .then((r) => {
                console.log(r)
                })
            .catch((error) => {
                console.log(error)
                });
            },

    },
    
}
</script>
