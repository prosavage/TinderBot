import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.json.JSONArray
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.lang.StringBuilder
import java.util.*

fun main(args: Array<String>) {
    Main().enable()
}


class Main() {

    fun enable() {
        val options = ChromeOptions()
        options.addArguments("user-data-dir=~/Documents/data")
        val driver = ChromeDriver(options)
        driver.get("https://tinder.com/app/recs")
        Thread.sleep(4000)
        println("READY!")
        val box = driver.findElements(
            By.xpath(
                "/html/body/div[1]/div/div[1]/div/main/div[1]/div/div/div[1]/div/div[1]/div[3]/div[1]/div[1]/div/div[1]/div"
            )
        )
        val url = box.first().findElement(By.tagName("div")).getCssValue("background-image")
        if (url == "none") {
            println("Something went wrong, maybe wrong element was selected.")
            return
        }
        val processedUrl = url.replace("url(\"", "").replace("\")", "")
        val fileUUID = UUID.randomUUID().toString()
        val fileToSave = File("/Users/prosavage/Documents/data", "$fileUUID.webp").toPath().toString()
        saveImage(processedUrl, fileToSave)
        Thread.sleep(1000)
        val response = sendReq(fileToSave)
        println(response)
//        val parser = Parser()
//        val json = parser.parse(StringBuilder(response)) as JsonObject
//        val people = json["people"] as JsonArray<JsonObject>
//        println(people[0]["attractiveness"])
    }


}