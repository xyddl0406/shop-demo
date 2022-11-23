<template>
  <div>
    <h1>MAIN 페이지입니다.</h1>
    <NuxtLink to="/code">Home page</NuxtLink>
    <button @click="route()">코드목록으로</button>
  </div>
</template>

<script>
  export default {
    methods: {
      route() {
        location.href = '/code'
      }
    }
  }
</script>
