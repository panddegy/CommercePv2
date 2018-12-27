package com.biz.commerce.vo;

//Commerce Project에서 사용할 VO Class 생성
public class CommVO {
	
	//매입매출데이터.txt와 상품정보.txt 파일을 읽어서 저장할 List를 구성할 변수를 선언
	private String strDate;		//거래일자
	private String strPCode; 	//상품코드
	private String strIO;		//구분
	private int intPrice;		//단가
	private int intQuan;		//수량
	private String strPName;	//상품명
	private int intIPrice;		//매입단가
	private int intOPrice;		//매출단가
	
	//private로 선언된 변수에 접근을 위한 setter와 getter Method 생성
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getStrPCode() {
		return strPCode;
	}
	public void setStrPCode(String strPCode) {
		this.strPCode = strPCode;
	}
	public String getStrIO() {
		return strIO;
	}
	public void setStrIO(String strIO) {
		this.strIO = strIO;
	}
	public int getIntPrice() {
		return intPrice;
	}
	public void setIntPrice(int intPrice) {
		this.intPrice = intPrice;
	}
	public int getIntQuan() {
		return intQuan;
	}
	public void setIntQuan(int intQuan) {
		this.intQuan = intQuan;
	}
	public String getStrPName() {
		return strPName;
	}
	public void setStrPName(String strPName) {
		this.strPName = strPName;
	}
	public int getIntIPrice() {
		return intIPrice;
	}
	public void setIntIPrice(int intIPrice) {
		this.intIPrice = intIPrice;
	}
	public int getIntOPrice() {
		return intOPrice;
	}
	public void setIntOPrice(int intOPrice) {
		this.intOPrice = intOPrice;
	}
	//CommVO에 저장된 값을 확인하기위한 toString Method override
	@Override
	public String toString() {
		return "CommVO [strDate=" + strDate + ", strPCode=" + strPCode + ", strIO=" + strIO + ", intPrice=" + intPrice
				+ ", intQuan=" + intQuan + ", strPName=" + strPName + ", intIPrice=" + intIPrice + ", intOPrice="
				+ intOPrice + "]";
	}
	
	
	
}