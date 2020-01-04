package com.tscloud.domain.resource.entity.trueMapStation;


/**
 * 
 * 功能描述：
 *
 * @author ：何佳杰
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class MemIndex implements Comparable<MemIndex> {
	private static int counter = 0;
	public int id;
	//同步器号
	public int vid;
	//相机号
	public char camera;
	//测站时间
	public long time;
	//影像级别
	public short level;
	
	public MemIndex(){
		id = ++counter;
		vid = 0;
		camera = 'X';
		time = 0;
		level = -1;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.vid == ((MemIndex) obj).vid
				&& this.camera == ((MemIndex) obj).camera
				&& this.time == ((MemIndex) obj).time && this.level == ((MemIndex) obj).level);
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public int compareTo(MemIndex o) {
		if (this.vid != o.vid)
			return (this.vid < o.vid ? -1 : 1);
		if (this.camera != o.camera)
			return (this.camera < o.camera ? -1 : 1);
		if (this.time != o.time)
			return (this.time < o.time ? -1 : 1);
		if (this.level != o.level)
			return (this.level < o.level ? -1 : 1);
		return 0;
	}
}
