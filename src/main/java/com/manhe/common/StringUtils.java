package com.manhe.common;
/**
 * @Author walking
 * @Description string工具类
 * @Date 15:56 2019-12-04
 * @Param
 * @return
 **/
public class StringUtils extends  org.apache.commons.lang3.StringUtils{

    private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" }; //定义移动端请求的所有可能类型

    /**
     * 判断User-Agent 是不是来自于手机
     * @param ua
     * @return
     */
    public static boolean checkAgentIsMobile(String ua) {
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }


    /**
     * 从结尾，忽略大小写，按“全词匹配”方式搜索字符串：被搜索字符串在源字符串中前后不能有其他字符。
     * @author walking
     * @param str 源字符串
     * @param searchStr 被搜索字符串
     * @return 如果源串中有匹配搜索字符串的字串，返回子串索引。否则返回-1。
     */
    public static int lastIndexOfWholeIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        return indexOfWhole(str, searchStr, str.length(), true, true);
    }



    /**
     * 忽略大小写，按“全词匹配”方式搜索字符串：被搜索字符串在源字符串中前后不能有其他字符。
     * @author walking
     * @param str 源字符串
     * @param searchStr 被搜索字符串
     * @return 如果源串中有匹配搜索字符串的字串，返回子串索引。否则返回-1。
     */
    public static int indexOfWholeIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        return indexOfWhole(str, searchStr, 0, true, false);
    }


    /**
     * 按“全词匹配”方式搜索字符串：被搜索字符串在源字符串中前后不能有其他字符。
     * @author walking
     * @param str 源字符串
     * @param searchStr 被搜索字符串
     * @param startPos 初始搜索位置	lastIndexOf=true时，从该位置往前搜索
     * @param ignoreCase 忽略大小写
     * @param lastIndexOf 从后往前搜索
     * @return 如果源串中有匹配搜索字符串的字串，返回子串索引。否则返回-1。
     */
    private static int indexOfWhole(final CharSequence str, final CharSequence searchStr, int startPos,
                                    boolean ignoreCase, boolean lastIndexOf) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }

        int index;
        int searchStrLen = searchStr.length();
        int limit = str.length() - searchStrLen; //搜索极限位置
        while(true){
            if(!lastIndexOf) { //正向搜索
                if(!ignoreCase) {
                    index = StringUtils.indexOf(str, searchStr, startPos);
                } else {
                    index = StringUtils.indexOfIgnoreCase(str, searchStr, startPos);
                }
                startPos = index + 1;
            } else { //反向搜索
                if(!ignoreCase) {
                    index = StringUtils.lastIndexOf(str, searchStr, startPos);
                } else {
                    index = StringUtils.lastIndexOfIgnoreCase(str, searchStr, startPos);
                }
//				startPos = index + searchStrLen - 2;
                startPos = index - 1;//StringUtils.lastIndexOfIgnoreCase(str, searchStr, startPos)搜索字符串为str.subSequence(0, startPos + searchStr.length())
            }

            if(index != -1) { //找到了字符串，判断前后是否有字符
                //判断前一个字符是否为空白
                if(index > 0) {
                    if(!Character.isWhitespace(str.charAt(index)) && //自己不是空格开头
                            !Character.isWhitespace(str.charAt(index - 1))) { //前一字符非空白字符，不符合条件，继续搜索
                        continue;
                    }
                }

                //判断后一个字符是否为空白
                if(index < limit) {
                    if(!Character.isWhitespace(str.charAt(index + searchStrLen - 1)) && //自己不是空格结尾
                            !Character.isWhitespace(str.charAt(index + searchStrLen))) { //后一字符非空白字符，不符合条件，继续搜索
                        continue;
                    }
                }
            }

            //找到了符合条件的结果，或没有符合条件的结果，返回索引。
            return index;
        }
    }

}
