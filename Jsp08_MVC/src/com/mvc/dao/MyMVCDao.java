package com.mvc.dao;

import java.util.List;

import com.mvc.dto.MyMVCDto;

public interface MyMVCDao  {
	
	String selectAllSql = "SELECT * FROM MYMVCBOARD ORDER BY SEQ DESC";
	String selectOneSql = "SELECT * FROM MYMVCBOARD WHERE SEQ = ?";
	String insertSql = "INSERT INTO MYMVCBOARD VALUES(MYMVCBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
	String updateSql = "UPDATE MYMVCBOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ?";
	String deleteSql = "DELETE FROM MYMVCBOARD WHERE SEQ = ?";
	
	public List<MyMVCDto> selectAll();
	public MyMVCDto selectOne(int seq);
	public boolean insert(MyMVCDto dto);
	public boolean update(MyMVCDto dto);
	public boolean delete(int seq);

}
