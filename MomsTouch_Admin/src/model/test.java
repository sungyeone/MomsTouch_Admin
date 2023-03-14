package model;

import java.util.Comparator;

public class test {

	public static void main(String[] args) {
		Object a = "11000";
		Object b = "2500";
		Comparator<Object> comp = new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				return Integer.compare(Integer.parseInt(String.valueOf(o1)), Integer.parseInt(String.valueOf(o2)));
			}
		};
		System.out.println(comp.compare(a, b));
	}
}
