<template>
    <v-main>
    <v-container>
        <v-layout>
            <hr>
<!--            <TreeNode/>-->
            <hr>
            <div>
                <h3>Editor</h3>
                <v-form id="ed1">
                    <v-text-field
                        label="Name"
                        v-model="catName"
                    ></v-text-field>

                    <v-text-field
                        label="Description"
                        v-model="catDescription"
                    ></v-text-field>

                    <v-file-input
                            small-chips
                            label="Select an Picture"
                            v-model="selectedFile"
                    ></v-file-input>
                    <v-btn @click="submitCategory">
                        Submit
                    </v-btn>
                </v-form>
            </div>
        </v-layout>
        <hr id="hr1">
        <v-container>
            <template>
                <v-row justify="center">
                    <v-expansion-panels accordion focusable>
                        <v-expansion-panel
                                v-for="cat in categoryList"
                                :key="cat.id"
                        >
                            <v-expansion-panel-header>{{cat.name}}</v-expansion-panel-header>
                            <v-expansion-panel-content>
                                <p>
                                {{cat.description}}
                                </p>

                                <v-text-field label="Name" v-model="subCatName"></v-text-field>
                                <v-text-field label="Description" v-model="subCatDescription"></v-text-field>
                                <v-btn @click="submitSubCategory(cat)">Submit</v-btn>

                                <hr class="my-3">
                                <template v-if="cat.childrenSet">
                                    <v-expansion-panels focusable>
                                        <v-expansion-panel
                                                v-for="(item,i) in cat.childrenSet"
                                                :key="item.id"
                                        >
                                            <v-expansion-panel-header>{{item.name}}</v-expansion-panel-header>
                                            <v-expansion-panel-content>
                                                {{item.description}}
                                                <v-text-field label="Name" v-model="subCatName"></v-text-field>
                                                <v-text-field label="Description" v-model="subCatDescription"></v-text-field>
                                                    <v-btn @click="submitSubCategory(cat)">Submit</v-btn>
                                            </v-expansion-panel-content>
                                        </v-expansion-panel>
                                    </v-expansion-panels>
                                </template>
                                <hr>
                            </v-expansion-panel-content>
                        </v-expansion-panel>
                    </v-expansion-panels>
                </v-row>
            </template>
        </v-container>
        <hr class="my-2">

        <v-container>
                <div v-for="cat in categoryList" :key="cat.id">
                    <ul class="catItem">
                        <li>
                            <div class="cat-item">
                                {{cat.name}}
                                <v-btn @click="addSubcategory(cat)"> Add Subcategory</v-btn>
                            </div>
                        </li>
                    </ul>
                </div>
        </v-container>

    </v-container>
    </v-main>
</template>

<script>
    import axios from 'axios'
    import TreeNode from "../Tree/TreeRoot.vue";
    export default {
        name: "Categories",
        components: {
            TreeNode
        },
        data () {
            return {
                selectedFile: null,
                catName : '',
                catDescription: '',
                subCatName : '',
                subCatDescription: '',
                categoryList:[],
                currentCategory:{
                    id: '',
                    name: '',
                    description: '',
                    shortDescription: '',
                    picture: '',
                    parentId: ''
                },
                categoryUrl: 'http://localhost:8080/category'
            }
        },
        methods: {
            submitCategory(){
                let fd = new FormData()
                fd.append('name', this.catName)
                fd.append('description', this.catDescription )

                axios.post('http://localhost:8080/category', fd)
                .then(res =>{
                    console.log(res)
                })
            },
            submitSubCategory(cat){
                // alert(cat.name)
                this.parentId=cat.id
                const fds= new FormData()
                fds.append('parent', this.parentId)
                fds.append('name', this.subCatName)
                fds.append('description', this.subCatDescription)
                console.log(fds)
                axios.post('http://localhost:8080/category/subcat', fds)
                .then(res =>{
                    console.log(res)
                })
            },

            addSubcategory(cat){
                alert(cat.id)
            }
        },
        created(){
            axios.get(this.categoryUrl)
            .then(res =>{
                console.log("Categories List")
                console.log(res.data)
                this.categoryList.push(...res.data)
                console.log('Parsed array')
                console.log(this.categoryList)
            })
        }
    }
</script>

<style scoped>
.cat-item{
    max-width: 500px;
    background-color: antiquewhite;
    border-bottom: 1px solid;
    border-radius: 4px;
    margin-bottom:5px;
    padding:5px;
    }
#ed1{
    border: 1px solid;
    border-radius: 5px;
    padding: 10px
}
#hr1{
    margin-top:10px;
    margin-bottom: 10px;
}
</style>