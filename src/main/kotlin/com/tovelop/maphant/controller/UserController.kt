package com.tovelop.maphant.controller

import com.tovelop.maphant.dto.*
import com.tovelop.maphant.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class MemberResponse(val data: MutableMap<String, String> = mutableMapOf(), val errors: MutableList<String> = mutableListOf())
@RestController
@RequestMapping("/user")
class SignupController(@Autowired val userService: UserService) {
    //이메일 검증 api
    @PostMapping("/validation/email")
    fun validationEmail(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<MemberResponse> {
        if (userService.isEmailValid(validationSignupDTO.email)/* 이메일 형식에 맞는지 확인 (ValidationSignupDTO.email만 인자로 받아 쓰셈)*/){
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("형식에 맞지 않는 이메일입니다.")))
        }

        if (userService.duplicateEmail(validationSignupDTO.email)/* db에 중복 있는지 유니크 검사 (ValidationSignupDTO.email만 인자로 받아 쓰셈)*/){
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("이미 사용중인 이메일입니다.")))
        }

        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }

    //nickname 검증 api
    @PostMapping("/validation/nickname")
    fun validationNickname(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<MemberResponse> {
        if (userService.isNicknameValid(validationSignupDTO.nickName)/* nickname 형식에 맞는지 확인 (ValidationSignupDTO.nickname만 인자로 받아 쓰셈)*/){
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("형식에 맞지 않는 별명입니다.")))
        }

        if (userService.duplicateNickname(validationSignupDTO.nickName)/* db에 중복 있는지 유니크 검사 (ValidationSignupDTO.nickname만 인자로 받아 쓰셈)*/){
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("이미 사용중인 별명입니다.")))
        }

        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }

    //phoneNum 검증 api
    @PostMapping("/validation/phonenum")
    fun validationPhonenum(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<MemberResponse> {
        if (true/* signService.isPhonenumValid(validationSignupDTO.phoneNum) nickname 형식에 맞는지 확인 (ValidationSignupDTO.phoneNum 인자로 받아 쓰셈)*/){
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("핸드폰 번호를 확인해주세요.")))
        }

        if (true/*signService.duplicatePhonenum(validationSignupDTO.phoneNum) db에 중복 있는지 유니크 검사 (ValidationSignupDTO.phoneNum 인자로 받아 쓰셈)*/){
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("이미 사용중인 핸드폰 번호입니다.")))
        }

        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }

    //pw 검증 api
    @PostMapping("/validation/password")
    fun validationPassword(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<MemberResponse> {
        if (true/* password만 받아서 사용 가능여부(형식에 맞는지) */){
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("비밀번호는 영문 소문자, 대문자, 숫자와 특수문자를 포함해야 합니다.")))
        }

        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }

    @PostMapping("/validation/passwordcheck")
    fun validationPasswordChk(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<MemberResponse> {
        if (true/* password, passwordChk 두 개 받아서 동일한지 검사 */){
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("비밀번호와 비밀번호 확인이 동일하지 않습니다.")))
        }

        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }

    @PostMapping("/signup")
    fun signup(@RequestBody signup: SignupDTO): ResponseEntity<MemberResponse> {
        //비밀번호 동일성 체크
        if(true/*서비스.비번 두 개 틀릴 때*/) {
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("비밀번호가 일치하지 않습니다.")))
        }

        //DB 유니크 검사
        if(true/*DB 유니크 검사 모아서 유효성 검사 함수 따로 만들건지 아님 validation에서 쓴거 재활용 할건지 */) {
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("학교와 이메일을 정확히 입력해주세요.")))
        }

        //비밀번호를 제외한 나머지 형식 확인 후 DB 저장
        if(true/*DB 저장 */) {
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("학교와 이메일을 정확히 입력해주세요.")))
        }

        //대학 이메일 체크 1. *.ac.kr* 2.대학명 추출 3. universityName
        if(true/* email in .ac.kr contains check & 유효하면 이메일 전송 (2, 3번은 api 따로 분리) */) {
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("대학교 이메일이 아닙니다.")))
        }

//        이메일 전송 서비스 사용.
//        signService.sendEmail()

        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }
    @PostMapping("/login")
    fun login(@RequestBody login: LoginDTO): ResponseEntity<MemberResponse> {
        //ID, PW DB 체크
        if(true /*입력받은 이메일, 비밀번호 DB에 있는 정보와 동일한 지 확인 */) {
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("이메일, 비밀번호를 확인해주세요.")))
        }
        //JWT 주기
        //로그 기록
        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }
    @PostMapping("/findemail")
    fun findEmail(@RequestBody findEmail: FindEmailDTO): ResponseEntity<MemberResponse> {
        //학번, 전화번호 DB 체크
//        val result: String? =
        if(true/*result.isNull()인지 확인. 입력 받은 학번, 전화번호 확인 후 일치할 때 return type 스트링으로 이메일 반환, 틀렸을 때 null*/) {
            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("학번, 전화번호를 확인해주세요.")))
        }
        //이메일
        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true"/*, "email" to 일부 별표 처리 된 result*/)))
    }
    @PostMapping("/changepw")
    fun ChangePw(@RequestBody changePw: ChangePwDTO): ResponseEntity<MemberResponse> {
        //이메일 DB 체크
//        if(true/*이메일이 일치하지 않을 때*/) {
//            return ResponseEntity.badRequest().body(MemberResponse(errors = mutableListOf("유효하지 않은 이메일입니다.")))
//        }
        //        이메일 전송 서비스 사용.
        //        signService.sendEmail()
        return ResponseEntity.ok().body(MemberResponse(data= mutableMapOf("success" to "true")))
    }

    @PostMapping("/changepw/authenticationcode")
    fun authenticationCode(@RequestBody newPw: NewPwDTO) {
        //인증번호 확인
    }

    @PostMapping("/newpw")
    fun newPw(@RequestBody newPw: NewPwDTO) {
        //패스워드 입력, 검증
        //DB 패스워드 치환
    }
}
