/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalprojectparti;

/**
 *
 * @author chenhenr
 */
public class FinalProjectPartI {

    public static void main(String[] args) {
        int N = 12;
        double p = 1;
        
        Network network = new Network(N, p);
        network.ranPair();
        
        network.printAllPairs();
        network.writeToFile("Output.txt");
        
    }
}
