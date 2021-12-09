package com.relyy.shop.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.relyy.shop.backend.mapper.DictMapper;
import com.relyy.shop.backend.entity.DictDO;

/**
 * 字典表
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-09 14:29:14
 */
@Service
public class DictService {
	@Autowired
	private DictMapper dictMapper;

	public DictDO get(Long id){
		return dictMapper.get(id);
	}

	public List<DictDO> list(Map<String, Object> map){
		return dictMapper.list(map);
	}

	public int count(Map<String, Object> map){
		return dictMapper.count(map);
	}

	public int save(DictDO dict){
		return dictMapper.save(dict);
	}

	public int update(DictDO dict){
		return dictMapper.update(dict);
	}

	public int remove(Long id){
		return dictMapper.remove(id);
	}

	public int batchRemove(Long[] ids){
		return dictMapper.batchRemove(ids);
	}
}
