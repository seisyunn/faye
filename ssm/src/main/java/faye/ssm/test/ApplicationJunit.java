package faye.ssm.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import faye.ssm.pojo.BaseDict;
import faye.ssm.service.BaseDictService;

@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml",
		"classpath:applicationContext-service.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationJunit {

	@Autowired
	private BaseDictService baseDictService;

	@Test
	public void findByDictTypeCode() {
		List<BaseDict> list = baseDictService.findByDictTypeCode("001");
		System.out.println(list);
	}

}
