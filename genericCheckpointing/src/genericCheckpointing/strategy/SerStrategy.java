package src.genericCheckpointing.strategy;

import src.genericCheckpointing.util.FileProcessor;

public interface SerStrategy {
	Object processInput(Object sObject, FileProcessor fileProcessor);

}