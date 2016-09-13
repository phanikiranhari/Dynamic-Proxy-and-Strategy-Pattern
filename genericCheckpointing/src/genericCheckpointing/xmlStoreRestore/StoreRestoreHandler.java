package src.genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import src.genericCheckpointing.strategy.SerStrategy;
import src.genericCheckpointing.strategy.XMLDeserializationStrategy;
import src.genericCheckpointing.strategy.XMLSerializationStrategy;
import src.genericCheckpointing.util.FileProcessor;
import src.genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		
		if ("readObj".equalsIgnoreCase(arg1.getName())) {
			if (arg2[0].equals("XML")){
				return serializeData(arg2[1], new XMLDeserializationStrategy());
			}
		} else if ("writeObj".equalsIgnoreCase(arg1.getName())) {
			if (arg2[1].equals("XML")) {
				try {
					FileProcessor fileProcessor = (FileProcessor) arg2[2];
					fileProcessor.writeLineToFile("<DPSerialization>");
					serializeAndDeserializeData((SerializableObject) arg2[0], fileProcessor, new XMLSerializationStrategy());
					//serializeDeserializeImpl.serializeAndStoreInFile(
						//	(SerializableObject) arg2[0], fileProcessor);
					fileProcessor.writeLineToFile("</DPSerialization>");
				} catch (Exception e) {
					System.out.println("Exception in writing to a file");
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public Object serializeData(Object arg2, SerStrategy sStrategy) {
		return sStrategy.processInput(arg2,null);
	}
	public Object serializeAndDeserializeData(SerializableObject sObject, FileProcessor fileProcessor, SerStrategy sStrategy) {
		return sStrategy.processInput(sObject, fileProcessor);
	}
}
