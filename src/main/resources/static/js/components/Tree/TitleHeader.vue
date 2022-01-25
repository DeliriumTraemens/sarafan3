<template>
    <v-container>
        <div>
            <h3>{{title}}</h3>
        </div>
    </v-container>
</template>

<script>
    export default {
        name: "TitleHeader",
        props:{
            title: [String]
        }
    }
</script>

<style scoped>
h3{
    color: darkblue;
    text-align: center;
}
</style>