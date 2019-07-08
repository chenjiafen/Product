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
		//��ȡmethod
		String method=	request.getParameter("method");
		
		//����method�ж�ִ���ĸ�����
		if("findAll".equals(method)){
			findAll(request,response);
		}
	}
	/**
	 * ��ѯ������Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//����productService
			ProductService ps=new ProductService();
			//��������
			List<Product> list= ps.findAll();
			
			//��list���Ϸ���request�������
			request.setAttribute("list", list);
			
			//����ת����list.jsp
			request.getRequestDispatcher("/list.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "��ѯ��Ʒ���ִ���");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
