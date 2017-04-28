package com.dgit.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.PageMaker;
import com.dgit.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public void registerGET(){
		logger.info("register GET....");
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String registerPOST(BoardVO vo,RedirectAttributes rttr) throws Exception{
		logger.info("regist POST .....");
		service.regist(vo);
		rttr.addFlashAttribute("result", "success");
	
		return "redirect:/board/listAll";
	}
	
	
	@RequestMapping(value="listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		logger.info("show all list.......................");
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value="listCri", method=RequestMethod.GET)
	public String listCri(Criteria cri ,Model model) throws Exception{
		logger.info("show Criteria list.......................");
		model.addAttribute("list", service.listCriteria(cri));
		
		return "/board/listAll";
	}
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public String listPage(Criteria cri ,Model model) throws Exception{
		logger.info("show Page list.......................");
		logger.info("cri, " + cri.toString());
		model.addAttribute("list", service.listCriteria(cri));
		
		PageMaker pageMaker= new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countPaging());//게시물 전체 갯수
		model.addAttribute("pageMaker", pageMaker);
		
		
		return "/board/listAll";
	}
	
	
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	public void read(@RequestParam("bno")int bno,
			@ModelAttribute("cri")Criteria cri, 
			boolean isUpdateCount,
			Model model) throws Exception{
		logger.info("show read .......................");
		if(isUpdateCount)
			service.updateViewCount(bno);
		
		model.addAttribute("boardVO", service.read(bno));
	}
	
	
	@RequestMapping(value="remove", method=RequestMethod.POST)
	public String remove(int bno, Criteria cri, RedirectAttributes attr) throws Exception{
		logger.info("show remove .......................");
		service.remove(bno);
		logger.info("cri,"+cri.toString());
		//attr.addAttribute("cri", cri);
		attr.addAttribute("page", cri.getPage());
		attr.addAttribute("perPageNum", cri.getPerPageNum());
		attr.addFlashAttribute("result", "success");
		
		return "redirect:/board/listPage";
	}	
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(int bno, @ModelAttribute("cri")Criteria cri,Model model) throws Exception{
		logger.info("show modify GET .......................");
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("show modify POST .......................");
		service.modify(board);
		System.out.println(cri.toString());
		rttr.addAttribute("bno", board.getBno());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/read";
	}
	
	
}















