package com.vic.ck.console.examine.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.examine.mapper.ExamineMapper;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.RiderExamine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 开通骑手提交信息审核
 * @author hanyihu
 * @date 2019/4/23 11:02
 */
@Service
public class ExamineService extends BaseService {

	@Resource
	private ExamineMapper examineMapper;
	

	/**
	 * 查询列表
	 */
    public PageInfo<RiderExamine> list(Lookup lookup) {
		startPage(lookup);

        List<RiderExamine> datas = examineMapper.list(lookup);

		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
    public RiderExamine findById(int id) {
		return examineMapper.findById(id);
	}

    /*根据骑手id查询*/
    public RiderExamine findByRiderId(Integer RiderId) {
        return examineMapper.findByRiderId(RiderId);
    }


	/*保存审核说明*/
    public int update(Customer entity) {
        return examineMapper.update(entity);
    }

    /*提交成功时保存审核状态为成功*/
    public int updateExamine(RiderExamine entity) {
        return examineMapper.updateExamine(entity);
    }

    /*修改提交骑手临时信息*/
    public int updateRiderExamine(RiderExamine entity) {
        return examineMapper.updateRiderExamine(entity);
    }

}
