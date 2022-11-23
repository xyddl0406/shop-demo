<script setup lang="ts"> 
    definePageMeta({ layout: 'login' })

    interface ResponseMessage {
        resultStatus: Number,
        message: String,
        data : any
    }

    const adminId = ref('')
    const adminPw = ref('')

    function checkEnter(e: KeyboardEvent) {
        if (e.key === 'Enter') {
            login()
        }
    }

    function login() {
        if (adminId.value == null || adminPw.value == null) {
            alert("아이디 또는 비밀번호를 확인 해 주세요.")
        } else {
            //return 으로 수정
            useFetch<ResponseMessage>(`http://shop.co.kr/auth/login?adminId=${adminId.value}&adminPw=${adminPw.value}`, {initialCache: false})
            .then(response => response.data.value)
            .then(responseMessage => {
                if (responseMessage?.resultStatus === 200) {
                    const redirectUrl = useRoute().query['from']?.toString()
                    
                    if (redirectUrl && !redirectUrl.startsWith("http://") && !redirectUrl.startsWith("https://") ) {
                        navigateTo(redirectUrl)
                    } else {
                        navigateTo('/')
                    }
                } else {
                    alert(responseMessage?.message)
                }
            }) 
        }        
    }
</script>

<template>
    <form>
        <div class="container-sm">
            <h1>관리자 로그인</h1>
            <div class="mb-3 ">
                <label for="adminId" class="form-label">아이디</label>
                <input type="text" class="form-control" id="adminId" v-model="adminId" @keyup="checkEnter">
            </div>
            <div class="mb-3">
                <label for="adminPw" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="adminPw" v-model="adminPw" @keyup="checkEnter">
            </div>
            <button type="button" class="btn btn-primary" @click="login">로그인</button>
        </div>
    </form>
</template>

<style scoped>
    .container-sm {
        padding: 2rem;
    }
</style>