package com.models;

import java.io.Serializable;
import java.lang.reflect.Field;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	// 此方法主要是用来将对象转换成Json的字符串。为每个class（包括内部类，但此时内部类必须为static）增加自动转成Json格式！
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		Field[] fields = getClass().getDeclaredFields();

		sb.append("{");
		for (Field f : fields) {
			f.setAccessible(true);
			sb.append("\"");
			sb.append(f.getName());
			sb.append("\":\"");
			try {
				Object o = f.get(this);
				if (o != null) {
					sb.append(f.get(this).toString()); // 此处内部类也可以进行操作了！已经重写了toString
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			sb.append("\", ");
		}
		sb.append("}");
		return super.toString();
	}
}
