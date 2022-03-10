package owner.use;

import owner.jack.Dog;

public class Test {
    public static void main(String[] args) {
        //由于import owner.jack.Dog，现在使用Dog即代表前者
        Dog dog = new Dog();
        System.out.println(dog);//打印dog的hashCode

        //由于不能重复引用同一个类名，声明dog1时需使用完整包名
        owner.rose.Dog dog1 = new owner.rose.Dog();
        System.out.println(dog1);

    }
}
