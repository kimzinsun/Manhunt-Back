package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class MemberResponse(val data: MutableMap<String, String> = mutableMapOf(), val errors: MutableList<String> = mutableListOf())
@RestController
@RequestMapping("/user")
class UserController {
    @PostMapping("/signup")
    fun signup(@RequestBody signup: SignupDTO): ResponseEntity<MemberResponse> {
        //비밀번호 동일성 체크
        if(true/*서비스.비번 두 개 틀릴 때*/) {
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("비밀번호가 일치하지 않습니다.")))
        }
        //대학 이메일 체크 1. *.ac.kr* 2.대학명 추출 3. universeName

        //DB 유니크 검사
        //DB 저장

        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }
    @PostMapping("/login")
    fun login(@RequestBody login: LoginDTO) {
        //ID, PW DB 체크
        //JWT 주기
        //로그 기록
    }
    @PostMapping("/findemail")
    fun findEmail(@RequestBody findEmail: FindEmailDTO) {
        //학번, 전화번호 DB 체크
        //이메일 반환
    }
    @PostMapping("/changepw")
    fun ChangePw(@RequestBody changePw: ChangePwDTO) {
        //이메일 DB 체크
        //이메일 전송
    }

    @PostMapping("/newpw")
    fun newPw(@RequestBody newPw: NewPwDTO) {
        //인증번호 확인
        //패스워드 입력, 검증
        //DB 패스워드 치환
    }
}
