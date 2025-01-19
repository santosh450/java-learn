package Java8._02FunctionalInterface;


// Example -1
interface FI31 {
}

@FunctionalInterface
interface FI32 extends FI31 {
    double display1(int x, int y);
}

/*-----------------------------------------------------------*/
// Example -2
@FunctionalInterface
interface FI33  {
    double display2(int x, int y);
}

@FunctionalInterface
interface FI34 extends FI33 {
}

/*-----------------------------------------------------------*/
// Example -3


@FunctionalInterface
interface FI35  {
    double none();
}

@FunctionalInterface
interface FI36  extends FI35{
    double none();
}
@FunctionalInterface
interface FI37  extends FI36{
    double none();
}


public class Test03 {
    public static void main(String[] args) {

    }
}
