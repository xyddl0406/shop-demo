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
        { field: '코드', field: "code" },
        { field: '코드명', field: "codeNm"},
      ]

const gridApi = null
const columnApi = null
const defaultColDef = {
        flex: 1,
        resizable: true,
        minWidth: 100
      }

const cacheBlockSize = 50 //한번의 요청으로 가져올 데이터의 row 수
const rowBuffer = 0 //cacheBlockSize 이외에 buffer로 로딩하여 가지고있는 수 (초기 화면진입시 cacheBlockSize + rowBuffer 만큼 로딩함)
const rowModelType = 'infinite' //무한row모드
const cacheOverflowSize = 0 //??
const maxConcurrentDatasourceRequests = 1 //row 요청이 여러번 발생했을때 한꺼번에 처리 할 갯수?..
const infiniteInitialRowCount = 200 //쉽게말해 처음 진입시 한번의 MAX 스크롤당가져올 행 갯수. 스크롤 맨밑으로 이동했을때 cacheBlockSize 만큼 데이터를 가져온다.
const maxBlocksInCache = 3 //불러온 데이터를 cacheBlockSize만큼 몇개의 블럭을 cache할지

const requestHeaders = new Headers()
const rhs = useRequestHeaders()

for (const rh in rhs) {
  requestHeaders.set(rh, String(rhs[rh]))
}

function onGridReady(params) {
  this.gridApi = params.api;
  this.gridColumnApi = params.columnApi;

      const updateData = (data) => {
        const dataSource = {
          rowCount: undefined,
          getRows: (params) => {
            console.log(
              'asking for ' + params.startRow + ' to ' + params.endRow
            );
            // At this point in your code, you would call the server.
            // To make the demo look real, wait for 500ms before returning
            setTimeout(function () {
              // take a slice of the total rows
              const rowsThisPage = data.slice(params.startRow, params.endRow);
              // if on or after the last page, work out the last row.
              let lastRow = -1;
              if (data.length <= params.endRow) {
                lastRow = data.length;
              }
              // call the success callback
              params.successCallback(rowsThisPage, lastRow); //결국 한페이지에서 보여줄 데이터리스트와 총갯수(만약 데이터가 더남아있다면 -1)
            }, 500);
          },
        };
        params.api.setDatasource(dataSource);
      };

      useFetch('http://shop.co.kr/system-api/code/list?paging=false', { headers :  this.requestHeaders, initialCache: false })
      .then((resp) => resp.data.value ) //resp.json()
      .then((data) => updateData(data.data.list));
      
}

</script>