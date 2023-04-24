package com.gdj59.bookmall.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.gdj59.bookmall.beans.BookVO;
import com.gdj59.bookmall.beans.CartVO;
import com.gdj59.bookmall.beans.UserVO;

public interface userMapper {
	@Insert("insert into user_table(user_id,user_pw,user_address,user_phone) values(#{user_id}, #{user_pw}, #{user_address}, #{user_phone})")
	public void registerInfo(UserVO userVO);

	@Select("select count(*) from user_table where user_id=#{user_id}")
	public int overLap(String user_id);

	@Select("select * from user_table where user_id=#{user_id} and user_pw=#{user_pw}")
	public UserVO selectOne(UserVO userVO);

	@Update("update user_table set user_pw = #{user_pw}, user_address = #{user_address},user_phone=#{user_phone} where user_id=#{user_id}")
	public void modInfo(UserVO userVO);

	@Delete("delete from user_table where user_id=#{user_id}")
	public void deleteMember(String user_id);

	@Select("select * from book_table where b_name=#{b_name}")
	public BookVO searchBookCnt(String b_name);

	@Update("update cart set ordercnt=ordercnt+1 where user_id=#{user_id} and b_num=#{b_num}")
	public void cartUpdate(@Param("user_id") String user_id, @Param("b_num") int b_num); 

	@Insert("INSERT INTO cart (user_id, b_num, b_name, b_price, ordercnt, b_category)VALUES (#{user_id}, #{book.b_num},#{book.b_name},#{book.b_price}, 1, #{book.b_category});")
	public void cartInsert(@Param("user_id") String user_id, @Param("book") BookVO book);

	@Select("select * from cart where user_id=#{user_id}")
	public ArrayList<CartVO> cartList(String user_id);

	@Update("update cart set ordercnt=#{ordercnt}-1 where b_name=#{b_name}")
	public void modifyCart(CartVO cartVO);

	@Select("select ordercnt,b_price from cart where b_name=#{b_name}")
	public CartVO selectCart(CartVO cartVO);

	@Select("delete from cart where cart_id=#{cart_id}")
	public void 완전삭제(int cart_id);

	@Select("select ordercnt from cart where user_id=#{user_id} and b_num=#{b_num}")
	public int cartBookCnt(@Param("user_id") String user_id, @Param("b_num") int b_num);
	
	// 누적금액 100,000원 이상 되면 회원 등급 vip
	@Update("update user_table set user_grade = #{user_grade} where user_id = #{user_id}")
	public void updateGrade(@Param("user_grade") String user_grade, @Param("user_id") String user_id);
	
	
	
}
