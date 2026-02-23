package com.me.task.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutBoxInMemoryRepository  {

    private final List<OutboxMessage> table = Collections.synchronizedList(new ArrayList<>());

    public void save(OutboxMessage message) {
        table.add(message);
        System.out.println("Saved OutboxMessage: " + message.getType() + " id=" + message.getId());
    }

    public List<OutboxMessage> findPending() {
        List<OutboxMessage> pending = new ArrayList<>();
        for (OutboxMessage msg : table) {
            if (!msg.isPublished()) {
                pending.add(msg);
            }
        }
        return pending;
    }

    public void clear() {
        table.clear();
    }
}
