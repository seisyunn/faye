package faye.ssm.service;

import faye.ssm.pojo.Customer;
import faye.ssm.pojo.PageBean;

public interface CustomerService {
	PageBean<Customer> list(PageBean<Customer> pageBean, String name, Integer source, Integer industry, Integer level);

	Customer findCustomerById(Integer cust_id);
}
