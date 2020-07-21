package com.huangxi.service.solo;

import com.huangxi.entity.bo.HeadLine;
import com.huangxi.entity.dto.Result;

import java.util.List;

public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(HeadLine headLine);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int page, int pageSize);
}
