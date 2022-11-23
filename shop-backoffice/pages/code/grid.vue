<template>
  <div class="container">
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

//const { data } = await useFetch<ResponseMessage>('http://shop.co.kr/system-api/code/list?paging=false', { headers :  requestHeaders, initialCache: false })

//const {data} = await useFetch('https://www.ag-grid.com/example-assets/olympic-winners.json')

</script>