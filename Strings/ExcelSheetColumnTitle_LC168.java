package Strings;

public class ExcelSheetColumnTitle_LC168 {
    

    
    //Time: O(log (columnNumber)){Base 26}, Space: O(length of string)
    public String convertToTitle(int columnNumber) {

        //String ans = "";
        StringBuilder sb = new StringBuilder();

        while(columnNumber > 0){
            columnNumber--;
            int digit = columnNumber % 26;

            //ans = (char)(digit + 'A') + ans;
            sb.append((char)(digit + 'A'));

            columnNumber = columnNumber / 26;
        }

        //return ans;
        return sb.reverse().toString();
        
    }
}