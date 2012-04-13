/**
 * 
 * fileName   : mainHeader.jsp
 * createDate : 2011. 02. 21. 오후 4:53:59
 * CreateUser : ahn
 * Document   : todo
 * 
 * FIXME : new Calendar 
 */


/*
 	SAMPLE 사용방법
 	
 	CASE 1
 	{
 		<input type="text" name="date" id="date1" readonly="readonly" onClick="new Calendar(this);" />
 	}
 	
 	CASE 2
 	{
	 	<input type="text" name="date" id="date2" readonly="readonly" /> 
		<input type="button" onClick="new Calendar('date2');" value="달력"/>
		
		
		<input type="text" name="date" id="date3" readonly="readonly" /> 
		<input type="button" onClick="new Calendar('date3');" value="달력3" />
	}
 	
 */




var Calendar = {}; 

/**
 * DIV Calendar 
 * 
 * 사용방법 : new Calendar(this)   or new Calendar('id 값')
 * 
 * @param obj
 */
Calendar = function(obj) { 
		//alert("1" + obj);
		if(!(this.obj=document.getElementById(obj))){
			
		    this.obj=obj;
		    
		  //  alert("2" + obj);

        }

        this.today = this.today = new Date(); 

        this.year = this.today.getFullYear(); 

        this.month = this.today.getMonth(); 

        this.date = 1; 





        this.dayIndex = new Array('SUN','Mon','TUE','WED','THU','FRI','SAT'); 

        this.lastDate = new Array(31,  28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); 

        var tmp = document.getElementById('get_date_div');

        if(tmp){document.body.removeChild(tmp);}

        this.calWin     = document.createElement('div');

        document.body.appendChild(this.calWin);

        this.makeCal();

};

