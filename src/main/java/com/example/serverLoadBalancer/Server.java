package com.example.serverLoadBalancer;

import java.util.LinkedList;
import java.util.List;

public class Server {
    List<Vm> vmList = new LinkedList<>();
    private double currentLoadPercentage = 0.d;
    private int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public Server(int capacity, double currentLoad) {
        this.capacity = capacity;
        this.currentLoadPercentage = currentLoad;
    }


    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }

    public void addVm(Vm vm) {
        vmList.add(vm);
        calculateLoadPercentage();
    }

    private void calculateLoadPercentage(){
        currentLoadPercentage = (double) sumVmSizes() / (double) capacity * 100.d;
    }

    private int sumVmSizes(){
        return  vmList.stream()
                .mapToInt(Vm::getSize)
                .sum();
    }

    public boolean contains(Vm vm) {
        return vmList.contains(vm);
    }
}
