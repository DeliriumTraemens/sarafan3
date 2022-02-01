<template>
    <div>
        <v-expansion-panels>
            <v-expansion-panel
                    v-for="(item,i) in items"
                    :key="i"
            >
                <v-expansion-panel-header>
                    {{item.name}} {{i}}
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    Lorem ipsum dolor sit amet, consequat.
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-expansion-panels>
    </div>
</template>

<script>
    export default {
        name: "ExpansionList",
        props:{
            items:[Array]
        },
    }
</script>

<style scoped>

</style>