package com.welab.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.welab.bean.Product;
import com.welab.service.ProductService;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取method
		String method=	request.getParameter("method");
		
		//根据method判断执行哪个方法
		if("findAll".equals(method)){
			findAll(request,response);
		}
	}
	/**
	 * 查询所有商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//创建productService
			ProductService ps=new ProductService();
			//创建方法
			List<Product> list= ps.findAll();
			
			//把list集合放入request域对象中
			request.setAttribute("list", list);
			
			//请求转发到list.jsp
			request.getRequestDispatcher("/list.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询商品出现错误");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
