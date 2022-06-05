package com.atsun.dormitory.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000118608635";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQClGp7fjoOlInu/MgF9ihHz7s/RhA3KBT34F3vxMcTOnnIGkBOvkKfqLYv9oSdSSdDdaB1+bovZTXIb+j9PibA7ETzuGY/NctKCr2YW2+68Lp0hv7Ly8BGFgHoiyNegD5x9UzDU6tGvKGkwCAVOTKWghkkqt5L476NUZHadTi7IlYeaZ1o4sW75cfzvKQN11PaT+Cdw+mMrCbWl5qHZZmZgdtL9CSIRjvfdem4JX0PokLQ9r9Vd+E6X8Jhoq7mI7nAzKweCfR+clG0K4zNhDPyFnIrRYHNZ6mBn2NMAEbrFoj25dZWED1nCMRlo9KiiBV/kx9nNb3/5/U6CYwmpVRgxAgMBAAECggEAdNXOwbLPNAef/F/9sAIJzE4d4E0LmP92p96uhbNLa0+yrzQ7ygya1tveaeAlvFSiKlvoKzU9X8wvSN8g+D8eZ949+Nw76A4uK+7FVr3SRnbSGTTYBJSgQZH2XDE2bX0+p208bB+zvLjjHus+HfedaWg91K3Nor2qHiltysmtz6DicW2UWubkj6EQgrLnse4cSDLkvfF2ox+PnRKBNTp9YhcQNRSEIYkThTUtBh21GVJ9Ym16U2rv8nsyHT7VR0WKOlVW1e2hIEhsVd44UD29kmvU+15n8Fnk5655pY3Lrqas5MyxOBRVcnoAb62nC3Dqqy4+l+3e9Mh7X8ZidJdoAQKBgQDxgWxIcpGjO04lNGPqJ9rWdz1jxZi03c85KXgOTCfGc82s5tKn3DsZxuPwjCgOsW+gr9X3wDP+kjUxHkj17XfoQqDl6xlxm6e5VExPa1Ga18Df57zaa+9P7+bRwmc5kVc9YvVW8rLFvtDKT8uqgQrzzNosH79TGI7NlKs7KGm4sQKBgQCvA1Y3bEvWU8FrtHVSa7QFQuedTnfZ9iIW5JUMj+mWCPQkPJ3nbovqxYXsZ+vK8c/Ey6dJmUFtJ47EAdew3crExTzclEHJZ79rEC5c+MjB60bRt4xzTol+sZxFsxFoJYk92Cl4YqScaP2x72xPcthf2YDkV4NkclQiQnIJ07E3gQKBgQCzOoae+Y/F4VRsXgYsl6lb8qA3tERRLjAEzxbPHYuDRxwDzkXV16Zwyd8N/SYZSWt7iCM2O4kay2z4Z+5jvPl5AEGigCig3JDMBJihdBba4HW+dZ/9RY8T9wmI8EplOczRvOgwv+sWwDb5feC7SAtVxdjAWEtIIviP715oh+R18QKBgQChVyEnTOYI+wOoCB/UUMgPxaMCSPGCdT7t/eJOR2me0DhKI7uqPCYu09NnolIksJHKqbhZyDghqcMafVxbh8xDZFV8QAz54HP9/lfPi+Z/pyeNdHm9XxywOIqJUtBsQ5Zm9h0dB0sbikq+u6sM/yHsEvaZCvhtJFzdjIXW+1o3gQKBgQDYSuxCQBVLICTgw0hgYM0bqAIssZ+Sq8WLlPZwbbkDQ2lYEbn0FZk4FxzWvoAIZuXg3r2Heac/x0WBdymqj9Nj3Y/WsVUM+oVk2EPKqoYPTVykMmsuldYBMpwbdS/rKUCD5CM+Cl+cJad2+JJRW9v9rCuP0TMYvGukdvU+CXs9rA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz4mEp2GHG2OvRwND5OvCAleSAoRMiOFfwrpGXV2iTnxCZlfoSdk4aPPWgToMJpRYwMjMxLolo98EkCy1+yWBmjUKQtVS9nv8l0b1bxiTSfQqOOqVbcxqjp4MGbTCKO0Joyb+xgCBPT2OzLrkp55BUjSOywUL/F4DrST1h5hwL/EOQT43MspHlhPdBPKpB/KOrDMOHMfy4+DrYuF8WEoLDfWlUKbm6EAWsScYxF2x2/dw6/lvyynDTJ/3ZiBjn0i34/Zjib75rtxczJm/VErBrkNkugNY4LcCCzb4QPgacC2VZyDzfkUUu+x21kFObwMz51dyODxKuWLWV3kl4sJxsQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://sun1.free.idcfengye.com/ali/notinfy";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8087/#/pay/zfb/index.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

