package gsuitevalidator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class GsuiteValidator {

	public static void main(String[] args) throws IOException {
		Long startTime = System.currentTimeMillis();
		int count = 0;
		System.out.println("Started");
		GsuiteValidatorCore2 gsuiteValidatorCore = new GsuiteValidatorCore2();
		List<String> emailAddresses = FileUtils.readLines(new File(args[0]));
		for (String emailAddress : emailAddresses) {
			try {
				count++;
				System.err.println("count" + count);
				Boolean valid = gsuiteValidatorCore.isValid(emailAddress);
				FileUtils.write(new File(args[1]), emailAddress + "," + valid + "\n", true);
				Thread.sleep(1 * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Completed in " + (System.currentTimeMillis() - startTime) + " ms");
	}
}
