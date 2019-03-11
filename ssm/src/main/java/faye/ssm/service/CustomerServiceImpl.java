package faye.ssm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import faye.ssm.mapper.BaseDictMapper;
import faye.ssm.mapper.CustomerMapper;
import faye.ssm.pojo.Customer;
import faye.ssm.pojo.PageBean;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private BaseDictMapper baseDictMapper;

	@Override
	public PageBean<Customer> list(PageBean<Customer> pageBean, String name, Integer source, Integer industry,
			Integer level) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("source", source);
		map.put("industry", industry);
		map.put("level", level);
		Integer datacount = customerMapper.customerCount(map);
		pageBean.setDatacount(datacount);
		map.put("index", pageBean.getIndex());
		map.put("pageSize", pageBean.getPageSize());
		List<Customer> list = customerMapper.findCustomerPageByNameOrSourceOrIndustryOrLevel(map);
		for (Customer customer : list) {
			customer.setSource(baseDictMapper.findByDictId(customer.getCust_source()));
			customer.setIndustry(baseDictMapper.findByDictId(customer.getCust_industry()));
			customer.setLevel(baseDictMapper.findByDictId(customer.getCust_level()));
		}
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Customer findCustomerById(Integer cust_id) {
		// TODO Auto-generated method stub
		Customer customer = customerMapper.findCustomerById(cust_id);
		customer.setSource(baseDictMapper.findByDictId(customer.getCust_source()));
		customer.setIndustry(baseDictMapper.findByDictId(customer.getCust_industry()));
		customer.setLevel(baseDictMapper.findByDictId(customer.getCust_level()));
		return customer;
	}

}
