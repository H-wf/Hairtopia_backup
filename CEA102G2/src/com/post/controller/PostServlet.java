package com.post.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.post.model.PostService;
import com.post.model.PostVO;

public class PostServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	String action = req.getParameter("action");
    	
    	if("insert".equals(action)) {
    		
    		List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
    		try {
    			
				Integer desNo = new Integer(req.getParameter("desNo"));
				
				String postCon = req.getParameter("postCon").trim();
				
				if (postCon == null || postCon.trim().length() == 0) {
					errorMsgs.add("貼文內容請勿空白");
				}
				
				PostVO postVo = new PostVO();
				postVo.setPostNo(desNo);
				postVo.setPostCon(postCon);
				
				PostService postSvc = new PostService();
				postVo = postSvc.addPost(desNo, postCon);
				
				String url = "/back-end/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

}
