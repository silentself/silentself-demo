package com.atdyl.interviewquestions;

/**
 * 找出数组中最长公共前缀
 *
 * @author Dong YL
 * @version V1.0 2019/12/19 17:04
 */
public class InterviewQuestionOne {

    public static void main(String[] args) {
        String[] strArr = {"java2blog", "javaworld", "javabean", "javatemp"};
        String longestPrefix = getLongestCommonPrefix(strArr);
        System.out.println("Longest Prefix : " + longestPrefix);
    }

    private static String getLongestCommonPrefix(String[] strArr) {
        if (strArr.length == 0) return "";
        // Find minimum length String
        String minStr = getMinString(strArr);

        int minPrefixStrLength = minStr.length();
        for (String s : strArr) {
            int j;
            for (j = 0; j < minPrefixStrLength; j++) {
                if (minStr.charAt(j) != s.charAt(j))
                    break;
            }
            if (j < minPrefixStrLength)
                minPrefixStrLength = j;
        }
        return minStr.substring(0, minPrefixStrLength);
    }

    private static String getMinString(String[] strArr) {
        String minStr = strArr[0];
        for (int i = 1; i < strArr.length; i++) {
            if (strArr[i].length() < minStr.length())
                minStr = strArr[i];
        }
        return minStr;
    }

}
