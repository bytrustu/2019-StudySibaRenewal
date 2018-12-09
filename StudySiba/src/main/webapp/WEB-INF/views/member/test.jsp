<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script>
$(document).ready(function(){
	$('.btn').on('click',function(){
		var value = $('input[name=text]').val();
		console.log(value);
		insertText(value);
		
	});
});


function insertText(value){
	$.ajax({
		type:'post',
		url:'/member/test',
		data:{
			text:value
		},
		success:function(data){
			console.log(data);
		}
	});
}

</script>

<input type="text" id="" name="text">
<button class="btn btn-primary">버튼</button>
