package com.ikea.resourceallocation.requestscrud.configuration;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.ikea.resourceallocation.requestscrud.repo.ClusterRepo;
import com.ikea.resourceallocation.requestscrud.repo.SubClusterRepo;
import com.ikea.resourceallocation.requestscrud.service.ClusterService;
import com.ikea.resourceallocation.requestscrud.service.RequestService;
import com.ikea.resourceallocation.requestscrud.service.impl.ClusterServiceImpl;
import com.ikea.resourceallocation.requestscrud.service.impl.RequestServiceImpl;
import com.ikea.resourceallocation.requestscrud.validation.Validation;
import com.ikea.resourceallocation.requestscrud.validation.impl.ValidationImpl;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.ikea.resourceallocation.requestscrud" })
public class ConfigurationClass {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ClusterRepo clusterRepo;
	
	@Autowired
	private SubClusterRepo subClusterRepo;

	@Bean
	public RequestService requestService() {
		return new RequestServiceImpl(entityManager, clusterRepo, subClusterRepo);
	}

	@Bean
	public Validation validation() {
		return new ValidationImpl();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ClusterService clusterService() {
		return new ClusterServiceImpl();
	}

}
