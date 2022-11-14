import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CopyHadis4 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Chromedriver\\chromedriver.exe");
		//collectMuslim();
		collectNasae();
	}

	static void collectMuslim() {
		//WebDriver driver = new ChromeDriver();
		String index;
		String errors = FFiles.read("F:\\Textsbooks\\majaherrorlog.txt");
		int ok = 0;
		for (int i = 1; i <= 4341; i++) {
			String id = String.valueOf(i);
			while(id.length()<4) id = "0"+id;
			try {
				String text = FFiles.read("F:\\Textsbooks\\Majah\\"+id+"\\text.txt");
				if(text.length() < 10)
					addErrorMuslim(i, "Small");
				else ok++;
			} catch (Exception e) {
				addErrorMuslim(i, "Error occured!");
			}
		}
		System.out.println(ok);
	}
	static void collectNasae() {
		WebDriver driver = new ChromeDriver();
		String index;
		String errors = FFiles.read("F:\\Textsbooks\\nasaeerrorlog.txt");
		int ok = 0;
		for (int i = 1; i <= 6000; i++) {
			String id = String.valueOf(i);
			if(errors.contains("\n"+id+" -")) {
				System.out.println("Gotta fix "+id);
				try {
					index = String.valueOf(i);
					while (index.length() < 4)
						index = "0" + index;
					driver.get("https://www.hadithbd.com/hadith/filter/?book=6&hadith="+i);
					WebElement t1;
					String res = "";
					t1 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span"));
					String text = t1.getText();
					if(text.contains(getSI(i)))
						res = text;

					if(res.length() < 2) {
						System.out.println("No result fouund");
						continue;
					}
					else {
						FFiles.createFolder("F:\\Textsbooks\\Nasae\\"+index);
						FFiles.createAndWrite("F:\\Textsbooks\\Nasae\\"+index+"\\text.txt", res);
					}
					if(!res.startsWith(getSI(i))) System.out.println("Has extra text");
				}
				catch (Exception e) {
					System.out.println("Error occured!");
				}
			}
		}
		System.out.println(ok);
	}

	static void addErrorMuslim(int i, String e) {
		FFiles.addTo("F:\\Textsbooks\\muslimerrorlog.txt", i + " ---> " + e + "\n");
	}

	static String getSI(int N) {
		String no = String.valueOf(N);
		String banglaNumerics[] = { "০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯" };
		String banglaNo = "";
		for (int i = 0; i < no.length(); i++) {
			banglaNo += banglaNumerics[no.charAt(i) - '0'];
		}
		return banglaNo;
	}
}
