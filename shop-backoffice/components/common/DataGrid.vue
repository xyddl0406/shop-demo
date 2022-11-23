<template>
    <div v-if="response.data">
        <div class="table_wrapper">
            <ClientOnly>
                <table>
                    <tr>
                        <th v-for="col in cols"> {{ col.name }} </th>
                    </tr>
                    <tr v-for="item in response.data.list">
                        <td v-for="col in cols">
                            {{ item[col.key] }}
                        </td>
                    </tr>
                    
                </table>
            </ClientOnly>
        </div>
        <div id="pagination">
            <span>전체 : {{ response.data.total }}행</span>
            <ul>
                <li v-if="useRoute().query.page && useRoute().query.page != 1" @click="previous">이전</li>
                <li v-if="!useRoute().query.page || useRoute().query.page == 1">이전</li>
                <li v-for="index in pageTotal"
                    class="navi-a"
                    href="#"
                    @click="gopage"
                    v-bind:page="index"
                    :class="{active: (useRoute().query.page == null && index == 1) || index == useRoute().query.page}"
                >    
                    {{ index }}
                        
                </li>
                <li v-if="useRoute().query.page != pageTotal" href="#" @click="next">다음</li>
                <li v-if="useRoute().query.page == pageTotal">다음</li>
            </ul>
        </div>
    </div>
</template>

<script setup>
  const props = defineProps({url: String, cols: Array})
  const route = useRoute()
  
  const currentPage = ref(route.query.page || 1) // 
  let limit = 10;
  let offset = ref((currentPage.value -1) * limit)

  /*const { data:response, pending, refresh, error } = await useFetch(`${props.url}?limit=${limit}&offset=${(currentPage.value -1) * limit}`,{
    headers : useRequestHeaders(),
    initialCache : false,
    watch: [currentPage]
  })*/

  const { data:response, refresh } = useAsyncData(() => $fetch(`${props.url}?limit=${limit}&offset=${offset.value}`, {headers: useRequestHeaders()}), {watch:[currentPage]})


  const pageTotal = computed(() => Math.ceil((response.value.data?.total || 0) / limit)) 

  function previous() {
    navigateTo({
                path: '/code',
                query: { page: --currentPage.value }
            })
  }

  function next() {
    navigateTo({
                path: '/code',
                query: { page: ++currentPage.value }
            })
  }

  function gopage(e) {
    
    navigateTo({
                path: '/code',
                query: { page: e.target.getAttribute('page') }
            })
    currentPage.value = e.target.getAttribute('page')
  }
  
</script>
<style>
    .table_wrapper {
        height: 350px;
    }

    table {
        border: solid black 1px;
        width: auto;
    }

    th, td {
        border: solid gray 1px;
    }

    #pagination ul {
        list-style:none;
    }

    #pagination li {
        list-style:none;
        float: left;
        margin-left: 10px;
    }

    #pagination li.active{
        text-decoration: underline;
        font-weight: bold;
    }
</style>