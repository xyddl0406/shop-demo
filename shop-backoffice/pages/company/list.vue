<template>
  <div class="container">
    <CommonDataAgGrid
      v-if="data"
      style="width: auto; height: 500px"
      class="ag-theme-alpine"
      :cols="cols"
      :data="data.data.list"
    >
    </CommonDataAgGrid>
  </div>
</template>

<script setup lang="ts"> 

const requestHeaders = new Headers()
const rhs = useRequestHeaders()

for (const rh in rhs) {
  requestHeaders.set(rh, String(rhs[rh]))
} //전개연산자 공부 "..."

interface ResponseMessage {
        resultStatus: Number,
        message: String,
        data : any
    }

interface IGetRowsParams {
  startRow: Number,
  endRow: Number
}

const cols = [
        { headerName: "업체고유번호", field: "compSeq" },
        { headerName: "업체명", field: "compNm" },
        { headerName: "업체상태코드", field: "compStatCd" },
        { headerName: "업체유형코드", field: "compTypeCd" },
        { headerName: "등록일시", field: "regDt" },
        { headerName: "수정일시", field: "modDt" },
        { headerName: "등록자", field: "regSeq" },
        { headerName: "수정자", field: "modSeq" },
      ]

//const { data } = await useFetch<ResponseMessage>('http://shop.co.kr/product-api/company/list', { headers :  requestHeaders, initialCache: false })
const { data } = await useAsyncData<ResponseMessage>(() => $fetch('http://shop.co.kr/product-api/company/list', {headers: requestHeaders})) //공통화 필요



</script>