package com.tag.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tag.model.TagService;
import com.tag.model.TagVO;

public class TagServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String tagName = req.getParameter("tagName");
			if (tagName == null || tagName.trim().length() == 0) {
				errorMsgs.add("檢舉內容請勿空白");
			}
			TagVO tagVo = new TagVO();
			tagVo.setTagName(tagName);
			
			TagService tagSvc = new TagService();
			tagSvc.addTag(tagName);
			
			String url = "/back-end/listAllTag.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
	}

}
