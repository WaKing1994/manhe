function rdt() {
    var d = new Date();
    return d.getTime();
}


$(function(){
	jQuery('a').bind('focus',function(){
			if(this.blur){
					this.blur();
			};
	});
	
	var now_file = location.href.substring(location.href.lastIndexOf("/")+1);
	
	if(now_file=="")
	{
		now_file="index";
	}
	

	$(".menu").find(" a[href^='"+now_file+"']").parent().addClass("first");
})

/*二级菜单*/

				$(function(){
					$(".menu > li").hover(function(){
							$(this).find("a:first").addClass("hover")
							// $(this).find(" > ul ").slideDown(10)
                            $(this).find(" > ul ").addClass("subnav")
						},function(){
							$(this).find("a:first").removeClass("hover")
							// $(this).find(" > ul ").slideUp(10)
                            $(this).find(" > ul ").removeClass("subnav")
						})	
				})




/*top网上*/
$(function(){
	
    $(".fd_bottom a").click(function(){
		$("html,body").animate({scrollTop:0},300);
	});
	
})

/*产品栏目切换*/
$(function(){
	//产品选项卡
	$(".box_top li").eq(0).addClass("sel");
	$(".box_bottom ul").eq(0).show();
	$(".box_top ul li").hover(function(){
		$(this).addClass("sel").siblings().removeClass("sel");
		var _this = $(".box_top ul li").index(this);
		$(".box_bottom ul").eq(_this).show().siblings().hide();
	});	
	//导航下拉
                    $(".headline li").hover(function(){
                            $(this).find("a:first").addClass("current")
                            $(this).find("div").addClass("subnav")
                        },function(){
                            $(this).find("a:first").removeClass("current")
                            $(this).find("div").removeClass("subnav")
                        })  
    //产品分类无产品时提示
    if($(".productslist li").length == 0) {
    	$(".productslist").append("<li class='nocontent'>资料正在整理中...</li>")
    }
})


//js无缝滚动代码
function marquee(i, direction){

var obj = document.getElementById("marquee" + i);

var obj1 = document.getElementById("marquee" + i + "_1");

var obj2 = document.getElementById("marquee" + i + "_2");

if (direction == "up"){

obj.scrollTop++;

if (obj.scrollTop == obj1.offsetHeight){

obj.scrollTop = 1;

}

}else{

obj.scrollLeft++;

if (obj.scrollLeft == obj1.offsetWidth){

obj.scrollLeft = 1;

}

}

}

function marqueeStart(i, direction){

var obj = document.getElementById("marquee" + i);

var obj1 = document.getElementById("marquee" + i + "_1");

var obj2 = document.getElementById("marquee" + i + "_2");

obj.scrollLeft = 1;
obj.scrollTop = 1;

obj2.innerHTML = obj1.innerHTML;

var marqueeVar = window.setInterval("marquee("+ i +", '"+ direction +"')",30);

obj.onmouseover = function(){

window.clearInterval(marqueeVar);

}

obj.onmouseout = function(){

marqueeVar = window.setInterval("marquee("+ i +", '"+ direction +"')",30);

}

}

 