package gsuitevalidator;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class GsuiteValidatorCore {

	public Boolean isValid(String emailAddress) throws Exception {
		Boolean valid = null;
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url("https://mail.google.com/mail/gxlu?email=" + emailAddress)
				.addHeader("Content-Type", "application/json").build();

		Response response = client.newCall(request).execute();
		String value = response.header("Set-Cookie");
		if (value != null) {
			valid = true;
		} else {
			valid = false;
		}

		return valid;
	}
}
