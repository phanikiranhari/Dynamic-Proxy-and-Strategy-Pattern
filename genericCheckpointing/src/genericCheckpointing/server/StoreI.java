package src.genericCheckpointing.server;

import src.genericCheckpointing.util.FileProcessor;
import src.genericCheckpointing.util.MyAllTypesFirst;
import src.genericCheckpointing.util.MyAllTypesSecond;

public interface StoreI extends StoreRestoreI {
	void writeObj(MyAllTypesFirst aRecord, String wireFormat, FileProcessor fileName);
	void writeObj(MyAllTypesSecond aRecord, String wireFormat, FileProcessor fileName);
}