Calendar.prototype = {

    makeCal : function(type, addSub) { 

        this.newDate = new Date(this.year, this.month, this.date); 

        if(type && addSub) { 

            if(type == 'year') { 

                if(addSub == 'add') 
					this.newDate.setFullYear(this.newDate.getFullYear()+1); else 
					this.newDate.setFullYear(this.newDate.getFullYear()-1); 

            } else if(type == 'month') { 

                if(addSub == 'add') 
					this.newDate.setMonth(this.newDate.getMonth()+1); else 
					this.newDate.setMonth(this.newDate.getMonth()-1); 

            } 

        } 



        this.year = this.newDate.getFullYear(); 

        this.month = this.newDate.getMonth(); 

        this.day = this.newDate.getDay(); 



        if(((this.year%4 == 0) && (this.year%100 != 0)) || (this.year%400 ==0)) { 

            this.lastDate[1] = 29; 

        } else { 

            this.lastDate[1] = 28; 

        } 


        _cal = this; 


        //년 월 이동  [달력 상단페이지]
        this.calsrc  = "<table border='0' cellpadding='0' cellspacing='0' borderColor='#eeeeee' width='100%'>\n";        

        this.calsrc += 		"<tr>\n"; 

        this.calsrc += 			"<td><span onClick=_cal.makeCal('year','sub') style='cursor:pointer;'><<</span> <span onClick=_cal.makeCal('month','sub') style='cursor:pointer;'><</span></td>\n"; 

        this.calsrc += 			"<td>"+this.newDate.getFullYear()+"."+(this.month+1)+"</td>\n"; 

        this.calsrc += 			"<td><span onClick=_cal.makeCal('month','add') style='cursor:pointer;'>></span> <span onClick=_cal.makeCal('year','add') style='cursor:pointer;'>>></span></td>\n"; 

        this.calsrc += 			"<td align='right'><span onclick='_cal.Close_Div();' style='cursor:pointer'>close</span></td>\n"; 

        this.calsrc += 		"</tr>\n"; 

        this.calsrc += 	"</table>\n"; 

        
        //
        this.calsrc += "<table border='0' cellpadding='0' cellspacing='0' borderColor='#eeeeee' width='100%'>\n"; 

        this.calsrc += "<tr>\n"; 

        
        //요일생성
        for(i=0; i<this.dayIndex.length; i++) { 
        	if(i == 0){
        		this.calsrc += "<td style='color:red'>"+this.dayIndex[i]+"</td>\n"; 
        	}else if(i==6){
        		this.calsrc += "<td style='color:blue;'>"+this.dayIndex[i]+"</td>\n"; 
        	}else{
        		this.calsrc += "<td>"+this.dayIndex[i]+"</td>\n"; 
        	}
        	
        } 

        this.calsrc += "</tr>\n"; 
        
        
        for(i=0; i < this.day+this.lastDate[this.newDate.getMonth()] ; i++) { 
        	
        	
        	//7일씩 잘라내기 CASE ONE
            if(!(i%7)) { 
                this.calsrc += "</tr>\n"; 
                this.calsrc += "<tr>\n"; 
            }
            
            
            if(i >= this.day) { 
            	if((i%7) == 0){
            		this.calsrc += "<td style='color:red'><span onClick=_cal.setDate('"+((i-this.day)+1)+"') style='cursor:pointer;'>"+((i-this.day)+1)+"</td>\n";
            	}else if((i%7) == 6){
            		this.calsrc += "<td style='color:blue'><span onClick=_cal.setDate('"+((i-this.day)+1)+"') style='cursor:pointer;'>"+((i-this.day)+1)+"</td>\n";
            	}else{
            		this.calsrc += "<td><span onClick=_cal.setDate('"+((i-this.day)+1)+"') style='cursor:pointer;'>"+((i-this.day)+1)+"</td>\n";
            	}
            	
            } else {
            	
                this.calsrc += "<td></td>\n";
                
            }
        	
        

        } 

        this.calsrc += "</tr>\n"; 

        this.calsrc += "</table>\n"; 

        this.Open_div(this.calsrc);

    }, 



    fillZero : function(len, value) { 

        var value = value.toString(); 

        var addLen = len - value.length; 

        for(i=0; i<addLen; i++) { 

            value = "0"+value; 

        } 

        return value; 

    }, 

    setDate : function(day) { 
    	
        this.obj.value = this.year+'-'+this.fillZero(2,this.month+1)+'-'+this.fillZero(2,day); 

        this.Close_Div();

    },
    
    //top 좌표 설정
    GetObjectTop : function(input_obj){ 

        var l_intTopSum = input_obj.offsetTop;

        var dd = document.documentElement;

        var db = document.body;

        if(dd){var y_scroll=dd.scrollTop;}else{var y_scroll=db.scrollTop;}

        while(input_obj.nodeName.indexOf('HTML') != 0 && input_obj.nodeName.indexOf('BODY') != 0){

            input_obj = input_obj.offsetParent;

            l_intTopSum += input_obj.offsetTop;

        }

        l_intTopSum -= y_scroll;

        return l_intTopSum;

    },
    
    
    //left 좌표 설정
    GetObjectLeft : function(input_obj){

        var l_intLeftSum = input_obj.offsetLeft;

        var dd = document.documentElement;

        var db = document.body;

        if(dd){var x_scroll=dd.scrollLeft;}else{var x_scroll=db.scrollLeft;}

        while(input_obj.nodeName.indexOf('HTML') != 0 && input_obj.nodeName.indexOf('BODY') != 0){

            input_obj = input_obj.offsetParent;

            l_intLeftSum += input_obj.offsetLeft;

        }

        l_intLeftSum -= x_scroll;

        return l_intLeftSum;    

    },
    
    //div open
    Open_div : function(tag){

            var div_class;

            this.calWin.id             = 'get_date_div';

            this.calWin.style.position = 'absolute';//'fixed'

            this.calWin.style.width    = "230px";

            this.calWin.style.height   = "180px";

            this.calWin.style.left     = this.GetObjectLeft(this.obj) + "px";

            this.calWin.style.top      = (this.GetObjectTop(this.obj)+this.obj.offsetHeight) + "px";

            this.calWin.zIndex   = 1;

            this.calWin.style.backgroundColor = "#FFFFFF";

            this.calWin.innerHTML = '';

            this.calWin.innerHTML = tag;

    },
    
    //clode div
    Close_Div : function(){

        document.body.removeChild(this.calWin);

    }

} 	