package com.post.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO_JDBC implements PostDAO_Interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/hairtopia?serverTimezone=Asia/Taipei";
	String userid = "root";
	String pw = "1qaz2wsx";
	
	private static final String INSERT_STMT = " INSERT INTO POST(desNo,postCon) VALUES(?,?);";
	private static final String GET_ALL_STMT = "SELECT * FROM hairtopia.post;";
	private static final String GET_ONE_STMT = "SELECT desNo , postCon, postPic1 FROM post where postNo = ?";	//back-end
	private static final String GET_DES_POST = "SELECT desNo , postCon, postPic1 FROM post where desNo = ?";	//front-end 複合查詢設計師名
	
	private static final String DELETE = "";
	private static final String UPDATE = "";

	@Override
	public void insert(PostVO postVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pw);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, postVo.getDesNo());
			pstmt.setString(2, postVo.getPostCon());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pw);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	public static void main(String[] args) {
		PostDAO_JDBC dao = new PostDAO_JDBC();
		
	//新增
		
//		PostVO postVo = new PostVO();
//		
//		postVo.setDesNo(1);
//		postVo.setPostCon("結衣俺の嫁");
//		dao.insert(postVo);
		
	//查全部
		List<PostVO> list = dao.getAll();
		for (PostVO aPost : list) {
			System.out.print(aPost.getPostNo() + ",");
			System.out.print(aPost.getDesNo() + ",");
			System.out.print(aPost.getPostCon() + ",");
			System.out.print(aPost.getPostPic1() + ",");
			System.out.print(aPost.getPostTime() + ",");
			System.out.print(aPost.getPostStatus() + ",");
			System.out.print(aPost.isPostPror());
			System.out.println();
		}
		
	}
}
