<template>

    <v-container>
        <v-layout>
        <div>
            <div>
                <h3>SHOP</h3>
            </div>
            <div id="shop-form">
                <v-form @submit.prevent="submitFormItem" ref="shopform" class="mb-3" id="formv" name="form-shop">
                    <h3>
                        Shop V-Form
                    </h3>
                    <v-text-field label="Name" v-model="itemName"></v-text-field>
                    <v-text-field label="Description" v-model="itemDescription"></v-text-field>
                    <input type="file" placeholder="Input image" @change="onFileSelected">
                   <div id="btn1">
                        <v-file-input

                                label="File input"
                        ></v-file-input>

                   </div>
                    <v-btn type="submit">
                        Submit
                    </v-btn>
                </v-form>

            </div>



        </div>

        </v-layout>
    </v-container>

</template>

<script>
    import axios from 'axios'
    import item from "../../api/item";
    export default {
        name: "ShopForm",
        data(){
            return{
                itemName: '',
                itemDescription: '',
                selectedFile: null,
                Item:{
                    'name':String,
                    'description':String
                },
                TestItem:{
                    'name': 'Test Name',
                    'description': 'Test Description'
                },
                items:[]
            }
        },
        methods: {

           async submitFormItem(){
                // alert('V-Form button Pressed')
                const fdata= new FormData()
                    fdata.append('name', this.itemName)
                    fdata.append('description', this.itemDescription)
                    fdata.append('file', this.selectedFile)

                console.log(...fdata)

               await axios.post('http://localhost:8080/message/addImage', fdata)
                    .then(res => {
                        console.log(res)
                    })
                this.itemName=''
                this.itemDescription=''
                this.selectedFile=null
            },


            async saveItem(){
                const itemUrl = 'http://localhost:8080/message/addItem';
                this.Item.name = this.itemName;
                this.Item.description = this.itemDescription;
                const res = await fetch(itemUrl,{
                    method:'post',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    credentials: 'include',
                    // body: JSON.stringify({
                    //     item: this.Item,
                    //     // name: this.Item.name,
                    //     // description: this.Item.description
                    // })
                    body: JSON.stringify(this.Item)


                })
                console.log(res)
                this.itemName = '';
                this.itemDescription = '';
            },
            saveFile(){
                const fd= new FormData();


                fd.append('file', this.selectedFile, this.selectedFile.name)
                axios.post('http://localhost:8080/message/addImage', fd)
                    .then(res => {
                    console.log(res)
                })
            },
            onFileSelected(event){
                this.selectedFile= event.target.files[0]
                console.log(this.selectedFile)

            }
        }
    }
</script>

<style scoped>
#shop-form {
    padding: 10px;
    border: 1px solid;
}
#btn1{
    margin-top: 10px;
}
</style>



























