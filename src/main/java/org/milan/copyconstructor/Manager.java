package org.milan.copyconstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Milan Rathod
 */
public class Manager extends Employee {

    private final List<Employee> directReports;

    public Manager(int id, String name, Date startDate, List<Employee> directReports) {
        super(id, name, startDate);
        this.directReports = directReports;
    }

    public Manager(Manager manager) {
        super(manager.id, manager.name, manager.startDate);
        this.directReports = new ArrayList<>(manager.directReports);
    }

    @Override
    public Employee copy() {
        return new Manager(this);
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }
}
