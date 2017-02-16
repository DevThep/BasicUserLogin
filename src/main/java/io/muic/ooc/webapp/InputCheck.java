package io.muic.ooc.webapp;


public class InputCheck {
    private boolean isEmpty(String str){
        return str.length()==0;
    }
    private boolean checkLength(String str){
        return (str.length()>=6 && str.length()<=14);
    }
    private boolean checkfirstChar(String str){
        int first = (int) str.charAt(0);
        return (first >= 65 && first<=90) && (first >= 97 && first <= 192);
    }

    public boolean checkUser(String str){
        if (isEmpty(str)){
            return false;
        }else if (!checkLength(str)){
            return false;
        }else if (!checkfirstChar(str)){
            return false;
        }else{
            return true;
        }
    }
}
