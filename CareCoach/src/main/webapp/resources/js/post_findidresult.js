
/**
 * 
 */

function openLoginModal3() {
    $('.modal-result-content').load("/findIdResult");
}

function post_findIdresult() {
	var url = "CareCoach?command=find_id_result";
 	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=300, top=300, left=300");
}