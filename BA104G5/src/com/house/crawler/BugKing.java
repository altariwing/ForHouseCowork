package com.house.crawler;

import java.io.File;

public class BugKing {

	public static void main(String[] args) {

		 // 抓取的東西都在這裡
		 File file = new File("D:/IOtest");
		 if (!file.exists()) {
		 file.mkdirs();
		 }
		
		 // 呼叫三個執行序，把信義每一頁的物件超聯結都抓下來
		 BugMissionsFactory2 getCommoditiesUrl = new BugMissionsFactory2();
		 Thread thread1 = new Thread(getCommoditiesUrl, "信義一號");
		 Thread thread2 = new Thread(getCommoditiesUrl, "信義二號");
		 Thread thread3 = new Thread(getCommoditiesUrl, "信義三號");
		 thread1.start();
		 thread2.start();
		 thread3.start();
		
		 try {
		 thread1.join();
		 thread2.join();
		 thread3.join();
		 } catch (InterruptedException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 System.out.println("獲取網址的工作結束前有東西壞掉了");
		 }
		
		 // 去每個房屋物件的網頁抓我們需要的參數
		 System.out.println("資料庫共有" + getCommoditiesUrl.currentUrlsQuantity()
		 + "筆資料");
		 BugPrincess downloadInfo = new BugPrincess();
		 thread1 = new Thread(downloadInfo, "信義一號");
		 thread2 = new Thread(downloadInfo, "信義二號");
		 thread3 = new Thread(downloadInfo, "信義三號");
		
		 thread1.start();
		 thread2.start();
		 thread3.start();
		
		 try {
		 thread1.join();
		 thread2.join();
		 thread3.join();
		 } catch (InterruptedException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 System.out.println("結束前東西壞掉了");
		 }
		
		 // 如果有下載失敗的網址，在這邊會顯示出來
		 downloadInfo.describeErrorList();
		 System.out.println("下載程式結束");

		// -------------------------下面是永慶房屋-------------------------------

//		System.out.println(BugMissionsFactory.totalPages);
//
//		// 呼叫三個執行序，把永慶每一頁的物件超聯結都抓下來
//		BugMissionsFactory getCommoditiesUrl = new BugMissionsFactory();
//		Thread thread1 = new Thread(getCommoditiesUrl, "永慶一號");
//		Thread thread2 = new Thread(getCommoditiesUrl, "永慶二號");
//		Thread thread3 = new Thread(getCommoditiesUrl, "永慶三號");
//		thread1.start();
//		thread2.start();
//		thread3.start();
//
//		try {
//			thread1.join();
//			thread2.join();
//			thread3.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("獲取網址的工作結束前有東西壞掉了");
//		}
//
//		// 去每個房屋物件的網頁抓我們需要的參數
//		System.out.println("資料庫共有" + getCommoditiesUrl.currentUrlsQuantity() + "筆資料");
//		BugPrince downloadInfo = new BugPrince();
//		thread1 = new Thread(downloadInfo, "一號");
//		thread2 = new Thread(downloadInfo, "二號");
//		thread3 = new Thread(downloadInfo, "三號");
//
//		thread1.start();
//		thread2.start();
//		thread3.start();
//
//		try {
//			thread1.join();
//			thread2.join();
//			thread3.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("結束前東西壞掉了");
//		}
//
//		// 如果有下載失敗的網址，在這邊會顯示出來
//		downloadInfo.describeErrorList();
//		System.out.println("下載程式結束");

	}

}
