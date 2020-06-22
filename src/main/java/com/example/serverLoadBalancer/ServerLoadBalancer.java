package com.example.serverLoadBalancer;

import java.util.List;

public class ServerLoadBalancer {
    public void balance(List<Server> serverList, List<Vm> vmList) {
        serverList.get(0).addVm(vmList.get(0));
    }
}
