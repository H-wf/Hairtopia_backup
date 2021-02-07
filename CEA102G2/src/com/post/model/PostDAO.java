package com.post.model;

import java.sql.*;
import java.util.*;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

public class PostDAO implements PostDAO_Interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/root");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = " INSERT INTO POST(desNo,postCon) VALUES(?,?);";
	private static final String GET_ALL_STMT = "SELECT * FROM hairtopia.post;";
	private static final String GET_ONE_STMT = "SELECT desNo , postCon, postPic1 FROM hairtopia.post where postNo = ?";	//back-end
	private static final String GET_DES_POST = "SELECT desNo , postCon, postPic1 FROM hairtopia.post where desNo = ?";	//front-end 複合查詢設計師名
	
	@Override
	public void insert(PostVO postVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, postVo. getDesNo());
			pstmt.setString(2, postVo.getPostCon());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
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
	public void update(PostVO postVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer postno) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public PostVO findByPrimaryKey(Integer postno) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PostVO> getAll() {
		
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				postVo = new PostVO();
				postVo.setPostNo(rs.getInt("postNo"));
				postVo.setDesNo(rs.getInt("desNo"));
				postVo.setPostCon(rs.getString("postCon"));
				postVo.setPostPic1(rs.getBytes("postPic1"));
				postVo.setPostTime(rs.getDate("postTime"));
				postVo.setPostStatus(rs.getInt("postStatus"));
				postVo.setPostPror(rs.getBoolean("postPror"));
				list.add(postVo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
