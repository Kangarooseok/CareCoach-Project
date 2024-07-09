//페이지 이동
function fn_paging(page) {
    var url = window.location.href;
    var newUrl = '';

    if (url.indexOf('?') > -1) {
        // URL에 파라미터가 있는 경우
        if (url.indexOf('curPage') > -1) {
            // 기존의 curPage 파라미터를 새로운 값으로 변경
            newUrl = url.replace(/(curPage=)[0-9]+/, '$1' + page);
        } else {
            // 기존의 curPage 파라미터가 없는 경우 추가
            newUrl = url + '&curPage=' + page;
        }
    } else {
        // URL에 파라미터가 없는 경우
        newUrl = url + '?curPage=' + page;
    }

    window.location.href = newUrl;
}



