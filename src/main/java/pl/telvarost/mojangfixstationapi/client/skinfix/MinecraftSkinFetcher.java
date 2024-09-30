/*
 * Copyright (C) 2024 js6pak
 *
 * This file is part of MojangFixStationAPI.
 *
 * MojangFixStationAPI is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, version 3.
 *
 * MojangFixStationAPI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with MojangFixStationAPI. If not, see <https://www.gnu.org/licenses/>.
 */

package pl.telvarost.mojangfixstationapi.client.skinfix;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonArray;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class MinecraftSkinFetcher {

    private static final String PROFILE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";

    public static String getSkinUrl(String uuid) {
        try {
            String profileJson = fetchProfileJson(uuid);
            if (profileJson == null) {
                return "";
            }

            String base64Textures = extractBase64Textures(profileJson);
            if (base64Textures == null) {
                return "";
            }

            String decodedTextures = new String(Base64.getDecoder().decode(base64Textures));
            JsonObject decodedJson = parseJson(decodedTextures);

            if (decodedJson != null && decodedJson.getObject("textures") != null) {
                JsonObject skinObject = decodedJson.getObject("textures").getObject("SKIN");
                if (skinObject != null && skinObject.get(String.class, "url") != null) {
                    return skinObject.get(String.class, "url");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getCapeUrl(String uuid) {
        try {
            String profileJson = fetchProfileJson(uuid);
            if (profileJson == null) {
                return "";
            }

            String base64Textures = extractBase64Textures(profileJson);
            if (base64Textures == null) {
                return "";
            }

            String decodedTextures = new String(Base64.getDecoder().decode(base64Textures));
            JsonObject decodedJson = parseJson(decodedTextures);

            if (decodedJson != null && decodedJson.getObject("textures") != null) {
                JsonObject capeObject = decodedJson.getObject("textures").getObject("CAPE");
                if (capeObject != null && capeObject.get(String.class, "url") != null) {
                    return capeObject.get(String.class, "url");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean hasSlimArms(String uuid) {
        try {
            String profileJson = fetchProfileJson(uuid);
            if (profileJson == null) {
                return false;
            }

            String base64Textures = extractBase64Textures(profileJson);
            if (base64Textures == null) {
                return false;
            }

            String decodedTextures = new String(Base64.getDecoder().decode(base64Textures));
            JsonObject decodedJson = parseJson(decodedTextures);

            if (decodedJson != null && decodedJson.getObject("textures") != null) {
                JsonObject skinObject = decodedJson.getObject("textures").getObject("SKIN");
                if (skinObject != null && skinObject.get(String.class, "metadata") != null) {
                    JsonObject metadata = skinObject.getObject("metadata");
                    if (metadata != null && "slim".equals(metadata.get(String.class, "model"))) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String fetchProfileJson(String uuid) throws Exception {
        URL url = new URL(PROFILE_URL + uuid);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        return content.toString();
    }

    private static String extractBase64Textures(String profileJson) {
        try {
            JsonObject profileObject = parseJson(profileJson);
            if (profileObject != null) {
                JsonElement propertiesElement = profileObject.get("properties");
                if (propertiesElement instanceof JsonArray) {
                    JsonArray properties = (JsonArray) propertiesElement;

                    for (JsonElement propertyElement : properties) {
                        JsonObject property = (JsonObject) propertyElement;
                        if ("textures".equals(property.get(String.class, "name"))) {
                            return property.get(String.class, "value");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static JsonObject parseJson(String json) {
        try {
            Jankson jankson = Jankson.builder().build();
            return jankson.load(json);
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
        return null;
    }
}
