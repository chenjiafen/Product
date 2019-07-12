package com.welab.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.welab.bean.Product;
import com.welab.utils.DataSourceUtils;

public class ProductDao {

	public List<Product> findAll() throws SQLException {

		// 创建QueryRunner
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

		// 编写sql
		String sql = "select * from product";

		// 执行sql
		List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return query;
	}

	/**
	 * 添加商品
	 * 
	 * @param pro
	 * @throws SQLException 
	 */
	public void saveProduct(Product pro) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?)";
		qr.update(sql, pro.getPid(),pro.getPname(),pro.getMarket_price(),pro.getShop_price(),pro.getPimage(),pro.getPdate(),pro.getPdesc());

	}

}
