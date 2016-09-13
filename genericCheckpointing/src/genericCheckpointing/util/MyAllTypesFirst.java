package src.genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;
	
	public int getMyInt() {
		return myInt;
	}
	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}
	public long getMyLong() {
		return myLong;
	}
	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}
	public String getMyString() {
		return myString;
	}
	public void setMyString(String myString) {
		this.myString = myString;
	}
	public boolean getMyBool() {
		return myBool;
	}
	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}

	@Override
	public boolean equals(SerializableObject first) {
		if (this == first)
			return true;
		if (first == null || this.getClass() != first.getClass()) {
			return false;
		}
		MyAllTypesFirst myAllTypesFirst = (MyAllTypesFirst) first;
		if (this.getMyBool() == myAllTypesFirst.getMyBool()
				&& this.getMyInt() == myAllTypesFirst.getMyInt()
				&& this.getMyLong() == myAllTypesFirst.getMyLong()
				&& this.getMyString().equalsIgnoreCase(
						myAllTypesFirst.getMyString()))
			return true;
		else
			return false;
	}
	@Override
	public void display(){
		Logger.writeMessage("");
		Logger.writeMessage(this.toString());
	}
	@Override 
	public String toString() {
		return "{myInt = " + this.getMyInt() + ", myLong = " + this.getMyLong()
				+ ", myString = " + this.getMyString() + ", myBool =  "
				+ getMyBool() + "}";
	}


	@Override
	public int hashCode() {
		final int primeNumber = 97;
		int result = 1;
		result = primeNumber * result + this.getMyString().hashCode();
		result = primeNumber * result
				+ Integer.valueOf(this.getMyInt()).hashCode();
		result = primeNumber * result
				+ Long.valueOf(this.getMyLong()).hashCode();
		result = primeNumber * result
				+ Boolean.valueOf(this.getMyBool()).hashCode();
		return result;
	}


}
