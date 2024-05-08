package cn.weedien.csust.medium.ee.expe05.test02;

public class Main {
    public static void main(String[] args) {
        AspectJTest aspectJTest = new AspectJTest();
        PersonInf person = new PersonSQLImp();
        Dog dog = new GunDog();

        // 使用动态代理为PersonInf和Dog接口添加日志
        PersonInf personProxy = (PersonInf) java.lang.reflect.Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    aspectJTest.before();
                    Object result = method.invoke(person, args1);
                    aspectJTest.after();
                    return result;
                });

        Dog dogProxy = (Dog) java.lang.reflect.Proxy.newProxyInstance(
                dog.getClass().getClassLoader(),
                dog.getClass().getInterfaces(),
                (proxy, method, args12) -> {
                    aspectJTest.before();
                    Object result = method.invoke(dog, args12);
                    aspectJTest.after();
                    return result;
                });

        // 调用PersonInf接口方法
        personProxy.insertData();
        personProxy.deleteData();
        personProxy.saveData();
        personProxy.deleteDataAgain();

        // 调用Dog接口方法
        dogProxy.bark();
        dogProxy.run();
    }
}