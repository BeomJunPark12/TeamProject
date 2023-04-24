package com.gdj59.bookmall.mapper;

import java.util.Vector;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gdj59.bookmall.beans.BookVO;

public interface BookMapper {
	
	
// 책 리스트
@Select("select * from book_table")
public Vector<BookVO> bookList();
	
// 책 리스트 (카테고리 선택)
@Select("select * from book_table where b_category = #{b_category}")
public Vector<BookVO> bookListCategory(String b_category);

// 선택(책) 읽기
@Select("select * from book_table where b_num = #{b_num}")
public BookVO bookListOne(int b_num);
	
// 책 등록 
@Insert("insert into book_table (b_name, b_price, "
		+ "b_stock, b_category, b_purchase) "
		+ "values (#{b_name}, #{b_price},"
		+ "#{b_stock}, #{b_category},"
		+ "#{b_purchase})")
public void bookIns(BookVO bookVo);

// 책 수정
@Update("update book_table set "
		+ "b_name = #{b_name}, "
		+ "b_price = #{b_price}, "
		+ "b_stock = #{b_stock}, "
		+ "b_category = #{b_category}, "
		+ "b_purchase = #{b_purchase} "
		+ "where b_num = #{b_num}"
		)
public void bookUpdate(BookVO bookVo);

// 책 삭제
@Delete("delete from book_table where b_num = #{b_num}")
public void bookDel(int b_num);
	
// 책 이름 중복검사
@Select("select count(*) from book_table where b_name = #{b_name}")
public int bookNameCheck(BookVO bookVo);


// 페이징












	
}
