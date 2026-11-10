package org.example.bff;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api")
public class AuthController {

    final private RestClient restClient;

    final private JwtService jwtService;

    public AuthController(JwtService jwtService){
        this.jwtService= jwtService;
        this.restClient= RestClient.builder().build();
    }
    // logga in(anrobar controllers)
    @RequestMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Object Credentials){
        return restClient.post()
                .uri("https://localhost:8081/auth/login")
                .body(credentials)
                .retrieve()
                .toEntity(object.class);
    }
    // Skapa användare (Anropar User Service)
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody Object user) {
        return restClient.post()
                .uri("http://localhost:8083/users")
                .body(user)
                .retrieve()
                .toEntity(Object.class);
    }

    //  Skicka meddelande (Anropar Message Service med JWT-krav)
    @PostMapping("/messages")
    public ResponseEntity<Object> forwardMessage(@RequestBody Object message, @RequestHeader("Authorization") String token) {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;

        if (!jwtService.validateToken(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ogiltig eller saknar token");
        }

        return restClient.post()
                .uri("http://localhost:8082/message/send")
                .body(message)
                .retrieve()
                .toEntity(Object.class);
    }

    //Hämta meddelanden (Anropar Message Service med JWT-krav)
    @GetMapping("/messages")
    public ResponseEntity<Object> getMessages(@RequestHeader("Authorization") String token) {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;

        if (!jwtService.validateToken(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ogiltig eller saknar token");
        }

        return restClient.get()
                .uri("http://localhost:8082/message/history") // Vi behöver lägga till denna i Message Service också
                .retrieve()
                .toEntity(Object.class);
    }
}



}
