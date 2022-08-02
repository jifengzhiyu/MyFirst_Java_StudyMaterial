function page(pageNo){
    window.location.href="/fruit/index/"+pageNo;
}

function delFruit(fid){
    if(confirm("是否确认删除？")){
        var frm = document.getElementById("frm");

        if(event && event.srcElement && event.srcElement.tagName=="INPUT"){
            var input = event.srcElement ;
            frm.action= "/fruit/del/"+fid;
            frm.submit();
            event.preventDefault();
        }
        //window.location.href="/fruit/del?fid="+fid;
    }
}