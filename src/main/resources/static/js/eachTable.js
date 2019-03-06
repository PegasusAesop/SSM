	$(function(){
		
		//1.点击按钮操作后：
		$("#djBtn").click(function(){
		
			//1.清空页面上元素tBody内容 ；
			$("#tBody").html("");
			$.ajax({
				url:"myServlet7.do",	//2请求路径；
				//data:"random="+Math.random(),	//3参数 
				data:{"p":Math.random()}, //json格式传参给servlet;
				type:"get",	//4请求方式：post/get
				//asyn:true,	//ajax模式;默认异步；
				dataType:"json", 	//5返回值的类型：text:普通文本：json:json文本
				success:function(data){ 	//6回调函数 data:返回值；		
					//$("#msg").html(data.s2.id+data.s2.name+data.s2.age);
					//json取值方式：
					alert(data);
					var i=1;
					$(data.sList).each(function(){
						
						//1每一个json用this来表示；
						$("#tBody").append("<tr><td>"+(i++)
								+"</td><td>"+this.id
								+"</td><td>"+this.name
								+"</td><td>"+this.age
								+"</td><td><a href='index7.jsp'>添加</a>||<a href='index7.jsp'>删除</a></td></tr>");   
					})
				},

				error:function(){
					alert("请确定服务器是否真的打开了哦！");
				}
			})
				
		})
		
		//1小功能演示
		$("p").css("color","pink");
		//2隐藏
		$("p").click(function(){
			
			$(this).hide(500);
		})
		//3显示
		$("#show").click(function(){
			
			$("p").show(1000);
		})
		//4切换
		$("#toggleId").click(function(){
			
			$("p").toggle(1000);
		});
		//5fade&toggle
		$("#fadeId").click(function(){
			
			$("#div1").fadeToggle();
			$("#div2").fadeToggle("slow");
			$("#div3").fadeToggle(3000,function(){
				alert(1111);
			});
		})
		
		//6鼠标悬停事件
		$("#show").mouseover(function(){
			
			//alert("点点更健康！");
		})

	})	