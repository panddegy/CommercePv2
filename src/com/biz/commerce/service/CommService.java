package com.biz.commerce.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.commerce.vo.CommVO;

//Commerce Project에서 사용할 Service Class 생성
public class CommService {
	
	//생성자 Method에서 받은 배열을 저장하기 윈한 String[] 변수 선언
	String[] files;
	//매입매출데이터.txt 파일을 읽어 저장할 List 선언
	List<CommVO> ioList;
	//상품정보.txt 파일을 읽어 저장할 List 선언
	List<CommVO> infoList;
	
	//CommService 생성자 Method Override
	public CommService(String[] files) {
		//매개변수로 받은 배열을 맴버변수에 저장
		this.files=files;
		//위에서 선언한 List들을 초기화
		ioList=new ArrayList();
		infoList=new ArrayList();
	}
	
	//매입 매출을 구분하는 Method
	public void checkIO(CommVO vo) {
		
		//VO에 저장된 구분값을 정수형으로 변환하여 검사
		int intIO=Integer.valueOf(vo.getStrIO());
		if(intIO==1) {
			//매입인 경우 "매입"으로 저장하고 단가에 매입단가, 매출단가에 0을 저장
			vo.setStrIO("매입");
			vo.setIntOPrice(0);
			vo.setIntPrice(vo.getIntIPrice());
		} else {
			//매출인 경우 "매출"로 저장하고 단가에 매출단가, 매입단가에는 0을 저장
			vo.setStrIO("매출");
			vo.setIntIPrice(0);
			vo.setIntPrice(vo.getIntOPrice());
		}
		
	}
	//저장된 List를 매입매출정보.txt 파일에 저장하는 Method
	public void writeList() {
		
		//PrintWriter 객체 선언
		PrintWriter pw;
		
		try {
			//PrintWriter 객체를 초기화하고 매개변수로 저장할 파일경로를 전달
			pw=new PrintWriter(files[2]);
			//Enhanced for를 이용하여 ioList에 저장된 VO를 호출
			for(CommVO vo:ioList) {
				//CheckIO Method를 이용하여 매입,매출 구분
				checkIO(vo);
				//PrintWriter 객체를 사용하여 파일에 저장
				pw.print(vo.getStrDate()+":");						//거래일자
				pw.print(vo.getStrIO()+":");						//구분
				pw.print(vo.getStrPCode()+":");						//상품코드
				pw.print(vo.getStrPName()+":");						//상품명
				pw.print(vo.getIntPrice()+":");						//단가
				pw.print(vo.getIntQuan()+":");						//수량
				pw.print(vo.getIntIPrice()*vo.getIntQuan()+":");	//매입금액
				pw.println(vo.getIntOPrice()*vo.getIntQuan());		//매출금액
			}
			//PrintWriter 객체 Close
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//파일에 저장된 것을 확인하는 Message
		System.out.println("저장완료");
	}
	
	//매입매출 List에 상품명, 매입/매출 단가를 저장하는 Method
	public void matchList() {
		
		//Enhanced for를 이용하여 ioList에 저장된 VO를 호출
		for(CommVO iov : ioList) {
			//호출된 VO를 checkList Method에 매개변수로 전달
			CommVO inv=checkList(iov);
			//return 값이 null이면 건너뜀
			if(inv==null) continue;
			//return 받은 inv의 저장된 값을 iov에 저장
			iov.setStrPName(inv.getStrPName());		//상품명
			iov.setIntIPrice(inv.getIntIPrice());	//매입단가
			iov.setIntOPrice(inv.getIntOPrice());	//매출단가
		}
	}
	
	//두개의 List의 값을 검사하는 Method
	public CommVO checkList(CommVO iov) {
		
		//Enhanced for를 이용하여 infoList에 저장된 VO를 호출
		for(CommVO inv : infoList) {
			//매개변수로 받은 iov와 Enhanced for로 호출한 inv의 상품코드를 비교하여
			//저장된 값이 같으면 inv를 return
			if(iov.getStrPCode().equals(inv.getStrPCode())) return inv;
		}
		//같은 값이 없으면 null을 return
		return null;
	}
	
	//매입매출데이터.txt 파일을 읽어서 ioList에 저장하는 Method
	public void ioRead() {
		
		//FileReader와 BufferedReader 객체 선언
		FileReader fr;
		BufferedReader buffer;
		
		try {
			//FileReader를 초기화 하고 매입매출데이터.txt 파일경로를 매개변수로 전달
			fr=new FileReader(files[0]);
			//BufferedReader를 초기화하고 매개변수로 FileReader 객체를 전달
			buffer=new BufferedReader(fr);
			//while을 이용하여 파일 읽기
			while(true) {
				//buffer에서 한줄을 읽어 reader변수에 저장
				String reader=buffer.readLine();
				//reader에 저장된 값이 null(End of File)이면 break;
				if(reader==null) break;
				//reader에 저장된 값을 ":"로 split하여 String[] ios에 저장
				String[] ios=reader.split(":");
				//CommVO 객체를 선언하고 초기화
				CommVO vo=new CommVO();
				//ios[]에 저장된 값을 읽어 와서 vo에 저장
				vo.setStrDate(ios[0]);
				vo.setStrPCode(ios[1]);
				vo.setStrIO(ios[2]);
				int intPrice=Integer.valueOf(ios[3]);
				int intQuan=Integer.valueOf(ios[4]);
				vo.setIntPrice(intPrice);
				vo.setIntQuan(intQuan);
				//값이 저장된 vo를 ioList에 저장
				ioList.add(vo);
			}
			//BufferedReader와 FileReader Close
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//상품정보.txt 파일을 읽어서 infoList에 저장하는 Method
	public void infoRead() {
		
		//FileReader와 BufferedReader 객체 선언
		FileReader fr;
		BufferedReader buffer;
		
		try {
			//FileReader를 초기화 하고 상품정보.txt 파일경로를 매개변수로 전달
			fr=new FileReader(files[1]);
			//BufferedReader를 초기화하고 매개변수로 FileReader 객체를 전달
			buffer=new BufferedReader(fr);
			//while을 이용하여 파일 읽기
			while(true) {
				//buffer에서 한줄을 읽어 reader변수에 저장
				String reader=buffer.readLine();
				//reader에 저장된 값이 null(End of File)이면 break;
				if(reader==null) break;
				//reader에 저장된 값을 ":"로 split하여 String[] ios에 저장
				String[] ios=reader.split(":");
				//CommVO 객체를 선언하고 초기화
				CommVO vo=new CommVO();
				//ios[]에 저장된 값을 읽어 와서 vo에 저장
				vo.setStrPCode(ios[0]);
				vo.setStrPName(ios[1]);
				int intIPrice=Integer.valueOf(ios[3]);
				int intOPrice=Integer.valueOf(ios[4]);
				vo.setIntIPrice(intIPrice);
				vo.setIntOPrice(intOPrice);
				//값이 저장된 vo를 infoList에 저장
				infoList.add(vo);
			}
			//BufferedReader와 FileReader Close
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
