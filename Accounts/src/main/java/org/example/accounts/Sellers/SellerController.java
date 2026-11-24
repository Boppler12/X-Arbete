package org.example.accounts.Sellers;

import lombok.RequiredArgsConstructor;
import org.example.accounts.DTO.AccountResponse;
import org.example.accounts.DTO.CreateSellerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<AccountResponse> createSeller(@RequestBody CreateSellerRequest request) {
        AccountResponse response = sellerService.createSeller(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}