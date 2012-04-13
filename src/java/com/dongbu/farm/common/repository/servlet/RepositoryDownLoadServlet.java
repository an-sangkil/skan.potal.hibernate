/*
 * $Id: RepositoryDownLoadServlet.java,v 1.2 2010/04/06 22:59:11 smrscvs3 Exp $
 * created by    : jiwoong
 * creation-date : 2008. 9. 30.
 * =========================================================
 * Copyright (c) 2008 Maninsoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.repository.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HttpServletBean;

import com.dongbu.farm.common.repository.RepositoryException;
import com.dongbu.farm.common.repository.manager.IRepositoryManager;
import com.dongbu.farm.common.repository.model.UploadFile;

/**
 * 
 * @author jiwoong
 * @version $Id: RepositoryDownLoadServlet.java,v 1.2 2010/04/06 22:59:11 smrscvs3 Exp $
 */

public class RepositoryDownLoadServlet extends HttpServletBean {
	private static final long serialVersionUID = -8076969656288482933L;
	private Log logger = LogFactory.getLog(RepositoryDownLoadServlet.class);
	private IRepositoryManager repositoryManager;

	protected void initServletBean() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		this.repositoryManager = (IRepositoryManager) (context.getBean("repositoryManager"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileId = request.getParameter("fileId");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		try {
			if (fileId != null) {
				UploadFile uploadFile = this.repositoryManager.getFileByFileId(fileId);
				File file = new File(uploadFile.getFile_path() + "/" + uploadFile.getFile_id());

				response.setHeader("Content-Type", "application/binary; charset=euc-kr;");
				response.setHeader("Content-Disposition", "Attachment; filename="
						+ new String(uploadFile.getOriginal_file_name().getBytes("EUC-KR"), "8859_1") + ";");
				response.setHeader("Pragma", "cache;");
				response.setHeader("Cache-Control", "cache;");
				response.setHeader("Content-Length", "" + file.length());

				bis = new BufferedInputStream(new FileInputStream(file));
				byte[] content = new byte[2048]; // buffer size 2K.

				int read = 0;
				while ((read = bis.read(content)) != -1) {
					bos.write(content, 0, read);
				}

			} else {
				response.setHeader("Content-Type", "text/html; charset=euc-kr;");
				response.getWriter().println("<script>alert('해당 파일을 찾을 수 없습니다');</script>");
				return;
			}
			bos.flush();
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception occured while download file ... 1: " + fileId);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 1번 오류가 발생하였습니다.');</script>");
			return;
		} catch (FileNotFoundException e) {
			logger.error("Exception occured while download file ... 2: " + fileId);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 2번 오류가 발생하였습니다.');</script>");
			return;
		} catch (IOException e) {
			logger.error("Exception occured while download file ... 3: " + fileId);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 3번 오류가 발생하였습니다.');</script>");
			return;
		} catch (RepositoryException e) {
			logger.error("Exception occured while download file ... 4: " + fileId, e);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 4번 오류가 발생하였습니다.');</script>");
			return;
		} catch (Exception e) {
			logger.error("Exception occured while download file ... 5: " + fileId, e);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 5번 오류가 발생하였습니다.');</script>");
			return;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

}

