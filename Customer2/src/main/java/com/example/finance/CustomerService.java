package com.example.finance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.finance.BankAccount;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/customers")
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerServiceDelegate customerServiceDelegate;
	
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public Customer create()
	{
		return customerDao.create();
	}
	
	@RequestMapping(value="/{customerId}",method=RequestMethod.GET)
	public BankAccount getBankDetails(@PathVariable String customerId) throws InterruptedException, ExecutionException
	{
		Callable<BankAccount> runnable1 = new Callable<BankAccount>() {

			@Override
			public BankAccount call() throws Exception {
				return customerServiceDelegate.getBankDetails(customerId);
			}
		};
		Callable<BankAccount> runnable2 = new Callable<BankAccount>() {

			@Override
			public BankAccount call() throws Exception {
				return customerServiceDelegate.getBankDetails(customerId);
			}
		};
		Callable<BankAccount> runnable3 = new Callable<BankAccount>() {

			@Override
			public BankAccount call() throws Exception {
				return customerServiceDelegate.getBankDetails(customerId);
			}
		};
		Callable<BankAccount> runnable4 = new Callable<BankAccount>() {

			@Override
			public BankAccount call() throws Exception {
				return customerServiceDelegate.getBankDetails(customerId);
			}
		};
		Callable<BankAccount> runnable5 = new Callable<BankAccount>() {

			@Override
			public BankAccount call() throws Exception {
				return customerServiceDelegate.getBankDetails(customerId);
			}
		};
		List<Callable<BankAccount>> list = new ArrayList<>();
		list.add(runnable1);
		list.add(runnable2);
		list.add(runnable3);
		list.add(runnable4);
		list.add(runnable5);
		ExecutorService ex = Executors.newFixedThreadPool(100);
		List<Future<BankAccount>> res =ex.invokeAll(list);
		return res.get(0).get();
		
	}
	
	
	
	
	

}
