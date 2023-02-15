package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.GenCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GencodeRepository extends JpaRepository<GenCode, Long> {

  Boolean existsByCode(String email);

}
