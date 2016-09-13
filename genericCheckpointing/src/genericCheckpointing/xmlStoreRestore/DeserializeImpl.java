package src.genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import src.genericCheckpointing.util.FileProcessor;
import src.genericCheckpointing.util.Logger;
import src.genericCheckpointing.util.SerializableObject;

public class DeserializeImpl {

	List<SerializableObject> serializableObjects = new ArrayList<SerializableObject>();
	SerializableObject serializableObject = null;
	
	public SerializableObject deserializeAndStoreInPojo(Object object)
			throws NoSuchMethodException, SecurityException,
			ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InstantiationException {
		Class<?> cls = null;
		FileProcessor fileProcessor = new FileProcessor(object.toString());
		String currentLine = null;
		String className = null;
		while ((currentLine = fileProcessor.readLineFromFile()) != null) {
			if (!(currentLine.startsWith("<DPSerialization>"))) {
				if (currentLine.trim().startsWith("<complexType")) {
					// Decide which type of class to be created
					String[] splitMainClassType = currentLine.trim().split(
							"\\s+");
					String xsiTypeForClass = splitMainClassType[1];
					className = xsiTypeForClass.substring(
							xsiTypeForClass.lastIndexOf(".") + 1,
							xsiTypeForClass.length() - 2);
					cls = Class.forName("src.genericCheckpointing.util."
							+ className);
					serializableObject = (SerializableObject) cls.newInstance();
				} else {
					if (!(currentLine.trim().startsWith("</complexType>"))) {

						if (currentLine.trim().equalsIgnoreCase(
								"</DPSerialization>")) {
							serializableObjects.add(serializableObject);
							serializableObject = new SerializableObject();
						} else {
							// For type of data members
							String[] splitDataMembers = currentLine.trim()
									.split("\\s+");
							String value;
							value = currentLine.substring(
									currentLine.indexOf(">") + 1,
									currentLine.lastIndexOf("<"));
							String xsiTypeForDataMember = splitDataMembers[1];
							String substringOfxsiTypeForDataMember = xsiTypeForDataMember
									.substring(xsiTypeForDataMember
											.indexOf("\"") + 1,
											xsiTypeForDataMember
													.lastIndexOf("\""));
							String[] splitSubStringOfDataVariables = substringOfxsiTypeForDataMember
									.trim().split(":");
							String dataType = splitSubStringOfDataVariables[1];
							switch (dataType.toUpperCase()) {
								case "INT" :
									deserializeInt(cls, value,
											serializableObject);
									break;
								case "LONG" :
									deserializeLong(cls, value,
											serializableObject);
									break;
								case "STRING" :
									deserializeString(cls, value,
											serializableObject);
									break;
								case "BOOLEAN" :
									deserializeBoolean(cls, value,
											serializableObject);
									break;
								case "DOUBLE" :
									deserializeDouble(cls, value,
											serializableObject);
									break;
								case "FLOAT" :
									deserializeFloat(cls, value,
											serializableObject);
									break;
								case "SHORT" :
									deserializeShort(cls, value,
											serializableObject);
									break;
								case "CHAR" :
									deserializeChar(cls, value,
											serializableObject);
									break;
								default :
									Logger.writeMessage("Default switch case");
							}
						}
					}
				}
			}
		}
		serializableObject.setList(serializableObjects);
		return serializableObject;
	}

	private void deserializeInt(Class<?> cls,
			String value, SerializableObject serializableObject) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object[] params = new Object[1]; 
		params[0] = new Integer(value);
		Method meth = cls.getMethod("setMyInt", int.class);
		meth.invoke(serializableObject, params);
	}
	private void deserializeString(Class<?> cls,
			String value, SerializableObject serializableObject) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object[] params = new Object[1]; 
		params[0] = new String(value);
		Method meth = cls.getMethod("setMyString", String.class);
		meth.invoke(serializableObject, params);
	}
	private void deserializeBoolean(Class<?> cls,
			String value, SerializableObject serializableObject) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object[] params = new Object[1]; 
		params[0] = new Boolean(value);
		Method meth = cls.getMethod("setMyBool", boolean.class);
		meth.invoke(serializableObject, params);
	}
	private void deserializeDouble(Class<?> cls,
			String value, SerializableObject serializableObject) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object[] params = new Object[1]; 
		params[0] = new Double(value);
		Method meth = cls.getMethod("setMyDoubleT", double.class);
		meth.invoke(serializableObject, params);
	}
	private void deserializeShort(Class<?> cls,
			String value, SerializableObject serializableObject) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object[] params = new Object[1]; 
		params[0] = new Short(value);
		Method meth = cls.getMethod("setMyShortT", short.class);
		meth.invoke(serializableObject, params);
	}
	private void deserializeFloat(Class<?> cls,
			String value, SerializableObject serializableObject) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object[] params = new Object[1]; 
		params[0] = new Float(value);
		Method meth = cls.getMethod("setMyFloatT", float.class);
		meth.invoke(serializableObject, params);
	}
	private void deserializeLong(Class<?> cls,
			String value, SerializableObject serializableObject) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object[] params = new Object[1]; 
		params[0] = new Long(value);
		Method meth = cls.getMethod("setMyLong", long.class);
		meth.invoke(serializableObject, params);
	}
	private void deserializeChar(Class<?> cls,
			String value, SerializableObject serializableObject) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object[] params = new Object[1]; 
		params[0] = value.charAt(0);
		Method meth = cls.getMethod("setMyCharT", char.class);
		meth.invoke(serializableObject, params);
	}


}
