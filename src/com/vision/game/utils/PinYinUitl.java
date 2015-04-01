package com.vision.game.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类，可以使中文汉字变成拼音
 * @author tangkunyin
 * @see www.hadooper.org
 */
public class PinYinUitl {
	public static String getStringPinYin(String str) {
	       StringBuilder sb = new StringBuilder();
	       String tempPinyin = null;
	       for(int i = 0; i < str.length(); i++) {
	            tempPinyin =getCharacterPinYin(str.charAt(i));
	            if(tempPinyin == null){
                  // 如果str.charAt(i)非汉字，则保持原样
                  sb.append(str.charAt(i));
	            }else{
                  sb.append(tempPinyin);
	            }
	       }
	       return sb.toString();
  }
  //转换单个字符
  public static String getCharacterPinYin(char c){
 	HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		String[] pinyin=null;
        try{
              pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
        }catch(BadHanyuPinyinOutputFormatCombination e){
                 e.printStackTrace();
        }
        // 如果c不是汉字，toHanyuPinyinStringArray会返回null
        if(pinyin == null)
     	   return null;
        // 只取一个发音，如果是多音字，仅取第一个发音
        return pinyin[0];   
  	}
}
