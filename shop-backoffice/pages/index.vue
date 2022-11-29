<template>
    <div>
        <p>this is pages/index.vue</p>
        {{ data }}
    </div>
</template>
<script setup>




const { data } = await useAsyncData(() => $fetch('http://shop.co.kr/system-api/admin/currentSession', {headers: { ...useRequestHeaders(['cookie']) }})) //공통화 필요
</script>