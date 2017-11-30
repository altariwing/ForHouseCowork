package com.house.crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BugMissionsFactory implements Runnable {
	Date date = new Date();

	// 儲存獲取到的每個房子超聯結
	private static Vector<String> urlList = new Vector<>(7000);
	static int CurrentMissionPageNumber = 1;
	static int totalPages;
	String yuching = "https://buy.yungching.com.tw/region";
	static {
		// 去電腦裡面抓到CHROM的DRIVER，這邊設定的路徑為D:/chromeDriver/chromedriver.exe
		System.setProperty("webdriver.chrome.driver", "D:/chromeDriver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		String yuching = "https://buy.yungching.com.tw/region";
		// 連結到永慶的買屋頁面
		driver.get(yuching);
		// 抓取最後一頁按鈕，從超聯結裡面去抓到總頁數
		WebElement lastPage = driver.findElementByPartialLinkText("最末頁");
		String finalPage = lastPage.getAttribute("href");
		String currentPage = finalPage.substring(finalPage.lastIndexOf("=") + 1);
		totalPages = Integer.valueOf(currentPage);
		System.out.println("永慶本次共有" + totalPages + "頁");
		driver.quit();
	}

	public int currentUrlsQuantity() {
		return urlList.size();
	}

	public synchronized void AddurlList(String url) {
		urlList.add(url);
	}	
			
	public synchronized String popUrlList() {
		String tempString = urlList.get(0);
		urlList.remove(0);
		return tempString;
	}

	// 去每個分頁抓超聯結
	public void getYuchingHouseData(int pageNumber) throws IOException {

		// 弄個DRIVER

		ChromeDriver driver = new ChromeDriver();
		// 弄個日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String currentTime = sf.format(date);
		// 弄個WRITER，寫進這隻txt檔裡面

		FileWriter fw = new FileWriter("D:/IOtest/Yunching" + currentTime + ".txt", true);
		BufferedWriter bw = new BufferedWriter(fw);

		// 連上網頁
		String currentUrl = yuching + "?pg=" + pageNumber;
		driver.get(currentUrl);

		// 等待二十秒連線or跳出錯誤
		// WebDriverWait wait = new WebDriverWait(driver, 20);
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".l-item-list")));

		// 把每一個分頁裡的物件網址依序存入urlList
		List<WebElement> webElements = driver.findElements(By.cssSelector(".l-item-list > .m-list-item > a"));
		for (int j = 0; j < webElements.size(); j++) {
			String url = webElements.get(j).getAttribute("href");
			urlList.add(url);
			bw.write(url);
			bw.newLine();
		}
		System.out.println(Thread.currentThread().getName() + "已經把第" + pageNumber + "頁的物件超聯結儲存至UrlList");
		bw.flush();
		bw.close();
		fw.close();
		driver.quit();
	}

	@Override // 執行序進入點
	public void run() {
		try {// 如果還有沒有執行的分頁，就去那個分頁工作，如果都已經執行完畢就結束
			int pageNumber = 0;
			while (CurrentMissionPageNumber <= totalPages) {
				// 線程安全
				synchronized (this) {
					if (CurrentMissionPageNumber <= totalPages) {
						pageNumber = CurrentMissionPageNumber++;
					}
				}
				if (pageNumber != 0) {
					System.out.println(Thread.currentThread().getName() + "正在處理第" + pageNumber++ + "頁");
					getYuchingHouseData(pageNumber);
				} else {
					System.out.println("網頁已經讀取完成" + Thread.currentThread().getName() + "已經下班");
				}
			}
			System.out.println(Thread.currentThread().getName() + "已經下班");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	};
}
