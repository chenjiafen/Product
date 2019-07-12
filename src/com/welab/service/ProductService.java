package com.welab.service;

import java.sql.SQLException;
import java.util.List;

import com.welab.bean.Product;
import com.welab.dao.ProductDao;



public class ProductService {
	/**
	 * ��ѯ������Ʒ
	 * @return
	 * @throws SQLException 
	 */
	public List<Product> findAll() throws SQLException {
		ProductDao pd = new ProductDao();
		return pd.findAll();
	}
	
	/**
	 * ������Ʒ
	 * @param pro
	 * @throws SQLException 
	 */
	public void saveProduct(Product pro) throws SQLException {
		ProductDao pd= new ProductDao();
		pd.saveProduct(pro);
	}

}
