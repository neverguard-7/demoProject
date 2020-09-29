package com.example.demo.officialAccounts;

import com.github.binarywang.java.emoji.EmojiConverter;
import org.junit.Test;
import org.springframework.stereotype.Component;

@Component
public class businessImpl {

    @Test
    public void test()
    {
        String content = "xF0x9Fx98x82xF0x9F ";
        EmojiConverter emojiConverter = EmojiConverter.getInstance();
        content= emojiConverter.toAlias(content);//将聊天内容进行转义
        System.out.println(content);
    }


}
