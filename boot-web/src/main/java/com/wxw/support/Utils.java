package com.wxw.support;


import com.alibaba.fastjson.JSON;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.thymeleaf.util.DateUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * 格式化字节大小
     * @param size
     * @return
     */
    public static String formatSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + "" + units[digitGroups];
    }

    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }
    public static int getStrLengthInSqlServer(String str) {
        if(StringUtils.isEmpty(str)) return 0;
        int count = str.length();
        for(char c : str.toCharArray()){
            if(String.valueOf(c).matches("[\\u4E00-\\u9FA5]")) count++;
        }
        return count;
    }

    /**
     * 与.net一致
     *
     * @param d 入参
     * @return v
     */
    public static double floor(double d) {
        return Math.floor(d * 100) / 100;
    }

    public static String schoolName(String s) {
        String pattern2 = "(\\(|（).*(\\)|）)";
        s = s.replaceAll(pattern2, "");
        return s;
    }

    public static String replaceFloatToPrecent(String str) {
        // String str = "0.03*f11*0.12";
        String pattern = "0.[0-9]{1,3}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        // System.out.println(m.matches());
        while (m.find()) {
            String s = m.group(0);
            float i = NumberUtils.toFloat(s) * 100;
            String strr = (int) i + "%";
            if (i != (int) i) {
                strr = (float) (Math.round(i * 10)) / 10 + "%";
            }
            str = str.replaceAll(s, strr);
        }
        return str;
    }

    public static boolean ifStringNotEmpty(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 简单加密，把一个字符串在原有的基础上+1
     *
     * @param data 需要解密的原字符串
     * @return 返回解密后的新字符串
     */
    public static String addOneEncode(String data) {
        // 把字符串转为字节数组
        byte[] b = data.getBytes();
        // 遍历
        for (int i = 0; i < b.length; i++) {
            b[i] += 1;// 在原有的基础上+1
        }
        return new String(b);
    }

    /**
     * 简单解密：把一个加密后的字符串在原有基础上-1
     *
     * @param data 加密后的字符串
     * @return 返回解密后的新字符串
     */
    public static String addOneDecode(String data) {
        // 把字符串转为字节数组
        byte[] b = data.getBytes();
        // 遍历
        for (int i = 0; i < b.length; i++) {
            b[i] -= 1;// 在原有的基础上-1
        }
        return new String(b);
    }

    /**
     * 比较两个List集合是否相等
     *
     * @param list1
     * @param list2
     * @return
     */
    public static <E> boolean equalsUtil(List<E> list1, List<E> list2) {
        // 两个list引用相同（包括两者都为空指针的情况）
        if (list1 == list2) {
            return true;
        }
        // 两个list都为空（包括空指针、元素个数为0）
        if ((list1 == null || list1.size() == 0) && (list2 == null || list2.size() == 0))
            return true;

        // 其中一个为空，另一个不为空
        if ((list1 == null && list2 != null && list2.size() != 0)
                || (list2 == null && list1 != null && list1.size() != 0)) {
            return false;
        }

        // 两个list元素个数不相同
        if (list1.size() != list2.size()) {
            return false;
        }

        if (!list1.containsAll(list2)) {
            return false;
        }

        return true;
    }

    public static String decimalFormat(int value, int length) {
        String valueStr = String.valueOf(value);
        while (valueStr.length() < length) {
            valueStr = "0" + valueStr;
        }
        return valueStr;
    }

    /**
     * 如果两个splitForArr之间有多个splitForKeyValue，以第一个为准
     * 如果两个splitForKeyValue之间有多个splitForArr，以最后一个为准
     * @param str 需要被分割的str
     * @param splitForKeyValue key-value中的分隔符
     * @param splitForArr 各个数据之间的分隔符
     * @return 返回解析后的 map list
     */
    public static List<Map<String, String>> strFormatKeyValueMap(String str, String splitForKeyValue, String splitForArr) {
        List<Map<String, String>> maps = new ArrayList<>();
        int equalsIndex = -1;
        int commaIndex = -1;
        int lastCommaIndex = -1;
        int nextEqualsIndex = -1;
        boolean isEnd = false;
        if(StringUtils.isNotBlank(str)){
            while (!isEnd){
                equalsIndex = str.indexOf(splitForKeyValue, lastCommaIndex+1);
                int preCommaIndex = equalsIndex == -1 ? -1 : str.substring(0,equalsIndex).lastIndexOf(splitForArr);
                Map<String, String> m = new HashMap<>();
                if(equalsIndex == -1){
                    m.put("key", str.substring(preCommaIndex+1));
                    m.put("value","");
                    if(m.get("key") != null && StringUtils.isNotBlank(m.get("key")))
                        maps.add(m);
                    break;
                }
                commaIndex = str.indexOf(splitForArr,equalsIndex);

                if(commaIndex == -1 || (nextEqualsIndex = str.indexOf(splitForKeyValue,commaIndex)) == -1){
                        lastCommaIndex = str.length()- (str.endsWith(splitForArr)?splitForArr.length():0);
                    isEnd = true;
                }else {
                    lastCommaIndex = str.substring(0,nextEqualsIndex).lastIndexOf(splitForArr);
                }

                m.put("key", str.substring(preCommaIndex+1,equalsIndex));
                m.put("value",str.substring(equalsIndex+1,lastCommaIndex));

                if(m.get("key") != null && StringUtils.isNotBlank(m.get("key")))
                    maps.add(m);
            }
        }
        return maps;
    }

    public static <T extends Serializable> List<T> deepCopy(List<T> objList){
        List<T> res = new ArrayList<>();
        objList.forEach(a->res.add(deepCopy(a)));
        return res;
    }
    public static <T extends Serializable> T deepCopy(T obj){
        T cloneObj = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){
            oos.writeObject(obj);
            final byte[] buf = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bis);
            cloneObj = (T) ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
    public static<T> String getKeyValueByClass(Class<T> c, String parseType){
        StringBuilder sb = new StringBuilder();
        String ignoreField = "serialVersionUID";
        parseType = StringUtils.lowerCase(parseType);
        HashMap<Class<?>, Object> classObjectHashMap = new HashMap<>();
        classObjectHashMap.put(Integer.class,1);
        classObjectHashMap.put(String.class,"2");
        classObjectHashMap.put(Double.class,3D);
        classObjectHashMap.put(Boolean.class,false);
        classObjectHashMap.put(Float.class,4f);
        classObjectHashMap.put(Date.class,new Date());
        if(parseType.equals("json")){
            try {
                Constructor<T> constructor = c.getConstructor();
                T t = constructor.newInstance();
                Field[] declaredFields = c.getDeclaredFields();
                for(Field f: declaredFields){
                    String name = f.getName();
                    if(ignoreField.contains(name)) continue;
                    Class<?> type = f.getType();
                    String methodStr = "set" + StringUtils.capitalize(name);
                    Method method = c.getMethod(methodStr, type);
                    Object value = classObjectHashMap.get(type);
                    method.invoke(t, value);
                }
                sb.append(JSON.toJSONString(t));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }else if(parseType.equals("bulk")){
            Field[] declaredFields = c.getDeclaredFields();
            for(Field f: declaredFields) {
                String name = f.getName();
                if(ignoreField.contains(name)) continue;
                Class<?> type = f.getType();
                Object value = classObjectHashMap.get(type);
                value = value == null ? new Object() : value;
                if(type.equals(Date.class)){
                    sb.append(name).append(":").append(DateUtils.format((Date) value, Locale.forLanguageTag("yyyy-MM-dd HH:mm:ss"))).append("\n");
                }else {
                    sb.append(name).append(":").append(value.toString()).append("\n");
                }
            }
        }
        return sb.toString();
    }

    public static<E> Object getObjectValue(E o, String field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return o.getClass().getMethod("get" + StringUtils.capitalize(field)).invoke(o);
    }

    public static List<Map<String, String>> formatStringObjectMap(Map<String, Object> map) {
        List<Map<String, String>> mapList = new ArrayList<>();
        if (map == null || map.size() == 0) return mapList;
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for(var entry: entries){
            Map<String, String> m = new HashMap<>();
            m.put("key", entry.getKey());
            Object value = entry.getValue();
            m.put("value",objectToString(value));
            mapList.add(m);
        }
        return mapList;
    }

    private static String objectToString(Object value) {
        if(value == null) return "";
        if(value instanceof String || value instanceof Integer || value instanceof Long || value instanceof Boolean || value instanceof Double){
            return value.toString();
        }
        return JSON.toJSONString(value);
    }

    public static List<Map<String, String>> formatStringArrayMap(Map<String, String[]> map) {
        List<Map<String, String>> mapList = new ArrayList<>();
        if (map == null || map.size() == 0) return mapList;
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        for(var entry: entries){
            Map<String, String> m = new HashMap<>();
            m.put("key", entry.getKey());

            String[] value = entry.getValue();
            String valueStr= "";
            if(value != null && value.length > 0){
                if(value.length == 1){
                    valueStr = value[0];
                }else {
                    valueStr = Arrays.toString(value);
                }
            }
            m.put("value", valueStr);
            mapList.add(m);
        }
        return mapList;
    }

    public static<E> E notNullElse(E e1, E e2) {
        if(e1 != null)
            return e1;
        else
            return e2;
    }
    public static String batchReplace(String text, String searchStr, Object... replacement){
        for(Object replace: replacement){
            if(replace == null) replace = "";

            text = text.replaceFirst(searchStr, replace.toString());
        }
        return text;
    }
    public static String defaultBatchReplace(String text, Object... replacement){
        return batchReplace(text,"\\{\\}",replacement);
    }
    public static String lastWord(String text){
        int index;
        if(StringUtils.isNotEmpty(text) && (index = text.lastIndexOf(".")) != -1){
            return text.substring(index+1);
        }
        return null;
    }

}
