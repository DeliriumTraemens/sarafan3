<template>
    <v-container>
        <v-layout>
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
                    <v-expansion-panels accordion>
                        <v-expansion-panel
                                v-for="cat in categoryList"
                                :key="cat.id"
                        >
                            <v-expansion-panel-header>{{cat.name}}</v-expansion-panel-header>
                            <v-expansion-panel-content>
                                <p>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                </p>
                                <v-text-field label="Name"></v-text-field>
                                <v-btn>Submit</v-btn>
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
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Categories",
        data () {
            return {
                selectedFile: null,
                catName : '',
                catDescription: '',
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