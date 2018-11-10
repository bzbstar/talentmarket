package com.bzb.talentmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.bzb.talentmarket.mapper")
public class TalentmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentmarketApplication.class, args);
	}
}
