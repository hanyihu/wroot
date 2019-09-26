package com.vic.ck.api.personal.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.console.mall.service.CustomerSwapService;
import com.vic.ck.entity.CustomerSwap;
import com.vic.ck.util.GeneratorNoUtils;

@Service
public class SwapService {

	@Resource
	private CustomerSwapService customerSwapService;
	
	@Resource
	private PersonalService personalService;

	/**
	 * 兑换积分商品
	 * @param swap
	 */
	@Transactional
	public void exchange(CustomerSwap swap) {
		String swapno = GeneratorNoUtils.generatorSwapno();
		swap.setSwapno(swapno);
		customerSwapService.insert(swap);
		//减少用户积分
		personalService.addCustomerScoreRecord(swap.getScore()/-1, FinalFiledParams.SCORE_SWAP, swap.getCustomerId());
	}

}
