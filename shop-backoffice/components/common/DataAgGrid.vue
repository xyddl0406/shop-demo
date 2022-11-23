<template>
  <div class="container">
    <AgGridVue
      style="height: 500px"
      class="ag-theme-alpine"
      :columnDefs="props.cols"
      :rowData="props.data">
    </AgGridVue>
  </div>
</template>

<script setup lang="ts"> 
import { AgGridVue } from "ag-grid-vue3";
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-alpine.css";

const props = defineProps({data: Array, cols: Array}) //이외의 설정값은 어떻게 받아서 처리할지 고민필요

</script>