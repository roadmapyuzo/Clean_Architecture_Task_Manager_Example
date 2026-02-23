package com.me.task.infra;

public interface EventRouter {

    String resolveDestination(String type);

}
