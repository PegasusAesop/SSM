$(function(){
		$("#usernameId").blur(function(){
			//1获取id值为usernameId标签输入的值；
			var username=$("#usernameId").val();
			
			if(username==""){
				return;
			}
			$.ajax({
				url:"registerServlet.do",	//2请求路径；
				data:"username="+username+"&t="+Math.random(),	//3传入参数 
				type:"get",	//4请求方式：post/get
				asyn:true,	//ajax模式;默认异步；
				dataType:"text", 	//5返回值的类型：text:普通文本：json:json文本
				//dataType:"json", 	//5返回值的类型：text:普通文本：json:json文本
				success:function(data){ 	//6回调函数 data:返回值；
					if(data=="success"){
						$("#msg").html("<font color='#D6D6FF'>用户名可用</font>");
					}else{
						$("#msg").html("<font color='pink'>用户名重复</font>");
					}
					//json取值方式：
					//alert(data.str1+"  "+data.str2);
				},
				error:function(){
					alert("请确定服务器是否真的打开了哦！");
				}
			})
		})
		
	})
