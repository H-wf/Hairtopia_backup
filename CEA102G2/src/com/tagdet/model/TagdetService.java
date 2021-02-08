package com.tagdet.model;

import java.util.List;


public class TagdetService {
	TagdetDAO_Interface dao;
	
	public TagdetService() {
		dao = new TagdetDAO();
	}
	
	public TagdetVO addTag(Integer tagNo,Integer postNo) {
		TagdetVO tagdetVo = new TagdetVO();
		
		tagdetVo.setTagNo(tagNo);
		tagdetVo.setPostNo(postNo);
		
		dao.insert(tagdetVo);
		
		return tagdetVo;
	}
	
	public List<TagdetVO> getAll() {
		return dao.getAll();
	}
}
