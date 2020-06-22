package com.example.serverLoadBalancer;

import java.util.List;
import java.util.stream.Collectors;

public class ServerLoadBalancer {
    public void balance(List<Server> serverList, List<Vm> vmList) {
        for (Vm vm : vmList) {
            try {
                putInApropiateServer(serverList,vm);
            } catch (NoApropiateServerException ignored){
            }
        }
    }

    private void putInApropiateServer(List<Server> servers, Vm vm){
        List<Server> serversWithEnoughCapacity = servers.stream()
                .filter(server -> server.canFit(vm))
                .collect(Collectors.toList());

        Server server = getMinServer(serversWithEnoughCapacity);
        server.addVm(vm);
    }

    private Server getMinServer(List<Server> servers) {
        return  servers.stream()
                .min(this::compereCurrentLoadOfServers)
                .orElseThrow(NoApropiateServerException::new);
    }

    private int compereCurrentLoadOfServers(Server first, Server second){
        return Double.compare(first.getCurrentLoadPercentage(),second.getCurrentLoadPercentage());
    }
}
