package com.welab.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.welab.bean.Product;
import com.welab.utils.DataSourceUtils;

public class ProductDao {

	public List<Product> findAll() throws SQLException {
		
		//¥¥Ω®QueryRunner
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		
		//±‡–¥sql
		String sql = "select * from product";
		
		//÷¥––sql 
		List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return query;
	}

}
