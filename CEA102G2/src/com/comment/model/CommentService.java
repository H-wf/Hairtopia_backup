package com.comment.model;

import java.util.List;


public class CommentService {

	CommentDAO_Interface dao;
	
	public CommentService() {
		dao = new CommentDAO();
	}
	
	public CommentVO addComment(Integer postNo,Integer memNo, String comCon) {
		CommentVO commentVo = new CommentVO();
		
		commentVo.setPostNo(postNo);
		commentVo.setMemNo(memNo);
		commentVo.setComCon(comCon);
		
		dao.insert(commentVo);
		
		return commentVo;
	}
	
	public List<CommentVO> getAll() {
		return dao.getAll();
	}
}
