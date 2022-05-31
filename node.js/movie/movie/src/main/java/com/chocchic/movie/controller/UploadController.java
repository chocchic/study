package com.chocchic.movie.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class UploadController {
	@PostMapping(value="/uploadajax")
	public void uploadFile(MultipartFile[] uploadFiles) {
        for (MultipartFile uploadFile : uploadFiles) {
            //이미지 파일만 업로드 가능
            if(uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return;
            }

            //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);
            /*
            String realUploadPath = makeFolder();
            //UUID
            String uuid = UUID.randomUUID().toString();
            //저장할 파일 이름 중간에 _를 이용해서 구분
            String saveName = uploadPath + File.separator + realUploadPath + File.separator + uuid + fileName;
            Path savePath = Paths.get(saveName);
            try {
                uploadFile.transferTo(savePath);
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
            */
        }
	}
}
