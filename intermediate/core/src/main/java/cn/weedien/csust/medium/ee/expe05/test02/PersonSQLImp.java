package cn.weedien.csust.medium.ee.expe05.test02;

class PersonSQLImp implements PersonInf {
    @Override
    public void insertData() {
        System.out.println("数据插入成功！");
    }

    @Override
    public void deleteData() {
        System.out.println("数据删除成功！");
    }

    @Override
    public void saveData() {
        System.out.println("数据保存成功！");
    }

    @Override
    public void deleteDataAgain() {
        System.out.println("数据删除成功！");
    }
}