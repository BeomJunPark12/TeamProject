package com.gdj59.bookmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdj59.bookmall.beans.BookVO;
import com.gdj59.bookmall.beans.Criteria;
import com.gdj59.bookmall.beans.PageVO;
import com.gdj59.bookmall.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/book/*")
@AllArgsConstructor
@Slf4j
public class BookController {

	@Autowired
	private BookService bookService;
	
	// 전체보기
	@GetMapping("/bookList")
	public String doList(Model model) throws Exception {
		List<BookVO> list = bookService.bookList();
		model.addAttribute("list",list);
		return "book/admin_bookList";
	}
	
	
//	책 리스트
//	===========================================================
//	// 책 리스트[전체](새로만든 것)
//	@GetMapping("/list")
//	public String bookList(Model model) throws Exception {
//		List<BookVO> list = bookService.bookList();
//		model.addAttribute("list",list);
//		return "book/bookList";
//	}
	
	// 책 리스트[카테고리](새로만든 것)
	@GetMapping("/listCategory")
	public String bookListCategory(@RequestParam String b_category , Model model) throws Exception {
		model.addAttribute("b_category", b_category);
		List<BookVO> list = bookService.bookListCategory(b_category);
		model.addAttribute("listCategory",list);
		return "book/bookList";
	}
//	===========================================================
	
	
	
	
	
	
	// 책 리스트 (카테고리)
	@GetMapping("/bookListCategory")
	public String doList(@RequestParam String b_category , Model model) throws Exception {
		model.addAttribute("b_category", b_category);
		List<BookVO> list = bookService.bookListCategory(b_category);
		model.addAttribute("listCategory",list);
		return "book/admin_bookList";
	}
	
	// 선택(책) 리스트
	@GetMapping("/bookListOne")
	public String ListOne(@RequestParam(name="b_num")int b_num, Model model) throws Exception{
		model.addAttribute("listOne",bookService.bookListOne(b_num));
		return "book/admin_bookListOne";
	}
	
	// 책 등록
	@GetMapping("/bookIns")
	public String Insert(@RequestParam String b_category, Model model ) throws Exception {
		model.addAttribute("b_category", b_category);
		return "book/admin_bookIns";
	}
	
	@PostMapping("/bookIns")
	public String Insert(@RequestParam String b_category,@ModelAttribute BookVO bookVo, Model model) throws Exception {
		model.addAttribute("b_category", b_category);
		int check = bookService.bookNameCheck(bookVo);
		if(check==0) {
			bookService.bookIns(bookVo);
			return "redirect:/book/bookList";
		}else {
			 model.addAttribute("bookVo", bookVo);
			 model.addAttribute("duplicateMsg", "중복된 책 이름이 존재합니다.");
			return "book/admin_bookIns";
		}
	}
	
	
	
	
	// 책 수정
	@GetMapping("/bookUpdate")
	public String Update(@RequestParam(name="b_num")int b_num, Model model) throws Exception{
		model.addAttribute("bookVo",bookService.bookListOne(b_num));
		return "book/admin_bookUpdate";
	}
	
	@PostMapping("/bookUpdate")
	public String Update(@RequestParam String b_category,@ModelAttribute BookVO bookVo, Model model)throws Exception{
		int check = bookService.bookNameCheck(bookVo);
		if(check==0) {
			bookService.bookUpdate(bookVo);
			return "redirect:/book/bookList";
		}else {
			 model.addAttribute("bookVo", bookVo);
			 model.addAttribute("duplicateMsg", "중복된 책 이름이 존재합니다.");
			return "book/admin_bookUpdate";
		}
	}
	
	
	
	
	// 책 삭제
	@GetMapping("/bookDel")
	public String Del(@RequestParam(name="b_num")int b_num) throws Exception{
		bookService.bookDel(b_num);
		return "redirect:/book/bookList";
	}
	
	
	
	
	// 페이징
	@GetMapping("/listPaging")
	public String getList(BookVO bookVo, Model model, Criteria criteria) {
		System.out.println(bookVo.toString());
		
//		model.addAttribute("listPaging", bookService.bookList() );
		model.addAttribute("pageMaker", new PageVO(criteria, 123) );
		
		return "book/bookList";
	}
	
	
	

		
	}
	

