<template>
  <div class="container">
    <NuxtLink type="button" class="btn btn-primary m-3" to="/code/form">코드 등록</NuxtLink>
    <CommonInfiniteGrid
      
      >
    </CommonInfiniteGrid>
  </div>
</template>

<script setup lang="ts"> 
const requestHeaders = new Headers()
const rhs = useRequestHeaders()

for (const rh in rhs) {
  requestHeaders.set(rh, String(rhs[rh]))
}

interface ResponseMessage {
        resultStatus: Number,
        message: String,
        data : any
    }
const cols = [
        { headerName: "코드그룹", field: "codeGrp" },
        { headerName: "코드", field: "code" },
        { headerName: "코드명", field: "codeNm" },
      ]
</script>