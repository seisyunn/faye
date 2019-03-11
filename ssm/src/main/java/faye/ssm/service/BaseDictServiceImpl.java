package faye.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import faye.ssm.mapper.BaseDictMapper;
import faye.ssm.pojo.BaseDict;

@Service
@Transactional
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictMapper baseDictMapper;

	@Override
	public List<BaseDict> findByDictTypeCode(String dictTypeCode) {
		// TODO Auto-generated method stub
		return baseDictMapper.findByDictTypeCode(dictTypeCode);
	}

}
