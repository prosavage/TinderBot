import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.nio.channels.Channels
import java.nio.channels.FileChannel
import java.nio.channels.ReadableByteChannel


fun waitUntilPageIsReady(driver: ChromeDriver) {
    val executor = driver as JavascriptExecutor
    WebDriverWait(driver, 2)
        .until { executor.executeScript("return document.readyState") == "complete" }
}
@Throws(IOException::class)
fun saveImage(imageUrl: String?, destinationFile: String?) {
    val readableByteChannel: ReadableByteChannel = Channels.newChannel(URL(imageUrl).openStream())
    val fileOutputStream = FileOutputStream(destinationFile)
    val fileChannel: FileChannel = fileOutputStream.channel
    fileOutputStream.channel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE)
}