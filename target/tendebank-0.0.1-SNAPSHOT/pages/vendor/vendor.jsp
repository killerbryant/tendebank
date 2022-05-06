<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<script type="text/javascript">
		$(function() {
			$('#add').click(function(){
				$.ajax({
		            type: "POST",
		            url: "saveVendor.do",
		            data: $("#formVendor").serialize(),
		            async: false,
		            timeout: 15000,
		            dataType:"json",
		            success: function(data) {
			           	var resultMsg = data.result;
			           	alert(data.successMsg);
			           	if(resultMsg) {
				        	$("#formVendor>input[type=text]").val("");
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
	<form id="formVendor" action="" method="post" >
		供應商編號:<input type="text" id="vendorNo" name="vendorNo" placeholder="輸入供應商編號" />
		<font color="red">
			
		</font>
		<br>
		供應商名稱:<input type="text" id="companyName" name="companyName" placeholder="輸入供應商名稱" />
		<br>
		供應商電話:<input type="text" id="telephone" name="telephone" />
		<br>
		供應商地址:<input type="text" id="address" name="address" />
		<br>
		<!--<s:submit value="新增"  />-->
		<input id="add" type="button" value="新增" />
		<input type="reset" value="清除" />
	</form>
</body>
