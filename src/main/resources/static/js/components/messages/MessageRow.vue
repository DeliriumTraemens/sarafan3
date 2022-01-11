<template>
    <v-card  class="my-2" elevation="3">
        <v-card-title primary-title>

        <b>{{ 'Id #'+message.id }} </b>
        </v-card-title>
        <v-card-text>
            {{ message.text }}
        </v-card-text>
        <Media v-if="message.link" :message="message"></Media>
    <v-card-actions>
        <v-btn color="orange lighten-2"
                depressed
               elevation="4"
               small
               value="Edit"
               @click="edit">Edit</v-btn>
        <v-btn icon
               elevation="3"
               color="red lighten-2"
               @click="del">
            <v-icon>
                delete_sweep
            </v-icon>
        </v-btn>
        </v-card-actions>
        <comment-list
            :comments="message.comments"
            :message-id="message.id"
        ></comment-list>
    </v-card>
</template>
<script>
    import { mapActions } from 'vuex'
    import Media from 'components/media/Media.vue'
    import CommentList from "../comment/CommentList.vue";
    export default {
        props: ['message', 'editMessage'],
        components: { CommentList, Media },
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style scoped>

</style>