<template>
    
    <select class="form-select" @change="props.onchange" v-model="props.selectedCodeGrp">
        <option value="">코드그룹을 선택해 주세요.</option>
        <option v-for="code in data?.data" v-bind:value="code.codeGrp" v-bind:selected="code.codeGrp == props.selectedCodeGrp">{{code.codeGrp}}</option>
    </select>
</template>

<script setup lang="ts">
interface ResponseMessage {
    resultStatus: Number,
    message: String,
    data : any
}
const props = defineProps({selectedCodeGrp: String, onchange: Function})

let { data } = await useAsyncData<ResponseMessage>(() => $fetch('http://shop.co.kr/system-api/code/grp', {headers: { ...useRequestHeaders(['cookie']) }})) //공통화 필요



</script>