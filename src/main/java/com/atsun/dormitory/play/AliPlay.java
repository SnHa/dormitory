package com.atsun.dormitory.play;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atsun.dormitory.config.AlipayConfig;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description: TODO(阿里支付)
 * @Author SH
 * @Date 2022/3/2 12:58
 */
@Data
public class AliPlay {

    public static String generatePcPlayHtml(String out_trade_no, String total_amount, String subject, String body) {
//获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        HashMap<String, String> map = new HashMap<>();
        //商户订单号，商户网站订单系统中唯一订单号，必填
        map.put("out_trade_no", out_trade_no);
        //付款金额，必填
        map.put("total_amount", total_amount);
        //订单名称，必填
        map.put("subject", subject);
        //商品描述，可空
        map.put("body", body);

        map.put("product_code", "FAST_INSTANT_TRADE_PAY");
        alipayRequest.setBizContent(JSONObject.toJSONString(map));

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
        return result;
    }

    /**
     * 验证支付宝异步通知签名是否合法
     *
     * @param request request
     * @return boolean
     * @throws UnsupportedEncodingException 异常
     * @throws AlipayApiException           异常
     */
    public static Boolean signVerified(HttpServletRequest request)  {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>(100);
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        try {
            return AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }
}
