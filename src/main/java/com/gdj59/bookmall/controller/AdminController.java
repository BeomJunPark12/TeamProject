package com.gdj59.bookmall.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdj59.bookmall.beans.BookVO;
import com.gdj59.bookmall.service.AdminServiceImpl;
import com.gdj59.bookmall.service.BookServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/*")
@AllArgsConstructor
@Slf4j
public class AdminController {

AdminServiceImpl a_service = new AdminServiceImpl();
BookServiceImpl b_service = new BookServiceImpl();
	
// 관리자페이지
@GetMapping("/main")
public String adminPage() {
	
	// user_grade 가 admin이 아닐시 반환 alert로 하기
	return "admin/main";
}

//	※user※
//-------------------------------------------------------



//	※book※
//-------------------------------------------------------

// 책 리스트[전체](새로만든 것)
@GetMapping("/list")
public String bookList(Model model) throws Exception {
	List<BookVO> list = b_service.bookList();
	model.addAttribute("list",list);
	return "admin/book_list";
}

// 책 리스트[카테고리](새로만든 것)
@GetMapping("/listCategory")
public String bookListCategory(@RequestParam String b_category , Model model) throws Exception {
	model.addAttribute("b_category", b_category);
	List<BookVO> list = b_service.bookListCategory(b_category);
	model.addAttribute("listCategory",list);
	return "admin/book_list";
}
	
//-------------------------------------------------------
	
// 선택(책) 리스트
@GetMapping("/listOne")
public String ListOne(@RequestParam(name="b_num")int b_num, Model model) throws Exception{
	model.addAttribute("listOne",b_service.bookListOne(b_num));
	return "book/admin_bookListOne";
}

//-------------------------------------------------------

// 책 등록
@GetMapping("/ins")
public String Insert(@RequestParam String b_category, Model model ) throws Exception {
	model.addAttribute("b_category", b_category);
	return "admin/book_ins";
}

@PostMapping("/ins")
public String Insert(@RequestParam String b_category,@ModelAttribute BookVO bookVo, Model model) throws Exception {
	model.addAttribute("b_category", b_category);
	int check = b_service.bookNameCheck(bookVo);
	if(check==0) {
		b_service.bookIns(bookVo);
		return "redirect:/book/bookList";
	}else {
		 model.addAttribute("bookVo", bookVo);
		 model.addAttribute("duplicateMsg", "중복된 책 이름이 존재합니다.");
		return "book/admin_bookIns";
	}
}

//-------------------------------------------------------

// 책 수정
@GetMapping("/update")
public String Update(@RequestParam(name="b_num")int b_num, Model model) throws Exception{
	model.addAttribute("bookVo",b_service.bookListOne(b_num));
	return "admin/book_update";
}

@PostMapping("/update")
public String Update(@RequestParam String b_category,@ModelAttribute BookVO bookVo, Model model)throws Exception{
	int check = b_service.bookNameCheck(bookVo);
	if(check==0) {
		b_service.bookUpdate(bookVo);
		return "redirect:/admin/book_list";
	}else {
		 model.addAttribute("bookVo", bookVo);
		 model.addAttribute("duplicateMsg", "중복된 책 이름이 존재합니다.");
		return "admin/book_update";
	}
}

//-------------------------------------------------------

// 책 삭제
@GetMapping("/del")
public String Del(@RequestParam(name="b_num")int b_num) throws Exception{
	b_service.bookDel(b_num);
	return "redirect:/admin/list";
}


	
	
	
	
	
	
	
	
	
}
