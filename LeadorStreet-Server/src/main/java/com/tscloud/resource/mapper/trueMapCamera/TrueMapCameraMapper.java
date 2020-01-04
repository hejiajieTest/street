package com.tscloud.resource.mapper.trueMapCamera;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tscloud.common.framework.mapper.BaseInterfaceMapper;
import com.tscloud.domain.resource.entity.trueMapCamera.TrueMapCamera;

@Repository
public interface TrueMapCameraMapper extends BaseInterfaceMapper<TrueMapCamera> {

	TrueMapCamera SearchCameraByMap(Map<String, Object> map);

}
