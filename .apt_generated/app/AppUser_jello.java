package app;

import com.google.gson.Gson;
import jello.model.EntityDef;
import jello.model.EntityDefFactory;

public class AppUser_jello implements EntityDefFactory {

@Override
public EntityDef getEntityDef() {

StringBuffer json = new StringBuffer();

json.append("{");
json.append("	\"name\" : \"app.AppUser\",");
json.append("	\"fields\" : [");
json.append("		{");
json.append("			\"name\" : \"id\",");
json.append("			\"type\" : \"java.lang.String\",");
json.append("			\"isPublic\" : true,");
json.append("			\"isStatic\" : false");
json.append("		},");
json.append("		{");
json.append("			\"name\" : \"logs\",");
json.append("			\"type\" : \"java.util.List<app.Log>\",");
json.append("			\"isPublic\" : false,");
json.append("			\"isStatic\" : false");
json.append("		},");
json.append("		{");
json.append("			\"name\" : \"profile\",");
json.append("			\"type\" : \"java.lang.String\",");
json.append("			\"isPublic\" : true,");
json.append("			\"isStatic\" : false");
json.append("		}");
json.append("	],");
json.append("	\"actions\" : [");
json.append("		{");
json.append("			\"name\" : \"createUser\",");
json.append("			\"isPublic\" : true,");
json.append("			\"isStatic\" : true,");
json.append("			\"isAsync\" : null,");
json.append("			\"accessible\" : [],");
json.append("			\"params\" : [");
json.append("			],");
json.append("			\"returnType\" : \"void\"");
json.append("		}");
json.append("	]");
json.append("}");

return (new Gson()).fromJson(json.toString(), EntityDef.class);
}
}
