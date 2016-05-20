package com.bhupendra.oci.rc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Executor {

	private static final Logger LOGGER = Logger.getLogger(Executor.class);
	private static final String VK_ENTER = "\n";

	public int execute(String command) {
		LOGGER.debug("Executing command: " + command);
		Process process = null;
		try {
			process = new ProcessBuilder().command(command, "-C").inheritIO().start();
			process.waitFor();
			LOGGER.debug("Execution completed successfully");
			return process.exitValue();
		} catch (Exception e) {
			LOGGER.error("Execution completed with error: ", e);
		}
		return -1;
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public static Map<String, String> steps;

	public static void main(String[] args) throws IOException, InterruptedException {

		PropertyConfigurator.configure("resources/log4j.properties");
		// Executor e = new Executor();
		// e.execute("D:\\Bhupendra\\Project\\Learn\\Maven\\Techathon\\OCI\\src\\com\\bhupendra\\oci\\rc\\a.bat");

		steps = new HashMap<String, String>();
		steps.put("Please read the Legal Notices in the documentation.", "");
		steps.put("Choose the installation directory", "F:\\TUI_TEST\\");

		Process ssh = Runtime.getRuntime().exec(new String[] { "F:\\All Share\\15x\\3DSpace-V6R2015x.Windows64\\3DSpace.Windows64\\1\\StartTUI.exe" });
		OutputStream stdIn = ssh.getOutputStream();

		String line = null;
		String preLine = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(ssh.getInputStream()));
		while ((line = input.readLine()) != null) {
			if (line == null || line.trim().isEmpty())
				continue;
			System.out.println(line);
			processLine(stdIn, line, preLine);
			preLine = line;

		}
		// readState(stdOut, stdIn);
		// readState(stdOut, stdIn);

	}

	private static void processLine(OutputStream stdIn, String line, String preLine) throws IOException, UnsupportedEncodingException {
		String value = steps.get(preLine);
		if (value != null) {
			value += VK_ENTER;
			stdIn.write(value.getBytes());
			stdIn.flush();
		}
	}

	private static void readState(Reader stdOut, OutputStream stdIn) throws IOException, UnsupportedEncodingException {
		char[] passRequest = new char[1280];
		int len = 0;
		while (true) {
			len += stdOut.read(passRequest, len, passRequest.length - len);
			System.out.println("LEN:" + len);
			if (len != -1) {
				String line = new String(passRequest, 0, len);
				System.out.println(line);
				if (steps.containsKey(line.trim())) {
					stdIn.write(steps.get(line).getBytes("US-ASCII"));
					stdIn.flush();
				}
				break;
			}

		}
	}
}
