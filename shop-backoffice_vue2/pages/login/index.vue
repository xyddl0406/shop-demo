<template>
    <div>
      <h1>ADMIN LOGIN</h1>
      <p>아이디 : <input type="text" name="adminId" id="adminId"></p>
      <p>비밀번호 : <input type="password" name="adminPw" id="adminPw"></p>
      <button @click="login()">로그인</button>
    </div>
  </template>
  
  <script>
    export default {
      methods: {
        login() {
            fetch('http://shop.co.kr/auth/login', {
              method: "POST",
              headers: {"Content-Type": "application/json",},
              body: JSON.stringify({adminId : document.getElementById('adminId').value, adminPw: document.getElementById('adminPw').value})
            })
            .then((response) => response.json())
            .then((data) => { 
                if (data.message == 'OK') {
                    location.href = '/'
                }
            });
        }
      }
    }
  </script>