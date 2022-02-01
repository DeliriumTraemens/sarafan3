<template>
    <v-app class="treeapp" >
       <v-container>
        <h1>Tree Main</h1>
           <v-row>
               <v-col cols="4">
                   <v-container mr-2 py-2>
                       <hr>
                       <ExpansionPanel :items="catList"/>
                       <hr>
                     <TreeRoot :nodes="catList" @artText="setArtText" @editSubCat="editSubCat"/>
                   </v-container>

               </v-col>
               <v-spacer></v-spacer>
               <v-col cols="8">
                   <v-container ml-3 py-2>
                       <ArticleList data="Hello" :article="articleText" :articleEdit="article"/>

                   </v-container>
               </v-col>
           </v-row>
<!--           <TreeView/>-->
        <Dialog1/>

       </v-container>
    </v-app>
</template>

<script>
    import axios from "axios";
    import TreeRoot from "./TreeRoot.vue";
    import Dialog1 from "../Forms/Dialog1.vue";
    import ArticleList from "./ArticleList.vue";
    import TreeView from "../expansionPanel/TreeView.vue";
    import ExpansionPanel from "../expansionPanel/ExpansionPanel.vue";

    export default {
        name: "Tree",
        components: {
            ExpansionPanel,
            TreeRoot,
            Dialog1,
            ArticleList,
            TreeView
        },
        data () {
            return{
                title: 'Hello From Tree Root Yooo',
                categoryUrl: 'http://localhost:8080/category',

                catList:[],
                articleText:[],
                article:{}
            }
        },
        methods: {
            articleData(name){
                alert('Its clicked '+name)
            },
            setArtText(name){
                // alert('Tree Component' + name)
                this.articleText = name
            },
            editSubCat(article){
                this.article=article;
            }
        },
        created (){
            axios.get(this.categoryUrl)
                .then(res =>{
                    // console.log("Categories List")
                    // console.log(res.data)
                    this.catList.push(...res.data)
                    console.log('Parsed array')
                    console.log(this.catList)
                })
        }

    }
</script>

<style scoped>
.treeapp{
    margin-bottom:1px;
}
</style>