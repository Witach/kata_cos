package com.example.serverLoadBalancer;

public class ServerBiudler {

    double currentLoad = 0;
    int capacity;

    public ServerBiudler withcapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public ServerBiudler withCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
        return this;
    }

    public Server build(){
        return  new Server(capacity, currentLoad);
    }
}
