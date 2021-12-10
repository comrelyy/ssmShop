package com.relyy.shop.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.relyy.shop.backend.mapper.DeptMapper;
import com.relyy.shop.backend.entity.DeptDO;

/**
 * 部门管理
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:55:05
 */
@Service
public class DeptService {
	@Autowired
	private DeptMapper deptMapper;

	public DeptDO get(Long deptId){
		return deptMapper.get(deptId);
	}

	public List<DeptDO> list(Map<String, Object> map){
		return deptMapper.list(map);
	}

	public int count(Map<String, Object> map){
		return deptMapper.count(map);
	}

	public int save(DeptDO dept){
		return deptMapper.save(dept);
	}

	public int update(DeptDO dept){
		return deptMapper.update(dept);
	}

	public int remove(Long deptId){
		return deptMapper.remove(deptId);
	}

	public int batchRemove(Long[] deptIds){
		return deptMapper.batchRemove(deptIds);
	}
}
