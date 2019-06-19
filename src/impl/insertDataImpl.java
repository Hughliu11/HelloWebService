package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import beans.BookBean;
import dao.CURDdata;
import util.DBUtil;

public class insertDataImpl implements CURDdata{
	
	public Connection conn;
	
	public insertDataImpl() {
		super();
		this.conn = DBUtil.getConnection();
	}

	@Override
	public void insertData(String xml) {
		
		String sql="insert into ws(strvalue) values(?)";
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, xml);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
			DBUtil.clossStatement(ps);
		}
		
	}

	@Override
	public void insertBookData(List<BookBean> list) {
		if(list!=null) {
			for(BookBean book:list) {
				String sql="insert into BookBean values(?,?,?,?)";
				PreparedStatement ps=null;
				
				try {
					ps=conn.prepareStatement(sql);
					ps.setString(1, book.getName());
					ps.setString(2, book.getAuthor());
					ps.setString(3, book.getYear());
					ps.setString(4, book.getPrice());
					ps.execute();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {			
					DBUtil.clossStatement(ps);
				}
				
			}
			
		}
		
	}

}
