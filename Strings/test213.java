public class test213 {
    public static void main(String[] args){

        String str = "12345";

        long result = 0;
        int index = 0;
        int len = str.length();

        while(index < len){
            int digit = (int)(str.charAt(index) - '0');

            result = (result * 10) + digit;

            index++;
        }

        System.out.println(result);
    }
}
