package com.gdj59.bookmall.dao;

import java.util.Vector;

import com.gdj59.bookmall.beans.BookVO;

public interface BookDao {

	
// 책 리스트
public Vector<BookVO> bookList() throws Exception;

// 책 리스트(카테고리)
public Vector<BookVO> bookListCategory(String b_category) throws Exception;

// 선택(책) 읽기
public BookVO bookListOne(int b_num) throws Exception;

// 책 등록
public void bookIns(BookVO bookVo) throws Exception;

// 책 수정
public void bookUpdate(BookVO bookVo) throws Exception;

// 책 삭제
public void bookDel(int b_num) throws Exception;

//책 이름 중복검사
public int bookNameCheck(BookVO bookVo) throws Exception;


//
//// 책 리스트
//public List<Map<String, Object>> selectBoardList(Criteria criteria) throws Exception;
//
//// 책 카운트
//public int totalCount() throws Exception;
//
////카운트 카테고리
//public int getTotalCategory(String b_category) throws Exception;







}
