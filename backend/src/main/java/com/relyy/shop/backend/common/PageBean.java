package com.relyy.shop.backend.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long total;
	private List<?> rows;

	public PageBean(List<?> list, Long total) {
		this.rows = list;
		this.total = total;
	}

	public PageBean(IPage page) {
		this.rows = page.getRecords();
		this.total = page.getTotal();
	}
}
