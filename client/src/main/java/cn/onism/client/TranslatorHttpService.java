package cn.onism.client;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 翻译服务（HTTP调用）
 *
 * @author Onism
 * @date 2024/10/01
 */
public class TranslatorHttpService {

    private TranslatorHttpService() {
        // do nothing
    }

    /**
     * 发送 HTTP GET 请求并解析 XML 响应为字符串数组
     *
     * @param operationName 操作名称
     * @param wordKey       单词键
     * @return 响应的字符串数组
     * @throws IOException                  io异常
     * @throws ParserConfigurationException 解析器配置异常
     * @throws SAXException                 萨克斯异常
     */
    public static String[] callGetRequest(String operationName, String wordKey) throws IOException, ParserConfigurationException, SAXException {
        // 构造 HTTP GET 请求 URL
        String requestUrl = "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx/" + operationName + "?wordKey=" + wordKey;
        URL url = new URL(requestUrl);

        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 处理响应
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // 将响应解析为 XML 文档
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new java.io.ByteArrayInputStream(content.toString().getBytes()));

        // 提取 <string> 标签中的内容
        NodeList nodeList = doc.getElementsByTagName("string");
        String[] results = new String[nodeList.getLength()];
        for (int i = 0; i < nodeList.getLength(); i++) {
            results[i] = nodeList.item(i).getTextContent();
        }

        return results;
    }

    /**
     * 发送 HTTP GET 请求并获取 MP3 数据
     *
     * @param mp3FileName mp3 文件名
     * @return MP3 的字节数组
     * @throws IOException io异常
     */
    public static byte[] callGetMp3Request(String mp3FileName) throws IOException {
        // 构造 HTTP GET 请求 URL，使用 MP3 文件名称作为参数
        String requestUrl = "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx/GetMp3?Mp3=" + mp3FileName;
        URL url = new URL(requestUrl);

        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 检查响应代码是否为 200
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            // 读取 MP3 数据（实际应处理为字节流）
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // 假设 MP3 数据为二进制，这里返回字节数组（实际情况中需根据服务器响应调整处理逻辑）
            return content.toString().getBytes();
        } else {
            System.out.println("获取 MP3 数据失败，HTTP 响应代码：" + responseCode);
            return new byte[0];
        }
    }
}
