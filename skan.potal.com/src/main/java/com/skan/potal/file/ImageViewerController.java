package com.skan.tms.mobile.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skan.tms.web.jpa.dto.FileDto;
import com.skan.tms.web.jpa.repository.FileJpaRepository;


@Controller
public class ImageViewerController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileJpaRepository fileJpaRepository;

	@RequestMapping("/file/imageViewer")
	public void imageViewer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileUUID = ServletRequestUtils.getRequiredStringParameter(request, "fileUUID");
		FileDto fileDto = fileJpaRepository.findByfileUUID(fileUUID);

		Path path = Paths.get(fileDto.getSaveFilePath(), fileDto.getAliasFileName());
		File file = path.toFile();
		
		// Response 에 컨텐트 타입을 추가해주어야 하나 
		
		try (InputStream in = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(in);
				BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
				) {
			
			//for (int data; (data = bis.read()) > -1;) {
			//	output.write(data);
			//}
			//
			byte[] buf = new byte[4096];
			int read = 0;
			while ((read = bis.read()) != -1 ) {
				output.write(read);
			}

		} catch (FileNotFoundException e1) {
			logger.error("파일이 존재 하지 않습니다. = {}", e1);
		} catch (IOException e2) {
			logger.error("파일 핸들링에 실패 하였습니다. = {}", e2);
		}

	}
}
