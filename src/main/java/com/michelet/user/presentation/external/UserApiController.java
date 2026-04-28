package com.michelet.user.presentation.external;

import com.michelet.common.response.ApiResponse;
import com.michelet.user.application.service.UserCommandService;
import com.michelet.user.presentation.UserSuccessCode;
import com.michelet.user.presentation.dto.request.SignUpRequest;
import com.michelet.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserCommandService userCommandService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponse>> signUp(@RequestBody SignUpRequest request){
        UserResponse response = UserResponse.from(userCommandService.signUp(request.toCommand()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(UserSuccessCode.USER_CREATED, response));
    }
}
