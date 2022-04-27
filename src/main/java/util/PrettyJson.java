package util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class PrettyJson {
	private static final int DEFAULT_INDENT_LENGTH = 4;

	private int indent = DEFAULT_INDENT_LENGTH;

	public String printPrettyJson(String src) {
		src = src.replaceAll("\n", "");
		src = src.replaceAll("\r", "");

		StringBuilder sb = new StringBuilder();
		JsonFactory jsonFactory = new JsonFactory();
		try {
			int depth = 0;
			InputStream stdin = new ByteArrayInputStream(src.getBytes("UTF-8"));
			JsonParser parser = jsonFactory.createParser(stdin);
			boolean inArray = false;
			while (parser != null && parser.nextToken() != null) {
				int id = parser.getCurrentToken().id();
				if (id == JsonToken.START_OBJECT.id()) {
					addIndent(sb, depth);
					sb.append(JsonToken.START_OBJECT.asString()).append("\n");
					depth += 1;
					System.out.println("start_obj. depth:" + depth);
				}
				else if (id == JsonToken.END_OBJECT.id()) {
					depth -= 1;
					addIndent(sb, depth);
					sb.append(JsonToken.END_OBJECT.asString()).append(",\n");
				}
				else if (id == JsonToken.FIELD_NAME.id()) {
					inArray = false;
					System.out.println("field. depth:" + depth + " " + parser.getCurrentName());
					addIndent(sb, depth);
					sb.append(parser.getCurrentName()).append(" : ");
				}
				else if (id == JsonToken.VALUE_STRING.id()) {
					System.out.println("string. depth:" + depth);
					addIndentInArray(sb, depth, inArray);
					sb.append(parser.getText()).append(",\n");
				}
				else if (id == JsonToken.VALUE_NUMBER_INT.id()) {
					addIndentInArray(sb, depth, inArray);
					sb.append(parser.getText()).append(",\n");
				}
				else if (id == JsonToken.START_ARRAY.id()) {
					System.out.println("start_array. depth: " + depth);
					inArray = true;
					depth += 1;
					sb.append(JsonToken.START_ARRAY.asString()).append("\n");
				}
				else if (id == JsonToken.END_ARRAY.id()) {
					inArray = false;
					depth -= 1;
					addIndent(sb, depth);
					sb.append(JsonToken.END_ARRAY.asString()).append("\n");
				}

				else {
					System.out.println("unknown id: " + id);
				}
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("sb: " + sb.toString());

		return sb.toString();
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	private void addIndent(StringBuilder sb, int depth) {
		for (int i = 0; i < indent * depth; ++i) {
			sb.append(" ");
		}
	}

	private void addIndentInArray(StringBuilder sb, int depth, boolean isInArray) {
		if (isInArray) {
			addIndent(sb, depth);
		}
	}
}
