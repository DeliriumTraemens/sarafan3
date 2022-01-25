<template>
    <v-container>
            <div>
                <h3>Tree node</h3>
            </div>
        <div v-if="categList">

            <div v-for="cat in categList" :key="cat.id">
                <div>
                    <TitleHeader :title="cat.name"/>

<!--                    <h3>{{cat.name}}</h3>-->
                    <p>Number {{cat.id}}</p>
                    <p>{{cat.description}}</p>
                    <hr>
                    <div v-if="cat.childrenSet">
                        <div class="ml-4" v-for="catc in cat.childrenSet" :key="catc.id">
                            <div>{{catc.name}}</div>

                        </div>

                    </div>
                    <hr>
                </div>
            </div>
        </div>
    </v-container>
</template>

<script>

    import TitleHeader from "./TitleHeader.vue";

    export default {
        name: "TreeNode",
        props: {
            categList: [Array]
        },
        components: {
            TitleHeader
        }
    }
</script>

<style scoped>

</style>