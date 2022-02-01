<template>
    <div>
<!--    <div class="cardwrap">-->

        <ul class="node-tree">
            <v-row>
                <v-col cols="8">
                    <div @click="showName(node)">
                        <li >{{node.name}}</li>
<!--                        <li>id: {{node.id}}</li>-->
<!--                        <li class="label">{{node.description}}</li>-->
                    </div>
                </v-col>
                <v-col cols="4">
                <Dialog1 :datas="node"/>
                <v-btn x-small @click="deleteSubCat(node.id)">Delete</v-btn>
                <v-btn x-small @click="editSubCat(node)">Edit</v-btn>
                </v-col>
            </v-row>
<!--            <div class="mt-2"></div>-->
            <hr >

<!--ACHTUNG &#45;&#45; RECURSION-->
            <div v-if="node.childrenSet">
                <div>
                     <Node  v-for="child in node.childrenSet" :key="child.id" :node="child"
                            @articleData="showName" @editSubCat="editSubCat"/>
                </div>
            </div>

        </ul>

<!--    </div>-->
    </div>
</template>

<script>

    import TitleHeader from "./TitleHeader.vue";
    import Dialog1 from "../Forms/Dialog1.vue";
    import axios from 'axios'

    export default {
        name: "Node",
        props: {
            node: [Object]
        },
        data(){
            return{
                selectedFile: null,
                subCatName : '',
                subCatDescription: '',
                requestPath:'http://localhost:8080/category/'
            }
        },
        components: {
            TitleHeader,
            Dialog1
        },
        methods: {
            addSub(node){
                alert(node.id)
            },
            showName(name){
                this.$emit("articleData",name)
            },
            deleteSubCat(id){
                alert('Delete SubCat '+ id)
                const fd=new FormData()
                fd.append('id', id)
                axios.delete(this.requestPath+id).then(res =>{
                    console.log(res)
                })
            },
            editSubCat(node){
                this.$emit("editSubCat", node)
                console.log('node to edit')
                console.log(node)
            }

        },
    }
</script>

<style scoped>
.cardwrap{
    border: 1px dotted darkblue;
    border-radius: 4px;
    padding: 10px;
}
li{
    margin-bottom: 1px;
}
p{
    margin-bottom: 0px;
    font-size: 14px;
    color:midnightblue;
}
.node-tree{
    margin-left: 16px;
}
</style>