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
* ������������Է���
*/
public class FaceDetect {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
    public static String faceDetect(String filePath) {
        // ����url
    	File file = new File(filePath);
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image",Base64Util.encode(FileUtil.readFileByBytes(filePath)));
            map.put("face_field", "age,beauty,expression,face_shape,gender,glasses,race,quality,eye_status,emotion,face_type,location");
            map.put("image_type", "BASE64");
            String param = GsonUtils.toJson(map);
            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
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
//    	System.out.println("���䣺"+list.getAge());
//    	System.out.println("��ֵ��"+list.getBeauty());
//    }
}