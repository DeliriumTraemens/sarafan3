<template>
    <div >
        <h1>Articles</h1>
        <div class="artblock">
            <h2>{{article.name}}</h2>
            <p>Article Id #{{article.id}}</p>
            <hr>
            <div v-html="article.description"></div>
            <hr>
            <v-btn @click="decrement" x-small>Decrement</v-btn>
            <span class="counter1" style="text-align:center">{{count}}</span>
            <v-btn @click="increment" x-small>Increment</v-btn>
        </div>
        <hr>
        <CategoryForm :article="articleEdit" :textData="articleEdit.description"/>
        <ExpansionPanel/>
<!--        <TreeView/>-->



    </div>
</template>

<script>
    import CategoryForm from "./CategoryForm.vue";
    import ExpansionPanel from "../expansionPanel/ExpansionPanel.vue";
    import TreeView from "../expansionPanel/TreeView.vue";

    export default {
        name: "ArticleList",
        components: {
            CategoryForm,
            ExpansionPanel,
            TreeView
        },
        props: {
            data: {
                type: String
            },
            article: {
                type: Object,
                default:{
                    'name': 'Header',
                    'id': 0,
                    'description':'Description'
                }
            },
            articleEdit:{
                type: Object,
                required: true
            }
        },
        data(){
            return{
                lorem15:"Lorem ipsum dolor sit amet, consectetur adipisicing elit.\n" +
                    "            Consequuntur esse et expedita\n" +
                    "            fugit, nobis reprehenderit?",
                articleText: '',
                count: 0,
                currArticle:{}
            }
        },
        computed: {
            artEd(){
                return this.currArticle=this.props.articleEdit

            }
        },
        methods: {
            increment(){
                this.count++;
            },
            decrement(){
                this.count--;
            },
        },
    }
</script>

<style scoped>
.artblock {
    margin:5px;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 5px;
}
.counter1{
    margin-left: 1rem;
    margin-right: 1rem;
}
</style>