package helpers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

// 解析网络来源的数据，暂时未用到！

public class GsonHelper {
	private JsonObject jsonObject;

	public GsonHelper(JsonElement jsonElement) {
		this(jsonElement.getAsJsonObject());
	}

	public GsonHelper(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getString(String key, String defValue) {
		JsonElement v = getJsonElement(jsonObject, key);
		if (v != null) {
			return v.getAsString();
		} else {
			return defValue;
		}
	}

	private static JsonElement getJsonElement(JsonObject jsonObject, String key) {
		if (jsonObject != null) {
			return jsonObject.get(key);
		} else {
			return null;
		}
	}

	public static String getString(JsonObject jsonObject, String key,
			String defValue) {
		if (jsonObject == null) {
			return defValue;
		}
		JsonElement v = getJsonElement(jsonObject, key);
		if (v == null) {
			return defValue;
		} else {
			return v.getAsString();
		}
	}

	public static String getString(JsonElement jsonElement, String key,
			String defValue) {
		return getString(jsonElement.getAsJsonObject(), key, defValue);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}

	public static <T> T fromJson(JsonElement element, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(element, clazz);
	}
}
