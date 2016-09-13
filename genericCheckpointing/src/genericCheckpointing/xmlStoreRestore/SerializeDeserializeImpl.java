package src.genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import src.genericCheckpointing.util.FileProcessor;
import src.genericCheckpointing.util.MyAllTypesFirst;
import src.genericCheckpointing.util.MyAllTypesSecond;
import src.genericCheckpointing.util.SerializableObject;
public class SerializeDeserializeImpl {
	private static final String CHAR_LIST =
	        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    private static final int RANDOM_STRING_LENGTH = 10;

	public MyAllTypesFirst generateMyAllTypesFirst() {
		MyAllTypesFirst myAllTypesFirst = new MyAllTypesFirst();

		Random random = new Random();
		myAllTypesFirst.setMyInt(random.nextInt());
		myAllTypesFirst.setMyLong(random.nextLong());
		myAllTypesFirst.setMyBool(random.nextBoolean());
		myAllTypesFirst.setMyString(generateRandomString());

		return myAllTypesFirst;
	}

	public MyAllTypesSecond generateMyAllTypesSecond() {
		MyAllTypesSecond myAllTypesSecond = new MyAllTypesSecond();
		
		Random random = new Random();
		myAllTypesSecond.setMyDoubleT(random.nextDouble());
		myAllTypesSecond.setMyFloatT(random.nextFloat());
		short s = (short) random.nextInt(Short.MAX_VALUE + 1);
		myAllTypesSecond.setMyShortT(s);
		char c = (char) (random.nextInt(26) + 'a');
		myAllTypesSecond.setMyCharT(c);
		
		return myAllTypesSecond;
	}

	public String generateRandomString(){
        
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
        	Random random = new Random();
            int number = random.nextInt(CHAR_LIST.length());
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

	public void serializeAndStoreInFile(SerializableObject object,
			FileProcessor fileProcessor) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException {
		Class<? extends Object> cls = object.getClass();
		Field fieldlist[] = cls.getDeclaredFields();
		fileProcessor.writeLineToFile("<complexType xsi:type="+"\""+cls.getName()+"\">");
		for (int i = 0; i < fieldlist.length; i++) {
			Field fld = fieldlist[i];
			String fieldName = fld.getName();
			String fieldType = fld.getType().getName();
			Method meth = cls.getMethod("get"
					+ Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1));
			//Object[] params = new Object[1];
			Object result = meth.invoke(object);
			writeToFile(fileProcessor, cls, fieldName,fieldType,
					result);
		}

	}
	private void writeToFile(FileProcessor fileProcessor, Class<? extends Object> cls, String name, String type,
			Object object) {
		if ("java.lang.Integer".equalsIgnoreCase(type)){
			fileProcessor.writeLineToFile("<"+name+" "+"xsi:type=\"xsd:int"+"\">"+object.toString()+"</"+name+">");
		}
		else if ("java.lang.String".equalsIgnoreCase(type)){
			fileProcessor.writeLineToFile("<"+name+" "+"xsi:type=\"xsd:string"+"\">"+object.toString()+"</"+name+">");
		}
		else{
			fileProcessor.writeLineToFile("<"+name+" "+"xsi:type=\"xsd:"+type+"\">"+object.toString()+"</"+name+">");
		}
	}
}
