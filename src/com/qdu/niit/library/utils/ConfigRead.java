package com.qdu.niit.library.utils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

class ConfigReader
{
    Properties readUse;

    /**
     * 写入配置信息包括路径
     * @throws IOException
     */
    public void writeIn() throws IOException {
        try {

        }catch (Exception e)
        {
            throw new IOException(e);
        }
        readUse = new Properties();
        readUse.load(new FileReader("", StandardCharsets.UTF_8));
    }
}
