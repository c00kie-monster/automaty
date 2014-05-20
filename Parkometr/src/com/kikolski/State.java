package com.kikolski;

import java.util.HashMap;
import java.util.Map;

public class State {
	private static final String[] edges = {"1", "2", "5"};
	private String id;
	private Map<String, State> follows;
	
	private boolean isFinal;
	private boolean isUnacceptable;
	
	public State(String state) {
		this(state, false, false);	
	}
	
	public State (String state, boolean isFinal, boolean isUnacceptable) {
		this.id = state;
		this.isFinal = isFinal;
		this.isUnacceptable = isUnacceptable;
		this.follows = new HashMap<>();
	}
	
	public String getId() {
		return id;
	}

	public void addFollowStates(State... states) {
		for (int i = 0; i < states.length; i++)
			follows.put(edges[i], states[i]);
	}
	
	public boolean isFinal() {
		return isFinal;
	}
		
	public boolean isUnacceptable() {
		return isUnacceptable;
	}

	public State moveTo(String edgeValue) {
		return follows.get(edgeValue);
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}