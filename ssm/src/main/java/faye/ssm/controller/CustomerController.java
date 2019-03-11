package faye.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import faye.ssm.pojo.BaseDict;
import faye.ssm.pojo.Customer;
import faye.ssm.pojo.Message;
import faye.ssm.pojo.PageBean;
import faye.ssm.service.BaseDictService;
import faye.ssm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private BaseDictService baseDictService;

	@Value("${basedict.industry}")
	private String industry_code;

	@Value("${basedict.source}")
	private String source_code;

	@Value("${basedict.level}")
	private String level_code;

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String list(Model model, String name, Integer source, Integer industry, Integer level, Integer pageNow,
			Integer pageSize) {
		List<BaseDict> _industry = baseDictService.findByDictTypeCode(industry_code);
		List<BaseDict> _source = baseDictService.findByDictTypeCode(source_code);
		List<BaseDict> _level = baseDictService.findByDictTypeCode(level_code);
		model.addAttribute("industry", _industry);
		model.addAttribute("source", _source);
		model.addAttribute("level", _level);
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setPageNow(pageNow == null ? 1 : pageNow <= 0 ? 1 : pageNow);
		pageBean.setPageSize(pageSize == null ? 6 : pageSize);
		PageBean<Customer> list = customerService.list(pageBean, name, source, industry, level);
		model.addAttribute("list", list);
		return "list";
	}

	@RequestMapping("/toedit")
	@ResponseBody
	public Customer toEdit(Customer customer) {
		customer = customerService.findCustomerById(customer.getCust_id());
		return customer;
	}

	@RequestMapping("/updatecustomer")
	@ResponseBody
	public Message updateCustomer(Customer customer) {
		System.out.println(customer);
		return new Message();
	}

}
