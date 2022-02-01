<template>
    <div>
        <v-container>
                <h4>Tree Root</h4>

            <ul class="tree-list">

                    <hr>
                    <TreeNode   v-for="nod in nodes " :key="nod.id"
                                :node="nod"
                                @articleData="articleData"
                                @editSubCat="editSubCat"/>


            </ul>
        </v-container>
    </div>
</template>

<script>
    import TitleHeader from "./TitleHeader.vue";
    import TreeNode from './TreeNode.vue'

    export default {
        name: 'TreeRoot',
        props:{
            nodes:[Array]
        },
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
        methods: {
            articleData(name){
                // alert('Tree Root '+name);
                this.$emit("artText",name)
            },
            editSubCat(article){
                this.$emit("editSubCat",article)
            }
        },
        // beforeMount (){
        //     axios.get(this.categoryUrl)
        //         .then(res =>{
        //             console.log("Categories List")
        //             console.log(res.data)
        //             this.catList.push(...res.data)
        //             console.log('Parsed array')
        //             console.log(this.catList)
        //         })
        // }

    }
</script>

<style scoped>
    .tree-list ul {
        margin: 6px 0;
        padding-left: 16px;
    }
</style>