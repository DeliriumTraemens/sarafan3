<template>
    <v-app>
        <v-container>
            <div class="tree">
                <ul class="tree-list">
                    <TreeNode :node="catList"/>
                </ul>
            </div>
        </v-container>
    </v-app>
</template>

<script>
    import axios from 'axios'
    import TitleHeader from "./TitleHeader.vue";
    import TreeNode from './TreeNode.vue'
    export default {
        name: 'TreeRoot',
        components:{
            TreeNode,
            TitleHeader
        },
        data () {
            return{
                title: 'Hello From Tree Root Yooo',
                categoryUrl: 'http://localhost:8080/category',

                catList:[]
            }
        },
        methods: {},
        beforeMount (){
            axios.get(this.categoryUrl)
                .then(res =>{
                    console.log("Categories List")
                    console.log(res.data)
                    this.catList.push(...res.data)
                    console.log('Parsed array')
                    console.log(this.catList)
                })
        }

    }
</script>

<style scoped>
    .tree-list ul {
        margin: 6px 0;
        padding-left: 16px;
    }
</style>