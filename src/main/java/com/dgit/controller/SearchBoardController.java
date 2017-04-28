package com.dgit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.PageMaker;
import com.dgit.domain.SearchCriteria;
import com.dgit.service.BoardService;
import com.dgit.util.UploadFileUtils;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Autowired
	private BoardService service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("SBoard list GET......");
		logger.info(cri.toString());
		
		//list만 들어있음
		model.addAttribute("list", service.listSearch(cri));
		
		//paging만 들어있음
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	public void read(@RequestParam("bno")int bno,
			@ModelAttribute("cri")SearchCriteria cri, 
			boolean isUpdateCount,
			Model model) throws Exception{
		logger.info("show read .......................");
		if(isUpdateCount)
			service.updateViewCount(bno);
		
		model.addAttribute("boardVO", service.read(bno));
	}
	
	@RequestMapping(value="remove", method=RequestMethod.POST)
	public String remove(int bno, SearchCriteria cri, RedirectAttributes attr) throws Exception{
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
	public void modifyGET(int bno, @ModelAttribute("cri")SearchCriteria cri,Model model) throws Exception{
		logger.info("show modify GET .......................");
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("show modify POST .......................");
		service.modify(board);
		System.out.println(cri.toString());
		rttr.addAttribute("bno", board.getBno());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/read";
	}
	
    @RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("register get ............");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registPOST(BoardVO board, List<MultipartFile> imagefiles, RedirectAttributes rttr) throws Exception{
		logger.info("regist post ..............");
		logger.info(board.toString());
		
		ArrayList<String> filenames = new ArrayList<>();
		for(MultipartFile file : imagefiles){
			logger.info("originalName: " + file.getOriginalFilename());
			logger.info("size: " + file.getSize());
			logger.info("contentType: " + file.getContentType());
			  
			//upload 처리 및 Thumbnail 이미지 만들기
			String savedName = UploadFileUtils.uploadFile(uploadPath, 
													file.getOriginalFilename(), 
													file.getBytes());
			//실제 upload된 파일 이름을 가지고 돌아가도록 한다.
			filenames.add(savedName);			
		}
		String[] sFiles = filenames.toArray(new String[filenames.size()]);
		board.setFiles(sFiles);
		
		service.regist(board);
		//model.addAttribute("result","success");
		//return "/board/success";
		rttr.addFlashAttribute("msg","SUCCESS");
		//return "redirect:/board/listAll";//리스트가 너무 많아서 전체 다 나오면 힘들당.
		return "redirect:/sboard/list";//page단위로 나오도록 처리하자.
		
	}
}












