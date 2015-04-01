package com.vision.game.utils;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码生成工具
 * @author tangkunyin
 */
public class QRCodeUtil {
	/*
	 *  传入一个关键字，返回生成图片的地址
	 */
	public static String getQRCodeImg(String text,File outputFile) {
		int width=260;
		int height=260;
		String format="png";
		Hashtable<EncodeHintType, String> hints=new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile); 
			if(outputFile.exists()){
				return outputFile.getPath();
			}
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "resources/qrcode/error-qrcode.gif";
	}
}
