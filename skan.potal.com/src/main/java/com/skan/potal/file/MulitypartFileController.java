package com.skan.potal.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.skan.auth.utils.SessionUtils;
import com.skan.com.util.utils.DateUtils;
import com.skan.com.util.utils.DateUtils.CalendarPattermn;
import com.skan.potal.web.jpa.dto.FileDto;
import com.skan.potal.web.jpa.dto.QFileDto;
import com.skan.potal.web.jpa.repository.FileJpaRepository;

/**
 * Description : 파일 업로드 컨트롤러, 스프링에서 사용하는 파일 업로드 기능을 사용한다.
 * 
 * @author skan
 * @since 2016. 10. 5.
 * @version
 *
 * 			Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Controller
@RequestMapping("/file")
public class MulitypartFileController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value(value = "${save.file.path:/home/knkcorp/upload/mount/")
	private String SAVE_FILE_PATH;

	@Autowired
	private FileJpaRepository fileJpaRepository;

	/**
	 * http://docs.fineuploader.com/branch/master/endpoint_handlers/traditional.html
	 * Response Data 참조
	 * 
	 * @param multipartFiles
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping("/attachment/upload")
	@ResponseBody
	public Map<String, ?> uploadAttachment(@RequestPart("qqfile") MultipartFile multipartFiles[],
			HttpServletRequest request) throws ServletRequestBindingException {
		Map<String, Object> responseData = new HashMap<>();
		try {
			if (multipartFiles != null && multipartFiles.length > 0) {
				for (MultipartFile multipartFile : multipartFiles) {

					String contentType = multipartFile.getContentType();
					String fileName = multipartFile.getOriginalFilename();
					String fieExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

					// 1. 파일 저장 디렉토리 위치 생성 및 확인
					// {기본저장위치}/{년월YYYYMM}/
					String yearMonth = DateUtils.getToDayString(CalendarPattermn.CALENDER_TYPE_YYYYMM);
//					if(StringUtils.isEmpty(SAVE_FILE_PATH)) {
						StringBuffer sfp = new StringBuffer()
								.append(System.getProperty("user.home")).append("/")
								.append("upload/mount").append("/").append(yearMonth);
								SAVE_FILE_PATH = sfp.toString();
//					}

					// 2. 고정된 그룹 아이디를 client에서 받아온다.
					String fileGroupUUID = ServletRequestUtils.getRequiredStringParameter(request, "groupUUID");
					String qqUUID = ServletRequestUtils.getRequiredStringParameter(request, "qquuid");
					// 3. 파일 이름 랜덤 생성
					String fileUuid = UUID.randomUUID().toString();

					// 4. 디렉토리 체크
					Path directory = Paths.get(SAVE_FILE_PATH);
					if (Files.notExists(directory)) {
						Files.createDirectories(directory);
					}

					// 5. 실제 파일 생성 및 저장
					Path filePath = Paths.get(SAVE_FILE_PATH + "/" + fileUuid);
					Files.createFile(filePath);
					File file = filePath.toFile();
					multipartFile.transferTo(file);

					// 6. database 파일 정보 저장
					FileDto filedto = new FileDto();
					filedto.setFileGroupId(fileGroupUUID);
					filedto.setFileUUID(qqUUID);

					// TODO :
					// 어느곳에서 온 데이터인지 확인, 게시판, 자료실, ....강의실 등등 사용하지 않을 수도 있다,
					filedto.setGroupCode("0001");

					filedto.setRealFileName(fileName);
					filedto.setAliasFileName(fileUuid);
					filedto.setPrefix(fieExtension);

					filedto.setFileSize(multipartFile.getSize());
					filedto.setSaveFilePath(SAVE_FILE_PATH);
					filedto.setFileContentType(contentType);
					filedto.setCreator(SessionUtils.getSessionUserEmail());
					filedto.setCreationTime(new Date());

					fileJpaRepository.saveAndFlush(filedto);

					// 7. 저장된 파일 정보 셋팅, Response Data 생성
					//
					responseData.put("success", "true");
					responseData.put("file", filedto);
				}
			} else {
				logger.info("파일이 존재 하지 않습니다.");
				responseData.put("fail", "true");
			}
			
			logger.info("파일 업로드 완료");
			
		} catch (IllegalStateException | IOException e) {
			
			logger.error("파일 업로드 실패 ", e);
			responseData.put("fail", "true");
		}

		return responseData;
	}

	/**
	 * 파일 다운로드
	 * 
	 * @param fileDto
	 *            saveFilePath 파일이 저장된 위치 aliasFileName 저장된 파일이름 (별칭)
	 *            realFileName 실제 파일 이름 prefix 확장자명
	 * 
	 * @param response
	 */
	@RequestMapping("/attachment/download")
	public void downloadFile(@Valid FileDto fileDto, HttpServletResponse response) {

		Path path = Paths.get(fileDto.getSaveFilePath(), fileDto.getAliasFileName());
		File file = path.toFile();

		try (InputStream in = new FileInputStream(file)) {

			response.setContentType("application/octet-stream");

			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(fileDto.getRealFileName(), "UTF-8") + fileDto.getPrefix());
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setCharacterEncoding("UTF-8");
			FileCopyUtils.copy(in, response.getOutputStream());

		} catch (FileNotFoundException e1) {
			logger.error("파일이 존재 하지 않습니다. = {}", e1);
		} catch (IOException e2) {
			logger.error("파일 핸들링에 실패 하였습니다. = {}", e2);
		}
	}

	/**
	 * 파일 삭제
	 * 
	 * @param seq
	 *            파일 번호
	 * @param fileGroupId
	 *            파일 그룹아이디
	 * @param saveFilePath
	 *            실 저장경로
	 * @param aliasFileName
	 *            저장된 파일이름
	 * @param response
	 */
	@RequestMapping("/attachment/delete/{fileUUID}")
	public void deleteFile(@PathVariable("fileUUID") String fileUUID, @RequestParam(required = true) String fileGroupId,
			@RequestParam(required = false) String saveFilePath, @RequestParam(required = false) String aliasFileName,
			HttpServletResponse response) {

		FileDto fileDto = fileJpaRepository.findOne(QFileDto.fileDto.fileUUID.eq(fileUUID));
		if (fileDto != null) {

			// DB file 정보 삭제
			fileJpaRepository.delete(fileDto.getFileSeq());

			// 실제 경로 파일 삭제.
			Path path = Paths.get(fileDto.getSaveFilePath() + "/" + fileDto.getAliasFileName());
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("파일 삭제에 실패 하였습니다. = {}", e);
			}
		}
	}
}
