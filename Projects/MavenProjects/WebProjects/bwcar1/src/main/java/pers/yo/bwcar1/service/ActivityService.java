package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.ActivityDTO;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;

public interface ActivityService {

    public abstract int addActivity(ActivityDTO activityDTO);
    public abstract int delActivity(Long id);
    public abstract int updateActivity(ActivityDTO activityDTO);
    public abstract ActivityDTO findById( Long id );
    public abstract DataGridResult findByPage(QueryDTO queryDTO);

}
