package com.dongbu.farm.common.repository.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

public class ImgViewServlet extends HttpServletBean {

	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(ImgViewServlet.class);
	private IRepositoryManager repositoryManager;
	
	/*
	 * repositoryManager Context LookUp
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HttpServletBean#initServletBean()
	 */
	protected void initServletBean() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		this.repositoryManager = (IRepositoryManager) (context.getBean("repositoryManager"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileId = request.getParameter("fileId");
		String serverFile = "";

		byte[] buffer = new byte[1024];
		ServletOutputStream o = response.getOutputStream();
		BufferedInputStream in = null;

		try {
			if (fileId != null) {
				UploadFile uploadFile = this.repositoryManager.getFileByFileId(fileId);
				serverFile = uploadFile.getFile_path() + "/" + uploadFile.getFile_id();

				in = new BufferedInputStream(new FileInputStream(serverFile));
				int n = 0;
				while ((n = in.read(buffer, 0, 1024)) != -1) {
					o.write(buffer, 0, n);
				}
			}

		} catch (UnsupportedEncodingException e) {
			logger.error("Exception occured while download file ... 1: " + fileId);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 1번 오류가 발생하였습니다.);history.back();</script>");
			return;
		} catch (FileNotFoundException e) {
			logger.error("Exception occured while download file ... 2: " + fileId);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 2번 오류가 발생하였습니다.);history.back();</script>");
			return;
		} catch (IOException e) {
			logger.error("Exception occured while download file ... 3: " + fileId);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 3번 오류가 발생하였습니다.);history.back();</script>");
			return;
		} catch (RepositoryException e) {
			logger.error("Exception occured while download file ... 4: " + fileId, e);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 4번 오류가 발생하였습니다.);history.back();</script>");
			return;
		} catch (Exception e) {
			logger.error("Exception occured while download file ... 5: " + fileId, e);
			response.setHeader("Content-Type", "text/html; charset=euc-kr;");
			response.getWriter().println("<script>alert('해당 파일 다운로드 중 5번 오류가 발생하였습니다.');</script>");
			return;
		} finally {
			if (o != null) {
				o.close();
			}
			if (in != null) {
				in.close();
			}
		}
	}

}