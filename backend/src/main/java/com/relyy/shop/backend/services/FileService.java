package com.relyy.shop.backend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.relyy.shop.backend.common.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.relyy.shop.backend.mapper.FileMapper;
import com.relyy.shop.backend.entity.FileDO;

/**
 * 上传文件信息
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2022-01-25 17:15:38
 */
@Service
public class FileService {
	@Autowired
	private FileMapper fileMapper;

	public FileDO get(Long id){
		return fileMapper.selectById(id);
	}

	public IPage<FileDO> listByPage(Query<FileDO> query){
		IPage page = new Page(query.getPage(),query.getLimit());
		QueryWrapper<FileDO> wrapper = new QueryWrapper<>(query.getCondition());
		return fileMapper.selectPage(page,wrapper);
	}

	public int count(Map<String, Object> map){
		return fileMapper.count(map);
	}

	public int save(FileDO file){
		return fileMapper.save(file);
	}

	public int update(FileDO file){
		return fileMapper.update(file);
	}

	public int remove(Long id){
		return fileMapper.remove(id);
	}

	public int batchRemove(Long[] ids){
		return fileMapper.batchRemove(ids);
	}
}
