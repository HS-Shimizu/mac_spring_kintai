package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.kintai;

@Repository
public interface KintaiRepository extends JpaRepository<kintai, String>{
	kintai findByYmd(String ymd);
//	kintai deleteByYmd(String ymd);
}

