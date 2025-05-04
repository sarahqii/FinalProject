/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalprojectparti;
import java.util.*;
/**
 *
 * @author chenhenr
 */
/* Main driver class for the Network Generator program.
   Allows user to choose between three network generation modes:
   - A predefined example network
   - A randomly generated network
   - A regular 2D4N lattice network */
public class FinalProjectPartI {
// Main method to run the program and prompt user interaction.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Network Generator");
        System.out.println("Please select a mode");
        System.out.println("1. Network 1(Example)");
        System.out.println("2. Random Network");
        System.out.println("3. 2D4N Regular Lattice");
        
        int mode = in.nextInt();
        
        while(mode != 1&&mode != 2&&mode != 3) {
            System.out.println("Invalid number! Please enter another number: ");
            mode = in.nextInt();
        }
        
        if(mode == 1){
            System.out.println("Generating Network 1...");
            Network network = new Network(12, 1);
            Agent agent;
            agent = network.getAgent(1);
            agent.unilateralPair(4);
            agent.unilateralPair(7);
            agent.unilateralPair(10);
            
            agent = network.getAgent(2);
            agent.unilateralPair(7);
            agent.unilateralPair(9);
            agent.unilateralPair(11);       
            
            agent = network.getAgent(4);
            agent.unilateralPair(1);
            agent.unilateralPair(5);

            agent = network.getAgent(5);
            agent.unilateralPair(4);
            
            agent = network.getAgent(7);
            agent.unilateralPair(1);
            agent.unilateralPair(2);
            
            agent = network.getAgent(8);
            agent.unilateralPair(10);
            
            agent = network.getAgent(9);
            agent.unilateralPair(10);
            agent.unilateralPair(12);
            
            agent = network.getAgent(10);
            agent.unilateralPair(1);
            agent.unilateralPair(8);
            agent.unilateralPair(9);
            
            agent = network.getAgent(11);
            agent.unilateralPair(2);
            
            agent = network.getAgent(12);
            agent.unilateralPair(9);
            
            network.printAllPairs();
            network.writeToFile("output.txt");

        }
        else if(mode == 2){
            System.out.println("Enter number of agents in the range of [10, 1000]");
            int N = in.nextInt();
            while (N < 10||N > 1000) {
                System.out.println("Invalid number! Please enter another number!");
                N = in.nextInt();
            }
            System.out.println("Enter the probability in the range of [1/N, 1]");
            double p = in.nextDouble();
            while (p < 1.0/N || p > 1.0) {          
                System.out.println("Invalid number! Please enter another number!");
                p = in.nextDouble();
            }
            Network network = new Network(N, p);
            network.ranPair();
            network.printAllPairs();
            network.writeToFile("output.txt");
        }
        else if(mode == 3){
            System.out.println("Enter number of agents in the range of [9, 1000]");
            int N = in.nextInt();
            while (N < 9 || N > 1000) {
                System.out.println("Invalid number! Please enter another number!");
                N = in.nextInt();
            }
            N = adjustAgentNum(N);
            Network network = new Network(N, 1.0);
            network.gridPair();
            network.printAllPairs();
            network.writeToFile("output.txt");
        
        }
        
        
    }
// Adjusts the number of agents to be a multiple of 3 for lattice construction.
    public static int adjustAgentNum(int agentNum){
        int row = 0;
        int remainder = agentNum % 3;
        if(remainder == 0){
            row = agentNum / 3;
        }
        else if(remainder == 1){
            row = agentNum / 3;
        }
        else{
            row = (agentNum / 3) + 1;
        }
        return row * 3;
    }
}
