package src.genericCheckpointing.server;

import src.genericCheckpointing.util.SerializableObject;

public interface RestoreI extends StoreRestoreI {
	SerializableObject readObj(String wireFormat, String fileName);

}
