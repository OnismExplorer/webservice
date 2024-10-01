package cn.onism.client;

import jakarta.xml.ws.Service;

import javax.xml.namespace.QName;
import java.net.URL;

/**
 * 翻译客户端（SOAP调用）
 *
 * @author Onism
 * @date 2024/10/01
 */
public class TranslatorClient {

    public static void main(String[] args) throws Exception {
        // 定义 WSDL 的 URL 和服务的命名空间和名称
        URL wsdlUrl = new URL("http://fy.webxml.com.cn/webservices/EnglishChinese.asmx?wsdl");
        QName qname = new QName("http://WebXml.com.cn/", "EnglishChinese");

        // 创建服务对象
        Service service = Service.create(wsdlUrl, qname);

        // 获取 Web 服务接口的代理对象，使用 EnglishChineseSoap 作为端口名称
        QName portName = new QName("http://WebXml.com.cn/", "EnglishChineseSoap");
        TranslatorService translator = service.getPort(portName, TranslatorService.class);

        // 示例：调用 TranslatorString 操作
        String wordKey = "hello";
        String[] translation = translator.translatorString(wordKey);
        System.out.println("单词 '" + wordKey + "' 的翻译结果：");
        for (String result : translation) {
            System.out.println(result);
        }

        // 示例：调用 SuggestWord 操作
        String[] suggestions = translator.suggestWord(wordKey);
        System.out.println("单词 '" + wordKey + "' 的候选词：");
        for (String suggestion : suggestions) {
            System.out.println(suggestion);
        }

        // 示例：调用 GetMp3 操作
        byte[] mp3Data = translator.getMp3(wordKey);
        if (mp3Data != null) {
            System.out.println("已获取单词 '" + wordKey + "' 的 MP3 数据。");
        }
    }
}
