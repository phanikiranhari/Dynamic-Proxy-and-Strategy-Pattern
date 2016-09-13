package src.genericCheckpointing.driver;

import java.util.ArrayList;
import java.util.List;

import src.genericCheckpointing.server.RestoreI;
import src.genericCheckpointing.server.StoreI;
import src.genericCheckpointing.server.StoreRestoreI;
import src.genericCheckpointing.util.FileProcessor;
import src.genericCheckpointing.util.Logger;
import src.genericCheckpointing.util.MyAllTypesFirst;
import src.genericCheckpointing.util.MyAllTypesSecond;
import src.genericCheckpointing.util.ProxyCreator;
import src.genericCheckpointing.util.SerializableObject;
import src.genericCheckpointing.xmlStoreRestore.SerializeDeserializeImpl;
import src.genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {
	private static List<MyAllTypesFirst> myAllTypesFirstsList = new ArrayList<MyAllTypesFirst>();
	private static List<MyAllTypesSecond> myAllTypesSecondsList = new ArrayList<MyAllTypesSecond>();
	private static List<SerializableObject> initialList = new ArrayList<SerializableObject>();
	private static List<SerializableObject> finalList = new ArrayList<SerializableObject>();

	public static void main(String[] args) {
		int noOfObjects = 0;
		if (args.length == 3) {
			String mode = args[0];
			try {
				noOfObjects = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				Logger.writeMessage("N can only be integer");
				System.exit(0);
			}
			String fileName = args[2];
			ProxyCreator pc = new ProxyCreator();
			StoreRestoreHandler storeRestoreHandler = new StoreRestoreHandler();

			StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
					new Class[]{StoreI.class, RestoreI.class},
					storeRestoreHandler);
			SerializableObject myRecordRet;
			if ("deser".equalsIgnoreCase(mode)) {
				myRecordRet = ((RestoreI) cpointRef).readObj("XML", fileName);
				initialList = myRecordRet.getList();
				for (SerializableObject object : initialList) {
					object.display();
				}
			} else if ("serdeser".equalsIgnoreCase(mode)) {
				SerializeDeserializeImpl serializeDeserializeImpl = new SerializeDeserializeImpl();
				// Logic for serialize and deserialize
				for (int i = 0; i < noOfObjects; i++) {
					// Prepare 5 instances of First and Second pojos
					MyAllTypesFirst myFirst = serializeDeserializeImpl
							.generateMyAllTypesFirst();
					MyAllTypesSecond mySecond = serializeDeserializeImpl
							.generateMyAllTypesSecond();
					myAllTypesFirstsList.add(myFirst);
					myAllTypesSecondsList.add(mySecond);
					initialList.add(myFirst);
					initialList.add(mySecond);

				}
				FileProcessor fileProcessor = new FileProcessor(fileName, true);
				for (int i = 0; i < noOfObjects; i++) {
					((StoreI) cpointRef).writeObj(myAllTypesFirstsList.get(i),
							"XML", fileProcessor);
					((StoreI) cpointRef).writeObj(myAllTypesSecondsList.get(i),
							"XML", fileProcessor);
				}
				fileProcessor.closeBufferWriter();
				myRecordRet = ((RestoreI) cpointRef).readObj("XML", fileName);
				finalList = myRecordRet.getList();

				int counter = 0;
				String message = null;
				for (int i = 0; i < initialList.size(); i++) {
					if (initialList.get(i).equals(finalList.get(i))) {
						message = "Check by Equals: The 2 vectors are equal and there are no mismatches";
					} else {
						counter = counter + 1;
					}
				}
				if (counter > 0) {
					Logger.writeMessage("There are total " + counter
							+ " mismatches between 2 vectors");
				} else {
					Logger.writeMessage(message);
				}

				int hashCounter = 0;
				String hashMessage = null;
				for (int i = 0; i < initialList.size(); i++) {
					if (initialList.get(i).hashCode() == (finalList.get(i))
							.hashCode()) {
						hashMessage = "Check by HashCode: The 2 vectors are equal and there are no mismatches";
					} else {
						hashCounter = hashCounter + 1;
					}

				}
				if (hashCounter > 0) {
					Logger.writeMessage("There are total " + hashCounter
							+ " mismatches between 2 vectors");
				} else {
					Logger.writeMessage(hashMessage);
				}
			}
		} else {
			Logger.writeMessage("Incorrect command line arguments");
			System.exit(0);
		}
	}
}