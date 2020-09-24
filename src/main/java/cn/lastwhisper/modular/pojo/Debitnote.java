package cn.lastwhisper.modular.pojo;

public class Debitnote {

	private String debitid;
	
	private String debitname;
	
	private String position;
	
	private String reason;
	
	private String member;

	private String money;
	
	private String check;
	
	private String note;
	
	private String cashier;
	
	private String borrower;

	private String departmentid;

	public String getDebitid() {
		return debitid;
	}

	public void setDebitid(String debitid) {
		this.debitid = debitid;
	}

	public String getDebitname() {
		return debitname;
	}

	public void setDebitname(String debitname) {
		this.debitname = debitname;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	@Override
	public String toString() {
		return "Debitnote{" +
				"debitid='" + debitid + '\'' +
				", debitname='" + debitname + '\'' +
				", position='" + position + '\'' +
				", reason='" + reason + '\'' +
				", member='" + member + '\'' +
				", money='" + money + '\'' +
				", check='" + check + '\'' +
				", note='" + note + '\'' +
				", cashier='" + cashier + '\'' +
				", borrower='" + borrower + '\'' +
				", departmentid='" + departmentid + '\'' +
				'}';
	}
}
