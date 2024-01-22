package com.mkl.home.dao;

import com.mkl.home.dto.MemberDto;

public interface MemberDao {
	public void joinDao(String mid, String mpw, String mname, String memail);//회원가입
	public int idCheckDao(String mid);//가입하려는 id의 존재여부 체크
	public int idPwCheckDao(String mid, String mpw);//아이디와 비밀번호가 일치하는 레코드 존재여부 체크
	public MemberDto memberInfoDao(String mid);//아이디로 검색하여 해당 회원의 정보 거져오기
	public void modifyInfoDao(String mid, String mpw, String mname, String memail);//회원정보 수정
	
}
