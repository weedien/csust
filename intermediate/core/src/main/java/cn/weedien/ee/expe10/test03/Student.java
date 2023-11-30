package cn.weedien.ee.expe10.test03;

class Student implements Runnable {
    private String name;
    private Canteen canteen;

    public Student(String name, Canteen canteen) {
        this.name = name;
        this.canteen = canteen;
    }

    @Override
    public void run() {
        try {
            canteen.getFood(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
