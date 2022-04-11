package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.kintai;

@Repository
public interface KintaiRepository extends JpaRepository<kintai, String>,JpaSpecificationExecutor{
	kintai findByYmd(String name);
}

