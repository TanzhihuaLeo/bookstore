<template>
    <div class="container ml-3">
        <form class="row" id="addForm">
            <div class="form-group col-lg-5">
                书籍名称：
                <input @keyup.enter="focusChange(this)" v-model="addName" type="text" class="form-control"> 国际书籍编号：
                <input @keyup.enter="focusChange(this)" v-model="addISBN" type="text" class="form-control"> 作者：
                <input @keyup.enter="focusChange(this)" v-model="addAuthor" type="text" class="form-control"> 出版社：
                <input @keyup.enter="focusChange(this)" v-model="addPublilsh" type="text" class="form-control"> 出版时间：
                <input @keyup.enter="focusChange(this)" v-model="addPublTime" type="date" class="form-control"> 书籍分类：
                <input @keyup.enter="focusChange(this)" v-model="addKind" type="text" class="form-control"> 价格：
                <input @keyup.enter="focusChange(this)" v-model="addPrice" type="text" class="form-control"> 库存：
                <input @keyup.enter="focusChange(this)" v-model="addStorage" type="text" class="form-control">
            </div>
            <div class="form-group col-lg-7 pl-5">
                <div class="row">
                    <div class="col-lg-8">
                        <canvas ref="imgPreview"></canvas>
                        <div class="custom-file">
                            <input @change="getCover($event)" type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="custom-file-input">
                            <label class="custom-file-label">选择书籍封面</label>
                        </div>
                    </div>
                    <div v-if="show" class="col col-lg-4 align-self-end">
                        <button @click="uploadImg()" class="btn btn-primary btn-block btn-sm">上传</button>
                    </div>
                </div>
                <div class="row mt-3 mb-3">
                    简介：
                    <textarea v-model="addSummary" class="form-control" rows="5"></textarea>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <input class="btn btn-primary btn-block btn-sm mt-2" type="reset" value="取消">
                    </div>
                    <div class="col-lg-6">
                        <input @click="addBook()" class="btn btn-primary btn-block btn-sm mt-2" value="确定">
                    </div>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: "AddBook",
    data() {
        return {
            addName: "",
            addISBN: "",
            addAuthor: "",
            addPublilsh: "",
            addPublTime: "",
            addKind: "",
            addPrice: "",
            addStorage: "",
            addSummary: "",
            imgSrc: '',
            base64: '',
            show: false,
            imgName: ''
        };
    },
    methods: {
        focusChange(current){
            console.log(current);
            let input = $('#addForm input');
            for(let i=0; i<input.length; i++){
                if(current == input[i]) {
                    input[i+1].focus();
                    break;
                }
            }
        },
        addBook() {
            let book = {
                bookname: this.addName,
                isbn: this.addISBN,
                author: this.addAuthor,
                publish: this.addPublilsh,
                publtime: this.addPublTime,
                kind: this.addKind,
                price: this.addPrice,
                storage: this.addStorage,
                summary: this.addSummary,
                cover: this.imgSrc,
                delete: "1"
            };
            alert("保存成功！");
            this.axios.post(`${this.host}/addBook`, book).then(() => {
                console.log("save book ok");
            });
        },
        getCover(event) {
            if(event.target.files.length>0){
                let file = event.target.files[0];
                // 读取图片
                let reader = new FileReader();
                let img = new Image();
                this.imgName = file.name;
                reader.readAsDataURL(file);
                reader.onloadend = (event) => {
                    img.src = event.target.result;
                    this.base64 = reader.result;
                }
                // 预览图片
                let canvas = this.$refs['imgPreview'];
                let context = canvas.getContext('2d');
                img.onload = ()=>{
                    img.width = 230;
                    img.height = 250
                    canvas.width = 230
                    canvas.height = 250
                    context.clearRect(0,0,230,250)
                    context.drawImage(img,0,0,230,250)
                }
                this.show = true;
            }
        },
        uploadImg(){
            this.axios.post(`${this.host}/uploadImg?imgName=${this.imgName}`, this.base64)
            .then(res=>{
                this.imgSrc = res.data;
                this.show = false;
            })
        }
    }
};
</script>

<style scoped>
#cover{
    max-height: 250px;
}
</style>
