package com.example.demo;

import com.github.javafaker.Faker;
import jakarta.persistence.LockModeType;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    Faker faker = new Faker();

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Employee addEmployee(Employee employee) throws Exception {
        Employee employeeSavedToDB = this.employeeRepository.save(employee);
//        List<Employee> employee1 = this.employeeRepository.findAll();
        Thread.sleep(500000);

        Address address = null;
        address.setId(123L);
        address.setAddress(faker.address().cityName());
        address.setEmployee(employee);

        // calling addAddress() method
        // of AddressService class
        this.addressService.addAddress(address);
        return employeeSavedToDB;
    }

    public Employee manualAddEmployee(Employee employee) throws Exception{
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Employee employeeSavedToDB = this.employeeRepository.save(employee);

            Address address = null;
            address.setId(123L);
            address.setAddress(faker.address().cityName());
            address.setEmployee(employee);

            // calling addAddress() method
            // of AddressService class
            this.addressService.addAddress(address);
            platformTransactionManager.commit(status);
            return employeeSavedToDB;
        } catch (Exception e){
            platformTransactionManager.rollback(status);
            throw e;
        }
    }
}
