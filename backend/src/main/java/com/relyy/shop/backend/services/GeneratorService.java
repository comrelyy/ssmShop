package com.relyy.shop.backend.services;

import com.google.common.collect.Lists;
import com.relyy.shop.backend.entity.GenColumnsDO;
import com.relyy.shop.backend.mapper.GenColumnsMapper;
import com.relyy.shop.backend.mapper.GeneratorMapper;
import com.relyy.shop.backend.utils.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	private GenColumnsMapper genColumnsMapper;
	@Autowired
	private GeneratorMapper generatorMapper;


	public List<Map<String, Object>> list(String tableName) {
		return generatorMapper.list(tableName);
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

	private List<GenColumnsDO> transMap(String tableName,List<Map<String, Object>> columnMapList){
		int columnSort = 0;
		List<GenColumnsDO> columnList = Lists.newArrayList();
		for (Map<String, Object> column : columnMapList) {
			if("PRI".equalsIgnoreCase(column.get("columnKey")+"")) continue;
			GenColumnsDO genColumnsDO =  GeneratorUtil.transGenColumnDO(tableName,column,++columnSort);
			columnList.add(genColumnsDO);
		}
		return columnList;
	}

}
