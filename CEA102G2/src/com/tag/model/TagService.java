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
	
	public Integer getTagNo(String tagName) {
		return dao.findByTagName(tagName);
	}

	public String getTagName(Integer tagNo) {
		return dao.findByTagNo(tagNo);
	} 
	
	public TagVO updateTag(Integer tagNo,String tagName) {
		
		TagVO tagVo = new TagVO();
		
		tagVo.setTagNo(tagNo);
		tagVo.setTagName(tagName);
		
		dao.update(tagVo);
		
		return tagVo;
	}
	
	public void deleteTag(Integer tagNo) {
		dao.delete(tagNo);
	}
	
}
