package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@RestController
@RequestMapping("api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity Me(OAuth2AuthenticationToken token) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login/google")
    public ResponseEntity GoogleLoginRedirect(Object anything) {
        return new ResponseEntity(HttpStatus.OK);
    }


}
