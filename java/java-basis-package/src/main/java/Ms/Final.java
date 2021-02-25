package Ms;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/2/24 10:16
 * @Description
 */
public class Final {

    final String string;

    public Final(String string) {
        this.string = string;
    }


    final void v1() {
        System.out.println("123");
    }

    public static void main(String[] args) {
        new Final("1").v1();
    }

}


final class FinallyTest {
    //测试return语句
    //结果显示：编译器在编译return new ReturnClass();时，
    //将它分成了两个步骤，new ReturnClass()和return，前一个创建对象的语句是在finally语句块之前被执行的，
    //而后一个return语句是在finally语句块之后执行的，也就是说finally语句块是在程序退出方法之前被执行的
    public ReturnClass testReturn() {
        try {
            return new ReturnClass();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行了finally语句");
            return null;
        }
    }

    //测试continue语句
    public void testContinue() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(i);
                if (i == 1) {
                    System.out.println("con");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("执行了finally语句");
            }
        }
    }

    //测试break语句
    public void testBreak() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(i);
                if (i == 1) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("执行了finally语句");
            }
        }
    }

    public static void main(String[] args) {
        FinallyTest ft = new FinallyTest();
        // 测试return语句
        ft.testReturn();
        System.out.println();
        // 测试continue语句
        ft.testContinue();
        System.out.println();
        // 测试break语句
        ft.testBreak();
    }
}

class ReturnClass {
    public ReturnClass() {
        System.out.println("执行了return语句");
    }
}

