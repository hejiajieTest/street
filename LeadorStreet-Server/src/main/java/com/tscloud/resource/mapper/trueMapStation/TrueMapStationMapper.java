package com.tscloud.resource.mapper.trueMapStation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tscloud.common.framework.mapper.BaseInterfaceMapper;
import com.tscloud.domain.resource.entity.trueMapStation.Next;
import com.tscloud.domain.resource.entity.trueMapStation.Pre;
import com.tscloud.domain.resource.entity.trueMapStation.TrueMapStation;

@Repository
public interface TrueMapStationMapper extends BaseInterfaceMapper<TrueMapStation> {


	Pre getPreviousPointByPrevious(@Param(value="stationID") String previous);

	Next getNextPointByNext(@Param(value="stationID") String next);

	TrueMapStation getByStationID(@Param(value="stationID") String stationID);

	TrueMapStation getByPoint(Map<String, Object> map);

	TrueMapStation getStationByStep(Map<String, Object> params);

	TrueMapStation GetStationOppositeDirection(Map<String, Object> params);

	List<TrueMapStation> GetStationSequence(Map<String, Object> params);

	List<TrueMapStation> getLastGuidByMap(Map<String, Object> map);

	List<TrueMapStation> getPreviousAndNext(@Param(value="stationID") String stationID);

	TrueMapStation searchStationByStationId(@Param(value="stationID") String stationID);

	List<TrueMapStation> getTypeAndVnumByLinkGuid(@Param(value="linkguid") String linkguid);

	List<TrueMapStation> filterByParams(Map<String, Object> params);


}
