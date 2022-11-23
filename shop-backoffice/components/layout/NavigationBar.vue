<template>
  <nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
      <NuxtLink class="navbar-brand" to="/">ADMIN</NuxtLink>
      <div>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <!-- 
          <li class="nav-item">
            <NuxtLink class="nav-link active" to="/code">Code</NuxtLink>
          </li>
           -->
          <li class="nav-item">
            <NuxtLink class="nav-link active" to="/code/grid">Codegrid</NuxtLink>
          </li>
          <li class="nav-item">
            <NuxtLink class="nav-link active" to="/company/list">업체리스트</NuxtLink>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="#" @click="logout">로그아웃</a>
            <!-- <NuxtLink class="nav-link active" to="/auth/logout">로그아웃</NuxtLink> -->
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
function logout() {
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

  useFetch<ResponseMessage>('http://shop.co.kr/auth/logout', { headers :  requestHeaders, initialCache: false })
  .then(data => data.data.value)
  .then(responseMessage => { 
    if (responseMessage) {
      alert(responseMessage.message)
      navigateTo('/login/form')
    }
  })
}
</script>