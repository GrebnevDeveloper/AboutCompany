/**
 * Created by Grebnev on 14.04.2017.
 */
package com.developer.grebnev.aboutcompany.utils;

import com.developer.grebnev.aboutcompany.model.Employee;

import java.util.Comparator;

/**
 * Comparator for sorting Employees by name
 */
public class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee employee1, Employee employee2) {
        return employee1.getName().compareTo(employee2.getName());
    }
}
