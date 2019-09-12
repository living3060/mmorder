package order.mm.com.util;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import order.mm.com.coomm.AppException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class HttpUtil {
    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    ;
    static final String CONTENT_TYPE_TEXT_JSON = "text/json";


    public static String postJson(Object o, String url) throws Exception {
        try {
            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity(JSON.toJSONString(o));
            entity.setContentType(CONTENT_TYPE_TEXT_JSON);
            post.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                throw new AppException("error response status " + response.getStatusLine().getStatusCode(), "-4");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
