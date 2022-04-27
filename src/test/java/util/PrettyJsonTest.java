package util;

import org.junit.Test;

public class PrettyJsonTest {
	private PrettyJson prettyJson = new PrettyJson();

	@Test
	public void printPrettyJsonTest() {
		String input = "{\"squadName\" : \"Super hero squad\", \"homeTown\" : \"Metro City\"}";
		System.out.println("input:\n" + input);
		String result = prettyJson.printPrettyJson(input);
		System.out.println("output:\n" + result);
	}

	@Test
	public void printPrettyJsonTest3() {
		String input = "{\"key1\":\"value1\",\"key2\":2}";
		System.out.println("input:\n" + input);
		String result = prettyJson.printPrettyJson(input);
		System.out.println("output:\n" + result);
	}

	@Test
	public void printPrettyJsonTest4() {
		String input = "{\"key1\":\"value1\",\"key2\":2,\"list\":[\"aaa\",\"bbb\"], \"group\":[{\"name\":\"test\",\"age\":30},{\"name\":\"gogo\",\"age\":20}]}";
		System.out.println("input:\n" + input);
		String result = prettyJson.printPrettyJson(input);
		System.out.println("output:\n" + result);
	}

	@Test
	public void printPrettyJsonTest2() {
		String input = "{\n" +
			"  \"name\": \"chpark\",\n" +
			"  \"job\": \"Software engineer\"\n" + "}";
		System.out.println("input:\n" + input);
		String result = prettyJson.printPrettyJson(input);
		System.out.println("output:\n" + result);
	}
}