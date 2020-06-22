package com.example.serverLoadBalancer;

import com.sun.source.tree.BreakTree;

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

    private void initialLoad(Server server){
        if (currentLoad > 0) {
            int expectedLoad = (int) (currentLoad / 100.d * (double) server
                    .getCapacity());
            server.addVm(new Vm(expectedLoad));
        }
    }

    public Server build(){
        Server server = new Server(capacity);
        initialLoad(server);
        return server;
    }
}
