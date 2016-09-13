package src.genericCheckpointing.strategy;

import java.lang.reflect.InvocationTargetException;

import src.genericCheckpointing.util.FileProcessor;
import src.genericCheckpointing.util.Logger;
import src.genericCheckpointing.util.SerializableObject;
import src.genericCheckpointing.xmlStoreRestore.SerializeDeserializeImpl;


public class XMLSerializationStrategy implements SerStrategy{

	@Override
	public Object processInput(Object sObject, FileProcessor fileProcessor) {
		SerializeDeserializeImpl serializeDeserializeImpl = new SerializeDeserializeImpl();
		try {
			serializeDeserializeImpl.serializeAndStoreInFile((SerializableObject) sObject, fileProcessor);
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchFieldException e) {
			Logger.writeMessage("Error in writing to file");
			e.printStackTrace();
		}
		return null;
	}


}
