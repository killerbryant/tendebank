<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="css/jqpagination.css">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="js/jquery.jqpagination.js"></script>
	<style type="text/css">
		#qanda_div table {
			border: 1px solid black;
		}
		
		#qanda_div th, tr, td {
			border: 1px solid black;
		}
	</style>
	<script type="text/javascript">
	var jsonGodsData;
	var totalCount;
	var pageItem = 4; // 一頁幾筆
	var maxPage; // 最多幾頁
	var pageName = "${pageName}";
		$(function() {
			$.ajax({
	            type: "POST",
	            url: "getQAndAByPageName.do",
	            data: {"pageName":pageName},
	            async: false,
	            timeout: 15000,
	            dataType:"json",
	            success: function(data) {
	            	jsonGodsData = data.gods;
	            	totalCount = data.totalCount;
	            	maxPage = Math.ceil(totalCount/pageItem);
	            	if(jsonGodsData.length > 0) {
	            		drawPageTable(jsonGodsData, 1, pageItem);
			        }
	            },
	            complete: function() {
	            	
	            }
	        });
			
			// 分頁
           	$('.pagination').jqPagination({
           		max_page : maxPage,
           		page_string : '當前第{current_page}頁,共{max_page}頁',
			    paged: function(page) {
			    	drawPageTable(jsonGodsData, page, pageItem);
			    }
			});
		});
		/*
			data = JSON DATA;
			page = 第幾頁;
			onePageCount = 一頁顯示幾筆;
		*/
		function drawPageTable(data, page, onePageCount) {
			var count = 1;
			var itemMinCount = page; // 1 3 5、1 4 7
			var itemMaxCount = onePageCount; // 2 4 6、3 6 9
			if(page != 1) {
				itemMinCount = page*onePageCount-(onePageCount-1);
				itemMaxCount = page*onePageCount;
			}
			$("#gods_div").empty();
			var table = $("<table>");
        	var tr = $("<tr>");
        	var th = $("<th>");
        	var td = $("<td>");
        	var a = $("<a>");
        	// 先繪出標題
        	table.attr({"width":"95%"}).append(tr.clone().append(th.clone().text("順序")).append(th.clone().text("標題")));
           	$.each(data, function(key, qanda){
               	// 分頁的第幾筆
           		if(count >= itemMinCount && count <= itemMaxCount) {
           			// 繪出內容
               		var qandaNo = qanda.qandaNo;
               		var title = qanda.title;
               		var contents = qanda.contents;
               		table.append(tr.clone().append(td.clone().attr({"width":"25%"}).text(qandaNo)).append(td.clone().attr({"width":"70%"}).html(a.clone().attr({"href":"#","onclick":"openContents('"+title+"','"+contents+"')"}).text(title))));
               		if(count == itemMaxCount) {
               			return false;
               		}
           		}
           		count++;
            });
           	$("#qanda_div").html(table);
		}
		
		/*
			dialog顯示Q&A內容
		*/
		function openContents(title, data){
			if(data != "undefined") {
				$("#qanda_contents").html(data);
			} else {
				$("#qanda_contents").html("");
			}
			
			$("#qanda_contents").css({display:"inline"});

		    $("#qanda_contents").dialog({
		        title: title,
		        bgiframe: true,
		        width: 350,
		        height: 350,
		        modal: true,
		        draggable: true,
		        resizable: false,
		        overlay:{opacity: 0.7, background: "#FF8899" },
		        open: function(event, ui) {
		            $(event.target).dialog('widget')
		                .css({ position: 'fixed' })
		                .position({ my: 'center', at: 'center', of: window });
		        },
		        buttons: {
		            '關閉': function() {
		                $(this).dialog('close');
		            }
		        }
		    });
		}
	</script>
</head>
<body>
	神明專區
	<br>
	<div id="qanda_div">
		
	</div>
	<br>
	<div class="pagination" style="margin-left: 35%;">
	    <a href="#" class="first" data-action="first">&laquo;</a>
	    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
	    <input type="text" readonly="readonly" data-max-page="1"/>
	    <a href="#" class="next" data-action="next">&rsaquo;</a>
	    <a href="#" class="last" data-action="last">&raquo;</a>
	</div>
	<div id="qanda_contents" title="" style="display:none;">
	
	</div>
</body>
