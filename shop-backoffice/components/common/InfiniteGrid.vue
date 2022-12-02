<template>
  <div style="height: 500px">
    <ag-grid-vue
        style="width: 100%; height: 100%;"
        class="ag-theme-alpine"
        :columnDefs="columnDefs"
        @grid-ready="onGridReady"
        :defaultColDef="defaultColDef"
        :rowBuffer="rowBuffer"
        :rowSelection="rowSelection"
        :rowModelType="rowModelType"
        :cacheBlockSize="cacheBlockSize"
        :cacheOverflowSize="cacheOverflowSize"
        :maxConcurrentDatasourceRequests="maxConcurrentDatasourceRequests"
        :infiniteInitialRowCount="infiniteInitialRowCount"
        :maxBlocksInCache="maxBlocksInCache">
    </ag-grid-vue>
  </div>
</template>

<script setup>
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-alpine.css';
import { AgGridVue } from 'ag-grid-vue3';

const props = defineProps({responseData: Array})

const columnDefs = [
        {
          headerName: '코드그룹',
          field: "codeGrp",
          cellRenderer: (params) => {
            if (params.value !== undefined) {
              return params.value;
            } else {
              return '<img src="https://www.ag-grid.com/example-assets/loading.gif">';
            }
          },
        },
        { headerName: '코드', field: "code" },
        { headerName: '코드명', field: "codeNm"},
        { headerName: '코드값1', field: "codeVal1"},
        { headerName: '코드값2', field: "codeVal2"},
        { headerName: '코드값3', field: "codeVal3"},
        { headerName: '코드값4', field: "codeVal4"},
        { headerName: '코드값5', field: "codeVal5"},
        { headerName: '사용여부', field: "useYn"},
        { headerName: '전시순서', field: "dispYn"},
      ]

const gridApi = null
const columnApi = null
const defaultColDef = {
        flex: 1,
        resizable: true,
        minWidth: 100
      }

const cacheBlockSize = 20 //한번의 요청으로 가져올 데이터의 row 수
const rowBuffer = 0 //cacheBlockSize 이외에 buffer로 로딩하여 가지고있는 수 (초기 화면진입시 cacheBlockSize + rowBuffer 만큼 로딩함)
const rowModelType = 'infinite' //무한row모드
const cacheOverflowSize = 0 //??
const maxConcurrentDatasourceRequests = 1 //row 요청이 여러번 발생했을때 한꺼번에 처리 할 갯수?..
const infiniteInitialRowCount = 200 //쉽게말해 처음 진입시 한번의 MAX 스크롤당가져올 행 갯수. 스크롤 맨밑으로 이동했을때 cacheBlockSize 만큼 데이터를 가져온다.
const maxBlocksInCache = 3 //불러온 데이터를 cacheBlockSize만큼 몇개의 블럭을 cache할지

const currentEndRow = cacheBlockSize

const requestHeaders = new Headers()
const rhs = useRequestHeaders()

for (const rh in rhs) {
  requestHeaders.set(rh, String(rhs[rh]))
}

function onGridReady(params) {
  this.gridApi = params.api;
  this.gridColumnApi = params.columnApi;

  params.api.setDatasource({
    //최초 실행시 or 스크롤이동으로인해 필요한 데이터가 생길때 getRows가 실행된다.
    getRows: (params) => {
      setTimeout(function () {
      // call the success callback
      useFetch(`http://shop.co.kr/system-api/code/list?limit=${params.endRow - params.startRow}&offset=${params.startRow}`, { headers :  this.requestHeaders, initialCache: false })
        .then((resp) => resp.data.value ) //resp.json()
        .then((data) => params.successCallback(data.data.list, data.data.total));
      }, 500);
    },
  });
}

</script>