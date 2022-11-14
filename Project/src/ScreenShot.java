import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.*;

public class ScreenShot {
	
	static WebDriver driver = null;
	static Random r = new Random();
	
	public static void main(String[] args) throws InterruptedException, IOException {
		Bukhari();
	}
	
	static void Bukhari() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of hadis: ");
		int H = sc.nextInt();
		for(int i=1; i<=H; i++) {
			String HN = i+"";
			while(HN.length() < 4) HN = "0"+HN;
			createAndMergeStep2("F://Bukhari//"+HN);
		}
	}
	
	static void createAndMergeStep2(String path) throws IOException {
		int sub = Integer.parseInt(FFiles.read(path+"//subfiles.txt"));
		for(int i=1; i<=sub; i++) {
			String photoOutput = path+"//photos//photo"+i+".png";
			String title = getTitle(path);
			String text = FFiles.read(path+"//texts//text"+i+".txt");
			write(r.nextInt(51), title, text);
			
			takeScreenShot(photoOutput);
		}

	}
	
	static String getTitle(String path) {
		String no = String.valueOf(Integer.parseInt(path.substring(path.length()-4, path.length())));
		String banglaNumerics[] = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
		String banglaNo = "";
		for(int i=0; i<no.length(); i++) {
			banglaNo += banglaNumerics[no.charAt(i)-'0'];
		}
		return "সহীহ বুখারী শরীফ | হাদীস নং "+banglaNo;
	}
	
	static void takeShots() throws InterruptedException, IOException {
		for(int i=0; i<51; i++) {
			write(i, "সাল্লাল্লাহু", "আলক্বামাহ ইবনু ওয়াক্কাস আল-লায়সী (রহ.) হতে বর্ণিত। আমি ’উমার ইবনুল খাত্তাব (রাঃ)-কে মিম্বারের উপর দাঁড়িয়ে বলতে শুনেছিঃ আমি আল্লাহর রাসূল সাল্লাল্লাহু আলাইহি ওয়াসাল্লাম-কে বলতে শুনেছিঃ কাজ (এর প্রাপ্য হবে) নিয়্যাত অনুযায়ী। আর মানুষ তার নিয়্যাত অনুযায়ী প্রতিফল পাবে। তাই যার হিজরত হবে ইহকাল লাভের অথবা কোন মহিলাকে বিবাহ করার উদ্দেশে- তবে তার হিজরত সে উদ্দেশেই হবে, যে জন্যে, সে হিজরত করেছে। ’আলক্বামাহ ইবনু ওয়াক্কাস আল-লায়সী (রহ.) হতে বর্ণিত। আমি ’উমার ইবনুল খাত্তাব (রাঃ)-কে মিম্বারের উপর দাঁড়িয়ে বলতে শুনেছিঃ আমি আল্লাহর রাসূল সাল্লাল্লাহু আলাইহি ওয়াসাল্লাম-কে বলতে শুনেছিঃ কাজ (এর প্রাপ্য হবে) নিয়্যাত অনুযায়ী। আর মানুষ তার নিয়্যাত অনুযায়ী প্রতিফল পাবে। তাই যার হিজরত হবে ইহকাল লাভের অথবা কোন মহিলাকে বিবাহ করার উদ্দেশে- তবে তার হিজরত সে উদ্দেশেই হবে, যে জন্যে, সে হিজরত করেছে। ’আলক্বামাহ ইবনু ওয়াক্কাস আল-লায়সী (রহ.) হতে বর্ণিত। আমি ’উমার ইবনুল খাত্তাব (রাঃ)-কে মিম্বারের উপর দাঁড়িয়ে বলতে শুনেছিঃ আমি আল্লাহর রাসূল সাল্লাল্লাহু আলাইহি ওয়াসাল্লাম-কে বলতে শুনেছিঃ");
			takeScreenShot("F://photos//photo"+i+".png");
		}
	}
	
	static void takeScreenShot(String output) throws IOException {
        if(driver == null) {
    		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Chromedriver\\chromedriver.exe");	
        	driver = new ChromeDriver();
            String baseUrl = "F://photos//webpage.html";					
            driver.get(baseUrl);
            driver.manage().window().maximize();
        }
        
        driver.navigate().refresh();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(output));
	}
	
	static void write(int bg, String title, String text) {
		String html = "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            margin: 0px;\r\n"
				+ "            padding: 0px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .bg-text {\r\n"
				+ "            font-family: 'Kalpurush', 'Arial' !important;"
				+ "            background-color: rgba(0, 0, 0, 0.2);\r\n"
				+ "            font-size: 48px;\r\n"
				+ "            color: white;\r\n"
				+ "            text-shadow: 2px 2px 2px black;\r\n"
				+ "            text-align: justify;\r\n"
				+ "            border: 1px solid #333;\r\n"
				+ "            position: absolute;\r\n"
				+ "            top: 50%;\r\n"
				+ "            left: 50%;\r\n"
				+ "            transform: translate(-50%, -50%);\r\n"
				+ "            z-index: 2;\r\n"
				+ "            width: 85%;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            border-radius: 20px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .bg-image {\r\n"
				+ "            background-image: url(\"bg/"+bg+".jpg\");\r\n"
				+ "            filter: blur(8px);\r\n"
				+ "            -webkit-filter: blur(8px);\r\n"
				+ "            height: 100%;\r\n"
				+ "            background-position: center;\r\n"
				+ "            background-repeat: no-repeat;\r\n"
				+ "            background-size: cover;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .title{\r\n"
				+ "			   font-family: 'CharuChandanHardStroke', 'Arial' !important;"	
				+ "            position: fixed;\r\n"
				+ "            top: 20px;\r\n"
				+ "            left: 0px;\r\n"
				+ "            background-color: rgba(0, 0, 0, 0.8);\r\n"
				+ "            font-size: 45px;\r\n"
				+ "            color: white;\r\n"
				+ "            text-shadow: 1px 1px 2px rgb(247, 247, 247);\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            padding-left: 30px;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            margin-left: -10px;\r\n"
				+ "            box-shadow: 2px 2px 2px white;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .footer1{\r\n"
				+ "            font-family: cursive;\r\n"
				+ "            position: fixed;\r\n"
				+ "            bottom: -10px;\r\n"
				+ "            left: 0px;\r\n"
				+ "            background-color: rgba(0, 0, 0, 0.5);\r\n"
				+ "            font-size: 35px;\r\n"
				+ "            color: white;\r\n"
				+ "            text-shadow: 1px 1px 2px rgb(247, 247, 247);\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            padding-left: 30px;\r\n"
				+ "            padding-bottom: 30px;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            margin-left: -10px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .footer2{\r\n"
				+ "            font-family: cursive;\r\n"
				+ "            position: fixed;\r\n"
				+ "            bottom: -10px;\r\n"
				+ "            right: 0px;\r\n"
				+ "            background-color: rgba(0, 0, 0, 0.5);\r\n"
				+ "            font-size: 35px;\r\n"
				+ "            color: white;\r\n"
				+ "            text-shadow: 1px 1px 2px rgb(247, 247, 247);\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            padding-right: 30px;\r\n"
				+ "            padding-bottom: 30px;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            margin-right: -10px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"bg-image\"></div>\r\n"
				+ "    <div class=\"title\">"+title+"</div>\r\n"
				+ "    <div class=\"footer1\"><img src=\"yt.png\" height=\"35px\">YouTube: Bioer Kotha | বইয়ের কথা</div>\r\n"
				+ "    <div class=\"footer2\">www.boierkotha.com</div>\r\n"
				+ "    <div class=\"bg-text\">"+text+ "</div>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		
		FFiles.changeData("F://photos//webpage.html", html);
	}

	static void customWrite(String path, int rn, String title, String text) {
		String html = "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            margin: 0px;\r\n"
				+ "            padding: 0px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .bg-text {\r\n"
				+ "            font-family: 'Kalpurush', 'Arial' !important;"
				+ "            background-color: rgba(0, 0, 0, 0.2);\r\n"
				+ "            font-size: 48px;\r\n"
				+ "            color: white;\r\n"
				+ "            text-shadow: 2px 2px 2px black;\r\n"
				+ "            text-align: justify;\r\n"
				+ "            border: 1px solid #333;\r\n"
				+ "            position: absolute;\r\n"
				+ "            top: 50%;\r\n"
				+ "            left: 50%;\r\n"
				+ "            transform: translate(-50%, -50%);\r\n"
				+ "            z-index: 2;\r\n"
				+ "            width: 85%;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            border-radius: 20px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .bg-image {\r\n"
				+ "            background-image: url(\""+path+"//"+rn+".jpg"+"\");\r\n"
				+ "            filter: blur(8px);\r\n"
				+ "            -webkit-filter: blur(8px);\r\n"
				+ "            height: 100%;\r\n"
				+ "            background-position: center;\r\n"
				+ "            background-repeat: no-repeat;\r\n"
				+ "            background-size: cover;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .title{\r\n"
				+ "			   font-family: 'CharuChandanHardStroke', 'Arial' !important;"	
				+ "            position: fixed;\r\n"
				+ "            top: 20px;\r\n"
				+ "            left: 0px;\r\n"
				+ "            background-color: rgba(0, 0, 0, 0.8);\r\n"
				+ "            font-size: 45px;\r\n"
				+ "            color: white;\r\n"
				+ "            text-shadow: 1px 1px 2px rgb(247, 247, 247);\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            padding-left: 30px;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            margin-left: -10px;\r\n"
				+ "            box-shadow: 2px 2px 2px white;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .footer1{\r\n"
				+ "            font-family: cursive;\r\n"
				+ "            position: fixed;\r\n"
				+ "            bottom: -10px;\r\n"
				+ "            left: 0px;\r\n"
				+ "            background-color: rgba(0, 0, 0, 0.5);\r\n"
				+ "            font-size: 35px;\r\n"
				+ "            color: white;\r\n"
				+ "            text-shadow: 1px 1px 2px rgb(247, 247, 247);\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            padding-left: 30px;\r\n"
				+ "            padding-bottom: 30px;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            margin-left: -10px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .footer2{\r\n"
				+ "            font-family: cursive;\r\n"
				+ "            position: fixed;\r\n"
				+ "            bottom: -10px;\r\n"
				+ "            right: 0px;\r\n"
				+ "            background-color: rgba(0, 0, 0, 0.5);\r\n"
				+ "            font-size: 35px;\r\n"
				+ "            color: white;\r\n"
				+ "            text-shadow: 1px 1px 2px rgb(247, 247, 247);\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            padding-right: 30px;\r\n"
				+ "            padding-bottom: 30px;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            margin-right: -10px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"bg-image\"></div>\r\n"
				+ "    <div class=\"title\">"+title+"</div>\r\n"
				+ "    <div class=\"footer1\"><img src=\"yt.png\" height=\"35px\">YouTube/Audiobook</div>\r\n"
				+ "    <div class=\"footer2\">www.audioboi.com</div>\r\n"
				+ "    <div class=\"bg-text\">"+text+ "</div>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		
		FFiles.changeData("F://photos//webpage.html", html);
	}
}
