package github.zyp.chapter2;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.*;

/**
 * <h1>编写 lambda 表达式的不同形式</h1>
 */
public class Chapter2 {
    public static void main(String[] args) {

    }

    /**
     * <h2>编写 lambda 表达式的不同形式</h2>
     */
    private static void knowledgePoint1() {
        Runnable noArguments = () -> System.out.println("Hello World");
        ActionListener oneArguments = event -> System.out.println("button clicked");
        Runnable multiStatement = () -> {
            System.out.println("MultiStatement");
            System.out.println("Hello World");
        };
        BinaryOperator<Long> add = (x, y) -> x + y;
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
        BinaryOperator<Long> addRecommend = Long::sum;
    }

    /**
     * <h2>Lambda 里使用局部变量要保证是 final 的或者 effectively final</h2>
     *  局部内部类和匿名内部类访问的局部变量必须由final修饰，java8开始，可以不加final修饰符，
     *  由系统默认添加。java将这个功能称为：Effectively final 功能。
     */
    private static void knowledgePoint2() {
        String name = "a";
        name = "b";
//        ActionListener error = event -> System.out.println("button clicked " + name);
        String finalName = name;
        ActionListener right = event -> System.out.println("button clicked " + finalName);
    }

    /**
     * <h2>Java 中常用的函数接口</h2>
     */
    private static void knowledgePoint3() {
        Predicate<Integer> predicate;
        Consumer<Integer> consumer;
        Function<Integer, Integer> function;
        Supplier<Integer> supplier;
        UnaryOperator<Integer> unaryOperator;
        BinaryOperator<Integer> binaryOperator;
    }

    /**
     * <h2>练习题1</h2>
     */
    private static void example1() {
        // 实现平方功能
        Function<Integer, Integer> square = x -> x * x;
    }

    /**
     * <h2>练习题2</h2>
     * ThreadLocal 的作用是包装一个线程安全的对象
     */
    private static void example2() {
        // java8 threadLocal lambda 构造方法
        Supplier<Integer> supplier = () -> 520;
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(supplier);
        // 创建一个线程安全的 DateFormatter 对象
        ThreadLocal<DateFormat> threadSafeDateFormat =
                ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy"));
    }
}
