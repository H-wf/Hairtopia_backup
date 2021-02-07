package com.post.model;

import java.util.List;

public interface PostDAO_Interface {
	public void insert(PostVO postVo);
    public void update(PostVO postVo);
    public void delete(Integer postNo);
    public PostVO findByPrimaryKey(Integer postNo);
    public List<PostVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
