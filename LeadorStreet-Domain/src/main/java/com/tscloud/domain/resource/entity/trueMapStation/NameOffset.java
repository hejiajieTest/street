package com.tscloud.domain.resource.entity.trueMapStation;

/**
 * 
 * 功能描述：
 *
 * @author ：何佳杰
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class NameOffset {

	//大文件序号
	public short fileIndex;
	//偏移地址
	public long offSet;
	
	public NameOffset(){
		fileIndex = -1;
		offSet = 0;
	}
}
