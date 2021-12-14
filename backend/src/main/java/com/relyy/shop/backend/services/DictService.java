package com.relyy.shop.backend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.relyy.shop.backend.entity.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public List<DictDO> getHobbyList(String hobbyStr) {
		if (StringUtils.isNotBlank(hobbyStr)) {
			List<Long> idList = Arrays.stream(hobbyStr.split(";"))
					.map(Long::valueOf)
					.collect(Collectors.toList());
			QueryWrapper<DictDO> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("type", "hobby");
			queryWrapper.in("id", idList);
			return dictMapper.selectList(queryWrapper);
		}
		return Collections.EMPTY_LIST;
	}

	public List<DictDO> getSexList() {
		return getListByType("sex");
	}
	public List<DictDO> getListByType(String dictType) {
		QueryWrapper<DictDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("type", dictType);
		return dictMapper.selectList(queryWrapper);
	}

	public List<DictDO> listType() {
		return dictMapper.getAllType();
	}
}
