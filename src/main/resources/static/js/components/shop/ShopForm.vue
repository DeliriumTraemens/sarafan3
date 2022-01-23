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
                                small-chips
                                label="Select an Picture"
                                v-model="selectedFile"
                        ></v-file-input>

                   </div>
                    <v-btn type="submit">
                        Submit
                    </v-btn>
                </v-form>
                <v-img
                src="http://localhost:8080/images/2.jpeg" width="300px"></v-img>

            </div>



        </div>

        </v-layout>
        <hr class="my-3">
        <v-layout>
           <div>
            <h2>TEST</h2>

            <div v-for="item in items" :key="item.id">
                <v-card class="my-2" id="itemBox">
                    <p>
                    {{item.name}}
                    </p>

                <p>
                    {{item.description}}
                </p>
                    <p>
                        {{item.picture}}
                    </p>

                    <div>
                        DDDD

                        <v-img :src="'./images/'+item.picture"
                               max-width="150px"
                               max-height="150px"></v-img>

                    </div>

                    <hr class="my-1">
                    <v-btn
                    @click="showDetail(item)">
                        Detail
                    </v-btn>
                </v-card>

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
                dialog: false,
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
            showDetail(item){
                // alert('Pressed  '+item.id)
                const req = 'http://localhost:8080/item/'+item.id
                // alert(req)
                axios.get(req)
                .then(resp =>{
                    console.log({...resp.data})
                } )
            },

            submitFormItem(){
                // alert('V-Form button Pressed')
                const fdata= new FormData()
                    fdata.append('name', this.itemName)
                    fdata.append('description', this.itemDescription)
                    fdata.append('file', this.selectedFile)

                console.log(...fdata)

                axios.post('http://localhost:8080/message/addImage', fdata)
                    .then(res => {
                        console.log(res)
                        this.items.push(res.data)
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
        },
        created(){
            axios.get('http://localhost:8080/item')
            .then(res =>{
                console.log('RESPONSE OFF ITEMS')
                console.log(res)
                this.items.push(...res.data)
            })
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

#itemBox{
    border: 1px solid;
    padding: 10px;
    background-color: beige;
}
</style>



























