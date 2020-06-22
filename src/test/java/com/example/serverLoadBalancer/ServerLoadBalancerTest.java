package com.example.serverLoadBalancer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServerLoadBalancerTest {
    ServerLoadBalancer serverLoadBalancer;

    @BeforeEach
    public void setUp() {
        serverLoadBalancer = new ServerLoadBalancer();
    }

    @Test
    void shouldKeepServerEmptyWhenNoVms() {
        List<Server> serverList = serverListOfCapacities(1);
        List<Vm> vmList = vmListOfSize();
        serverLoadBalancer.balance(serverList, vmList);
        assertEquals(serverList.get(0).getCurrentLoadPercentage(), 0.d);
    }

    @Test
    void shouldFillServerWithOneSlotWithOneVm() {
        List<Server> serverList = serverListOfCapacities(1);
        List<Vm> vmList = vmListOfSize(1);
        Server server = serverList.get(0);
        serverLoadBalancer.balance(serverList, vmList);
        assertEquals(server.getCurrentLoadPercentage(), 100.d);
        assertTrue(server.contains(vmList.get(0)));
    }

    @Test
    void shouldFillServerIn10PercentWhenServerHas10SlotAndVmIsSize1() {
        List<Server> serverList = serverListOfCapacities(10);
        List<Vm> vmList = vmListOfSize(1);
        Server server = serverList.get(0);
        serverLoadBalancer.balance(serverList, vmList);
        assertEquals(server.getCurrentLoadPercentage(), 10.d);
        assertTrue(server.contains(vmList.get(0)));
    }

    @Test
    void serverShouldBeFilledWhenItsHaveEnoughSapce(){
        List<Server> serverList = serverListOfCapacities(2);
        List<Vm> vmList = vmListOfSize(1,1);
        Server server = serverList.get(0);
        serverLoadBalancer.balance(serverList,vmList);
        assertEquals(server.getCurrentLoadPercentage(), 100.d);
        assertTrue(serverContainsTheaseVms(server,vmList));
    }

    @Test
    void vmShouldBePutInLessLoadedServer(){
        Server firstServer  = server().withcapacity(100).withCurrentLoad(45.d).build();
        Server secondServer = server().withcapacity(100).withCurrentLoad(40.d).build();
        List<Vm> vmList = vmListOfSize(10);
        serverLoadBalancer.balance(List.of(firstServer,secondServer), vmList);
        assertTrue(secondServer.contains(vmList.get(0)));
    }

    private ServerBiudler server() {
        return new ServerBiudler();
    }


    List<Server> serverListOfCapacities(int... capacity) {
        return Arrays.stream(capacity)
                .mapToObj(Server::new)
                .collect(Collectors.toList());
    }

    List<Vm> vmListOfSize(int... size) {
        return Arrays.stream(size)
                .mapToObj(Vm::new)
                .collect(Collectors.toList());
    }

    boolean serverContainsTheaseVms(Server server,List<Vm> vms){
        long numberOfVms = vms.stream()
                .filter(server::contains)
                .count();
        return  numberOfVms == vms.size();
    }

}
