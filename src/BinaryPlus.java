import java.util.Scanner;

public class BinaryPlus {

    public static String addingNumbersThatAreNegative(String pk1, String pk2) {
        String result = addingOnlyPositiveNumbers(pk1,pk2);
        result = result.substring(3);
        if(pk1.contains("11,") && pk2.contains("11,")) {
            System.out.println("Зміна знаку");
            return "00," +  result;
        }
        else{
            String signPk1,signPk2;
            signPk1 = pk1.substring(0,3);
            signPk2 = pk2.substring(0,3);
            pk1 = pk1.substring(3);
            pk2 = pk2.substring(3);
            if(Integer.parseInt(pk1) > Integer.parseInt(pk2)) {
                return signPk1 + result;
            }
            else if(Integer.parseInt(pk1) < Integer.parseInt(pk2)) {
                    return signPk2 + result;
            }
            else {
                    return "0";
            }
        }
    }
    public static String addingOnlyPositiveNumbers(String pk1, String pk2) {
        String sign = pk1.substring(0,3);
        int countSeniorRank = 0;
        String result = "";
        pk1 = pk1.substring(3);
        pk2 = pk2.substring(3);
        int index1 = pk1.length() - 1, index2 = pk2.length() -1;
        while(index1 >= 0 && index2 >= 0) {
            if(pk1.charAt(index1) == '1' && pk2.charAt(index2) == '1') {
                result = "0" + result;
                countSeniorRank++;
            }
            else if(pk1.charAt(index1) != '0' || pk2.charAt(index2) != '0'){
                if(countSeniorRank >= 1) {
                    result = "0" + result;
                    countSeniorRank--;
                }
                else {
                    result = "1" + result;
                }
            }
            else if(pk1.charAt(index1) == '0' && pk2.charAt(index2) == '0') {
                result = "0" + result;
            }
            index1--; index2--;
        }
        if(index1 > -1) {
            pk1 = pk1.substring(0,index1 + 1);
            if(countSeniorRank == 0) {
                result = pk1 + result;
            }
            while(countSeniorRank > 0 && index1 >= 0) {
                if(pk1.charAt(index1) == '0') {
                    result = "1" + result;
                    index1--;
                    countSeniorRank--;
                }
                else {
                    result = "0" + result;
                    index1--;
                }
            }
        }
        else if(index2 > -1) {
            pk2 = pk2.substring(0,index2 + 1);
            if(countSeniorRank == 0) {
                result = pk2 + result;
            }
            while(countSeniorRank > 0 && index2 >= 0) {
                if(pk2.charAt(index2) == '0') {
                    result = "1" + result;
                    countSeniorRank--;
                    index2--;
                }
                else {
                    result = "0" + result;
                    index2--;
                }
            }
        }
        if(countSeniorRank > 0) {
            System.out.println("Є переповнення на " + countSeniorRank + " розряд(и)");
            for(int i = 0; i < countSeniorRank; i++) {
                result = "1" + result;
            }
        }
        return sign + result;
    }
    public static String directlyCodeConvertToReverseCode(String code) {
        String sign = code.substring(0,2);
        if(sign.equals("11")) {
            code = code.substring(2);
            code = code.replace("0", "one");
            code = code.replace("1", "0");
            code = code.replace("one", "1");
            code = sign + code;
        }
        return code;
    }
    public static String reverseCodeConvertToAdditionalCode(String code) {
        String sign = code.substring(0,3);
        String change = "";
        if(sign.equals("11,")) {
            code = code.substring(4);
            if(code.charAt(code.length() - 1) == '1') {
                int index = code.length() - 2;
                change = "0";
                while (!change.contains("1") && index >= 0) {
                    if (code.charAt(index) == '1') {
                        change = "0" + change;
                    }
                    if (index == 0) {
                        change = "10" + change;
                        break;
                    }
                    index--;
                }
                if(index >= 0)
                code = code.substring(0,index) + change;
            }
            else{
                if(code.length() > 1)
                code = code.substring(0,code.length() - 2) + "1";
                else code = "1";
            }
        }
        else {
            return code;
        }
        return sign + code;
    }
    public static String numberConvertToBinarySystem(int num) {
        String result = "";
        int n = num;
        if(num < 0)
            n*= -1;
        do {
            result = (n&1) + result;
            n = n >> 1;
        }
        while(n > 0);
        if(num > 0) {
            result = "00," + result;
        }
        else if(num < 0) {
            result = "11," + result;
        }
        return result;
    }
    public static void enter() {
        System.out.println("====================================================================");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String binaryFirstNumberPK, binarySecondNumberPK
                ,binaryFirstNumberOB,binarySecondNumberOB
                ,binaryFirstNumberDOP,binarySecondNumberDOP, result;
        System.out.print("Введіть перше число: " );
        binaryFirstNumberPK = numberConvertToBinarySystem(scanner.nextInt());
        System.out.print("Введіть друге число: ");
        binarySecondNumberPK = numberConvertToBinarySystem(scanner.nextInt());
        enter();
        System.out.println("А прямий код: " + binaryFirstNumberPK);
        System.out.println("Б прямий код: " + binarySecondNumberPK);
        enter();
        binaryFirstNumberOB = directlyCodeConvertToReverseCode(binaryFirstNumberPK);
        System.out.println("A обернений код: " + binaryFirstNumberOB);
        binarySecondNumberOB = directlyCodeConvertToReverseCode(binarySecondNumberPK);
        System.out.println("B обернений код: " + binarySecondNumberOB);
        enter();
        binaryFirstNumberDOP = reverseCodeConvertToAdditionalCode(binaryFirstNumberOB);
        System.out.println("A доповняльний код: " + binaryFirstNumberDOP);
        binarySecondNumberDOP = reverseCodeConvertToAdditionalCode(binarySecondNumberOB);
        System.out.println("B доповняльний код: " + binarySecondNumberDOP);
        enter();

        if(binaryFirstNumberPK.contains("00,")
                && binarySecondNumberPK.contains("00,")) {
            System.out.println("\n" + binaryFirstNumberPK + "\n+");
            System.out.println(binarySecondNumberPK);
            result = addingOnlyPositiveNumbers(binaryFirstNumberPK,binarySecondNumberPK);
            System.out.println(result);
            System.out.println("\nВідповідь у прямому коді: " + result);
        }
        else {
            System.out.println("\n" + binaryFirstNumberOB + "\n+");
            System.out.println(binarySecondNumberOB);
            result = addingNumbersThatAreNegative(binaryFirstNumberOB, binarySecondNumberOB);
            System.out.println(result);
            System.out.println("\nВідповідь в оберненому коді: " + result);
            enter();
            System.out.println("\n" + binaryFirstNumberDOP + "\n+");
            System.out.println(binarySecondNumberDOP);
            result = addingNumbersThatAreNegative(binaryFirstNumberDOP, binarySecondNumberDOP);
            System.out.println(result);
            System.out.println("\nВідповідь у доповняльному коді: " + result);
        }
    }
}
