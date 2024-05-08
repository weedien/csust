package cn.weedien.csust.medium.designpattern.prototype;

public abstract class Prototype<T> implements Cloneable {

    /**
     * Object a shallow copy of this object or null if this object is not Cloneable.
     */
    @SuppressWarnings("unchecked")
    public T copy() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}