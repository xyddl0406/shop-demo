<script setup lang="ts">
interface ResponseMessage {
    resultStatus: Number,
    message: String,
    data : any
}
const codeInfo = {
    codeGrp: '',
    code: '',
    codeNm: '',
    useYn: 'Y',
    dispNo: null
}

let valid = ref({
    codeGrp: false,
    code: false,
    codeNm: false,
    dispNo: false
})

function selectChange(e: Event) {
    //v-model로 codeInfo.codeGrp를 넘겨서 세팅했지만 reference 자체를 넘기는건 아닌듯?
    //수동으로 값을 바꿔줘야함.
    codeInfo.codeGrp = e.target?.value
}

function insertCode() {
    //console.log(codeInfo)
    useFetch<ResponseMessage>('/system-api/code', {
        method: 'POST',
        body: codeInfo,
        initialCache: false,
        onResponse({ request, response, options }) {
            if (response.status === 200) {
                alert("저장되었습니다.")
                navigateTo("/code/grid")
            } else {
                console.log(response._data.data)
                
                valid.value.codeGrp = false;
                valid.value.code = false;
                valid.value.codeNm = false;
                valid.value.dispNo = false;

                Object.keys(response._data.data).forEach((item, index) => {
                    valid.value[item] = true
                })

                
                
                //console.log(response._data.data)
                //alert(response._data.data)
            }
        }
    })
    /*
    .
    .then(response => response.data.value)
    .then(value => {
        console.log(value)
        if (value?.resultStatus === 200) {
            alert('저장되었습니다.')
            navigateTo('/code/grid')
        } else {
            console.log(value)
            //alert(value?.data)
            //alert('저장에 실패하였습니다. 관리자에게 문의해주세요.')
        }
    })
    */
}
</script>

<template>
    <!-- maxlangth, notnull, dataType 조건 유효성검사 정책 필요. -->
    <div class="container pt-5">
        <form>
            <div class="mb-3 codeGrp_div">
                <label for="codeGrp" class="form-label">코드그룹</label>
                <CommonCodeSelect :selectedCodeGrp="codeInfo.codeGrp" :onchange="selectChange" :class="{'is-invalid': valid.codeGrp}" aria-describedby="codeGrpFeedback"></CommonCodeSelect>
                <div id="codeGrpFeedback" class="invalid-feedback">
                        코드그룹은 필수값 입니다.
                    </div>
                <!-- <select v-model="codeInfo.codeGrp">
                    <option value="123">123</option>
                    <option value="456">456</option>
                </select> -->
            </div>
            
                <div class="mb-3 col code_div">
                    <label for="code" class="form-label">코드</label>
                    <input type="text" class="form-control" :class="{'is-invalid': valid.code}" id="code" v-model="codeInfo.code" aria-describedby="codeFeedback">
                    <div id="codeFeedback" class="invalid-feedback">
                        코드는 필수값 입니다.
                    </div>
                </div>
                <div class="mb-3 col code_div">
                    <label for="codeNm" class="form-label">코드명</label>
                    <input type="text" class="form-control" :class="{'is-invalid': valid.codeNm}" id="codeNm" v-model="codeInfo.codeNm" aria-describedby="codeNmFeedback">
                    <div id="codeNmFeedback" class="invalid-feedback">
                        코드명은 필수값 입니다.
                    </div>
                </div>
            
                <div class="mb-3 col code_div">
                    <label for="codeNm" class="form-label">사용여부</label><br/>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="useYn" id="useYnY" value="Y" v-model="codeInfo.useYn">
                        <label class="form-check-label" for="useYnY">사용</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="useYn" id="useYnN" value="N" v-model="codeInfo.useYn">
                        <label class="form-check-label" for="useYnN">미사용</label>
                    </div>
                </div>
                <div class="mb-3 col code_div">
                    <label for="codeNm" class="form-label">전시순서</label>
                    <input type="text" class="form-control" :class="{'is-invalid': valid.dispNo}" id="dispNo" v-model="codeInfo.dispNo" aria-describedby="dispNoFeedback">
                    <div id="dispNoFeedback" class="invalid-feedback">
                        전시순서는 필수값 입니다.
                    </div>
                </div>
            
            <button type="button" class="btn btn-primary mt-5" @click="insertCode">저장</button>
        </form>
    </div>
</template>