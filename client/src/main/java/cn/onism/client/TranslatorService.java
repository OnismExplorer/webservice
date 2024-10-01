package cn.onism.client;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

/**
 * 翻译服务（SOAP调用）
 *
 * @author Onism
 * @date 2024/10/01
 */
@WebService(targetNamespace = "http://WebXml.com.cn/")
public interface TranslatorService {

    /**
     * 基本的单词翻译，返回一个字符串数组
     *
     * @param wordKey 字键
     * @return {@link String[] }
     */
    @WebMethod(operationName = "TranslatorString",action = "http://WebXml.com.cn/TranslatorString")
    String[] translatorString(@WebParam(name = "wordKey") String wordKey);

    /**
     * 提供单词翻译，返回包含翻译结果的 DataSet 数据
     *
     * @param wordKey 字键
     * @return {@link String[] }
     */
    @WebMethod(operationName = "Translator")
    String[] translator(@WebParam(name = "wordKey") String wordKey);

    /**
     * 提供相关单词的候选词建议
     *
     * @param wordKey 字键
     * @return {@link String[] }
     */
    @WebMethod(operationName = "SuggestWord")
    String[] suggestWord(@WebParam(name = "wordKey") String wordKey);

    /**
     * 返回英文单词的发音 MP3 字节流
     *
     * @param wordKey 字键
     * @return {@link byte[] }
     */
    @WebMethod(operationName = "GetMp3")
    byte[] getMp3(@WebParam(name = "wordKey") String wordKey);
}
