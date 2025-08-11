package com.homecleaning.Service;


import com.homecleaning.Entity.Coupon;
import java.util.List;

public interface CouponService {
    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
    Coupon getCouponById(Long id);
    void deleteCoupon(Long id);
}
