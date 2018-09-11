<template>
    <div class="container">
        <div class="row form-group">
            <div class="col col-lg-4">
                <select v-model="year" @click="changeYear()" class="form-control" style="width:60%">
                    <option disabled value="">请选择年份</option>
                    <option :value="y" v-for="(y, index) in years" :key="index">{{ y }}</option>
                </select>
            </div>
            <div class="col col-lg-4">
                <select v-model="way" @change="selectWay()" class="form-control" style="width:60%">
                    <option disabled value="">按周/月/季度查看</option>
                    <option value="1">按月查看</option>
                    <option value="2">按季度查看</option>
                </select>
            </div>
            <div class="col col-lg-4">
                <select v-model="month" @change="selectMonth()" class="form-control" style="width:60%">
                    <option disabled value="">请选择月</option>
                    <option v-for="(m, index) in months" :key="index" :value="m">{{ m }}月</option>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col col-lg-6" id="volume"></div>
            <div class="col col-lg-6" id="money"></div>
        </div>
        <div class="row mt-3">
            <div class="col col-lg-6" id="order"></div>
            <div class="col col-lg-6 pl-5" id="kind"></div>
        </div>
        <div class="row mt-3">
            <h5>销量前10的书籍</h5>
        </div>
        <div class="row mt-3">
            <table class="table">
                <thead>
                    <tr>
                        <th style="width: 5%"></th>
                        <th style="width: 20%">书名</th>
                        <th style="width: 20%">作者</th>
                        <th style="width: 20%">出版社</th>
                        <th style="width: 20%">分类</th>
                        <th style="width: 10%">销量</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(b, index) in books" :key="index">
                        <th scope="row">{{ index+1 }}</th>
                        <td>{{ b.bookName }}</td>
                        <td>{{ b.bookAuthor }}</td>
                        <td>{{ b.bookPublish}}</td>
                        <td>{{ b.bookKind }}</td>
                        <td>{{ b.itemQuantity }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
let time = new Date();
let ys = new Array();
for(let i = time.getFullYear(); i >= 2015; i--){
    ys.push(i);
}
export default {
    name: "CountMgr",
    data() {
        return {
            year: time.getFullYear(),
            way: 1,
            month: time.getMonth(),
            years: ys,
            months: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            books: []
        };
    },
    mounted() {
        this.getMonthVolume();
        this.getMonthMoney();
        this.getMonthOrder();
        this.getMonthKind();
        this.getMonthVolumeTen();
    },
    methods: {
        changeYear() {
            this.selectWay();
        },
        selectWay() {
            if (this.way == 1) {
                this.getMonthVolume();
                this.getMonthMoney();
                this.getMonthOrder();
            }
            if (this.way == 2) {
                this.getSeasonMoney();
                this.getSeasonVolume();
                this.getSeasonOrder();
            }
        },
        selectMonth() {
            this.getMonthKind();
            this.getMonthVolumeTen();
        },
        getMonthVolume() {
            let volumeDiv = this.$echarts.init(document.getElementById("volume"),'light');
            this.axios
                .get(`${this.host}/countVolumeByMonth?time=${this.year}`)
                .then(volume => {
                    volumeDiv.setOption({
                        title: { text: "销量" },
                        tooltip: {},
                        xAxis: {
                            data: [
                                "1",
                                "2",
                                "3",
                                "4",
                                "5",
                                "6",
                                "7",
                                "8",
                                "9",
                                "10",
                                "11",
                                "12"
                            ]
                        },
                        yAxis: {},
                        series: [
                            {
                                data: volume.data,
                                type: "line",
                                smooth: true
                            }
                        ]
                    });
                });
        },
        getMonthMoney() {
            let moneyDiv = this.$echarts.init(document.getElementById("money"),'light');
            this.axios
                .get(`${this.host}/countMoneyByMonth?time=${this.year}`)
                .then(money => {
                    moneyDiv.setOption({
                        title: { text: "销售额" },
                        tooltip: {},
                        xAxis: {
                            data: [
                                "1",
                                "2",
                                "3",
                                "4",
                                "5",
                                "6",
                                "7",
                                "8",
                                "9",
                                "10",
                                "11",
                                "12"
                            ]
                        },
                        yAxis: {},
                        series: [
                            {
                                type: "bar",
                                data: money.data
                            }
                        ]
                    });
                });
        },
        getMonthOrder() {
            let orderDiv = this.$echarts.init(document.getElementById("order"),'light');
            this.axios
                .get(`${this.host}/countOrderByMonth?time=${this.year}`)
                .then(order => {
                    orderDiv.setOption({
                        title: { text: "订单量" },
                        tooltip: {},
                        xAxis: {
                            data: [
                                "1",
                                "2",
                                "3",
                                "4",
                                "5",
                                "6",
                                "7",
                                "8",
                                "9",
                                "10",
                                "11",
                                "12"
                            ]
                        },
                        yAxis: {},
                        series: [
                            {
                                data: order.data,
                                type: "line",
                                smooth: true
                            }
                        ]
                    });
                });
        },
        getSeasonMoney() {
            let moneyDiv = this.$echarts.init(document.getElementById("money"),'light');
            this.axios
                .get(`${this.host}/countMoneyByQuarter?time=${this.year}`)
                .then(money => {
                    moneyDiv.setOption({
                        title: { text: "销售额" },
                        tooltip: {},
                        xAxis: {
                            data: ["春", "夏", "秋", "冬"]
                        },
                        yAxis: {},
                        series: [
                            {
                                type: "bar",
                                data: money.data
                            }
                        ]
                    });
                });
        },
        getSeasonVolume() {
            let volumeDiv = this.$echarts.init(
                document.getElementById("volume"),'light'
            );
            this.axios
                .get(`${this.host}/countVolumeByQuarter?time=${this.year}`)
                .then(volume => {
                    volumeDiv.setOption({
                        title: { text: "销量" },
                        tooltip: {},
                        xAxis: {
                            data: ["春", "夏", "秋", "冬"]
                        },
                        yAxis: {},
                        series: [
                            {
                                data: volume.data,
                                type: "line",
                                smooth: true
                            }
                        ]
                    });
                });
        },
        getSeasonOrder() {
            let orderDiv = this.$echarts.init(document.getElementById("order"),'light');
            this.axios
                .get(`${this.host}/countOrderByQuarter?time=${this.year}`)
                .then(order => {
                    orderDiv.setOption({
                        title: {
                            text: "订单量"
                        },
                        tooltip: {},
                        xAxis: {
                            data: ["春", "夏", "秋", "冬"]
                        },
                        yAxis: {},
                        series: [
                            {
                                data: order.data,
                                type: "line",
                                smooth: true
                            }
                        ]
                    });
                });
        },
        getMonthKind() {
            let kindDiv = this.$echarts.init(document.getElementById("kind"),'light');
            this.axios
                .get(
                    `${this.host}/countKindVolumeByMonth?year=${
                        this.year
                    }&month=${this.month}`
                )
                .then(kind => {
                    let kindsData = kind.data;
                    let kindData = new Array();
                    let kinds = new Array();
                    for (let i = 0; i < kindsData.length; i++) {
                        let k = { name: "", value: "" };
                        k.name = kindsData[i].bookKind;
                        k.value = kindsData[i].volume;
                        kinds[i] = kindsData[i].bookKind;
                        kindData[i] = k;
                    }
                    kindDiv.setOption({
                        title: {
                            text: "书籍分类销量百分比",
                            x: "center"
                        },
                        tooltip: {
                            trigger: "item",
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: "vertical",
                            left: "left",
                            data: kinds
                        },
                        series: [
                            {
                                name: "销量",
                                type: "pie",
                                radius: "55%",
                                center: ["50%", "60%"],
                                data: kindData,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: "rgba(0, 0, 0, 0.5)"
                                    }
                                }
                            }
                        ]
                    });
                });
        },
        getMonthVolumeTen() {
            this.axios
                .get(
                    `${this.host}/selectTopVolumeByMonth?year=${
                        this.year
                    }&month=${this.month}`
                )
                .then(books => {
                    this.books = books.data;
                });
        }
    }
};
</script>

<style scoped>
#volume,
#money,
#order {
    height: 300px;
}
</style>

