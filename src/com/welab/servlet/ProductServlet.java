package com.welab.servlet;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.welab.bean.Product;
import com.welab.service.ProductService;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//请求乱码
		request.setCharacterEncoding("UTF-8");
		// 获取method
		String method = request.getParameter("method");
		// 根据method判断执行哪个方法
		if ("findAll".equals(method)) {
			findAll(request, response);
		} else if ("addUI".equals(method)) {
			addUI(request, response);
		} else if ("add".equals(method)) {
				add(request, response);
		

		}
	}

	/**
	 * 添加商品
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取前台录入的数据 map
			Map<String, String[]> map = request.getParameterMap();
			// 创建bean
			Product pro = new Product();
			// 把map中的数据拷贝到bean中
			BeanUtils.populate(pro, map);
			// 把pid和pdate放入bean中
			pro.setPid(UUIDCls.getUUid());
			pro.setPdate(new Date().toLocaleString());

			// 调用service完成保存操作
			ProductService ps=	new ProductService();
			ps.saveProduct(pro);
			
			//请求转发到查询链接/product?method=findAll
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "添加商品失败");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} 

	}

	/**
	 * 防止具体的资源暴露在地址栏后面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/product.jsp").forward(request, response);

	}

	/**
	 * 查询所有商品
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 创建productService
			ProductService ps = new ProductService();
			// 创建方法
			List<Product> list = ps.findAll();

			// 把list集合放入request域对象中
			request.setAttribute("list", list);

			// 请求转发到list.jsp
			request.getRequestDispatcher("/list.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询商品出现错误");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
