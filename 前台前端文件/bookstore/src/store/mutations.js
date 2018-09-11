import * as types from './mutation-type.js';

export default {
    [types.SET_ONLINE](state, online) {
        state.online = online;
    },
    [types.SET_USER](state, user) {
        state.user = user;
    },
    [types.SET_CAR_LIST](state, cartList) {
        state.catList = carList;
    },
    [types.SET_ALERT](state, alert) {
        state.alert = alert;
    },
};