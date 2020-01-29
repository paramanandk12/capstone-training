package com.mindtree.practice;

public class Employee implements Comparable {

	private int eid;
	private String ename;
	private String dept;
	private double sal;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int eid, String ename, String dept, double sal) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.dept = dept;
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "EmployeeList [eid=" + eid + ", ename=" + ename + ", dept=" + dept + ", sal=" + sal + "]";
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	@Override
	public int compareTo(Object arg0) {
		
		return 0;
	}

}
