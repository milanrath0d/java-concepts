package org.milan.copyconstructor;

import java.util.Date;

/**
 * Example of copy constructor
 * 1. Copy constructor is much easier to implement. we do not need to
 * implement the Cloneable interface and handle CloneNotSupportedException.
 * 2. The clone method returns a general object reference. we need to
 * typecast it to the appropriate type.
 * 3. We can not assign a value to a final field in the clone method.
 * However we can do so in the copy constructor.
 *
 * @author Milan Rathod
 */
public class Employee {

    protected int id;

    protected String name;

    protected Date startDate;

    public Employee(int id, String name, Date startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
        this.startDate = new Date(employee.startDate.getTime());
    }

    public Date getStartDate() {
        return startDate;
    }

    public Employee copy() {
        return new Employee(this);
    }
}
