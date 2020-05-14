package vn.toanhda.springboottemplate.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

import java.lang.reflect.Modifier;

/** Created by ToanHDA at May 07, 2020 */
public class JsonProtoUtil {
  private static final JsonFormat.Parser PARSER = JsonFormat.parser().ignoringUnknownFields();
  private static final JsonFormat.Printer PRINTER =
      JsonFormat.printer()
          .includingDefaultValueFields()
          .omittingInsignificantWhitespace()
          .preservingProtoFieldNames();
  private static final Gson GSON =
      new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).disableHtmlEscaping().create();;

  private JsonProtoUtil() {}

  public static <B extends Message.Builder, P extends Message> P parse(String json, B protoBuilder)
      throws InvalidProtocolBufferException {
    PARSER.merge(json, protoBuilder);
    return (P) protoBuilder.build();
  }

  public static String print(MessageOrBuilder proto) {
    if (proto == null) {
      return "{}";
    }

    try {
      return PRINTER.print(proto);
    } catch (InvalidProtocolBufferException e) {
      System.out.println(e);
    }

    return "{}";
  }

  public static String printGson(Object object) {
    return GSON.toJson(object);
  }

  public static <T> T parseGson(String json, Class<T> clazz) {
    return GSON.fromJson(json, clazz);
  }
}
