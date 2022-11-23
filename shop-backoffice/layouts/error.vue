<template>
  <div class="error_title">
    <slot></slot>
    <NuxtLink to="/">Go Home</NuxtLink>
  </div>
</template>

<style scoped>
  .error_title {
    text-align: center;
    padding-top: 3rem;
  }
</style>