package com.biz.commerce.exec;

import com.biz.commerce.service.CommService;

public class CommExec {

	//Commerce Project의 진입점 Method
	public static void main(String[] args) {
		
		//읽고 쓸 파일경로를 String 변수에 저장
		String ioDataFile="src/com/biz/commerce/매입매출데이터.txt";
		String pInfoFile="src/com/biz/commerce/상품정보.txt";
		String ioInfoFile="src/com/biz/commerce/매입매출정보.txt";
		
		//파일경로 변수를 배열에 저장
		String[] files= {ioDataFile,pInfoFile,ioInfoFile};
		
		//CommService 객체 생성 및 초기화하고 매개변수로 파일경로가 저장된 배열을 전달
		CommService cs=new CommService(files);
		
		//매입매출데이터.txt 파일을 읽어서 List에 저장하는 Method 실행
		cs.ioRead();
		//상품정보.txt 파일을 읽어서 List에 저장하는 Method 실행
		cs.infoRead();
		//매입매출데이터 List와 상품정보 List의 상품코드를 비교하여 같은 상품을 찾고
		//매입매출데이터 List에 상품정보를 저장하는 Method 실행
		cs.matchList();
		//매입매출데이터 List에 저장된 값을 매입매출정보.txt에 쓰는 Method 실행
		cs.writeList();
	}
}
