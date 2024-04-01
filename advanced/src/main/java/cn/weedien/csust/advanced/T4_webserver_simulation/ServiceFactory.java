package cn.weedien.csust.advanced.T4_webserver_simulation;

public class ServiceFactory {
    public static Service getService(String request) {
        if (request.equals("service1")) {
            return new Service1();
        } else if (request.equals("service2")) {
            return new Service2();
        } else {
            return null;
        }
    }
}