package com.management.model;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Manager extends Employee{
	private HashMap<Integer, Employee> reportingEmployees = new HashMap<>();
	public Manager() {
		super();
	}

	public HashMap<Integer, Employee> getReportingEmployees() {
		return reportingEmployees;
	}
	public void setReportingEmployees(HashMap<Integer, Employee> ReportingEmployees) {
		this.reportingEmployees = ReportingEmployees;
	}
	public void setReportingEmployee(Employee reportingEmployee) {
        if (reportingEmployee.getManager() != 0) {
//        	reportingEmployee.getManager().getReportingEmployees().remove(reportingEmployee.getEmp_id());
        }
        reportingEmployee.setManager(this.getEmp_id());
        reportingEmployees.put(reportingEmployee.getEmp_id(),reportingEmployee);
    }
    public void removeReportingEmployee(Employee reportingEmployee) {
    	reportingEmployee.setManager(0);
        reportingEmployees.remove(reportingEmployee.getEmp_id());
    }
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Manager [emp_id=").append(emp_id).append(", dept_id=").append(dept_id).append(", manager=")
		.append(manager).append(", first_name=").append(first_name).append(", last_name=").append(last_name)
		.append(", dob=").append(dob).append(", gender=").append(gender).append(", country=").append(country)
		.append(", state=").append(state).append(", city=").append(city).append(", address=").append(address)
		.append(", pincode=").append(pincode).append(", email=").append(email).append(", mobile_no=")
		.append(mobile_no).append(", doj=").append(doj).append(", reportingEmployees=")
		.append(reportingEmployees.entrySet().toString()).append(", manager=").append(manager)
		.append("]");
		return builder.toString();
	}
    
}
