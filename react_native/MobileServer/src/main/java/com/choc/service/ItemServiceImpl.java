package com.choc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choc.dto.ItemDTO;
import com.choc.dto.PageRequestItemDTO;
import com.choc.dto.PageResponseItemDTO;
import com.choc.model.Item;
import com.choc.persistence.ItemRepository;
import com.choc.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	
	//application.properties 파일에 작성한 속성 가져오기
	@Value("${com.choc.upload.path}")
	private String uploadPath;
	
	//업로드한 날짜 별로 이미지를 저장하기 위해서 날짜별로 디렉토리를 만들어서 경로를 리턴하는 메서드
	private String makeFolder() {
		//오늘 날짜를 문자열로 생성
		String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		//문자열을 치환 - /를 운영체제의 디렉토리 구분자로 치환
		String realUploadPath = str.replace("//", File.separator);
		//디렉토리 생성
		File uploadPathDir = new File(uploadPath, realUploadPath);
		if(uploadPathDir.exists() == false) {
			uploadPathDir.mkdirs();
		}
		return realUploadPath;
		
	}
	//삽입이나 수정 그리고 삭제시 작업 시간을 기록하는 메서드
	//이 시간을 읽어서 데이터가 변경되었는지 확인
	private void updateDate() {
		try(PrintWriter pw = new PrintWriter(
				new FileOutputStream("./updatedate.dat"))){
			//현재 날짜 및 시간 가져오기
			String str = LocalDateTime.now().format(
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			pw.println(str);
			pw.flush();
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	
	@Override
	public Long registerItem(ItemDTO dto) {
		//파일 업로드 처리
		//전송 받은 파일을 가져오기
		//image 파라미터의 값을 가져오기
		MultipartFile uploadFile = dto.getImage();
		//전송된 파일이 있다면
		if(uploadFile.isEmpty() == false) {
			//이미지 파일 만 업로드하기 위해서 이미지 파일이 아니면 작업 중단
			if(uploadFile.getContentType().startsWith("image") == false) {
				return null;
			}
			//원본 파일의 파일 이름 찾아오기
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(
					originalName.lastIndexOf("\\") + 1);
			
			//파일을 업로드할 디렉토리 경로를 생성
			String realUploadPath = makeFolder();
			
			//업로드 할 파일의 경로를 생성
			String uuid = UUID.randomUUID().toString(); //파일 이름의 중복을 피하기 위해서 생성
			String saveName = uploadPath + File.separator + 
					realUploadPath + File.separator + uuid + fileName;
			Path savePath = Paths.get(saveName);
			try {
				//파일 업로드
				uploadFile.transferTo(savePath);
				//이미지 경로를 DTO에 설정
				dto.setPictureurl(realUploadPath + File.separator + uuid + fileName);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			
		}
		
		Item item = dtoToEntity(dto);
		itemRepository.save(item);
		//수정한 시간을 기록
		updateDate();
		return item.getItemid();
	}

	@Override
	public ItemDTO getItem(ItemDTO dto) {
		Long itemid = dto.getItemid();
		Optional<Item> optional = itemRepository.findById(itemid);
		if(optional.isPresent()) {
			return entityToDto(optional.get());
		}
		return null;
	}

	@Override
	public Long updateItem(ItemDTO dto) {
		Item item = dtoToEntity(dto);
		Long itemid = item.getItemid();
		itemRepository.save(item);
		return itemid;
	}

	@Override
	public Long deleteItem(ItemDTO dto) {
		Item item = dtoToEntity(dto);
		Long itemid = item.getItemid();
		itemRepository.deleteById(itemid);
		updateDate();
		return itemid;
	}

	@Override
	public PageResponseItemDTO getList(PageRequestItemDTO dto) {
		Sort sort = Sort.by("itemid").descending();
		Pageable pageable = PageRequest.of(
				dto.getPage()-1, dto.getSize(), sort);
		Page<Item> page = itemRepository.findAll(pageable);
		
		PageResponseItemDTO result = new PageResponseItemDTO();
		result.makePageList(pageable);
		result.setTotalPage(page.getTotalPages());
		List<ItemDTO> list = new ArrayList<>();
		page.get().forEach(item -> {
			list.add(entityToDto(item));
		});
		result.setItemList(list);
		return result;
	}

}