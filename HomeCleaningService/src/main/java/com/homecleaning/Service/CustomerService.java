package com.homecleaning.Service;

import java.util.List;

import com.homecleaning.Entity.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    void deleteCustomer(Long id);
    Customer updateCustomer(Long id, Customer customer);
}
