package faye.ssm.mapper;

import java.util.List;

import faye.ssm.pojo.BaseDict;

public interface BaseDictMapper {
	List<BaseDict> findByDictTypeCode(String dictTypeCode);

	BaseDict findByDictId(String id);
}
