package src.genericCheckpointing.strategy;

import java.lang.reflect.InvocationTargetException;

import src.genericCheckpointing.util.FileProcessor;
import src.genericCheckpointing.util.Logger;
import src.genericCheckpointing.xmlStoreRestore.DeserializeImpl;

public class XMLDeserializationStrategy implements SerStrategy{

	@Override
	public Object processInput(Object sObject, FileProcessor fileProcessor) {
		DeserializeImpl deserializeImpl = new DeserializeImpl();
		try {
			return deserializeImpl.deserializeAndStoreInPojo(sObject);
		} catch (NoSuchMethodException | SecurityException
				| ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			Logger.writeMessage("Exception in processInput");
			e.printStackTrace();
			return null;
		}
	}


}
