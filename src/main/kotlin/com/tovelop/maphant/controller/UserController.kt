package com.tovelop.maphant.controller

import com.tovelop.maphant.configure.security.PasswordEncoderBcrypt
import com.tovelop.maphant.dto.*
import com.tovelop.maphant.service.UserService
import com.tovelop.maphant.type.response.Response
import com.tovelop.maphant.type.response.ResponseUnit
import com.tovelop.maphant.utils.SendGrid
import com.tovelop.maphant.utils.ValidationHelper
import com.tovelop.maphant.utils.isSuccess
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class SignupController(@Autowired val userService: UserService, @Autowired val sendGrid: SendGrid) {
    @Autowired
    lateinit var passwordEncoder: PasswordEncoderBcrypt

    //이메일 검증 api
    @PostMapping("/validation/email")
    fun validationEmail(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<ResponseUnit> {
        if (!userService.isEmailValid(validationSignupDTO.email!!)) {
            return ResponseEntity.badRequest().body(Response.error("형식에 맞지 않는 이메일입니다."))
        }

        if (userService.isDuplicateEmail(validationSignupDTO.email)) {
            return ResponseEntity.badRequest().body(Response.error("이미 가입이 되어있는 이메일 입니다."))
        }

        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/validation/nickname")
    fun validationNickname(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<ResponseUnit> {
        if (!ValidationHelper.isValidNickname(validationSignupDTO.nickname!!)) {
            return ResponseEntity.badRequest().body(Response.error("별명은 3~20자의 영문, 한글, 숫자로 구성해야 합니다."))
        }

        if (userService.isDuplicateNickname(validationSignupDTO.nickname)) {
            return ResponseEntity.badRequest().body(Response.error("이미 사용중인 별명입니다."))
        }

        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/validation/phoneNum")
    fun validationPhonenum(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<ResponseUnit> {
        if (!ValidationHelper.isValidPhoneNum(validationSignupDTO.phoneNum!!)) {
            return ResponseEntity.badRequest().body(Response.error("핸드폰 번호를 형식에 맞춰주세요. ex) 010-1234-5678"))
        }
        if (userService.isDuplicatePhoneNum(validationSignupDTO.phoneNum)) {
            return ResponseEntity.badRequest().body(Response.error("이미 사용중인 핸드폰 번호입니다."))
        }

        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/validation/password")
    fun validationPassword(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<ResponseUnit> {
        if (!ValidationHelper.isValidPassword(validationSignupDTO.password!!)) {
            return ResponseEntity.badRequest()
                .body(Response.error("비밀번호는 영문 소문자/대문자 1개 이상, 숫자와 특수문자를 포함하고, 최소 8자로 구성되어야 합니다."))
        }

        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/validation/passwordCheck")
    fun validationPasswordChk(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<ResponseUnit> {
        if (validationSignupDTO.password != validationSignupDTO.passwordCheck) {
            return ResponseEntity.badRequest().body(Response.error("비밀번호와 비밀번호 확인이 동일하지 않습니다."))
        }

        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("validtion/category")
    fun validationCategory(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<ResponseUnit> {
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/validation/major")
    fun validationMajor(@RequestBody validationSignupDTO: ValidationSignupDTO): ResponseEntity<ResponseUnit> {
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/universitylist")
    fun listUniversity(@RequestBody universityName: String): ResponseEntity<Response<List<String>>> {
        //입력이 포함된 대학이름 검색 리스트로 반환
        return ResponseEntity.ok().body(Response.success(listOf()))
    }

    @PostMapping("/categorylist")
    fun listCategory(@RequestBody categoryDTO: CategoryDTO): ResponseEntity<Response<List<String>>> {
        //그냥 계열 검색 반환
        return ResponseEntity.ok().body(Response.success(listOf()))
    }

    @PostMapping("/majorlist")
    fun listMajor(@RequestBody categoryDTO: CategoryDTO): ResponseEntity<Response<List<String>>> {
        //입력이 포함된 전공이름 검색 리스트로 반환
        return ResponseEntity.ok().body(Response.success(listOf()))
    }

    @PostMapping("/selection/categorymajor")

    fun selectionCategory(@RequestBody categoryDTO: CategoryDTO): ResponseEntity<ResponseUnit> {
        //이메일, 카테고리, 메이저
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/signup")
    fun signup(@RequestBody signupDTO: SignupDTO): ResponseEntity<ResponseUnit> {
        val emailValidation = validationEmail(ValidationSignupDTO(email = signupDTO.email))
        if (!emailValidation.isSuccess()) {
            return emailValidation
        }

        val nicknameValidation = validationNickname(ValidationSignupDTO(nickname = signupDTO.nickname))
        if (!nicknameValidation.isSuccess()) {
            return nicknameValidation
        }

        val phoneNumValidation = validationPhonenum(ValidationSignupDTO(phoneNum = signupDTO.phoneNum))
        if (!phoneNumValidation.isSuccess()) {
            return phoneNumValidation
        }

        val passwordValidation = validationPassword(ValidationSignupDTO(password = signupDTO.password))
        if (!passwordValidation.isSuccess()) {
            return passwordValidation
        }

        val passwordChkValidation = validationPasswordChk(
            ValidationSignupDTO(
                password = signupDTO.password, passwordCheck = signupDTO.passwordCheck
            )
        )
        if (!passwordChkValidation.isSuccess()) {
            return passwordChkValidation
        }

        if (!ValidationHelper.isValidName(signupDTO.name)) {
            return ResponseEntity.badRequest().body(Response.error("이름을 형식에 맞춰주세요. ex) 홍길동"))
        }

        val universityId = userService.findUniversityIdBy(signupDTO.universityName)
        if (universityId == null) return ResponseEntity.badRequest().body(Response.error("해당 도메인을 가진 학교가 없습니다."))
        if (!userService.matchEmail(signupDTO.email, universityId)) {
            return ResponseEntity.badRequest().body(Response.error("이메일과 학교 이름을 확인해주세요."))
        }

        userService.signUp(signupDTO.toUserDTO(universityId, passwordEncoder))
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/login")
    fun login(@RequestBody login: LoginDTO): ResponseEntity<ResponseUnit> {
        //ID, PW DB 체크
        if (true /*입력받은 이메일, 비밀번호 DB에 있는 정보와 동일한 지 확인 */) {
            return ResponseEntity.badRequest().body(Response.error("이메일, 비밀번호를 확인해주세요."))
        }

        return ResponseEntity.ok(Response.stateOnly(true))
    }


//    @PostMapping("/findemail")
//    fun findEmail(@RequestBody findEmailDTO: FindEmailDTO): ResponseEntity<Any> {
//        val emailcheck = userService.findEmailBy(findEmailDTO.sNo, findEmailDTO.phoneNo)
//        if (emailcheck.isNullOrEmpty()) return ResponseEntity.badRequest()
//            .body(Response.error<String>("일치하는 회원정보가 없습니다"))
//        return ResponseEntity.ok(Response.success(mapOf<String, String>("email" to emailcheck)))
//    }


    //개인정보 수정 페이지 접근 전, 본인 확인 절차: 비밀번호 확인
    @PostMapping("/identification")
    fun identification(@RequestBody identificationDTO: IdentificationDTO): ResponseEntity<ResponseUnit> {
        val ogPwd = userService.findPasswordByEmail(identificationDTO.email)
        if (!passwordEncoder.matches(identificationDTO.password, ogPwd)) {
            return ResponseEntity.badRequest().body(Response.error("비밀번호를 확인해주세요."))
        }
        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/changeinfo")
    fun changeInfo(@RequestBody changeInfoDTO: ChangeInfoDTO): ResponseEntity<Response<UserDTO>> {
        val user = userService.getUser(listOf(changeInfoDTO.email))!!

        return ResponseEntity.ok().body(Response.success(user))
    }

    @PostMapping("/changeinfo/nickname")
    fun changeInfoNickname(@RequestBody changeInfoDTO: ChangeInfoDTO): ResponseEntity<ResponseUnit> {
        if (!ValidationHelper.isValidNickname(changeInfoDTO.nickname!!)) {
            return ResponseEntity.badRequest().body(Response.error("별명은 3~20자의 영문, 한글, 숫자로 구성해야 합니다."))
        }

        if (userService.isDuplicateNickname(changeInfoDTO.nickname!!)) {
            return ResponseEntity.badRequest().body(Response.error("이미 사용중인 별명입니다."))
        }

        //email로 nickname db저장

        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/changeinfo/phonenum")
    fun changePhonenum(@RequestBody changeInfoDTO: ChangeInfoDTO): ResponseEntity<ResponseUnit> {
        if (!ValidationHelper.isValidPhoneNum(changeInfoDTO.phoneNum!!)) {
            return ResponseEntity.badRequest().body(Response.error("핸드폰 번호를 형식에 맞춰주세요. ex) 010-1234-5678"))
        }

        //email로 phoneNum db저장

        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/changeinfo/pw")
    fun changeInfoPwd(@RequestBody changeInfoDTO: ChangeInfoDTO): ResponseEntity<ResponseUnit> {
        val ogPwd = userService.findPasswordByEmail(changeInfoDTO.email)

        if (!ValidationHelper.isValidPassword(changeInfoDTO.password!!)) {
            return ResponseEntity.badRequest()
                .body(Response.error("비밀번호는 영문 소문자/대문자 1개 이상, 숫자와 특수문자를 포함하고, 최소 8자로 구성되어야 합니다."))
        }

        if (changeInfoDTO.password != changeInfoDTO.passwordChk) {
            return ResponseEntity.badRequest().body(Response.error("비밀번호와 비밀번호 확인이 동일하지 않습니다."))
        }

        if (passwordEncoder.matches(changeInfoDTO.passwordChk, ogPwd)) {
            return ResponseEntity.badRequest().body(Response.error("기존 비밀번호입니다."))
        }

        userService.updateUserPasswordByEmail(changeInfoDTO.email, passwordEncoder.encode(changeInfoDTO.passwordChk))

        return ResponseEntity.ok(Response.stateOnly(true))
    }


    @PostMapping("/changepw/sendemail")
    fun changePw(@RequestBody changePw: ChangePwDTO): ResponseEntity<ResponseUnit> {
        if (!userService.isEmailValid(changePw.email)) {
            return ResponseEntity.badRequest().body(Response.error("형식에 맞지 않는 이메일입니다."))
        }

        if (userService.isDuplicateEmail(changePw.email)) {
            sendGrid.sendChangePW(changePw.email)
        }

        return ResponseEntity.ok(Response.stateOnly(true))
    }

    @PostMapping("/changepw/authenticationcode")
    fun authenticationCode(@RequestBody emailAuthDTO: EmailAuthDTO): ResponseEntity<ResponseUnit> {
        val result = sendGrid.confirmEmailToken(emailAuthDTO.email, emailAuthDTO?.authcode ?: "")

        return if (result) ResponseEntity.ok(Response.stateOnly(true)) else ResponseEntity.badRequest()
            .body(Response.error("인증번호가 일치하지 않습니다."))
    }

    @PostMapping("/changepw/newpw")
    fun newPw(@RequestBody newPwDTO: NewPwDTO): ResponseEntity<ResponseUnit> {
        if (!ValidationHelper.isValidPassword(newPwDTO.password)) {
            return ResponseEntity.badRequest()
                .body(Response.error("비밀번호는 영문 소문자/대문자 1개 이상, 숫자와 특수문자를 포함하고, 최소 8자로 구성되어야 합니다."))
        }

        if (newPwDTO.password != newPwDTO.passwordChk) {
            return ResponseEntity.badRequest().body(Response.error("비밀번호와 비밀번호 확인이 동일하지 않습니다."))
        }

        val ogPwd = userService.findPasswordByEmail(newPwDTO.email)
        if (passwordEncoder.matches(newPwDTO.passwordChk, ogPwd)) {
            return ResponseEntity.badRequest().body(Response.error("기존 비밀번호입니다."))
        }

        userService.updateUserPasswordByEmail(newPwDTO.email, passwordEncoder.encode(newPwDTO.passwordChk))

        return ResponseEntity.ok(Response.stateOnly(true))
    }
}
