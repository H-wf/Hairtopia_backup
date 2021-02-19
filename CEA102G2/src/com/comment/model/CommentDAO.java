package com.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommentDAO implements CommentDAO_Interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/root");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO COMMENT(postNo,memNo,comCon) VALUES(?,?,?);";
	private static final String GET_ALL_STMT = "SELECT * FROM hairtopia.comment;";
	
	private static final String COM_OF_APOST = "SELECT comNo , postNo, memNO , comCon , comTime , comStatus FROM hairtopia.comment where postNo = ?";	
	//取得單篇貼文留言
	private static final String GET_DES_POST = "SELECT desNo , postCon, postPic1 FROM post where desNo = ?";	//front-end 複合查詢設計師名
	
	private static final String DELETE = "UPDATE hairtopia.comment SET comStatus=? WHERE comNo=?;";
	private static final String UPDATE = "UPDATE hairtopia.comment SET comCon=? WHERE comNo=?;";
	
	
	@Override
	public void insert(CommentVO commentVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, commentVo.getPostNo());
			pstmt.setInt(2, commentVo.getMemNo());
			pstmt.setString(3, commentVo.getComCon());
			
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Override
	public void update(CommentVO commentVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, commentVo.getComCon());
			pstmt.setInt(2, commentVo.getComNo());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Override
	public void delete(CommentVO commentVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setBoolean(1, commentVo.isComStatus());
			pstmt.setInt(2, commentVo.getComNo());
			
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	@Override
	public List getComOfApost(Integer postNo) {
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(COM_OF_APOST);
			pstmt.setInt(1,postNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				commentVo = new CommentVO();
				commentVo.setComNo(rs.getInt("comNo"));
				commentVo.setPostNo(rs.getInt("postNo"));
				commentVo.setMemNo(rs.getInt("memNo"));
				commentVo.setComCon(rs.getString("comCon"));
				commentVo.setComTime(rs.getDate("comTime"));
				commentVo.setComStatus(rs.getBoolean("comStatus"));
				list.add(commentVo); // Store the row in the list
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
	@Override
	public List<CommentVO> getAll() {
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				commentVo = new CommentVO();
				commentVo.setComNo(rs.getInt("comNo"));
				commentVo.setPostNo(rs.getInt("postNo"));
				commentVo.setMemNo(rs.getInt("memNo"));
				commentVo.setComCon(rs.getString("comCon"));
				commentVo.setComTime(rs.getDate("comTime"));
				commentVo.setComStatus(rs.getBoolean("comStatus"));
				list.add(commentVo); // Store the row in the list
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
