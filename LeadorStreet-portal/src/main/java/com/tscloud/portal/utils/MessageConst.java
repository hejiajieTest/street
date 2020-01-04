/**
 * MessageConst.java	  V1.0   2019年12月4日 下午2:34:34
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.portal.utils;

/**
 * 
 * 功能描述：返回消息常量类
 *
 * @author ：何佳杰
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class MessageConst {

	public static final String FAILUREMESSAGE = "数据获取失败！" ;
	
	public final class GetStationByID{
		public static final String SUCCESSMESSAGE = "成功获取测站数据。" ;
		public static final String STATION = "Station" ;
	}
	
	public final class GetStationJoints{
		public static final String SUCCESSMESSAGE = "成功获取路口数据。" ;
		public static final String JUNCTION = "Junction" ;
		
	}
	
	public final class GetStationByCoord{
		public static final String SUCCESSMESSAGE = "成功获取路口数据。" ;
		public static final String STATION = "Station" ;
	}
	
	public final class GetImageInfo{ 
		public static final String SUCCESSMESSAGE = "成功获得测站所有相机信息。" ;
		public static final String SIZES = "Sizes" ;
	}
	public final class GetStationByStep{
		public static final String SUCCESSMESSAGE = "成功获取测站数据。" ;
		public static final String STATIONIDNOTEXIST = "当前位置点找不到！" ;
		public static final String STATION = "Station" ;
	}
	
	public final class GetStationOppositeDirection{ 
		public static final String SUCCESSMESSAGE = "成功获取反向测站数据。" ;
		public static final String STATION = "Station" ;
	}
	
	public final class GetStationSameDirection{ 
		public static final String SUCCESSMESSAGE = "成功获取同向测站数据。" ;
		public static final String STATIONS = "Stations" ;
	}
	
	public final class GetStationSequence{ 
		public static final String SUCCESSMESSAGE = "成功获取测站串数据。" ;
		public static final String STATION = "Station" ;
	}
	public final class DeleteImageMarker{ 
		public static final String SUCCESSMESSAGE = "标注信息删除成功。" ;
		public static final String FAILUREMESSAGE = "标注信息删除失败。" ;
		public static final String GUID = "Guid" ;
	}
	
	public final class SearchImageMarkers{ 
		public static final String SUCCESSMESSAGE = "标注信息获取成功。" ;
		public static final String FAILUREMESSAGE = "标注信息获取失败。" ;
		public static final String IMAGEMARKERS = "ImageMarkers" ;
	}
	
	public final class GetImageMarkerAttributes{ 
		public static final String SUCCESSMESSAGE = "指定标注所有属性获取成功。" ;
		public static final String FAILUREMESSAGE = "指定标注所有属性获取失败。" ;
		public static final String ATTRIBUTES = "Attributes" ;
	}
	
	public final class SetImageMarkerAttributes{ 
		public static final String SUCCESSMESSAGE = "指定标注属性信息更新成功。" ;
		public static final String FAILUREMESSAGE = "指定标注属性信息更新失败。" ;
		public static final String MarkerISNOTEXIST = "标注信息不存在。" ;
	}
	
	public final class GetImageMarkerAttributeEnum{ 
		public static final String SUCCESSMESSAGE = "影像标注属性枚举获取成功。" ;
		public static final String FAILUREMESSAGE = "影像标注属性枚举获取失败。" ;
		public static final String NAMES = "Names" ;
	}
	
	public final class AddMarkerSymbol{ 
		public static final String SUCCESSMESSAGE = "新的符号信息添加成功。" ;
		public static final String FAILUREMESSAGE = "新的符号信息添加失败。" ;
		public static final String GUID = "Guid" ;
	}
	
	public final class UpdateMarkerSymbol{ 
		public static final String SUCCESSMESSAGE = "符号信息修改成功。" ;
		public static final String FAILUREMESSAGE = "符号信息修改失败。" ;
	}
	public final class DeleteMarkerSymbol{ 
		public static final String SUCCESSMESSAGE = "符号信息删除成功。" ;
		public static final String FAILUREMESSAGE = "符号信息删除失败。" ;
	}
	
	public final class SearchMarkerSymbols{ 
		public static final String SUCCESSMESSAGE = "检索符号信息请求成功。" ;
		public static final String FAILUREMESSAGE = "检索符号信息请求失败。" ;
		public static final String SYMBOLS ="Symbols" ;
		      
	}
	
	public final class QueryMatchImage{ 
		public static final String SUCCESSMESSAGE = "影像ID查找匹配影像请求成功。" ;
		public static final String FAILUREMESSAGE = "影像ID查找匹配影像请求失败。" ;
		public static final String MATCHIMAGE ="matchImage" ;
		      
	}
	
	public final class GetImageSize{ 
		public static final String SUCCESSMESSAGE = "影像尺寸大小请求成功。" ;
		public static final String FAILUREMESSAGE = "影像尺寸大小请求失败。" ;
		public static final String IMAGESIZE ="ImageSize" ;
		      
	}
	
}
