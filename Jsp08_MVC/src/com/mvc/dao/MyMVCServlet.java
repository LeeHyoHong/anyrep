package com.mvc.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dto.MyMVCDto;


@WebServlet("/MyMVCServlet")
public class MyMVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyMVCServlet() {


    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		MyMVCDao dao = new MyMVCDaoImpl();
		
		if(command.equals("list")) {
			List<MyMVCDto> list = dao.selectAll();
			request.setAttribute("list", list);
			
			dispatch("boardlist.jsp",request,response);
		} else if(command.equals("selectone")) { 
			int res = Integer.parseInt(request.getParameter("seq"));
			MyMVCDto dto = dao.selectOne(res);
			request.setAttribute("dto", dto);
			
			dispatch("boarddetail.jsp",request,response);
			
		} else if(command.equals("insertform")) {
			response.sendRedirect("boardinsert.jsp");
			
		} else if(command.equals("boardinsert")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MyMVCDto dto = new MyMVCDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			boolean res = dao.insert(dto);
			System.out.println(res);
			if(res) {
				jsResponse("글쓰기 성공", "controller.do?command=list",response);
			} else {
				dispatch("controller.do?command=insertform",request,response);
			}
			
		} else if(command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyMVCDto dto = dao.selectOne(seq);
			
			request.setAttribute("dto", dto);
			
			dispatch("update.jsp", request, response);
			
		} else if(command.equals("update")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title= request.getParameter("title");
			String content = request.getParameter("content");
			
			MyMVCDto dto = new MyMVCDto(seq, title, content);
			
			boolean res = dao.update(dto);
			
			if(res) {
				jsResponse("수정 성공","controller.do?command=selectone&seq="+seq,response);
			} else {
				jsResponse("수정 실패","controller.do?command=selectone&seq="+seq,response);
			}
			
		} else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			boolean res = dao.delete(seq);
			
			if(res) {
				jsResponse("삭제 성공","controller.do?command=list",response);
				
			}else {
				dispatch("controller.do?command=selectone&seq="+seq,request,response);
			}
		}
		
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>alert('"+msg+"'); location.href='"+url+"';" + "</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	

}
