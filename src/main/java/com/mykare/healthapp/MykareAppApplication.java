package com.mykare.healthapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.mykare.healthapp.controller","com.mykare.healthapp.service",
		"com.mykare.healthapp.repository","com.mykare.healthapp.model",
		"com.mykare.healthapp.configuration"})
public class MykareAppApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MykareAppApplication.class, args);
	}

}
