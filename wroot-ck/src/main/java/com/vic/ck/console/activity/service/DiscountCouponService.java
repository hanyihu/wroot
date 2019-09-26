package com.vic.ck.console.activity.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.activity.mapper.PlatformActivityDiscountCouponMapper;
import com.vic.ck.entity.PlatformActivityDiscountcoupon;
import com.vic.wroot.common.tool.RedpacketTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 营销中心----优惠券
 */
@Service
public class DiscountCouponService extends BaseService {

    @Resource
    private PlatformActivityDiscountCouponMapper yhq;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            BigDecimal redPacket = new BigDecimal(RedpacketTools.getFirstRedpacket(300, 100))
                    .divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP);
            System.out.println(redPacket);
        }
    }

    /**
     * 查询列表
     */
    public PageInfo<PlatformActivityDiscountcoupon> list(Lookup lookup) {
        startPage(lookup);
        List<PlatformActivityDiscountcoupon> datas = yhq.list(lookup);
        return PageInfo.instance(datas, lookup);
    }

    /**
     * 根据主键查询
     */
    public PlatformActivityDiscountcoupon findById(int id) {
        return yhq.findById(id);
    }

    /**
     * 新增
     */
    @Transactional
    public int insert(PlatformActivityDiscountcoupon entity) {
        int result = yhq.insert(entity);
        // 根据优惠券所属商家不同 对应新增到活动关联商家表
        if (entity.getApply() != null && entity.getApply() == 2) {
            for (int i = 0; i < entity.getMerchantid().size(); i++) {
                yhq.inserts(entity.getId(), entity.getMerchantid().get(i));
            }
        }
        return result;
    }

    /**
     * 修改
     */
    @Transactional
    public int update(PlatformActivityDiscountcoupon entity) {
        int update = yhq.update(entity);
        // 删除优惠券对应商家表
        yhq.deletemerchantId(new int[entity.getId()]);
        // 循环添加 新优惠券对应商家表
        for (int i = 0; i < entity.getMerchantid().size(); i++) {
            yhq.inserts(entity.getId(), entity.getMerchantid().get(i));
        }
        return update;
    }

    /***
     * 删除
     */
    @Transactional
    public int delete(int... ids) {
        int delete = yhq.delete(ids);
        // 删除优惠券对应商家表
        yhq.deletemerchantId(ids);
        return delete;
    }

    // 查询领取优惠券的用户
    public List<PlatformActivityDiscountcoupon> listuse(int id) {
        // activity_id
        List<PlatformActivityDiscountcoupon> datas = yhq.listuse(id);
        return datas;
    }

    // 用户 领取优惠券：查询优惠券
    public PlatformActivityDiscountcoupon discountCouponById(int id) {
        return yhq.discountCouponById(id);
    }

    // 查询优惠券 是否属于当前商家
    public int discountCouponByMerchantId(int id, int merchantid) {
        return yhq.discountCouponByMerchantId(id, merchantid);
    }

}
