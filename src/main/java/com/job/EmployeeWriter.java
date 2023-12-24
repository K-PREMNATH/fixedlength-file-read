package com.job;

import com.model.Employee;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeWriter  implements ItemWriter<Employee> {
    @Override
    public void write(List<? extends Employee> items) throws Exception {
        for (Employee item : items) {
            System.out.println("Writing item: " + item);
        }
    }
}
