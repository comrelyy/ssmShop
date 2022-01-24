package com.relyy.shop.backend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.relyy.shop.backend.entity.GenColumnsDO;
import com.relyy.shop.backend.entity.GenTableDO;
import com.relyy.shop.backend.mapper.GenColumnsMapper;
import com.relyy.shop.backend.mapper.GenTableMapper;
import com.relyy.shop.backend.mapper.GeneratorMapper;
import com.relyy.shop.backend.utils.GeneratorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/8
 */
@Service
public class GeneratorService {

	@Autowired
	private GenTableMapper genTableMapper;
	@Autowired
	private GenColumnsMapper genColumnsMapper;
	@Autowired
	private GeneratorMapper generatorMapper;

	public Integer saveTable(GenTableDO tableDO){
		return genTableMapper.insert(tableDO);
	}

	public List<GenTableDO> list(String tableName) {
		QueryWrapper<GenTableDO> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(tableName)) {
			queryWrapper.like("table_name", tableName);
		}
		List<GenTableDO> genTableDOS = genTableMapper.selectList(queryWrapper);
		genTableDOS.addAll(generatorMapper.list(tableName));
		return genTableDOS;
	}

	public void generatorCode(String[] tableNames) {
		for (String tableName : tableNames) {
			//查询表信息
			Map<String, String> table = generatorMapper.get(tableName);
			//查询列信息
			List<Map<String, Object>> maps = generatorMapper.listColumns(tableName);

			Map<String, Object> priColum = generatorMapper.getPriColum(tableName);
			//生成代码
			GeneratorUtil.generatorCode(table, GeneratorUtil.transGenColumnDO(tableName,priColum,0), transMap(tableName,maps));
		}
	}

	public List<GenColumnsDO> listColumnsByTableName(String tableName) {
		//查询列信息
		QueryWrapper<GenColumnsDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("table_name",tableName);
		queryWrapper.eq("status","unable");
		List<GenColumnsDO> genColumnsDOS1 = genColumnsMapper.selectList(queryWrapper);
		List<Map<String, Object>> maps = generatorMapper.listColumns(tableName);
		//Map<String, Object> priColum = generatorMapper.getPriColum(tableName);
		List<GenColumnsDO> genColumnsDOS = transMap(tableName, maps);
		genColumnsDOS1.addAll(genColumnsDOS);
		//genColumnsDOS.add(GeneratorUtil.transGenColumnDO(tableName,priColum,0));
		return genColumnsDOS1;
	}

	public int genColumnsSave(GenColumnsDO genColumnsDO) {
		return genColumnsMapper.insert(genColumnsDO);
	}

	private List<GenColumnsDO> transMap(String tableName,List<Map<String, Object>> columnMapList){
		int columnSort = 0;
		List<GenColumnsDO> columnList = Lists.newArrayList();
		for (Map<String, Object> column : columnMapList) {
			//if("PRI".equalsIgnoreCase(column.get("columnKey")+"")) continue;
			GenColumnsDO genColumnsDO =  GeneratorUtil.transGenColumnDO(tableName,column,++columnSort);
			columnList.add(genColumnsDO);
		}
		return columnList;
	}
}
