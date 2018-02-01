package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expenses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String date;
	private String amount;
	private String vat;
	private String reason;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}


}
