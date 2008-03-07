package org.mule.extras.seasar2.sender.file;

public class Customer {

	public static final boolean MAN = true;
	
	public final boolean WEMAN = false;
	
	/**  ID */
	public int id;
	
	/** 名前*/
	public String name;
	
	/** 年齢*/
	public int age;
	
	/** 性別*/
	public boolean sex;
	
	/** 住所*/
	public String address;
	
	/** 電話番号*/
	public String phoneNumber;
	
	/** E-mailアドレス*/
	public String eMailAddress;
	
	@Override
	public String toString() {
		String sex = this.sex ? "男" : "女";
		return new StringBuffer().append("ID : ").append(id).append("\n")
							.append("名前 : ").append(name).append("\n")
							.append("年齢 : ").append(age).append("\n")
							.append("性別 : ").append(sex).append("\n")
							.append("住所 : ").append(address).append("\n")
							.append("電話番号 : ").append(phoneNumber).append("\n")
							.append("E-mail : ").append(eMailAddress).append("\n").toString();
	}
	
	
}
