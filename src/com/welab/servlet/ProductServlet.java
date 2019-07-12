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
		//��������
		request.setCharacterEncoding("UTF-8");
		// ��ȡmethod
		String method = request.getParameter("method");
		// ����method�ж�ִ���ĸ�����
		if ("findAll".equals(method)) {
			findAll(request, response);
		} else if ("addUI".equals(method)) {
			addUI(request, response);
		} else if ("add".equals(method)) {
				add(request, response);
		

		}
	}

	/**
	 * �����Ʒ
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// ��ȡǰ̨¼������� map
			Map<String, String[]> map = request.getParameterMap();
			// ����bean
			Product pro = new Product();
			// ��map�е����ݿ�����bean��
			BeanUtils.populate(pro, map);
			// ��pid��pdate����bean��
			pro.setPid(UUIDCls.getUUid());
			pro.setPdate(new Date().toLocaleString());

			// ����service��ɱ������
			ProductService ps=	new ProductService();
			ps.saveProduct(pro);
			
			//����ת������ѯ����/product?method=findAll
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "�����Ʒʧ��");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} 

	}

	/**
	 * ��ֹ�������Դ��¶�ڵ�ַ������
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
	 * ��ѯ������Ʒ
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// ����productService
			ProductService ps = new ProductService();
			// ��������
			List<Product> list = ps.findAll();

			// ��list���Ϸ���request�������
			request.setAttribute("list", list);

			// ����ת����list.jsp
			request.getRequestDispatcher("/list.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "��ѯ��Ʒ���ִ���");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
