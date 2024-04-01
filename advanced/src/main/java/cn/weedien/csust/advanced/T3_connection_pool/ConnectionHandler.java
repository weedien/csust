package cn.weedien.csust.advanced.T3_connection_pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;

public class ConnectionHandler implements InvocationHandler {
    private final Connection target;
    private final List<Connection> connectionPool;

    public ConnectionHandler(Connection target, List<Connection> connectionPool) {
        this.target = target;
        this.connectionPool = connectionPool;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果调用的是close方法,则将Connection放回连接池
        if (method.getName().equals("close")) {
            connectionPool.add(target);
            System.out.println("连接被放回池中");
            return null;
        }
        // 其他方法调用,直接转发给目标Connection对象
        return method.invoke(target, args);
    }
}