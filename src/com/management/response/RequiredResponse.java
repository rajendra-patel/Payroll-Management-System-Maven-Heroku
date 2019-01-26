package com.management.response;

import java.util.HashMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.management.model.Leave;

public class RequiredResponse {
	
	public ResponseBuilder setResponse(HashMap<Integer, Leave> kek) {
//		if(kek==null)
//		return Response.noContent();
//		else 
			return Response.ok(kek);
	}

}
