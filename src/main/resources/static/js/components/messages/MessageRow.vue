<template>
    <v-card  class="my-2" elevation="3">
        <v-card-title primary-title>

        <b>{{ 'Id #'+message.id }} </b>
        </v-card-title>
        <v-card-text>
            {{ message.text }}
        </v-card-text>
    <v-card-actions>
        <v-btn color="orange lighten-2"
                depressed
               elevation="3"
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
    </v-card>
</template>
<script>
    import { mapActions } from 'vuex'
    export default {
        props: ['message', 'editMessage'],
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