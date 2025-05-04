/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalprojectparti;
import java.util.*;
import java.io.*;

/**
 *
 * @author chenhenr
 */
// Represents a network consisting of multiple agents.
// Supports generation of example, random, and grid-based networks.
public class Network {
    private Agent[] agents;
    private int agentNum;
    private double p;
    private Random ran;
    private int gridRow;
// Constructs a new network of agents.
    public Network(int agentNum, double p){
        this.agents = new Agent[agentNum + 1];
        for(int i = 1; i <= agentNum; i++){
            agents[i] = new Agent(i, 0, agentNum, p);
        }
        this.agentNum = agentNum;
        this.p = p;
        this.ran = new Random();
        gridRow = 0;
    }
// Randomly pairs agents with each other using the specified probability.
    public void ranPair(){
        for(int i = 1; i <= agentNum; i++){
            for(int j = 1; j <= agentNum; j++){
                agents[i].ranPair(j, agents[j]);
            }
        }
    }
// Prints all valid agent pairs to the console.
    public void printAllPairs(){
        for(int i = 1; i <= agentNum; i++){
            Agent agent = agents[i];
            PriorityQueue<Integer> pairs = agent.getPairs();
            if(pairs.isEmpty()){
                System.out.println(agent.getIndex() + " -1");
            }
            else{
                for(int pairedIndex: pairs){
                    int index = agent.getIndex();
                    if(index < pairedIndex){
                        System.out.println(index + " " + pairedIndex);
                    }
                }
            }
        }
    }
    
// Writes all agent pairs to a text file.
    public void writeToFile(String filename){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            for(int i = 1; i <= agentNum; i++){
                Agent agent = agents[i];
                PriorityQueue<Integer> pairs = agent.getPairs();
                if(pairs.isEmpty()){
                    writer.println(agent.getIndex() + " -1");
                }
                else{
                    for(int pairedIndex : pairs){
                        int index = agent.getIndex();
                        if(index < pairedIndex){
                            writer.println(index + " " + pairedIndex);
                        }
                    }
                }
            }
            writer.close();
            System.out.println("Successfully write in!");
        }
        catch(IOException e){
            System.out.println("Failure in write in!" + e.getMessage());
        }
    }
// Determines the number of rows required for constructing a 2D lattice based on the total number of agents.
    public void makeGrid(){
        int remainder = agentNum % 3;
        if(remainder == 0){
            gridRow = agentNum / 3;
        }
        else if(remainder == 1){
            gridRow = agentNum / 3;
        }
        else{
            gridRow = (agentNum / 3) + 1;
        }
    }
    
    public int coorToInt(int[] coor){
        return (coor[0] - 1)*3 + coor[1];
    }
    public int[] intToCoor(int i){
        int[] coor = new int[2];
        coor[0] = ((i - 1) / 3) + 1; //Row
        coor[1] = (i - 1) % 3 + 1; //Col
        return coor;
    }
    
    public int rowIncrement(int row){
        int newRow = row + 1;
        if(newRow > gridRow){
            newRow = newRow % gridRow;
            return newRow;
        }
        else{
            return newRow;
        }
    }
    public int rowDecrement(int row){
        int newRow = row - 1;
        if(newRow < 1){
            newRow = newRow + gridRow;
            return newRow;
        }
        else{
            return newRow;
        }
    }
    public int colIncrement(int col){
        int newCol = col + 1;
        if(newCol > 3){
            newCol = newCol % 3;
            return newCol;
        }
        else{
            return newCol;
        }
    }
    public int colDecrement(int col){
        int newCol = col - 1;
        if(newCol < 1){
            newCol = newCol + 3;
            return newCol;
        }
        else{
            return newCol;
        }
    }
    
    
    
    public void gridPair(){
        makeGrid();
      //  System.out.println("Row is " + gridRow);
        int[] coor0 = new int[2];
        int[] coor1 = new int[2]; //Upgrid
        int[] coor2 = new int[2]; //Rightgrid
        int[] coor3 = new int[2]; //Downgrid
        int[] coor4 = new int[2]; //Leftgrid
        int iUp, iRight, iDown, iLeft;
        for(int i = 1; i <= 3*gridRow; i++){
            coor0 = intToCoor(i);
            
            coor1[0] = coor0[0];
            coor1[1] = colDecrement(coor0[1]);
            iLeft = coorToInt(coor1);
            
            coor2[0] = rowIncrement(coor0[0]);
            coor2[1] = coor0[1];
            iDown = coorToInt(coor2);
            
            coor3[0] = coor0[0];
            coor3[1] = colIncrement(coor0[1]);
            iRight = coorToInt(coor3);
            
            coor4[0] = rowDecrement(coor0[0]);
            coor4[1] = coor0[1];
            iUp = coorToInt(coor4);
            
            agents[i].unilateralPair(iUp);
            agents[i].unilateralPair(iRight);
            agents[i].unilateralPair(iDown);
            agents[i].unilateralPair(iLeft);
        }
    }
    
    public Agent getAgent(int i) {
        return agents[i];
    }
}
