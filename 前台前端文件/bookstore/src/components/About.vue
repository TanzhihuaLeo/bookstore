<template>
    <div>
        <h1>{{detail}}</h1>
        <h1>{{name}}</h1>
        <router-link to="/Car">Home</router-link>
        <router-link></router-link>
    </div>
</template>
<script>
export default {
    name:"About",
    data(){
        return{
            detail:{},
            name:{},
        }
    },
    mounted(){
        this.detail = this.$route.params.uid,
        this.name = this.$route.params.name
    }
}
</script>
