package com.example.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.marketplace.models.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{
}
