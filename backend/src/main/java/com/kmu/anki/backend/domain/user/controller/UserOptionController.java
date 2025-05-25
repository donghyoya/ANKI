package com.kmu.anki.backend.domain.user.controller;

import com.kmu.anki.backend.domain.auth.legacy.utils.PrincipalUtils;
import com.kmu.anki.backend.domain.user.dto.UserOptionDto;
import com.kmu.anki.backend.domain.user.exception.UserOptionValidationException;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.domain.user.utils.SessionUtils;
import com.kmu.anki.backend.global.controller.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/option")
@RestController
public class UserOptionController {
    private final UserOptionService userOptionService;

    @GetMapping()
    public UserOptionDto getUserOption(Authentication authentication, HttpServletRequest request){
        UserOptionDto userOptionDto = userOptionService.readOption(authentication.getName());
        SessionUtils.setUserOptions(
                request,
                userOptionDto.getDailyStudyWords(),
                userOptionDto.getDailyReviewWords(),
                userOptionDto.getLanguageCode()
        );
        return userOptionDto;
    }

    @PostMapping
    public UserOptionDto putUserOption(
            @Valid @RequestBody UserOptionDto form, BindingResult bindingResult, Authentication authentication, HttpServletRequest request
    ){
        Long id = Long.parseLong(authentication.getName());
        if(bindingResult.hasErrors()){
            boolean isNotNull = false;
            for (ObjectError error : bindingResult.getAllErrors()) {
                String code = error.getCode();
                if(code!= null && code.equals("NotNull")){
                    isNotNull = true;
                }
            }
            if(isNotNull){
                userOptionService.updateOption(
                        id,
                        null,
                        null,
                        null,
                        null
                );
            }
            throw new UserOptionValidationException();
        }
        UserOptionDto userOptionDto = userOptionService.updateOption(
                id,
                form.getDailyStudyWords(),
                form.getDailyReviewWords(),
                form.getLanguageCode(),
                form.getUtcOffset()
        );
        SessionUtils.setUserOptions(
                request,
                userOptionDto.getDailyStudyWords(),
                userOptionDto.getDailyReviewWords(),
                userOptionDto.getLanguageCode()
        );

        return userOptionDto;
    }

    @ExceptionHandler(UserOptionValidationException.class)
    public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException ex){
        log.error("[400] RuntimeException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(), "UserOption must not null. and daily card's range : [1,50]"), HttpStatus.BAD_REQUEST);
    }
}
