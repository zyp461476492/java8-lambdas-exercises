package github.zyp.chapter4;

import java.util.function.Consumer;

/**
 * <h1></h1>
 */
public class Chapter4 {

    public static void main(String[] args) {
        Chapter4 chapter4 = new Chapter4();
        chapter4.example1();
    }
    /**
     * <h2>重载解析</h2>
     * javac 会挑出最具体的类型。
     * Lambda 表达式作为参数时，其类型由它的目标类型推导得出
     * 遵循如下规则
     *  *如果只有一个可能的目标类型，由相应函数接口里的参数类型推导得出；
     *  *如果有多个可能的目标类型，由最具体的类型推导得出；
     *  *如果有多个可能的目标类型且最具体的类型不明确，则需人为指定类型。
     */
    private void example1() {
        overloadedMethod("abc");
    }

    private void overloadedMethod(Object o) {
        System.out.print("Object");
    }
    private void overloadedMethod(String s) {
        System.out.print("String");
    }

    /**
     * <h2>@FunctionalInterface 注释</h2>
     * 事实上，每个用作函数接口的接口都应该添加这个注释。
     */
    @FunctionalInterface
    public interface FunctionDemo {
        boolean test();
    }

    /**
     * <h2>默认方法</h2>
     */
    public interface Example3<T> {
        void message(String body);
        default void welcome() {
            message("Parent: Hi!");
        }
        String getLastMessage();
    }
}
