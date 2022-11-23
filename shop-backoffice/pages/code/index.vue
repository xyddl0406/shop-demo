<template>
  <div class="container">
    <p>this page is pages/code/index.vue</p>
    <CommonDataGrid
      :cols="cols"
      :url="url"
      >
    </CommonDataGrid>
  </div>
</template>

<script setup>
const cols = [
                {name: '코드그룹', key: 'codeGrp'},
                {name: '코드', key: 'code'},
                {name: '코드명', key: 'codeNm'}
              ]
const url = 'http://shop.co.kr/system-api/code/list'
</script>