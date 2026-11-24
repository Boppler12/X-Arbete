package org.example.accounts.Buyer;

import lombok.RequiredArgsConstructor;
import org.example.accounts.DTO.AccountResponse;
import org.example.accounts.DTO.CreateBuyerRequest;
import org.example.accounts.DTO.CreateSellerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buyers")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createbuyer(@RequestBody CreateBuyerRequest request) {
        AccountResponse response = buyerService.createBuyer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
