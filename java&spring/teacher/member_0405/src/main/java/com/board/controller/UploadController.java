package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload/")
public class UploadController {

	@GetMapping("uploadForm")
	public void uploadForm() {
		System.out.println("upload form 요청 ");
	}
	
	@PostMapping("uploadPro")
	public String uploadPro(String msg, MultipartHttpServletRequest request, Model model) {
		
		// #1. 넘어온 파일 정보 꺼내기 
		System.out.println("msg : " + msg);
		MultipartFile mf = request.getFile("img"); 
		System.out.println("mf size : " + mf.getSize());
		System.out.println("mf orgName : " + mf.getOriginalFilename());
		System.out.println("mf contentType : " + mf.getContentType());
		
		String ct = mf.getContentType();
		String type = ct.substring(0, ct.indexOf("/")); 
		if(!type.equals("image")) { // 이미지가 아니면 저장 하지말자.
			System.out.println("이미지파일 아닙니다.");
			return "redirect:/upload/uploadForm"; // 저장하지말고 폼페이지로 강제 이동 
		}
		
		//D:\gangsa\spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\member\resources
		// 서버에 파일 저장 
		// #2. 파일 저장할 폴더 경로 찾아 (save) 
		String savePath = request.getRealPath("resources\\save");
		System.out.println(savePath);
		// #3. 파일 이름 (원본파일 이름을 저장할것인가 no, 파일이름 중복처리해서 서버에저장) 
		/* #3-1. 오리지날파일명 + 현재시간millis + 확장명 
		String orgName = mf.getOriginalFilename(); 
		String ext = orgName.substring(orgName.lastIndexOf(".")); 
		String imgName = orgName.substring(0, orgName.lastIndexOf("."));
		System.out.println("ext : " + ext);
		System.out.println("imgName : " + imgName);
		String newName = imgName + System.currentTimeMillis() + ext; 
		System.out.println("newName : " + newName);
		*/
		// #3-2. UUID + orgName 
		String orgName = mf.getOriginalFilename(); 		
		UUID uuid = UUID.randomUUID(); 
		String newName = uuid+orgName; 
		
		// #4. 파일을 실제로 저장 처리 
		//	: 내가만든 파일명과 폴더 전체경로를 연결해서 그쪽으로 파일을 이동 개념  
		String imgPath = savePath + "\\" + newName; 
		File f = new File(imgPath); 
		try {
			mf.transferTo(f);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// (#5.) DB에 파일이름저장 : newName
		
		// view에 파일명 전달해서 pro페이지에 img로 띄우기
		// (사용자가 브라우저에서 내가 업로드한 이미지 보기)
		model.addAttribute("imgName", newName);
		
		return "upload/uploadPro";
	}
	
	// 다운 로드 버튼 보여지는 페이지 요청 
	@GetMapping("downForm")
	public void downForm() {
		System.out.println("다운로드 버튼 페이지 요청");
		
	}
	// 다운버튼 눌렀을때 요청 처리 
	@RequestMapping("download")
	public ModelAndView down(HttpServletRequest request) {
		// 버튼 사용자가 누르면 다운시켜줄 파일 객체 생성 (서버에 넣어둔 파일) 
		String path = request.getRealPath("resources\\imgs");
		String filePath = path + "\\beach.jpg";
		File f = new File(filePath);
		// fileDown : viewName에 해당하는 값. 
		//			-> servlet-context.xml 에 DownloadView클래스 빈으로 등록한 id와 일치 
		// downloadFile : Model로 보내는 데이터의 이름 
		//			-> DownloadView 클래스안에 File file = (File)model.get("downloadFile"); 키값과 일치 
		// f : 다운시킬 실제 File 객체 : Model로 보내는 실제 데이터 
		// 								(viewName, modelName, modelObject) 
		//											model.addAttribute("downloadFile", f); 
		ModelAndView mv = new ModelAndView("fileDown", "downloadFile", f);
		return mv;
	}
	
	
	
	
	
	
}
