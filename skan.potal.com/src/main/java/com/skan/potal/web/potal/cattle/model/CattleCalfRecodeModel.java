package com.skan.potal.web.potal.cattle.model;

import java.util.Arrays;
import java.util.Date;

public class CattleCalfRecodeModel {
	
	/** 성별. */
	private String gender[];

	/** 판매여부. */
	private String sellYn[];

	/** 태어난날?. */
	private Date birthday[];

	public String[] getGender() {
		return gender;
	}

	public void setGender(String[] gender) {
		this.gender = gender;
	}

	public String[] getSellYn() {
		return sellYn;
	}

	public void setSellYn(String[] sellYn) {
		this.sellYn = sellYn;
	}

	public Date[] getBirthday() {
		return birthday;
	}

	public void setBirthday(Date[] birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "CattleCalfRecodeModel [gender=" + Arrays.toString(gender) + ", sellYn=" + Arrays.toString(sellYn)
				+ ", birthday=" + Arrays.toString(birthday) + "]";
	}
}
