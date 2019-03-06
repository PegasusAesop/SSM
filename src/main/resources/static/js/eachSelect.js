$(function(){
		
	$("#djBtn").click(function(){
		
		$("#pid").html("<option >--请选择--</option>");
		
		$.ajax({
			url:"province/list.do",
			data:"p="+Math.random(),
			type:"get",
			dataType:"json",
			success:function(data){
				
				//alert(data);
				$(data.pList).each(function(){
					
					$("#pid").append("<option value='"+this.id+"'>"+this.name+"</option>");
					
				})
			},
			error:function(){
				
				alert("请确定服务器是否真的打开了哦！");
			}
		})
		
	})
		
	//1为省份的下拉框绑定，下拉框选中事件
	$("#pid").change(function(){
		
		$("#cid").html("<option >--请选择--</option>");
		//2取pid值；
		var pid=$("#pid").val();
		
		$.ajax({
			
			url:"city/list.do",
			//data:"pid="+pid+"&p="+Math.random(),
			data:{
				"pid":pid,
				//"p":Math.random()
				"p":new Date().getTime()
			},
			type:"get",
			dataType:"json",
			success:function(data){
				
				//alert(data);
				$(data.cList).each(function(){
					
					$("#cid").append("<option value='"+this.id+"'>"+this.name+"</option>");
					
				})

			}
		})
		
	})
		
	//2.city下拉菜单选中一个选项后；
	//3.将选中的省名及城市名显示出来；
	$("#cid").change(function(){
		//4.将所有内容全部选中；
		//var msg=$("#pid").text();
		//5.选中的才选上；
		var msg=$("#pid").find("option:selected").text()+"->"+$("#cid").find("option:selected").text();
		$("#msg").html(msg);
		//alert(22223);
	})
			
})
