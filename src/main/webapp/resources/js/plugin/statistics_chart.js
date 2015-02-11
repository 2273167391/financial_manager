//统计图插件
var statistics={
	//柱状图
	barChart:function(options){
		//默认参数
		var setting={elem:"chart-container",title:"",subTitle:"",x:"",y:"",type:"",month:"",data:[]};
		jQuery.extend(setting,options);
		
		//初始化数据
		var initData=[];
		//封装年初始化数据
		if(setting.type=="year"){
			for(var i=1;i<=12;i++){
				initData.push({"label":""+i+"","value":""});
			}
		//封装月初始化数据
		}else if(setting.type=="month"){
			var date=new Date(new Date().getFullYear(),setting.month.substring(setting.month.indexOf('-')+1),0);
			//根据获取每个月的天数初始化数据
			for(var i=1;i<=date.getDate();i++){
				initData.push({"label":""+i+"","value":""});
			};
		}
		
		//判断相同数据则替换
		for(var i=0;i<setting.data.length;i++){
			for(var j=0;j<initData.length;j++){
				if(parseInt(setting.data[i].label)==parseInt(initData[j].label)){
					initData[j].value=setting.data[i].value;
					break;
				}
			}
		}
		
		FusionCharts.ready(function () {
		    var revenueChart = new FusionCharts({
		        type: 'column3d',
		        renderAt: setting.elem,
		        width: '100%',
		        height: '530',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": setting.title,
		                "subCaption": setting.subTitle,
		                "xAxisName": setting.x,
		                "yAxisName": setting.y,
		                "paletteColors": "#1FAFF5",
		                "baseFont": "Helvetica Neue,Arial,'微软雅黑'",
		                "captionFontSize": "18",
		                "subcaptionFontSize": "14",
		                "subcaptionFontBold": "0",
		                "rotateValues": "1",
		                "showShadow": "0",
		                "divlineColor": "#999999",               
		                "divLineIsDashed": "1",
		                "divLineDashLen": "1",
		                "canvasBgColor": "#ffffff"
		            },
		            "data": initData
		        }
		    });
		    revenueChart.render();
		});
	},
	//饼图
	pieChart:function(options){
		var setting={
				elem:"chart-container",
				title:"",
				subTitle:"",
				color:"#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000,#ff0ff0",
				data:[]
		};
		
		jQuery.extend(setting,options);
		
		FusionCharts.ready(function () {
		    var ageGroupChart = new FusionCharts({
		        type: 'pie3d',
		        renderAt: setting.elem,
		        width: '100%',
		        height: '500',
		        dataFormat: 'json',
		        dataSource: {
		            "chart": {
		                "caption": setting.title,
		                "subCaption": setting.subTitle,
		                "paletteColors":setting.color,
		                "bgColor": "#ffffff",
		                "showBorder": "0",
		                "use3DLighting": "0",
		                "showShadow": "0",
		                "enableSmartLabels": "0",
		                "startingAngle": "0",
		                "showPercentValues": "1",
		                "showPercentInTooltip": "0",
		                "decimals": "1",
		                "captionFontSize": "14",
		                "subcaptionFontSize": "14",
		                "subcaptionFontBold": "0",
		                "toolTipColor": "#ffffff",
		                "toolTipBorderThickness": "0",
		                "toolTipBgColor": "#000000",
		                "toolTipBgAlpha": "80",
		                "toolTipBorderRadius": "2",
		                "toolTipPadding": "5",
		                "showHoverEffect":"1",
		                "showLegend": "1",
		                "legendBgColor": "#ffffff",
		                "legendBorderAlpha": '0',
		                "legendShadow": '0',
		                "legendItemFontSize": '10',
		                "legendItemFontColor": '#666666'
		            },
		            "data": setting.data
		        }
		    }).render();
		});
	}
};