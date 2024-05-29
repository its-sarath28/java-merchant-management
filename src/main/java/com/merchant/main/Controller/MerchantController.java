package com.merchant.main.Controller;

import java.util.List;

// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.main.DTO.MerchantRequest;
// import com.merchant.main.Exception.NotFoundException;
import com.merchant.main.Model.Merchant;
import com.merchant.main.Response.SuccessResponse;
// import com.merchant.main.Response.ErrorResponse;
import com.merchant.main.Service.MerchantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/merchants")
@Validated
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    // @PostMapping("/create-merchant")
    // public ResponseEntity<?> createMerchant(@Valid @RequestBody MerchantRequest
    // merchant) {
    // try {
    // return ResponseEntity.ok(merchantService.createMerchant(merchant));
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
    // Error("An error occoured"));
    // }
    // }

    // @GetMapping("")
    // public ResponseEntity<?> getAllMerchants() {
    // try {
    // return ResponseEntity.ok(merchantService.getAllMerchants());
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
    // Error("An error occoured"));
    // }
    // }

    // @GetMapping("/{merchantId}")
    // public ResponseEntity<?> getSingleMerchantDetails(@PathVariable Long
    // merchantId) {
    // try {
    // Merchant singleMerchant =
    // merchantService.getSingleMerchantDetails(merchantId);
    // return ResponseEntity.ok(singleMerchant);
    // } catch (NotFoundException ex) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND)
    // .body(new ErrorResponse(ex.getMessage()));
    // } catch (Exception ex) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
    // ErrorResponse("An error occurred"));
    // }
    // }

    // @DeleteMapping("/{merchantId}")
    // public ResponseEntity<?> deleteMerchant(@PathVariable Long merchantId) {
    // try {
    // SuccessResponse merchantToDelete =
    // merchantService.deleteMerchant(merchantId);
    // return ResponseEntity.ok(merchantToDelete);
    // } catch (NotFoundException ex) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND)
    // .body(new ErrorResponse(ex.getMessage()));
    // } catch (Exception ex) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
    // ErrorResponse("An error occurred"));
    // }
    // }

    @PostMapping("/create-merchant")
    public ResponseEntity<SuccessResponse> createMerchant(@Valid @RequestBody MerchantRequest merchant)
            throws Exception {
        SuccessResponse response = merchantService.createMerchant(merchant);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        List<Merchant> merchants = merchantService.getAllMerchants();
        return ResponseEntity.ok(merchants);
    }

    @GetMapping("/{merchantId}")
    public ResponseEntity<Merchant> getSingleMerchantDetails(@PathVariable Long merchantId) {
        Merchant singleMerchant = merchantService.getSingleMerchantDetails(merchantId);
        return ResponseEntity.ok(singleMerchant);
    }

    @DeleteMapping("/{merchantId}")
    public ResponseEntity<SuccessResponse> deleteMerchant(@PathVariable Long merchantId) {
        SuccessResponse response = merchantService.deleteMerchant(merchantId);
        return ResponseEntity.ok(response);
    }

}
