package dao;

import java.util.List;

import beans.BookBean;

public interface CURDdata {
	
public void insertData(String xml);

public void insertBookData(List<BookBean> list);

}
