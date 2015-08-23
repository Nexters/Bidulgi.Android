package com.teamnexters.bidulgi.common.data;


public class NiceAuthData {
	
	//SKT, LGT, KTF, SKM, LGM, KTM
	String mobileCorporation;
	
	String name;
	
	//male : 1, female : 0
	int gender;
	
	//0 : local / 1 : foreign
	int nationalInfo;
	
	//ex: 1992
	String birthDate1;
	//ex: 09
	String birthDate2;
	//ex: 12
	String birthDate3;
	//ex: 010
	String mobileno1;
	//ex: 8612
	String mobileno2;
	//ex: 7652
	String mobileno3;
	// do not set this
	String answer;
	

	public String getMobileCorporation() {
		return mobileCorporation;
	}

	public void setMobileCorporation(String mobileCorporation) {
		this.mobileCorporation = mobileCorporation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getNationalInfo() {
		return nationalInfo;
	}

	public void setNationalInfo(int nationalInfo) {
		this.nationalInfo = nationalInfo;
	}

	public String getBirthDate1() {
		return birthDate1;
	}

	public void setBirthDate1(String birthDate1) {
		this.birthDate1 = birthDate1;
	}

	public String getBirthDate2() {
		return birthDate2;
	}

	public void setBirthDate2(String birthDate2) {
		this.birthDate2 = birthDate2;
	}

	public String getBirthDate3() {
		return birthDate3;
	}

	public void setBirthDate3(String birthDate3) {
		this.birthDate3 = birthDate3;
	}

	public String getMobileno1() {
		return mobileno1;
	}

	public void setMobileno1(String mobileno1) {
		this.mobileno1 = mobileno1;
	}

	public String getMobileno2() {
		return mobileno2;
	}

	public void setMobileno2(String mobileno2) {
		this.mobileno2 = mobileno2;
	}

	public String getMobileno3() {
		return mobileno3;
	}

	public void setMobileno3(String mobileno3) {
		this.mobileno3 = mobileno3;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
