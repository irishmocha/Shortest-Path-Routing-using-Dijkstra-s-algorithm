package contents;

import java.io.*;
import java.util.*;


public class LinkState {
	
	static int[][] routingTable;
	
	static int dimension;
	static int srcRouterNum;
	static int destRouterNum;
	
	static int[] found;
	static int[] distance;
	static int[] via;
	
	static int[] vec;
	
	public static void CallMenu() {
		System.out.println("CS542 Link State Routing Simulator\n");
		System.out.println("(1) Create a Network Topology");
		System.out.println("(2) Build a Connection Table");
		System.out.println("(3) Shortest Path to Destination Router");
		System.out.println("(4) Modify a topology");
		System.out.println("(5) Exit\n");
		System.out.println("Command:");
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// new MainFrame();
		
		Scanner selector = null;
		int selectNum = 0;
		String inputFileName = null;
		
		try{
			selector = new Scanner(System.in);
		
			System.out.println("start");
			
			while(true) {
				CallMenu();
				selectNum = selector.nextInt();
				switch(selectNum) {
				case 1:
					System.out.println("Input original network topology matix data file:");
					System.out.print("Input:");
					
					inputFileName = selector.nextLine();
					
					Scanner scaner = new Scanner(new BufferedReader(new FileReader("c:\\test.txt")));
					
					boolean isLengthChecked = false;
					
					for(int i = 0; scaner.hasNext(); i++) {
						
						String[] temp = scaner.nextLine().split(" ");
						int[] temp2 = new int[temp.length];
						
						for(int k = 0; k < temp.length; k++) {
							temp2[k] = Integer.parseInt(temp[k]);
						}
						
						if(isLengthChecked == false) {
							System.out.println("length of temp is" + temp.length);
							dimension = temp.length;
							routingTable = new int[temp.length][temp.length];
							isLengthChecked = true;
						}
						routingTable[i] = temp2;
					}
					
					for(int[] forPrint : routingTable) {
						for(int s : forPrint) {
							System.out.print(s + "\t");
						}
						System.out.println();
					}
					System.out.println("Review original topology matrix");
					break;
				
				case 2:
					System.out.println("Select a source router");
					System.out.print("Source router:");
					
					srcRouterNum = selector.nextInt();
					
					System.out.println("Router" + " " + srcRouterNum + " " + "Connection Table");
					System.out.println("Destination  Interface");
					System.out.println("======================");
					
					for(int i = 0; i < dimension; i++) {
						if(srcRouterNum == i+1) {
							System.out.println(i+1 + "\t\t" + "-");
						} else {
							System.out.println(i+1 + "\t\t" + routingTable[srcRouterNum-1][i]);
						}
					}
					break;
					
				case 3:
					vec = new int[dimension];
					
					System.out.println("Select the destination router:");
					System.out.print("Destination router?");
					
					destRouterNum = selector.nextInt();
					
					found = new int[dimension];
					distance = new int[dimension];
					
					int k = 0;
					
					for(int i = 0; i < dimension; i++) {
						vec[i] = 0;
						distance[i] = -1;
					}
					
					distance[srcRouterNum - 1] = 0;
					
					for (int i = 0; i < dimension; i++)
					{
						int min = -1;
						for (int j = 0; j < dimension; j++)
						{
							if (vec[j] == 0 && distance[j] < min)   {
								k = j;
								min = distance[j];
							}
						}
						vec[k] = 1;
						if (min == -1) break;
						for (int j = 0; j<dimension; j++)
						{
							if (distance[j] > distance[k] + routingTable[k][j])
							{
								distance[j] = distance[k] + routingTable[k][j];
								via[j] = k;
							}
						}
					}
					
					break;
				case 4:
					break;
				case 5:
					System.out.println("Exit CS542 project.  Good Bye!");
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}