package com.example.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CustomerServiceDelegate {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getBankDetails_FallBack")
	//@HystrixCommand(fallbackMethod = "getBankAccountDetails_Fallback", commandKey="test")
	public BankAccount getBankDetails(String customerId)
	{
		Integer bankAccountId = customerDao.getBankAccountId(Integer.valueOf(customerId));
		System.out.println("Making a call");
		BankAccount bankAccount =  restTemplate.exchange("http://localhost:9091/bankAccounts/{bankAccountId}",HttpMethod.GET, null, new ParameterizedTypeReference<BankAccount>() {},bankAccountId).getBody();
		System.out.println("Made a call");
		return bankAccount;
	}
	
	@SuppressWarnings("unused")
	private BankAccount getBankDetails_FallBack(String customerId) {
		System.out.println("getBankDetails_FallBack");
		BankAccount bankAccount = new BankAccount(999,"Current",9999L);
		return bankAccount;
	}


	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
