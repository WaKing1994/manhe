// JavaScript Document

 


jQuery(document).ready(function(){
	
	//企业荣誉脚本
	jQuery(".gsrr_dt").click(function(){
		jQuery(".gsrr_dd").hide();
		jQuery(this).next(".gsrr_dd").fadeToggle();
		
		
		})
	
		//营销网络脚本
	jQuery(".yxwl_dt").click(function(){
		jQuery(".yxwl_dd").hide();
		jQuery(this).next(".yxwl_dd").fadeToggle();
		
		
		})
	

	//导航栏脚本
	
		jQuery("#nav_dj").click(function(event){
		 event.stopPropagation(); 
		 jQuery("#nav").fadeToggle();
		 jQuery(".bj").fadeToggle();
		 
		})
		
		
			 jQuery("#nav").click(function (event) {
				 	 event.stopPropagation(); 
			 })
		
		   jQuery(document).click(function (event) { 
		   	 jQuery("#nav").hide();
		 jQuery(".bj").hide();
jQuery(".tc").hide();
	jQuery(".tc_w").hide(400);
jQuery(".xh_cs_gd").hide();
   }); 

	jQuery(".in_s li").click(function(event){
				 jQuery(".in_s_bj").hide();
				 	 jQuery(".in_s_x").hide();
			 event.stopPropagation()
			 jQuery(this).children(".cc").children(".in_s_bj").show();
			 	
			  jQuery(this).children(".cc").children(".in_s_x").fadeToggle();
			 
	})
	/*产品内页颜色脚本*/
			

	
	
	
	
	jQuery(".xh_cs_j").click(function(event){
		 event.stopPropagation(); 
var dis=jQuery(this).next('.xh_cs_gd').css('display')=='none';
if(dis){
	jQuery(".xh_cs_gd").show();
}else{
		jQuery(".xh_cs_gd").hide();
	}
	})

						
				
				

			})
 jQuery(window).scroll(function () {
            var scroll_top = jQuery(document).scrollTop();
            if (scroll_top >= 200) {
                jQuery("#path").slideDown();
				jQuery("#gettop").fadeOut("slow",0.25);
				
				}
              else
                   {
					   jQuery("#path"). slideUp();
				   jQuery("#gettop").fadeTo("slow",0.25);
				   
				   }           
  
   

				    
        });
	jQuery(function(){
		
		 jQuery("#path").click(function(){
			 
			 jQuery('html,body').animate({scrollTop:0},700);
			 })

	
	})



jQuery(window).resize(function(){
		/*if (typeof indexSlides != 'undefined' && indexSlides.reformat) 
		indexSlides.reformat();*/
	dxcc();

})