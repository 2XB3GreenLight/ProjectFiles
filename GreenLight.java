package cas2xb3.greenlight;

import java.util.Scanner;

public class GreenLight {

	public static void main(String[] args) {
		
		// Take the input from the user:
		/*
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Insert x-coordinate of destination: ");
		double xcrd = keyboard.nextDouble();
		System.out.println("Insert y-coordinate of destination: ");
		double ycrd = keyboard.nextDouble();
		*/
		

		Heap.sort(Data.getArray(), 2);
		System.out.println(Data.getArray().length);
		Data.printArray();
		
		Graph g = new Graph(Data.getCollisionCount());
		
		g.addEdge(71804, 80672);
		
		System.out.println(Data.getCollisionCount());
		//System.out.println(g.toString());
		//System.out.println(Data.equation(80681));
	}

}