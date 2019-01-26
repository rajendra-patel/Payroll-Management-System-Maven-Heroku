package com.management.model;
import java.time.LocalDate;


public class Leave {
	
	private int leave_emp_id;
	private int leave_id;
	private String leave_type;
	private LocalDate leave_from;
	private LocalDate leave_till;
	private int leave_total;
	private int leave_taken;
	private int leave_left;
	
	
	public int getLeave_emp_id() {
		return leave_emp_id;
	}
	public void setLeave_emp_id(int leave_emp_id) {
		this.leave_emp_id = leave_emp_id;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public LocalDate getLeave_from() {
		return leave_from;
	}
	public void setLeave_from(LocalDate leave_from) {
		this.leave_from = leave_from;
	}
	public LocalDate getLeave_till() {
		return leave_till;
	}
	public void setLeave_till(LocalDate leave_till) {
		this.leave_till = leave_till;
	}
	public int getLeave_id() {
		return leave_id;
	}
	public int setLeave_id(int leave_id) {
		this.leave_id = leave_id;
		return leave_id;
	}
	public int getLeave_total() {
		return leave_total;
	}
	public void setLeave_total(int leave_total) {
		this.leave_total = leave_total;
	}
	public int getLeave_taken() {
		return leave_taken;
	}
	public void setLeave_taken(int leave_taken) {
		this.leave_taken = leave_taken;
	}
	public int getLeave_left() {
		return leave_left;
	}
	public void setLeave_left(int leave_left) {
		this.leave_left = leave_left;
	}
	@Override
	public String toString() {
		return "Leave [leave_emp_id=" + leave_emp_id + ", leave_id=" + leave_id + ", leave_type=" + leave_type
				+ ", leave_from=" + leave_from + ", leave_till=" + leave_till + ", leave_total=" + leave_total
				+ ", leave_taken=" + leave_taken + ", leave_left=" + leave_left + "]";
	}
	
}
