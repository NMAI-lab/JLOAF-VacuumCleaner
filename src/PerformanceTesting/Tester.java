package PerformanceTesting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {
	//quick reference to the options

	
	public enum TestType {
		FixedSequenceAgent, MultipleSequenceAgent, WallFollowerAgent, ZigZagAgent;
		public String getFolder() {
			System.out.print(this);
			if (this.equals(TestType.FixedSequenceAgent)) {
				return "hi";
						
			}
			return null;
		}
	}
	public static void main(String args[]) {
		System.out.print(TestType.FixedSequenceAgent.getFolder());
	}
}
