<template>
    <v-layout>
        <v-text-field
                label="Address comment"
                placeholder="Type Comments here"
                v-model="text"
                @keyup.enter="save"/>

        <v-btn  @click="save">Add Commment</v-btn><!--<input type="button" value="Save" @click="save">-->
    </v-layout>
</template>

<script>
    import {mapActions} from 'vuex';

    export default {
        name: "CommentForm",
        props:['messageId'],
        //data в виде метода, а не объекта -- для замыкания стейта внутри компонента
        data() {
            return {
                text: ''
            }
        },
        methods:{
            ...mapActions(['addCommentAction']),
            async save(){
                await this.addCommentAction({
                    text: this.text,
                    message:{
                        id: this.messageId
                    }
                })
                this.text = '';
            }
        }
    }
</script>

<style scoped>

</style>