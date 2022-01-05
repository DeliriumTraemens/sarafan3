import Vue from 'vue';

const comment = Vue.resource('/comment{/id}')
export default{
    add: comment => comment.save({}, comment),
    // update: message => comment.update({id: message.id}, message),
    // remove: id => comment.remove({id})
}