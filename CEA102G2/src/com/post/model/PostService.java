package com.post.model;

import java.util.*;

public class PostService {
	private PostDAO_Interface dao;
	
	public PostService(){
		dao = new PostDAO();
	}
	
	public PostVO addPost(Integer desNo,String postCon) {
		PostVO postVo = new PostVO();
		
		postVo.setDesNo(desNo);
		postVo.setPostCon(postCon);
		
		dao.insert(postVo);
		
		return postVo;
	}
	
	public List<PostVO> getAll() {
		return dao.getAll();
	}
}
