package cn.weedien.ee.expe03.test01;

public class Factory {
    // 获取当前类的包名
    private static final String PACKAGE_NAME = Factory.class.getPackage().getName() + ".";

    public static Fruit getInstance(String fruitName) throws Exception {
        // 忽略大小写
        fruitName = fruitName.toLowerCase();
        // 首字母大写
        fruitName = fruitName.substring(0, 1).toUpperCase() + fruitName.substring(1);

        Class<? extends Fruit> clazz = Class.forName(PACKAGE_NAME + fruitName)
                .asSubclass(Fruit.class);
        return clazz.getConstructor().newInstance();
    }

    public static void main(String[] args) {
        try {
            Factory.getInstance("apple").eat();
            Factory.getInstance("Banana").eat();
            Factory.getInstance("ORANGE").eat();
            Factory.getInstance("peach").eat();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
