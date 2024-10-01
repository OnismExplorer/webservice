package cn.onism.client;

/**
 * 翻译客户端（HTTP调用）
 *
 * @author Onism
 * @date 2024/10/01
 */
public class TranslatorHttpClient {
    public static void main(String[] args) {
        try {
            // 要翻译的单词
            String wordKey = "hello";

            // 调用 TranslatorString 操作
            String[] translation = TranslatorHttpService.callGetRequest("TranslatorString", wordKey);
            System.out.println("单词 '" + wordKey + "' 的翻译结果：");
            for (String result : translation) {
                System.out.println(result);
            }

            // 调用 SuggestWord 操作
            String[] suggestions = TranslatorHttpService.callGetRequest("SuggestWord", wordKey);
            System.out.println("单词 '" + wordKey + "' 的候选词：");
            for (String suggestion : suggestions) {
                System.out.println(suggestion);
            }

            // 调用 GetMp3 操作
            byte[] mp3Data = TranslatorHttpService.callGetMp3Request(wordKey);
            if (mp3Data != null) {
                System.out.println("已获取单词 '" + wordKey + "' 的 MP3 数据。");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
