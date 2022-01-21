import Vue from 'vue';

const itemToSend = Vue.resource('/message/addItem}')
export default{

    add: itemToSave => itemToSend.save(itemToSave)
}
