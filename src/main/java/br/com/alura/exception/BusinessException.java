package br.com.alura.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BusinessException extends Exception {

	private List<String> messages = new ArrayList<String>();

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
		this.messages.add(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void addMessage(String... messages) {
		this.messages.addAll(Arrays.asList(messages));
	}

}