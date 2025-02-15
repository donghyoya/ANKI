package com.kmu.anki.backend.global.auth;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(
        factory = WithMockCustomOauth2SecurityContextFactory.class
)
public @interface WithMockCustomOAuth2 {
    String name() default "name";

    String email() default "testuser@fortest.email";

    String role() default "ROLE_USER";

}
