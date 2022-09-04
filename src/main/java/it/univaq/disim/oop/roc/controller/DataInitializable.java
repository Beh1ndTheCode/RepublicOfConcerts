package it.univaq.disim.oop.roc.controller;

public interface DataInitializable<T> {

	default void initializeData(T t) {

	}
	
	default void initializeBool(Boolean b) {

	}
}
