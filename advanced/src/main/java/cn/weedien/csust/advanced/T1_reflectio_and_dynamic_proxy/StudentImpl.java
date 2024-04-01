package cn.weedien.csust.advanced.T1_reflectio_and_dynamic_proxy;

class StudentImpl implements IStudent {
    @Override
    public void doExercise(String courseName) {
        System.out.println("Doing exercise for: " + courseName);
    }
}