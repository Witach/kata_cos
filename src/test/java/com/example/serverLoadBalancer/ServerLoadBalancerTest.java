package com.example.serverLoadBalancer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerLoadBalancerTest {
    ServerLoadBalancer serverLoadBalancer;

    @BeforeEach
    public  void setUp(){
        serverLoadBalancer = new ServerLoadBalancer();
    }

    @Test
    void shouldKeepServerEmptyWhenNoVms(){
        List<Server> serverList = serverListOfCapacities(1);
        List<Vm> vmList = vmListOfSize();
        serverLoadBalancer.balance(serverList, vmList);
        assertEquals(serverList.get(0).getCurrentLoadPercentage(), 0.d);
    }

    @Test
    void shouldFillServerWithOneSlotWithOneVm(){
        Server server = new Server(1);
        List<Server> serverList = List.of(server);
        List<Vm> vmList = List.of(new Vm(1));
        serverLoadBalancer.balance(serverList,vmList);
        assertEquals(server.getCurrentLoadPercentage(),100.d);
    }

    @Test
    void shouldFillServerIn10PercentWhenServerHas10SlotAndVmIsSize1(){
        Server server = new Server(10);
        List<Server> serverLsit = List.of(server);
        List<Vm> vmList = List.of(new Vm(1));
        serverLoadBalancer.balance(serverLsit,vmList);
        assertEquals(server.getCurrentLoadPercentage(),10.d);
    }

    List<Server> serverListOfCapacities(int... capacity){
        return Arrays.stream(capacity)
                .mapToObj(Server::new)
                .collect(Collectors.toList());
    }

    List<Vm> vmListOfSize(int... size){
        return Arrays.stream(size)
                .mapToObj(Vm::new)
                .collect(Collectors.toList());
    }

}
