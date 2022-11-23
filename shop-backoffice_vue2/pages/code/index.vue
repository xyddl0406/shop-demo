<template>
    <div>
      <div>
        <p>
          코드검색 : 
          <select name="searchType" id="searchType">
            <option value="codeGrp">코드그룹</option>
            <option value="codeNm">코드명</option>
          </select>
          <input type="search" name="searchWord"  id="searchWord"/>
          <button @click="searchCode">검색</button>
        </p>
      </div>
      <ZingGrid>
      </ZingGrid>
    </div>
</template>

<script>
import ZingGrid from zinggrid;


export default {
  components: {
    ZingGrid
  },
  methods: {
    searchCode() {
      var searchType = document.getElementById('searchType').value;
      var searchWord = document.getElementById('searchWord').value;

    }
  },
  data: () => ({
    
  }),
  beforeMount() {
    

    //this.rowData = [];
  },
  
  async asyncData () {
    return await fetch('http://shop.co.kr/system-api/code/list')
    .then((response) => response.json())
    .then((data) => { return {"data" : data.data.list}; });
  }
  
}
</script>