package com.example.consulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.orbitz.consul.Consul;
import com.orbitz.consul.cache.KVCache;
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulapiApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ConsulapiApplication.class, args);
		//String consulUrl = getConfigPropertyString(config, "tsd.discovery.consul_url", "http://localhost:8500");


		/*
		 * Consul consul = Consul.builder().withUrl(consulUrl).build(); KVCache kvCache
		 * = KVCache.newCache(consul.keyValueClient(), path, watchSeconds);
		 */}

}
