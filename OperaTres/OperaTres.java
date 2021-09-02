public class OperaTres{
    public static void main(String[] args){
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int res = a*(b+c);
        System.out.println("Resultado de "+a+" x "+"("+b+" + "+c+"): " + res);
    }
}