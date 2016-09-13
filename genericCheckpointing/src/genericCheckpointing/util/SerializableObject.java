package src.genericCheckpointing.util;

import java.util.List;

public class SerializableObject {
	
	private List<SerializableObject> list;

	public List<SerializableObject> getList() {
		return list;
	}

	public void setList(List<SerializableObject> list) {
		this.list = list;
	}
	
	public void display(){ 
		Logger.writeMessage("Call the right display");
	}

	public boolean equals(SerializableObject first) {
		return false;
	}
	public String toString(){
		return null;
	}
	
    public int hashCode() {
		return 0;
	}
}
