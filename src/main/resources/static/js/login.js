/**
 * 
 */

 function loginCheck() {
	 if(document.loginForm.mid.value.length == 0) {
		 alert("아이디를 입력해 주세요.");
		 return;
	 }
	 if(document.loginForm.mpw.value.length == 0) {
		 alert("비밀번호를 입력해 주세요.");
		 return;
	 }

	 document.loginForm.submit();
 }