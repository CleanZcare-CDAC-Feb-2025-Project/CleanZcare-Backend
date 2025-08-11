package com.homecleaning.Controllers;


import com.homecleaning.Entity.Coupon;
import com.homecleaning.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
@CrossOrigin(origins = "http://localhost:5173") // allow frontend
public class CouponController {

 @Autowired
 private CouponService couponService;

 @PostMapping
 public Coupon createCoupon(@RequestBody Coupon coupon) {
     return couponService.createCoupon(coupon);
 }

 @GetMapping
 public List<Coupon> getAllCoupons() {
     return couponService.getAllCoupons();
 }

 @GetMapping("/{id}")
 public Coupon getCouponById(@PathVariable Long id) {
     return couponService.getCouponById(id);
 }

 @DeleteMapping("/{id}")
 public void deleteCoupon(@PathVariable Long id) {
     couponService.deleteCoupon(id);
 }
}
