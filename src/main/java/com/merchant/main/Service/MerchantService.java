package com.merchant.main.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.merchant.main.DTO.MerchantRequest;
import com.merchant.main.Exception.NotFoundException;
import com.merchant.main.Model.Category;
import com.merchant.main.Model.Merchant;
import com.merchant.main.Repository.CategoryRepository;
import com.merchant.main.Repository.MerchantRepository;
import com.merchant.main.Response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    private final CategoryRepository categoryRepository;

    private String generateRegistrationNumber() {
        return UUID.randomUUID().toString();
    }

    public SuccessResponse createMerchant(MerchantRequest merchant) throws Exception {
        Category category = categoryRepository.findByNameIgnoreCaseAndIgnoreSpaces(merchant.getCategory_name())
                .orElseThrow(() -> new NotFoundException("category_name", "Category not found"));

        Merchant newMerchant = new Merchant();

        newMerchant.setMerchant_name(merchant.getMerchant_name());
        newMerchant.setRegistration_number(generateRegistrationNumber());
        newMerchant.setContact(merchant.getContact());
        newMerchant.setCreated_at(LocalDateTime.now());
        newMerchant.setCategory(category);

        merchantRepository.save(newMerchant);

        return new SuccessResponse("Merchant created successfully");
    }

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public Merchant getSingleMerchantDetails(Long merchantId) {
        return merchantRepository.findById(merchantId)
                .orElseThrow(() -> new NotFoundException("merchant_id", "Merchant not found with ID: " + merchantId));
    }

    public SuccessResponse deleteMerchant(Long merchantId) {
        Merchant merchantToDelete = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new NotFoundException("merchant_id", "Merchant not found with ID: " + merchantId));

        merchantRepository.delete(merchantToDelete);

        return new SuccessResponse("Merchant deleted successfully");
    }
}
