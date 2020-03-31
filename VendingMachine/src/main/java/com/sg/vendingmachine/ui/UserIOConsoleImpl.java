/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.util.Scanner;

/**
 *
 * @author Jose
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int value = -1;
        do {
            print(prompt);
            value = Integer.parseInt(sc.nextLine());
            if (value < min || value > max) {
                print("Error value outside of range!!!");
            }
            
        } while (value < min || value > max);
        return value;
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double value = -1.0;
        do {
            print(prompt);
            value = Double.parseDouble(sc.nextLine());
            if (value < min || value > max) {
                print("Error value outside of range!!!");
            }
            
        } while (value < min || value > max);
        return value;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        return Float.parseFloat(sc.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float value = -1;
        do {
            print(prompt);
            value = Float.parseFloat(sc.nextLine());
            if (value < min || value > max) {
                System.out.println("Error value outside of range!!!");
            }
           
        } while (value < min || value > max);
         return value;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long value = -1;
        do {
            print(prompt);
            value = Long.parseLong(sc.nextLine());
            if (value < min || value > max) {
                System.out.println("Error value outside of range!!!");
            }
            
        } while (value < min || value > max);
        return value;
    }
}
