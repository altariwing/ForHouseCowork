package com.house.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.house.model.HouseVO;
import com.house.tool.CrawlerToOracle;
import com.house.tool.HouseDataFormat;

public class BugPrince implements Runnable {
	// 用來儲存每個網頁的次要圖片
	// private static Map<String, List<String>> imgUrlMap = new HashMap<String,
	// List<String>>(7000);
	// 用來儲存下載失敗的物件的網址
	private static List<String> errorList = new Vector<>();
	int i = 1;

	// 錯誤的網址會被放進來，程式結束後印出
	public void describeErrorList() {
		for (String a : errorList) {
			System.out.println(a);
		}
		System.out.println(errorList.size());
	}

	// 透過截取的網址，到每個網頁裡面去把內容抓下來
	public void download() throws IOException {
		FileWriter fw;
		BufferedWriter bw;
		HouseDataFormat dataformat = new HouseDataFormat();
		BugMissionsFactory factory = new BugMissionsFactory();
		System.setProperty("webdriver.chrome.driver", "D:/chromeDriver/chromedriver.exe");
		ChromeDriver driver = null;
		while (factory.currentUrlsQuantity() != 0) {
			String currentUrl = null;
			synchronized (this) {
				currentUrl = factory.popUrlList();
			}
			try {
				driver = new ChromeDriver();
				driver.get(currentUrl);
				try {
					WebDriverWait wait = new WebDriverWait(driver, 5);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".detail-data")));
				} catch (Exception e) {
					driver.navigate().refresh();
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".detail-data")));
				}

				// 獲取每個物件的詳細資訊
				String re_no = "永慶";
				String house_serial_number = driver.findElement(By.cssSelector(".house-info-num")).getText();
				String title = driver.findElement(By.cssSelector(".house-info-name")).getText();
				String location = driver.findElement(By.cssSelector(".house-info-addr")).getText();
				String house_type = driver.findElement(By.cssSelector(".text span:nth-of-type(2)")).getText();
				String priceString = driver.findElement(By.cssSelector(".price-num")).getText();
				Integer price = dataformat.priceToInteger(priceString);
				String pings_info = driver.findElement(By.cssSelector(".bg-square .detail-list-lv1")).getText();

				// 撈建物總坪數
				Pattern p = Pattern.compile("建物坪數：[0-9 .]*坪");
				Matcher matcher = p.matcher(pings_info);
				String total_pingsStr = "";
				while (matcher.find()) {
					total_pingsStr = matcher.group();
				}
				Double total_pings = dataformat.StringToDouble(total_pingsStr);

				// 撈主建物坪數
				String main_pingsStr = "";
				p = Pattern.compile("主建物小計：[0-9 .]*坪");
				matcher = p.matcher(pings_info);
				while (matcher.find()) {
					main_pingsStr = matcher.group();
				}
				Double main_pings = dataformat.StringToDouble(main_pingsStr);

				// 共同建物坪數
				String amenity_pingsStr = "共同使用小計：0.0坪";
				p = Pattern.compile("共同使用小計：[0-9 .]*坪");
				matcher = p.matcher(pings_info);
				while (matcher.find()) {
					amenity_pingsStr = matcher.group();
				}
				Double amenity_pings = dataformat.StringToDouble(amenity_pingsStr);

				// 附屬建物坪數
				String accessory_pingsStr = "附屬建物小計：0.0坪";
				p = Pattern.compile("附屬建物小計：[0-9 .]*坪");
				matcher = p.matcher(pings_info);
				while (matcher.find()) {
					accessory_pingsStr = matcher.group();
				}
				Double accessory_pings = dataformat.StringToDouble(accessory_pingsStr);

				String floor = driver.findElement(By.cssSelector(".text span:nth-of-type(3)")).getText();
				String ageStr = driver
						.findElement(By.cssSelector(".house-info-sub:nth-of-type(3)>.text>span:nth-of-type(1)"))
						.getText();
				Double age = dataformat.StringToDouble(ageStr);
				String pattern = driver.findElement(By.cssSelector(".house-info-sub:nth-of-type(2)")).getText();
				String orientation = driver
						.findElement(By.cssSelector(".m-house-detail-ins:nth-of-type(2)>ul>li:last-child")).getText();
				String building_materials = driver
						.findElement(By.cssSelector(".m-house-detail-ins:nth-of-type(2)>ul>li:nth-last-of-type(2)"))
						.getText();
				String parking_space = "無車位";
				try {
					parking_space = driver.findElement(By.cssSelector(".bg-car")).getText();
				} catch (Exception e) {
					parking_space = "無車位";
				} finally {
					driver.quit();
				}

				// 抓土地分區
				String classification_of_land = "土地使用分區：查無資料區";
				p = Pattern.compile("土地使用分區：[\u4e00-\u9fa5]*區");
				matcher = p.matcher(pings_info);
				while (matcher.find()) {
					classification_of_land = matcher.group();
				}

				// 抓土地坪數
				String land_pingsStr = "土地坪數：請洽房仲人員坪";
				p = Pattern.compile("土地坪數：[0-9 .]*坪");
				matcher = p.matcher(pings_info);
				while (matcher.find()) {
					land_pingsStr = matcher.group();
				}
				Double land_pings = dataformat.StringToDouble(land_pingsStr);

				WebElement countElement = driver.findElement(By.cssSelector(".house-photos-count"));
				int imgCount = Integer.valueOf(countElement.getText().substring(2));

				// -----------其餘資訊--------------
				List<WebElement> data_info_Elements = driver.findElements(By.cssSelector(".bg-other li"));
				List<String> data_info_list = new ArrayList<>();
				for (int j = 0; j < data_info_Elements.size(); j++) {
					String info_of_list = data_info_Elements.get(j).getText();
					data_info_list.add(info_of_list);
				}
				String data_info = dataformat.dataInfoAddTokenizer(data_info_list);

				// 獲取房屋的經緯度
				String lngStr = "Lng= " + driver.findElement(By.id("hiddenCenterLng")).getAttribute("Value");
				Double lng = dataformat.StringToDouble(lngStr);
				String latStr = "Lat= " + driver.findElement(By.id("hiddenCenterLat")).getAttribute("Value");
				Double lat = dataformat.StringToDouble(latStr);

				// System.out.println(re_no);
				// System.out.println(house_serial_number);
				// System.out.println(title);
				// System.out.println(location);
				// System.out.println(house_type);
				// System.out.println("價格 = " + price);
				// System.out.println("總坪數 = " + total_pings);
				// System.out.println("主坪數 = " + main_pings);
				// System.out.println("共有坪數 = " + amenity_pings);
				// System.out.println("附屬坪數 = " + accessory_pings);
				// System.out.println(floor);
				// System.out.println("屋齡 =" + age);
				// System.out.println(pattern);
				// System.out.println(orientation);
				// System.out.println(building_materials);
				// System.out.println(parking_space);
				// System.out.println(classification_of_land);
				// System.out.println("土地坪數 = " + land_pings);
				// System.out.println("-----------DATA INFO----------");
				// System.out.println(data_info);
				// System.out.println("------------------------------");
				// System.out.println(lng);
				// System.out.println(lat);

				StringBuilder sb = new StringBuilder();
				sb.append(re_no);
				sb.append("\r\n");
				sb.append(house_serial_number);
				sb.append("\r\n");
				sb.append(title);
				sb.append("\r\n");
				sb.append(location);
				sb.append("\r\n");
				sb.append(house_type);
				sb.append("\r\n");
				sb.append(price);
				sb.append("\r\n");
				sb.append(total_pings);
				sb.append("\r\n");
				sb.append(main_pings);
				sb.append("\r\n");
				sb.append(amenity_pings);
				sb.append("\r\n");
				sb.append(accessory_pings);
				sb.append("\r\n");
				sb.append(floor);
				sb.append("\r\n");
				sb.append(age);
				sb.append("\r\n");
				sb.append(pattern);
				sb.append("\r\n");
				sb.append(orientation);
				sb.append("\r\n");
				sb.append(building_materials);
				sb.append("\r\n");
				sb.append(parking_space);
				sb.append("\r\n");
				sb.append(classification_of_land);
				sb.append("\r\n");
				sb.append(land_pings);
				sb.append("\r\n");
				sb.append(data_info);
				sb.append("\r\n");
				sb.append(lat);
				sb.append("\r\n");
				sb.append(lng);
				sb.append("\r\n");
				// System.out.println("共有 " + imgCount + " 張圖片");

				File document = new File("D:/IOtest/Yunching/" + house_serial_number);
				if (!document.exists()) {
					document.mkdirs();
				}

				File goal = new File(document, house_serial_number + ".txt");
				fw = new FileWriter(goal);
				bw = new BufferedWriter(fw);
				bw.write(sb.toString());
				bw.flush();
				bw.close();
				fw.close();

				CrawlerTool.igoreVerify();

				WebElement nextPage = driver.findElement(By.cssSelector(".house-photos-main-next"));
				InputStream is = null;

				String main_img_path = "";
				List<String> img_paths = new ArrayList<String>();
				for (int j = 1; j < imgCount; j++) {
					WebElement img = driver.findElement(By.cssSelector(".carousel-main-photo"));
					String imgUrl = img.getAttribute("src");
					// System.out.println("imgUrl = " + imgUrl);
					URL url = new URL(imgUrl);
					is = url.openConnection().getInputStream();
					byte[] imgBytes = new byte[is.available()];
					File outputGoal = new File(document, j + ".jpg");
					if (j == 1) {
						main_img_path = outputGoal.getCanonicalPath();
					} else {
						img_paths.add(outputGoal.getCanonicalPath());
					}
					OutputStream os = new FileOutputStream(outputGoal);
					int size = 0;
					while ((size = is.read(imgBytes)) != -1) {
						os.write(imgBytes, 0, size);
					}
					os.flush();
					os.close();
					is.close();
					nextPage.click();
				}

				System.out.println(main_img_path);

				CrawlerToOracle cto = CrawlerToOracle.getInstance();
				String house_states = "selling";
				HouseVO returnedHouseVo = cto.insertToHouseInfo(re_no, house_serial_number, title, location, house_type,
						price, total_pings, main_pings, amenity_pings, accessory_pings, floor, age, pattern,
						orientation, building_materials, parking_space, classification_of_land, land_pings, data_info,
						main_img_path, lat, lng, house_states);

				String returnedHouse_no = returnedHouseVo.getHouse_no();
				String state = "using";
				cto.insertToHouseImage(returnedHouse_no, img_paths, state);

				System.out.println(Thread.currentThread().getName() + "下載完第" + i++ + "筆房屋資訊");

				// 因為驗證器問題，這個DRIVER已經不正常
				// 所以直接在電腦的process中刪除掉DRIVER,釋放記憶體空間
				driver.quit();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(currentUrl + "下載錯誤,已增加至errorList");
				errorList.add(currentUrl);
			} finally {
				driver.quit();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(Thread.currentThread().getName() + "開始下載" + "第" + i + "筆房屋資訊");
			download();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public Map<String, List<String>> getImgUrlMap() {
	// return imgUrlMap;
	// }

	// public String getImgUrlMapWithJson() {
	// Gson gson = new Gson();
	// return gson.toJson(getImgUrlMap());
	// }

}