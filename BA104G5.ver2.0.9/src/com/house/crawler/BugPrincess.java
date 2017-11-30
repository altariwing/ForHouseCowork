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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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

public class BugPrincess implements Runnable {

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

	public void download() throws IOException {
		HouseDataFormat hdf = new HouseDataFormat();
		BugMissionsFactory2 factory = new BugMissionsFactory2();
		System.setProperty("webdriver.chrome.driver", "D:/chromeDriver/chromedriver.exe");
		ChromeDriver driver = null;
		String currentUrl = null;
		while (factory.currentUrlsQuantity() != 0) {
			synchronized (this) {
				currentUrl = factory.popUrlList();
			}
			try {
				driver = new ChromeDriver();
				driver.get(currentUrl);
				try {
					WebDriverWait wait = new WebDriverWait(driver, 5);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("basic-data")));
				} catch (Exception e) {
					driver.navigate().refresh();
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("basic-data")));
				}

				FileWriter fw;
				BufferedWriter bw;

				String basicData = driver.findElement(By.id("basic-data")).getText();
				// System.out.println(basicData);

				String bread = driver.findElement(By.id("crumb")).getText();
				String subData = driver.findElement(By.id("obj-info")).getText();
				// System.out.println(subData);

				String re_no = "信義";

				// 房屋標題跟房屋序號
				String title = null;
				String house_serial_number = null;
				Pattern p = Pattern.compile(".+(\\([0-9A-Z]{4,8}\\))");
				Matcher m = p.matcher(bread);
				while (m.find()) {
					title = m.group();
					house_serial_number = m.group(1).substring(1, m.group(1).length() - 1);
				}

				// 抓地址
				String location = null;
				p = Pattern.compile("[\u4e00-\u9fa5]{2}(市|縣)[\u4e00-\u9fa5]+(區|市)[\u4e00-\u9fa5]+(路|街|巷|地段)");
				m = p.matcher(basicData);
				while (m.find()) {
					location = m.group();
				}
				// 抓房屋類型
				String houseTypeOrigin = null;
				String house_type = null;
				p = Pattern.compile("類       型：[\u4e00-\u9fa5、]*");
				m = p.matcher(subData);
				while (m.find()) {
					houseTypeOrigin = m.group().substring(10);
					house_type = hdf.houseTypeForSinyi(houseTypeOrigin);
				}
				// 抓價錢
				Integer price = null;
				p = Pattern.compile("[0-9, ]+萬");
				m = p.matcher(basicData);
				while (m.find()) {
					price = hdf.priceToInteger(m.group());
				}
				Double total_pings = null;
				p = Pattern.compile("建物登記：[0-9.]+坪");
				m = p.matcher(subData);
				while (m.find()) {
					total_pings = hdf.StringToDouble(m.group());
				}
				Double main_pings = null;
				p = Pattern.compile("主  建  物：[0-9.]+坪");
				m = p.matcher(subData);
				while (m.find()) {
					main_pings = hdf.StringToDouble(m.group());
				}
				Double amenity_pings = null;
				p = Pattern.compile("公共設施[0-9. ]*坪");
				m = p.matcher(basicData);
				while (m.find()) {
					amenity_pings = hdf.StringToDouble(m.group());
				}
				Double accessory_pings = total_pings - main_pings - amenity_pings;

				// 樓層
				String floor = null;
				p = Pattern.compile("樓層\\/樓高：[0-9~-]+\\/[0-9]+樓");
				m = p.matcher(subData);
				while (m.find()) {
					floor = m.group().substring(6);
				}
				Double age = 0.0;
				p = Pattern.compile("屋       齡：[0-9. ]*年");
				m = p.matcher(subData);
				while (m.find()) {
					age = hdf.StringToDouble(m.group());
				}
				String pattern = null;
				p = Pattern.compile("格       局：[0-9]房\\/ [0-9]廳\\/ [0-9]衛\\/ [0-9]室");
				m = p.matcher(subData);
				while (m.find()) {
					pattern = hdf.patternForSinyi(m.group());
				}
				String orientation = null;
				p = Pattern.compile("朝向 大樓：[\u4e00-\u9fa5]{1,2}");
				m = p.matcher(basicData);
				while (m.find()) {
					orientation = m.group().substring(6);
				}
				String building_materials = null;
				p = Pattern.compile("外牆建材 [\u4e00-\u9fa5]{1,4}");
				m = p.matcher(basicData);
				while (m.find()) {
					building_materials = m.group();
				}
				String parking_space = null;
				p = Pattern.compile("車       位：[ \u4e00-\u9fa5]*");
				m = p.matcher(subData);
				while (m.find()) {
					parking_space = m.group().substring(10);
				}
				String classification_of_land = "請洽詢業務人員";
				Double land_pings = 0.0;
				p = Pattern.compile("土地登記：[0-9. ]*坪");
				m = p.matcher(subData);
				while (m.find()) {
					land_pings = hdf.StringToDouble(m.group());
				}
				List<String> date_infos = new ArrayList<String>();
				List<WebElement> data_info_elements = driver.findElements(By.cssSelector(".features li"));
				for (WebElement we : data_info_elements) {
					date_infos.add(we.getText());
				}
				String data_info = hdf.dataInfoAddTokenizer(date_infos);

				WebElement lat_lng = driver.findElement(By.id("static_map"));
				String href = lat_lng.getAttribute("data-src");
				
				// 抓緯度
				String lngStr = href.substring(href.lastIndexOf("_") + 1, href.lastIndexOf("."));
				Double lng = hdf.StringToDouble(lngStr);
				// 抓經度
				String temphref = href.substring(0, href.lastIndexOf("_"));
				String latStr = temphref.substring(temphref.lastIndexOf("_") + 1);
				Double lat = hdf.StringToDouble(latStr);

				String house_states = "selling";

				// System.out.println("------------------------------------------");
				// System.out.println("房屋序號 = " + house_serial_number);
				// System.out.println("標題 = " + title);
				// System.out.println("地址 = " + location);
				// System.out.println("類型 = " + house_type);
				// System.out.println("價格 = " + price);
				// System.out.println("總坪數 = " + total_pings);
				// System.out.println("主建物坪數 = " + main_pings);
				// System.out.println("公共設施坪數 = " + amenity_pings);
				// System.out.println("附屬建物坪數 = " + accessory_pings);
				// System.out.println("樓層 = " + floor);
				// System.out.println("屋齡 = " + age);
				// System.out.println("格局 = " + pattern);
				// System.out.println("朝向 = " + orientation);
				// System.out.println("建材 = " + building_materials);
				// System.out.println("車位 = " + parking_space);
				// System.out.println("土地使用分區 = " + classification_of_land);
				// System.out.println("土地坪數 = " + land_pings);
				// System.out.println("其餘細節 = " + data_info);
				// System.out.println("lat = " + lat);
				// System.out.println("lng = " + lng);
				// System.out.println("房屋狀態 = " + house_states);

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

				File document = new File("D:/IOtest/Sinyi/" + house_serial_number);
				if (!document.exists()) {
					document.mkdirs();
				}

				File goal = new File(document, house_serial_number + ".txt");
				try {
					fw = new FileWriter(goal);
					bw = new BufferedWriter(fw);
					bw.write(sb.toString());
					bw.flush();
					bw.close();
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					driver.quit();
				}

				int count = 1;
				String main_img_path = "";
				Set<String> img_paths = new LinkedHashSet<String>();
				List<WebElement> imgs = driver.findElements(By.cssSelector("#obj_list_photo img"));

				// 讓browser遊覽每個照片,每個照片停0.3秒
				for (WebElement img : imgs) {
					img.click();
					Thread.sleep(500);
				}

				// 下載每個照片到本機端，並將本機端路徑存成一個集合，以便後續上傳至資料庫
				for (WebElement img : imgs) {
					String imgUrl = img.getAttribute("src");
					System.out.println(imgUrl);

					File outputGoal = new File(document, count + ".jpg");
					// 第一張視為主照片，上傳到houesInfo，其餘存起來上傳到houseImage
					if (count == 1) {
						main_img_path = outputGoal.getCanonicalPath();
					} else {
						img_paths.add(outputGoal.getCanonicalPath());
					}

					// 下載到本機
					URL url = new URL(imgUrl);
					byte[] imgBytes = new byte[8192];
					InputStream is = url.openConnection().getInputStream();
					OutputStream os = new FileOutputStream(outputGoal);
					int size = 0;
					while ((size = is.read(imgBytes)) != -1) {
						os.write(imgBytes, 0, size);
					}
					os.flush();
					os.close();
					is.close();
					count++;
				}

				CrawlerToOracle cto = CrawlerToOracle.getInstance();
				// 新增到HOUSEINFO
				HouseVO returnedHouseVo = cto.insertToHouseInfo(re_no, house_serial_number, title, location, house_type,
						price, total_pings, main_pings, amenity_pings, accessory_pings, floor, age, pattern,
						orientation, building_materials, parking_space, classification_of_land, land_pings, data_info,
						main_img_path, lat, lng, house_states);
				// 抓自增主鍵
				String returnedHouse_no = returnedHouseVo.getHouse_no();

				// 新增到HOUSEIMAGES
				String state = "using";
				cto.insertToHouseImage(returnedHouse_no, img_paths, state);

				System.out.println(Thread.currentThread().getName() + "下載完成" + "第" + i++ + "筆房屋資訊");

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
			System.out.println(Thread.currentThread().getName() + "開始下載" + "第" + i++ + "筆房屋資訊");
			download();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}