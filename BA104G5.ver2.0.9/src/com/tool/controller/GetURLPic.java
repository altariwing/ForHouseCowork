package com.tool.controller;

import java.net.*;
import java.io.*;

public class GetURLPic {

	public static InputStream GetPic(String url) {
		URL urllink = null;
		InputStream in = null;
		try {
			
			urllink = new URL(url);
			System.out.println(urllink);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 建立URL物件url
		try {
			in = urllink.openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;

	}
}