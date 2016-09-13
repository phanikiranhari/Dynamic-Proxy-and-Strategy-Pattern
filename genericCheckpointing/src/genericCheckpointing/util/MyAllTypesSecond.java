package src.genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject{

	private double myDoubleT;
	private short myShortT;
	private float myFloatT;
	private char myCharT;
	
	public double getMyDoubleT() {
		return myDoubleT;
	}
	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}
	public short getMyShortT() {
		return myShortT;
	}
	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
	}
	public float getMyFloatT() {
		return myFloatT;
	}
	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}
	public char getMyCharT() {
		return myCharT;
	}
	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
	}
	@Override
	public boolean equals(SerializableObject second) {
		if (this == second)
			return true;
		if (second == null || this.getClass() != second.getClass()) {
			return false;
		}
		MyAllTypesSecond myAllTypesSecond = (MyAllTypesSecond) second;
		if (this.getMyDoubleT() == myAllTypesSecond.getMyDoubleT()
				&& this.getMyShortT() == myAllTypesSecond.getMyShortT()
				&& this.getMyFloatT() == myAllTypesSecond.getMyFloatT()
				&& this.getMyCharT() == myAllTypesSecond.getMyCharT())
			return true;
		else
			return false;
	}
	@Override
	public void display(){
		Logger.writeMessage(this.toString());
	}
	@Override 
	public String toString() {
		return "{myDoubleT = " + this.getMyDoubleT() + ", myShortT = " + this.getMyShortT()
				+ ", myFloatT = " + this.getMyFloatT() + ", myCharT =  "
				+ getMyCharT() + "}";
	}
	@Override
	public int hashCode() {
		final int primeNumber = 97;
		int result = 1;
		result = primeNumber * result
				+ Double.valueOf(this.getMyDoubleT()).hashCode();
		result = primeNumber * result
				+ Float.valueOf(this.getMyFloatT()).hashCode();
		result = primeNumber * result
				+ Short.valueOf(this.getMyShortT()).hashCode();
		result = primeNumber * result
				+ Character.valueOf(this.getMyCharT()).hashCode();
		return result;
	}
}
