<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<script type="text/javascript">
		$(function() {
			$('#add').click(function(){
				var fd = new FormData();
				fd.append("file", $('input[type=file]')[0].files[0]);
				
				var dataObj = new Object();
				dataObj.productNo = $("#productNo").val();
				dataObj.productName = $("#productName").val();
				dataObj.saleprice = $("#saleprice").val();
				dataObj.cost = $("#cost").val();
				dataObj.vendor = $("#vendor").val();
				
				fd.append("product", new Blob([JSON.stringify(dataObj)], {
	                type : "application/json"  // ** specify that this is JSON**
	            })); 
	            
				$.ajax({
		            type: "POST",
		            url: "saveProduct.do",
		            data: fd,
		            async: false,
		            timeout: 15000,
		            dataType:"json",
		            contentType: false,
	                processData: false,
	                cache: false,
		            success: function(data) {
		            	var resultMsg = data.result;
			           	alert(data.successMsg);
			           	if(resultMsg) {
				        	$("#formProduct>input[type=text]").val("");
					    }
		            },
		            complete: function() {
		            	
		            }
		        });
			});
		});
	</script>
</head>
<body>
	新增產品
	<form id="formProduct" action="saveProduct.do" method="post" enctype="multipart/form-data">
		產品編號:<input type="text" id="productNo" name="productNo" placeholder="輸入產品代號" />
		<font color="red">
			
		</font>
		<br>
		產品名稱:<input type="text" id="productName" name="productName" placeholder="輸入產品名稱" />
		<br>
		售價:<input type="text" id="saleprice" name="salePrice" />
		<br>
		成本:<input type="text" id="cost" name="cost" />
		<br>
		供應商:<input type="text"id="vendor" name="vendor" />
		<br>
		上傳圖片:<input type="file" name="file" />
		<br>
		<!--<input id="add" type="submit" value="新增" />-->
		<input id="add" type="button" value="新增" />
		<input type="reset" value="清除" />
	</form>
</body>
