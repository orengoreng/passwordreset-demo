package com.gumtree.demo.business;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Object to Json Writer utility class
 */
@Data
@AllArgsConstructor
public class ObjectToJsonFileWriterUtil {

	private String folder;
	private String filename;

	public void writeJsonFile(Object content) {
		try {
			// Convert to JSON
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(content);
			// Create saved directory
			Path path = Paths.get(folder);
			if (!Files.exists(Paths.get(folder))) {
				Files.createDirectory(path);
			}
			Path savedPath = Paths.get(folder + "/" + filename);
			byte[] strToBytes = json.getBytes();

			Files.write(savedPath, strToBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
