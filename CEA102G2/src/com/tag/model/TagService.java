package com.tag.model;

import java.util.List;

import com.tag.model.TagVO;

public class TagService {
	TagDAO_Interface dao;
	
	public TagService() {
		dao = new TagDAO();
	}
	
	public TagVO addTag(String tagName) {
		TagVO tagVo = new TagVO();
		
		tagVo.setTagName(tagName);
		
		dao.insert(tagVo);
		
		return tagVo;
	}
	
	public List<TagVO> getAll() {
		return dao.getAll();
	}
}
