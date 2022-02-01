<template>
   <div class="mb-3">
<!--       <v-container>-->
           <template>
<!--               <v-row justify="left">-->
                   <v-dialog

                           v-model="dialog"
                           persistent
                           max-width="600px"
                   >
                       <template v-slot:activator="{ on, attrs }">
                           <v-btn
                                   x-small
                                   color="primary"
                                   dark
                                   v-bind="attrs"
                                   v-on="on"
                           >
                               Add New Sub
                           </v-btn>
                       </template>
                       <v-card>
                           <v-card-title>
                               <h5 class="text-h5">Category Editor Parent category is {{datas.name}} </h5><br>
                               <p> </p>
                           </v-card-title>
                           <v-card-text>
                               <v-container>
                                   <v-row>
                                       <v-col
                                               cols="12"
                                               sm="12"
                                               md="12"
                                       >
                                           <v-text-field
                                                   label="Subcategory name*"
                                                   required
                                                   v-model="subCatName"
                                           ></v-text-field>
                                       </v-col>
                                       <v-col
                                               cols="12"
                                       >
                                           <v-text-field
                                                   label="Subcategory description"
                                                   hint="example of helper text only on focus"
                                                   v-model="subCatDescription"
                                           ></v-text-field>
                                       </v-col>
                                       <v-col cols="12">
                                           <v-text-field
                                                   label="Subcategory short description*"
                                                   hint="example of persistent helper text"
                                                   persistent-hint
                                                   v-model="subCatShortDescription"
                                           ></v-text-field>
                                       </v-col>

                                       <v-col cols="12">
                                           <v-file-input
                                                   prepend-icon="mdi-camera"
                                                   small-chips
                                                   label="Select an Picture"
                                                   v-model="selectedFile"
                                           ></v-file-input>
                                       </v-col>
                                       <v-col
                                               cols="12"
                                               sm="6"
                                       >
                                           <v-select
                                                   :items="['0-17', '18-29', '30-54', '54+']"
                                                   label="Age"

                                           ></v-select>
                                       </v-col>
                                       <v-col
                                               cols="12"
                                               sm="6"
                                       >
                                           <v-autocomplete
                                                   :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"
                                                   label="Interests"
                                                   multiple
                                           ></v-autocomplete>
                                       </v-col>
                                   </v-row>
                               </v-container>
                               <small>*indicates required field</small>
                           </v-card-text>
                           <v-card-actions>
                               <v-spacer></v-spacer>
                               <v-btn
                                       color="blue darken-1"
                                       text
                                       @click="dialog = false"
                               >
                                   Close
                               </v-btn>
                               <v-btn
                                       color="blue darken-1"
                                       text
                                       @click="submitNewSubCat(datas.id)"
                               >
                                   Save
                               </v-btn>
                           </v-card-actions>
                       </v-card>
                   </v-dialog>
<!--               </v-row>-->
           </template>
<!--       </v-container>-->
   </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Dialog1",
        props:{
            datas: {}
        },
        data: () => ({
            dialog: false,
            selectedFile: null,
            subCatName: '',
            subCatDescription: '',
            subCatShortDescription: '',
            parentId: ''
        }),
        // data(){
        //     return {
        //         dialog: false,
        //     }
        // },
        methods:{
            submitNewSubCat(SubCat){
               this.dialog = false
                alert(SubCat)
                this.parentId = SubCat
                const fsd = new FormData()
                fsd.append('parent', this.parentId)
                fsd.append('name', this.subCatName)
                fsd.append('description', this.subCatDescription)
                fsd.append('shortDescription', this.subCatShortDescription)

                axios.post('http://localhost:8080/category/subcat', fsd)
                    .then(res =>{
                        console.log(res)
                    })
            }
        }
    }
</script>

<style scoped>

</style>