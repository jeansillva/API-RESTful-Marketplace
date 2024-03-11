package com.example.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.marketplace.models.VendedorModel;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorModel,Long> {
}
