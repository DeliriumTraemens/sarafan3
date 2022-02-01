<template>
    <div class="catForm">
        <h3>This is a Form</h3>
        <p>{{article.name}}</p>
        <v-row>
            <v-col cols="12">
                <v-text-field label="Input Name" v-model=catName >Bbbb</v-text-field>
            </v-col>
            <v-col cols="12">
                <v-container-fluid>

                    <v-textarea
                            outlined
                            name="ArticleDescription"
                            v-model="catDescription"


                    >
                        FFFFF
                    </v-textarea>
                </v-container-fluid>

            </v-col>
            <v-col>
                <template>
                    <v-container fluid>
                        <v-textarea
                                outlined
                                name="input-7-4"
                                label="Outlined textarea"
                                :value='categoryUrl'
                        ></v-textarea>
                    </v-container>
                </template>
            </v-col>
        </v-row>
        <v-btn @click="catText">Set text</v-btn>
        <v-btn @click="submitChange(article)">Submit!</v-btn>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "CategoryForm",
        props: {
            article:
                {type: Object},
            textData: {
                type: String,
                default:'Category Description'
            },

        },
        data() {
            return {
                catName: '',
                textaAreaCont: '',

                catDescription: String,
                placeholderText: 'GGGGG',
                categoryUrl: 'http://localhost:8080/category',

            }
        },
        created() {
           return this.catDescription=this.textData

        },

        methods: {
            catText(){
                // alert(this.textData)
                this.catDescription=this.article.description;
                this.catName=this.article.name;
            },
            getDescription() {
                return this.textaAreaCont = this.article.description
            },
            submitChange(article) {
                alert(article.id + ' ' + this.catName + ' ' + this.catDescription)
                const dataForm = new FormData()
                dataForm.append('id', article.id)
                dataForm.append('name', this.catName)
                dataForm.append('description', this.catDescription)
                axios.patch(this.categoryUrl, dataForm)
                    .then(res => {
                        console.log(res)
                    })
            }
        }
    }
</script>

<style scoped>
    .catForm {
        border: 1px solid #aaaaaa;
        border-radius: 1em;
        margin: 2em;
        padding: 2em
    }
</style>