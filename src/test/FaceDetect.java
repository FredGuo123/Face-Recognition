package test;

import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.aip.util.Base64Util;
import com.bejson.pojo.Face_list;
import com.bejson.pojo.JsonRootBean;
import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.ai.aip.utils.GsonUtils;

import java.io.File;
import java.util.*;

/**
* 人脸检测与属性分析
*/
public class FaceDetect {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String faceDetect(String filePath) {
        // 请求url
    	File file = new File(filePath);
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image",Base64Util.encode(FileUtil.readFileByBytes(filePath)));
            map.put("face_field", "age,beauty,expression,face_shape,gender,glasses,race,quality,eye_status,emotion,face_type,location");
            map.put("image_type", "BASE64");
            String param = GsonUtils.toJson(map);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//    	String resultjson = FaceDetect.faceDetect("C:\\Users\\Fred's LEGION\\Desktop\\2.png");
//    	System.out.println(resultjson);
//    	JsonRootBean rootBean = GsonUtils.fromJson(resultjson, JsonRootBean.class);
//    	Face_list list = rootBean.result.face_list[0];
//    	System.out.println("年龄："+list.getAge());
//    	System.out.println("颜值："+list.getBeauty());
//    }
}