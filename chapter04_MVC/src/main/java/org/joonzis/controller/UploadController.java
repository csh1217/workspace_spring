package org.joonzis.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.joonzis.domain.BoardAttachVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class UploadController {
	
	@ResponseBody
	@PostMapping(value = "/uploadAsyncAction",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> uploadAsyncPost(MultipartFile[] uploadFile) {
		log.info("upload async post....");
		
		List<BoardAttachVO> list = new ArrayList<>();
		String uploadFolder = "C:\\upload";
		
		//make folder---------
		File uploadPath = new File(uploadFolder, getFoleder());
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		
		for(MultipartFile multipartFile : uploadFile) {
			BoardAttachVO attachDTO = new BoardAttachVO();
			
			log.info("----------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());	
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name : " + uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(getFoleder());
				attachDTO.setFileName(multipartFile.getOriginalFilename());
				
				list.add(attachDTO);
			} catch(Exception e) {
				log.error(e.getMessage());
			}
		}
		return ResponseEntity.ok(list);
	}
	
	// 파일 다운로드
	@GetMapping(value = "/download",
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	//Resource => springframework.core.io 패키지
	public ResponseEntity<Resource> downloadFile(String fileName){
		log.info("download file...." + fileName);
		Resource resource = new FileSystemResource("C:\\upload\\" + fileName);
		log.info("resource : " + resource);
		
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();
		
		try {
	         headers.add("Content-Disposition",
	                 "attachment; fileName=" + new String(resourceName.getBytes("utf-8"),
	                 "ISO-8859-1"));
			} catch (UnsupportedEncodingException e) {
	              e.printStackTrace();
	        }
		return new ResponseEntity<>(resource,headers, HttpStatus.OK);
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(@RequestBody String fileName) {
	      log.info("deleteFile : " + fileName);
	      
	      File file;
	      
	      try {
	         file = 
	         new File("C:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));
	         file.delete();
	      } catch (Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	      return ResponseEntity.ok("delete");
	   }
	
	
	// 오늘 날짜의 경로를 문자열로 생성
	public String getFoleder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
}
