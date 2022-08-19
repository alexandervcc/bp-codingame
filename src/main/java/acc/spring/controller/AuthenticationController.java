package acc.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AuthenticationController {
    @PostMapping(path = "/sign-up")
    public ResponseEntity<?> signup(){
        return ResponseEntity.status(HttpStatus.OK).body("Created");
    }

    @PostMapping(path = "/log-in")
    public ResponseEntity<?> login(){
        return ResponseEntity.status(HttpStatus.OK).body("Into System");
    }



}
