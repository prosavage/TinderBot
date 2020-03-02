import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.*
import java.net.URL
import java.net.URLConnection
import java.nio.channels.Channels
import java.nio.channels.FileChannel
import java.nio.channels.ReadableByteChannel
import java.nio.file.Files
import java.nio.file.Paths


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

fun sendReq(filePath: String): String {
    val url =
        URL("https://api.haystack.ai/api/image/analyze?apikey=bcc555e7f0a8f53f04f3f2abeae71611&output=json&model=attractiveness")
    val conn: URLConnection = url.openConnection()
    conn.setDoOutput(true)

    val imageData: ByteArray = Files.readAllBytes(Paths.get(filePath))
    val os: OutputStream = BufferedOutputStream(conn.getOutputStream())
    os.write(imageData)
    os.close()

    val `is`: InputStream = conn.getInputStream()
    var buffer = ByteArray(1024)
    val responseBuffer = ByteArrayOutputStream()

    while (true) {
        val n: Int = `is`.read(buffer, 0, buffer.size)
        if (n <= 0) break
        responseBuffer.write(buffer, 0, n)
    }

    val response: String = responseBuffer.toString("UTF-8")
    return response
}