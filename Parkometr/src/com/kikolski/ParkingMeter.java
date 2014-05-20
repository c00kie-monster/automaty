package com.kikolski;

import java.util.LinkedList;
import java.util.List;


public class ParkingMeter {
	private List<State> history = new LinkedList<>();;
	
	private State s0 = new State("0"); 
	private State s1 = new State("1");
	private State s2 = new State("2");
	private State s3 = new State("3");
	private State s4 = new State("4");
	private State s5 = new State("5");
	private State s6 = new State("6");
	private State s7 = new State("7", true, false);
	private State es = new State("es", false, true);
	
	private State currentState = s0;
	
	public ParkingMeter() {
		s0.addFollowStates(s1, s2, s5);
		s1.addFollowStates(s2, s3, s6);
		s2.addFollowStates(s3, s4, s7);
		s3.addFollowStates(s4, s5);
		s4.addFollowStates(s5, s6);
		s5.addFollowStates(s6, s7);
		s6.addFollowStates(s7);
		history.add(currentState);
	}
			
	public State getCurrentState() {
		return (currentState != null) ? currentState : es;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public void changeState(String edge) {
		currentState = currentState.moveTo(edge);
		if (currentState != null)
			history.add(currentState);
		else
			history.add(es);
	}

	public void rollback() {
		currentState = s0;
		history.clear();
		history.add(currentState);
	}
	
	@Override
	public String toString() {
		return String.valueOf(currentState.getId());
	}
	
	public String historyToString() {
		StringBuilder builder = new StringBuilder();
		for (State s : history) {
			builder.append(s);
			if (history.indexOf(s) != history.size() - 1)
				builder.append(" -> ");
		}
		return builder.toString();
	}
}