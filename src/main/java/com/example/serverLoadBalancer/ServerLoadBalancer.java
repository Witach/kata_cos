package com.example.serverLoadBalancer;

import java.util.List;

public class ServerLoadBalancer {
    public void balance(List<Server> serverList, List<Vm> vmList) {
            vmList.forEach(
                    vm -> putInApropiateServer(serverList,vm)
            );
    }

    private void putInApropiateServer(List<Server> servers, Vm vm){
        Server server = getApropiateServer(servers);
        server.addVm(vm);
    }

    private Server getApropiateServer(List<Server> servers){
        return  servers.stream()
                .findFirst()
                .orElseThrow();
    }
}
