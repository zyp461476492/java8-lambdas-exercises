package github.zyp.chapter4;

import github.zyp.common.Album;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * <h1></h1>
 * java8 中引入了默认方法和接口的静态方法
 */
public class Chapter4 {

    public static void main(String[] args) {
//        printTrackLengthStatistics(new Album());
    }

    /**
     * <h2>基本类型</h2>
     * 为了减少装箱拆箱的性能开销，Java中，仅仅对整形，长整形，双浮点型做了特殊处理
     */
    public static void printTrackLengthStatistics(Album album) {
        IntSummaryStatistics trackLengthStats
                = album.getTracks()
                .mapToInt(track -> track.getLength())
                .summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());
    }

    /**
     * <h2>重载解析</h2>
     * javac 会挑出最具体的类型。
     * Lambda 表达式作为参数时，其类型由它的目标类型推导得出
     * 遵循如下规则
     * *如果只有一个可能的目标类型，由相应函数接口里的参数类型推导得出；
     * *如果有多个可能的目标类型，由最具体的类型推导得出；
     * *如果有多个可能的目标类型且最具体的类型不明确，则需人为指定类型。
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

    public void example4() {
        List<Integer> list = new ArrayList<>();
        list.stream()
                .mapToDouble(Integer::intValue)
                .mapToLong(i -> (long) i)
                .mapToDouble(i -> (double) i)
                .summaryStatistics();
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
     * 和类不同，接口没有成员变量，因此默认方法只能通过调用子类的方法来修改子类本身，
     * 避免了对子类的实现做出各种假设。
     */
    public interface Example3<T> {
        void message(String body);

        default void welcome() {
            message("Parent: Hi!");
        }

        String getLastMessage();
    }

}
