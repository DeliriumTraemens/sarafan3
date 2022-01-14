<template>
    <v-list-tile>
        <v-list-tile-avatar >
            <img :src="comment.author.userpic" alt="comment.author.name" size="36px">
        </v-list-tile-avatar>
        <v-list-tile-content>
            <v-list-tile-title>{{comment.text}}</v-list-tile-title>
            <v-list-tile-subtitle>{{comment.author.name}}</v-list-tile-subtitle>
        </v-list-tile-content>
    </v-list-tile>
</template>

<script>
    export default {
        name: 'CommentItem',
        props: ['comment']
    }
</script>

<style scoped>

</style>