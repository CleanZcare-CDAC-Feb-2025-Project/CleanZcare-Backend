package com.homecleaning.ServiceImmpl;


import com.homecleaning.Entity.Coupon;
import com.homecleaning.Repository.CouponRepository;
import com.homecleaning.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

 @Autowired
 private CouponRepository couponRepository;

 @Override
 public Coupon createCoupon(Coupon coupon) {
     return couponRepository.save(coupon);
 }

 @Override
 public List<Coupon> getAllCoupons() {
     return couponRepository.findAll();
 }

 @Override
 public Coupon getCouponById(Long id) {
     return couponRepository.findById(id).orElse(null);
 }

 @Override
 public void deleteCoupon(Long id) {
     couponRepository.deleteById(id);
 }
}

