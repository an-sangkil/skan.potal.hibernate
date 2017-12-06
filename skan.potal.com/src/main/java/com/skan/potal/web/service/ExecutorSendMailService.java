package com.skan.potal.web.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.mail.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.skan.potal.common.mail.BaseMailSender;

/**
 * Description : 이메일을 병렬로 처리 하기 위한 ThreadExceution 서비스  
 * 				 BaseMailSender.java는 기본적으로 @primary 로 지정된 SmtpJavaMailService를 사용한다.
 * 				 
 * 				 BaseMailSender를 직접 인스턴스를 사용할경우.
 * 				 SimpleMailSender를 생성자에 주입하여 사용 할 수 있다.
 * 
 * @author skan
 * @since 2016. 9. 29.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Service
public class ExecutorSendMailService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private BaseMailSender mailSend;
	public ExecutorSendMailService(BaseMailSender mailSend){
		if(executorService == null || executorService.isTerminated()) {
			int codeNumber = Runtime.getRuntime().availableProcessors();
			this.executorService = new ThreadPoolExecutor(codeNumber, codeNumber, 100L, TimeUnit.MILLISECONDS, blockingQueue);
			logger.debug("new threadakeExecuter  {} " , executorService);
		} else {
			logger.debug("old threadakeExecuter  {} " , executorService);
		}
		this.mailSend = mailSend;
	}
	
	// 이메일 보낼 Thread 구분지어질 방법
	public enum ThreadSendEnum {
		CALLABLE,
		RUNNABLE
	}
	
	
	
	// executor Service 생성 
	// static ExecutorService executorService = Executors.newFixedThreadPool(4);
	BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>();
	private ExecutorService executorService;
	
	public void sendEmail(String from, String to, String subject, String text, ThreadSendEnum sendType) {
		
		logger.trace("최대 코어 스레드수 = {}" ,Runtime.getRuntime().availableProcessors());
		
		
		try {
			switch (sendType) {
			
			// Return 이 필요한 경우이며, 결과반환을 위해 완료대기 까지 대기시간이 있다. 
			case CALLABLE:
				
				Future<Integer> sendCountFuture = executorService.submit(new Callable<Integer>() {
					
					@Override
					public Integer call() throws Exception {
						
						ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
						int poolSize = threadPoolExecutor.getPoolSize();
						String threadName = Thread.currentThread().getName();
						logger.info("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);
						
						int successCount = 0;
						mailSend.send(from, to, subject, text);
						return successCount+1;
					}
				});
				
				try {
					sendCountFuture.get();
				} catch (InterruptedException | ExecutionException e1) {
					e1.printStackTrace();
				}
				
				break;
				
				// 결과값 없음
			case RUNNABLE :
				
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						
						ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
						int poolSize = threadPoolExecutor.getPoolSize();
						String threadName = Thread.currentThread().getName();
						logger.info("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);
						
						mailSend.send(from, to, subject, text);
						
					}
				});
				
				
				break;
			}
			
		} catch (Exception e) {

			logger.debug("이메일 발송 에러  = {}" , e);
		} finally {
			//웹환경에서는 사용하지 않음.
			//executorService.shutdown();
		}
	}
	
	/**
	 * 대량 메일 발송.
	 * @param messages
	 * @param sendType
	 */
	public void bulkSendMail(List<Message> messages, ThreadSendEnum sendType){
		try {
			
			switch (sendType) {
			case CALLABLE :
				
				Future<Integer> successCount =  executorService.submit(new Callable<Integer>() {
				
					@Override
					public Integer call() {
						
						int successCount = 0;
						ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
						int poolSize = threadPoolExecutor.getPoolSize();
						String threadName = Thread.currentThread().getName();
						logger.info("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);
						mailSend.send(messages);
						return successCount+1;
					}
				});
				
				logger.info("발송 {} 건 중  {} 건이 발송 되었습니다.", messages.size(), successCount);
				
				break;
			case RUNNABLE :
				
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						
						ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
						int poolSize = threadPoolExecutor.getPoolSize();
						String threadName = Thread.currentThread().getName();
						logger.info("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);
						
						mailSend.send(messages);
					}
				});
				
				break;
			}
			
			
			
		} catch (Exception e) {

			logger.debug("이메일 발송 에러  = {}" , e);
		} finally {
			//웹환경에서는 사용하지 않음.
			//executorService.shutdown();
		}
		
	}
	
	
	
	// Thread 종료
	public void emailSendShotdown() {
		executorService.shutdown();

		// 모두 종료되었는지 종료대기까지 기다린다. waiting~!
		while (!executorService.isTerminated()) {
			logger.info("THREAD_POOLE_XECUTOR shutdown and isWait......1");
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					logger.info("THREAD_POOLE_XECUTOR shutdown and isWait......2");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
