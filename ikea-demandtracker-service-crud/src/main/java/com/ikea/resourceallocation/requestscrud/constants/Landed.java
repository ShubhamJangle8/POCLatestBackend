package com.ikea.resourceallocation.requestscrud.constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import com.google.common.collect.ImmutableMap;
import com.ikea.resourceallocation.requestscrud.messages.CrudMessages;

public class Landed {

	private static Map<String, String> landed = Map
			.of("1", "Offshore", "2", "Onshore");

	public static List<String> getAllLandedData() {
		List<String> landedId = new ArrayList<String>(landed.values());
		return landedId;
	}

//	public static String getLandedDataValues(String id) {
//		Set<String> landedData = landed.getOrDefault(id.toUpperCase(), CrudMessages.message.get("LandedMsg"));
//		System.out.println(landedData);
//		return landedData;
//	}
}
