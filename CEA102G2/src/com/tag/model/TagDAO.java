package com.tag.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TagDAO implements TagDAO_Interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/root");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO TAG(tagName) VALUES(?);";
	private static final String GET_ALL_STMT = "SELECT * FROM hairtopia.tag;";
	private static final String GET_ONE_STMT = "SELECT desNo , postCon, postPic1 FROM post where postNo = ?";	//back-end
	private static final String GET_DES_POST = "SELECT desNo , postCon, postPic1 FROM post where desNo = ?";	//front-end 複合查詢設計師名
	
	private static final String DELETE = "";
	private static final String UPDATE = "";
	
	
	@Override
	public void insert(TagVO tagVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, tagVo.getTagName());
			
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	
	}
	@Override
	public void update(TagVO tagVo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer tagNo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public TagVO findByPrimaryKey(Integer tagNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TagVO> getAll() {
		List<TagVO> list = new ArrayList<TagVO>();
		TagVO tagVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				tagVo = new TagVO();
				tagVo.setTagNo(rs.getInt("tagNo"));
				tagVo.setTagName(rs.getString("tagName"));
				list.add(tagVo); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	

}
