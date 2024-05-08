package cn.weedien.csust.advanced.aop;

public class User implements Hello {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String sayHello(String name) {
        return message + " " + name;
    }
}