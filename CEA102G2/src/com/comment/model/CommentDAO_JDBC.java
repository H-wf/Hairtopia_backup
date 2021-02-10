package com.comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CommentDAO_JDBC implements CommentDAO_Interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/hairtopia?serverTimezone=Asia/Taipei";
	String userid = "root";
	String pw = "1qaz2wsx";
	
	private static final String INSERT_STMT = "INSERT INTO COMMENT(postNo,memNo,comCon) VALUES(?,?,?);";
	private static final String GET_ALL_STMT = "SELECT * FROM hairtopia.comment;";
	private static final String GET_ONE_STMT = "SELECT comNo , postNo, memNO , comCon , comTime , comStatus FROM hairtopia.comment where comNo = ?";	//back-end
	private static final String GET_DES_POST = "SELECT desNo , postCon, postPic1 FROM post where desNo = ?";	//front-end 複合查詢設計師名
	
	private static final String DELETE = "";
	private static final String UPDATE = "";
	
	
	@Override
	public void insert(CommentVO commentVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pw);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, commentVo.getPostNo());
			pstmt.setInt(2, commentVo.getMemNO());
			pstmt.setString(3, commentVo.getComCon());
			
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
	public void update(CommentVO commentVo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer comNo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public CommentVO findByPrimaryKey(Integer comNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CommentVO> getAll() {
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVo = null;

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
				commentVo = new CommentVO();
				commentVo.setComNo(rs.getInt("comNo"));
				commentVo.setPostNo(rs.getInt("postNo"));
				commentVo.setMemNO(rs.getInt("memNo"));
				commentVo.setComCon(rs.getString("comCon"));
				commentVo.setComTime(rs.getDate("comTime"));
				commentVo.setComStatus(rs.getBoolean("comStatus"));
				list.add(commentVo); // Store the row in the list
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
		CommentDAO_JDBC dao = new CommentDAO_JDBC();
		
		//新增
			
//			CommentVO commentVo = new CommentVO();
//			
//			commentVo.setPostNo(8);
//			commentVo.setMemNO(8);
//			commentVo.setComCon("油");
//			dao.insert(commentVo);
			
		//查全部
			List<CommentVO> list = dao.getAll();
			for (CommentVO aComment : list) {
				System.out.print(aComment.getComNo() + ",");
				System.out.print(aComment.getPostNo() + ",");
				System.out.print(aComment.getMemNO() + ",");
				System.out.print(aComment.getComCon() + ",");
				System.out.print(aComment.getComTime() + ",");
				System.out.print(aComment.isComStatus());
				System.out.println();
			}
	}
	

}
