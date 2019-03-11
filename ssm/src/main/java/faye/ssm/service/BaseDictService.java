package faye.ssm.service;

import java.util.List;

import faye.ssm.pojo.BaseDict;

public interface BaseDictService {
	List<BaseDict> findByDictTypeCode(String dictTypeCode);
}
