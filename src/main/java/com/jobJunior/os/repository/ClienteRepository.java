package com.jobJunior.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobJunior.os.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
