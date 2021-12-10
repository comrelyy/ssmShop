package com.relyy.shop.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.relyy.shop.backend.mapper.DataPermMapper;
import com.relyy.shop.backend.entity.DataPermDO;

/**
 * 数据权限管理
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:57:22
 */
@Service
public class DataPermService {
	@Autowired
	private DataPermMapper dataPermMapper;

	public DataPermDO get(Long id){
		return dataPermMapper.get(id);
	}

	public List<DataPermDO> list(Map<String, Object> map){
		return dataPermMapper.list(map);
	}

	public int count(Map<String, Object> map){
		return dataPermMapper.count(map);
	}

	public int save(DataPermDO dataPerm){
		return dataPermMapper.save(dataPerm);
	}

	public int update(DataPermDO dataPerm){
		return dataPermMapper.update(dataPerm);
	}

	public int remove(Long id){
		return dataPermMapper.remove(id);
	}

	public int batchRemove(Long[] ids){
		return dataPermMapper.batchRemove(ids);
	}
}
