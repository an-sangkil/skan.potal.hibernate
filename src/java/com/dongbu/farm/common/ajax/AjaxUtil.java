package com.dongbu.farm.common.ajax;

import java.io.Writer;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;

public class AjaxUtil {

	public static void successWrite(HttpServletResponse response) throws Exception {
		HashMap<Object,Object>  hmData = new HashMap<Object,Object> ();
		response.setContentType("text/html;charset=utf-8");
		Writer w = response.getWriter();
		JSONWriter jw = new JSONWriter();

		hmData.put("result", "SUCCESS");
		w.write(jw.write(hmData));

	}

	public static void successWrite(HttpServletResponse response, HashMap<Object,Object> hmData) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		Writer w = response.getWriter();
		JSONWriter jw = new JSONWriter();

		hmData.put("result", "SUCCESS");
		w.write(jw.write(hmData));

	}

	public static void failWrite(HttpServletResponse response) throws Exception {
		HashMap<Object,Object>  hmData = new HashMap<Object,Object> ();
		response.setContentType("text/html;charset=utf-8");
		Writer w = response.getWriter();
		JSONWriter jw = new JSONWriter();

		hmData.put("result", "FAIL");
		w.write(jw.write(hmData));

	}

	public static void failWrite(HttpServletResponse response, HashMap<Object,Object>  hmData) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		Writer w = response.getWriter();
		JSONWriter jw = new JSONWriter();

		hmData.put("result", "FAIL");
		w.write(jw.write(hmData));

	}
}
