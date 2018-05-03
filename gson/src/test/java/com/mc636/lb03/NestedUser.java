package com.mc636.lb03;

public class NestedUser {
	public String parent;
	public int age;
	static Child child;
	
	NestedUser(String name, int age, boolean isParent) {
	      this.parent = name;
	      this.age = age;
	}
	
	public class Child {
		String name;
		void Cry() {
			//do something
			child = this;
		}
	}
	
}
