package gsuitevalidator;

import org.json.JSONObject;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class GsuiteValidatorCore2 {

	public Boolean isValid(String emailAddress) throws Exception {
		Boolean valid = null;
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"input01\":{\"Input\":\"GmailAddress\",\"GmailAddress\":\""
				+ emailAddress + "\",\"FirstName\":\"\",\"LastName\":\"\"},\"Locale\":\"en\"}");
		Request request = new Request.Builder().url("https://accounts.google.com/InputValidator?resource=SignUp")
				.post(body).addHeader("Content-Type", "application/json").build();

		Response response = client.newCall(request).execute();
		String responseStr = response.body().string();
		JSONObject responseJson = new JSONObject(responseStr);
			
			String errorMsg = ((JSONObject) responseJson.getJSONObject("input01")).getString("ErrorMessage");
		if(errorMsg!=null) {
			if(errorMsg.equalsIgnoreCase("That looks like your email address. You can enter that below. Choose a new Google username (which will also be your new gmail.com address).")) {
				valid = false;
			} else if(errorMsg.equalsIgnoreCase("You entered an email address that is already associated with an account.")) {
				valid = true;
			}
			}
		
		return valid;
	}
}
