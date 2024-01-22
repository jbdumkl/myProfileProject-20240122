package com.mkl.home.dao;

import java.util.List;

import com.mkl.home.dto.QAboardDto;

public interface BoardDao {
	
	public List<QAboardDto> listDao(int amount, int pageNum);//페이지 번호에 따른 글 리스트 가져오기모든 글 리스트 가져오기
	public void writeDao(String qbmid, String qbmname, String qbmemail, String qbtitle, String qbcontent);//게시판 글쓰기
	public QAboardDto contentViewDao(String qbnum);//글 번호로 해당 글의 레코드 반환
	public void contentModifyDao(String qbnum, String qbtitle, String qbcontent);//글 수정
	public void contentDeleteDao(String qbnum);//글 삭제
	public int boardAllCountDao();//게시판 내 모든 총 개수를 반환
}
