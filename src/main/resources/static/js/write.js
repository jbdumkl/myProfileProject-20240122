/**
 * 
 */

 function writeCheck() {
	 if(document.writeForm.qgtitle.value.length == 0) {
		 alert("제목을 입력해주세요.");
		 return;
	 }
 	 if(document.writeForm.qbcontent.value.length == 0) {
		 alert("내용을 입력해주세요.");
		 return;
	 }
	 if(document.writeForm.qbcontent.value.length < 10) {
		 alert("내용은 10자 이상이어야 합니다.");
		 return;
	 }
	 
	 document.writeForm.submit();
 }