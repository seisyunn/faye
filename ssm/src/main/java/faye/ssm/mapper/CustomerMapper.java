package faye.ssm.mapper;

import java.util.List;
import java.util.Map;

import faye.ssm.pojo.Customer;

public interface CustomerMapper {
	List<Customer> findCustomerPageByNameOrSourceOrIndustryOrLevel(Map<String, Object> map);

	Integer customerCount(Map<String, Object> map);

	Customer findCustomerById(Integer cust_id);
}
