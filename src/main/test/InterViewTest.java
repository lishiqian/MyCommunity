public class InterViewTest {
    private static InterViewTest instance = new InterViewTest("name");
    private String name;

    public InterViewTest(String name){
        this.name = name;
        System.out.println(instance.name);
    }

    public InterViewTest got(){
        return null;
    }

    public void got(int a){

    }

    public static void main(String[] args) {

    }
}


class A extends InterViewTest{

    public A(String name) {
        super(name);
    }

    @Override
    public A got() {
        return null;
    }

    @Override
    public void got(int a) {
        super.got(a);
    }
}