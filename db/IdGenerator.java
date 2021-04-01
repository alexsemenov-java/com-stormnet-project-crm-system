package com.stormnet.crm.system.db;

public class IdGenerator {

    private static final IdGenerator generator = new IdGenerator();

    public static IdGenerator getGenerator() {
        return generator;
    }

    private int nextId = 0;

    private IdGenerator() {
    }

    public Integer nextId() {
        nextId++;
        return nextId;
    }
}

