package com.dongbu.farm.common.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dongbu.farm.common.ajax.JSONWriter;
import com.dongbu.farm.system.code.manager.ICodeManager;
import com.dongbu.farm.system.code.model.Code;

public class CommonCodeTag extends TagSupport {
	private static final long serialVersionUID = -7652820146661824773L;
	private String codeGroup = null;
	private String all = null;
	private String isSelectBox = null;
	private String attribute = null;
	private String selectedInfo = null;
	private String depth = null;
	private String lang = null;

	private ICodeManager codeManagerImpl;

	public ICodeManager getCodeManager() {
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		if (codeManagerImpl == null) {
			if (wc != null) {
				codeManagerImpl = (ICodeManager) wc.getBean("codeManagerImpl");
			}
		}
		return codeManagerImpl;
	}

	

	/**
	 * @return the codeManagerImpl
	 */
	public ICodeManager getCodeManagerImpl() {
		return codeManagerImpl;
	}



	/**
	 * @param codeManagerImpl the codeManagerImpl to set
	 */
	public void setCodeManagerImpl(ICodeManager codeManagerImpl) {
		this.codeManagerImpl = codeManagerImpl;
	}



	/**
	 * @return Returns the codeGroup.
	 */
	public String getCodeGroup() {
		return this.codeGroup;
	}

	/**
	 * @param codeGroup The codeGroup to set.
	 */
	public void setCodeGroup(String codeGroup) {
		try {
			this.codeGroup = (String) ExpressionEvaluatorManager.evaluate("codeGroup", codeGroup, java.lang.String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the isSelectBox
	 */
	public String getIsSelectBox() {
		return isSelectBox;
	}

	/**
	 * @param isSelectBox the isSelectBox to set
	 */
	public void setIsSelectBox(String isSelectBox) {
		this.isSelectBox = isSelectBox;
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		try {
			this.attribute = (String) ExpressionEvaluatorManager.evaluate("attribute", attribute, java.lang.String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the selectedInfo
	 */
	public String getSelectedInfo() {
		return selectedInfo;
	}

	/**
	 * @param selectedInfo the selectedInfo to set
	 */
	public void setSelectedInfo(String selectedInfo) {
		try {
			this.selectedInfo = (String) ExpressionEvaluatorManager.evaluate("selectedInfo", selectedInfo, java.lang.String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		try {
			this.all = (String) ExpressionEvaluatorManager.evaluate("all", all, java.lang.String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public String getDepth() {
		return depth == null ? "1" : depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}
	
	public String getLang() {
		return lang == null ? "KOR" : lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * doStartTag is called by the JSP container when the tag is encountered
	 */
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			CodeSearchCondition cCondition = new CodeSearchCondition();
			cCondition.setGroupCode(this.codeGroup);
			
			List<Code> list = getCodeManager().getCodeList(cCondition);

			if (list != null) {
				if (this.all != null && "Y".equals(this.all)) {
					List<Code> allList = new ArrayList<Code>();
					allList.add(new Code("", this.getLang().equals("KOR") ? "전체" : "ALL"));
					allList.addAll(list);
					list = allList;
				}
				
				//
				if (isSelectBox != null && (isSelectBox.equals("Y") || isSelectBox.equals("true"))) {
					StringBuffer sb = new StringBuffer();					
					//sb.append("<option></option>");
					for (Code code : list) {
						if (selectedInfo != null && selectedInfo.equals(code.getCode())) {
							sb.append("<option value='" + code.getCode() + "' selected >" + code.getCode_name() + "</option>");
						} else {
							sb.append("<option value='" + code.getCode() + "'  >" + code.getCode_name() + "</option>");
						}
					}
					String html = "<SELECT " + attribute + ">" + sb.toString() + "</SELECT>";
					out.print(html);
				} 
				//
				else {
					JSONWriter writer = new JSONWriter();
					out.print(writer.write(list));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO LOG ó��
			// throw new JspException(e);
		}

		return SKIP_BODY;
	}
}
