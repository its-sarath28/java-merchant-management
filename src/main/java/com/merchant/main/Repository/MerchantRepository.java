package com.merchant.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merchant.main.Model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
