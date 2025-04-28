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
public class Network {
    private Agent[] agents;
    private int agentNum;
    private double p;
    private Random ran;
    
    public Network(int agentNum, double p){
        this.agents = new Agent[agentNum + 1];
        for(int i = 1; i <= agentNum; i++){
            agents[i] = new Agent(i, 0, agentNum, p);
        }
        this.agentNum = agentNum;
        this.p = p;
        this.ran = new Random();
    }
    
    public void ranPair(){
        for(int i = 1; i <= agentNum; i++){
            for(int j = i + 1; j <= agentNum; j++){
                agents[i].ranPair(j, agents[j]);
            }
        }
    }
    
    public void printAllPairs(){
        for(int i = 1; i <= agentNum; i++){
            Agent agent = agents[i];
            ArrayList<Integer> pairs = agent.getPairs();
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
    
    
    public void writeToFile(String filename){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            for(int i = 1; i <= agentNum; i++){
                Agent agent = agents[i];
                ArrayList<Integer> pairs = agent.getPairs();
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
    
    
}
