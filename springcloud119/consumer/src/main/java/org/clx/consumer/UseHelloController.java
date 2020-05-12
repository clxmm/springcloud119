package org.clx.consumer;

import org.clx.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author clx
 * @date 2020-02-20 15:16
 */
@RestController
public class UseHelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 借助 Eureka Client 提供的 DiscoveryClient 工具，利用这个工具，我们可以根据
     * 服务名从 Eureka Server 上查询到一个服务的详细信息，改造后的代码如下：
     *
     * @return
     */
    @GetMapping("/hello1")
    public String hello1() {
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        ServiceInstance serviceInstance = provider.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        HttpURLConnection connection = null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        try {
            URL url = new URL(stringBuffer.toString());
            connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String s = reader.readLine();
                reader.close();
                return s;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "";
    }


    @Autowired
    RestTemplate restTemplate;

    @GetMapping("hello4")
    public void hello4() {


        String s1 = restTemplate.getForObject("http://127.0.0.1:10001/hello2?name={1}", String.class, "clx1");
        System.out.println(s1);
        ResponseEntity<String> entity = restTemplate.getForEntity("http://127.0.0.1:10001/hello2?name={1}", String.class, "clx2");
        String body = entity.getBody();
        System.out.println("body: " + body);
        HttpStatus statusCode = entity.getStatusCode();
        System.out.println("statusCode: " + statusCode);
        int statusCodeValue = entity.getStatusCodeValue();
        System.out.println("codeValue: " + statusCode);

        HttpHeaders headers = entity.getHeaders();
        Set<String> keySet = headers.keySet();

        System.out.println("header: --");
        for (String s : keySet) {
            System.out.println(s + ":" + headers.get(s));
        }

    }

    @GetMapping("hello5")
    public void hello5() throws UnsupportedEncodingException {
        String s1 = restTemplate.getForObject("http://127.0.0.1:10001/hello2?name={1}", String.class, "clx1");
        System.out.println(s1);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "clx2");
        String s2 = restTemplate.getForObject("http://127.0.0.1:10001/hello2?name={name}", String.class, map);
        System.out.println(s2);


        String uri = "http://127.0.0.1:10001/hello2?name=" + URLEncoder.encode("楚留香", "utf-8");
        String s3 = restTemplate.getForObject(uri, String.class);
        System.out.println(s3);

    }


    @GetMapping("/hello6")
    public void hello6() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "clx");
        map.add("password", "123");
        map.add("id", 99);

        User user = restTemplate.postForObject("http://127.0.0.1:10001/user1", map, User.class);
        System.out.println(user);


        user.setId(98);
        User user1 = restTemplate.postForObject("http://127.0.0.1:10001/user2", user, User.class);
        System.out.println(user1);


    }

    @GetMapping("/hello7")
    public void hello7() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "clx");
        map.add("password", "123");
        map.add("id", 99);
        URI uri = restTemplate.postForLocation("http://127.0.0.1:10001/register", map);
        System.out.println(uri);
        String s = restTemplate.getForObject(uri, String.class);
        System.out.println(s);
    }


    @GetMapping("/hello8")
    public void hello8() {

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "clx");
        map.add("password", "123");
        map.add("id", 99);
        restTemplate.put("http://127.0.0.1:10001/user1", map);

        User user = new User();
        user.setUsername("name");
        restTemplate.put("http://127.0.0.1:10001/user2",user);
    }


    @GetMapping("/hello9")
    public  void  hello9() {

//        restTemplate.delete("http://127.0.0.1:10001/user2/2");

        restTemplate.delete("http://provider/user1?id={1}",1);


    }


}
