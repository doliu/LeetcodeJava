public class Solution {
    private java.util.Vector<String> polishStr = new java.util.Vector<String>();
    private java.util.Vector<String> numbersVec = new java.util.Vector<String>();
    private java.util.Vector<String> operatorsVec = new java.util.Vector<String>();
    
    //create a Vector containing tokens instead of String[]
    public void createPolishStr(String[] tokens)
    {
        for(int i = tokens.length-1; i >= 0; i--)
        {
            polishStr.addElement(tokens[i]);
        }
    }
    
    //check whether token is a valid operator or not
    public boolean checkOperator(String token)
    {
        if(token.equals("+"))
            return true;
        else if(token.equals("-"))
            return true;
        else if(token.equals("*"))
            return true;
        else if(token.equals("/"))
            return true;
        return false;
    }
    
    //read token of numbers into numbersVec and token of operators into operatorsVec
    public void readStr(java.util.Vector<String> polishStr)
    {
        while(!polishStr.isEmpty() && checkOperator((polishStr.elementAt(polishStr.size()-1)).toString()) != true)
        {
            numbersVec.addElement((polishStr.elementAt(polishStr.size()-1)).toString());
            polishStr.removeElementAt(polishStr.size()-1);
        }
        if(!polishStr.isEmpty())
        {
            operatorsVec.addElement((polishStr.elementAt(polishStr.size()-1)).toString());
            polishStr.removeElementAt(polishStr.size()-1);
        }
    }
    
    //calculate for 2 numbers and 1 operator
    public int calculateItem()
    {
        //get 2 numbers
        int num1 = Integer.parseInt((numbersVec.elementAt(numbersVec.size()-1)).toString());
        numbersVec.removeElementAt(numbersVec.size()-1);
        int num2 = Integer.parseInt((numbersVec.elementAt(numbersVec.size()-1)).toString());
        numbersVec.removeElementAt(numbersVec.size()-1);
        
        //get 1 operator
        String op = (operatorsVec.elementAt(operatorsVec.size()-1)).toString();
        operatorsVec.removeElementAt(operatorsVec.size()-1);
        
        //calculate
        if(op == "+")
            return num2 + num1;
        else if(op == "-")
            return num2 - num1;
        else if(op == "*")
            return num2 * num1;
        else
            return num2 / num1;
    }
    public int evalRPN(String[] tokens) {
        //transfer tokens into polishStr Vector
        createPolishStr(tokens);
        
        int result = 0;
        //if str has only one number, return it
        if(polishStr.size() < 2)
            return Integer.parseInt((polishStr.elementAt(0)).toString());
        
        while(!polishStr.isEmpty())
        {
            readStr(polishStr);
            result = calculateItem();
            numbersVec.addElement(result + "");
        }
        
        return result;
    }
    
    public static void main(String[] args)
    {
    	String[] tokens = {"3", "-4", "+"};
    	Solution so = new Solution();
    	System.out.println(so.evalRPN(tokens));
    }
}
