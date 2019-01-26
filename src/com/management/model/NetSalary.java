package com.management.model;

public class NetSalary {

		private int sal_emp_id;
		
//		BasicSalary basic;
//		Allowance allowance;
//		Deductions deduction;

		
		private double basicsal;
		private double hra;
		private double da;
		private double cca;
		private double ta;
		private double med;
//		private int allow_emp_id;
//		private int deduct_emp_id;
		private double pf;
		private double pt;
		private double loan;
		private double ins;


		
		
		public double getBasicsal() {
			return basicsal;
		}
		public void setBasicsal(double basicsal) {
			this.basicsal = basicsal;
		}
		public int getSal_emp_id() {
			return sal_emp_id;
		}
		public void setSal_emp_id(int sal_emp_id) {
			this.sal_emp_id = sal_emp_id;
		}
		
		//
		
		
		
		public double getHra() {
			return hra;
		}
		public void setHra(double hra) {
			this.hra = hra;
		}
		public double getDa() {
			return da;
		}
		public void setDa(double da) {
			this.da = da;
		}
		public double getCca() {
			return cca;
		}
		public void setCca(double cca) {
			this.cca = cca;
		}
		public double getTa() {
			return ta;
		}
		public void setTa(double ta) {
			this.ta = ta;
		}
		public double getMed() {
			return med;
		}
		public void setMed(double med) {
			this.med = med;
		}
//		public int getAllow_emp_id() {
//			return allow_emp_id;
//		}
//		public void setAllow_emp_id(int allow_emp_id) {
//			this.allow_emp_id = allow_emp_id;
//		}
//		
//		
//		
//		
//		
//		
//		public int getDeduct_emp_id() {
//			return deduct_emp_id;
//		}
//		public void setDeduct_emp_id(int deduct_emp_id) {
//			this.deduct_emp_id = deduct_emp_id;
//		}
		public double getPf() {
			return pf;
		}
		public void setPf(double pf) {
			this.pf = pf;
		}
		public double getPt() {
			return pt;
		}
		public void setPt(double pt) {
			this.pt = pt;
		}
		public double getLoan() {
			return loan;
		}
		public void setLoan(double loan) {
			this.loan = loan;
		}
		public double getIns() {
			return ins;
		}
		public void setIns(double ins) {
			this.ins = ins;
		}
		@Override
		public String toString() {
			return "NetSalary [sal_emp_id=" + sal_emp_id + ", basicsal=" + basicsal + ", hra=" + hra + ", da=" + da
					+ ", cca=" + cca + ", ta=" + ta + ", med=" + med + ", pf=" + pf + ", pt=" + pt + ", loan=" + loan
					+ ", ins=" + ins + "]";
		}


//	public BasicSalary getBasic() {
//			return basic;
//		}
//		public void setBasic(BasicSalary basic) {
//			this.basic = basic;
//		}
//		public Allowance getAllowance() {
//			return allowance;
//		}
//		public void setAllowance(Allowance allowance) {
//			this.allowance = allowance;
//		}
//		public Deductions getDeduction() {
//			return deduction;
//		}
//		public void setDeduction(Deductions deduction) {
//			this.deduction = deduction;
//		}
		
		
}
