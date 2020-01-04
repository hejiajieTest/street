/**
 * Links.java	  V1.0   2019年11月14日 上午9:54:44
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.domain.resource.entity.trueMapStation;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * 功能描述：
 *
 * @author ：何佳杰
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class Links {

	@JSONField(name="Next")
	private Next next ;
	@JSONField(name="Pre")
	private Pre pre ;

	public Next getNext() {
		return next;
	}

	public Pre getPre() {
		return pre;
	}

	public void setNext(Next next) {
		this.next = next;
	}

	public void setPre(Pre pre) {
		this.pre = pre;
	}
	
	
}
