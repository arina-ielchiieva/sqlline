/*
// Licensed to Julian Hyde under one or more contributor license
// agreements. See the NOTICE file distributed with this work for
// additional information regarding copyright ownership.
//
// Julian Hyde licenses this file to you under the Modified BSD License
// (the "License"); you may not use this file except in compliance with
// the License. You may obtain a copy of the License at:
//
// http://opensource.org/licenses/BSD-3-Clause
*/
package sqlline;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;

public class PromptWorker {

  public static class PromptVisitor extends PromptParserBaseVisitor<AttributedString> {

    private final SqlLine sqlLine;
    AttributedStringBuilder builder = new AttributedStringBuilder();

    public PromptVisitor(SqlLine sqlLine) {
      this.sqlLine = sqlLine;


      DatabaseConnection databaseConnection =
        sqlLine.getDatabaseConnection();
      DatabaseMetaDataWrapper databaseMetaData =
        databaseConnection == null
          ? null
          : databaseConnection.meta;

    }


    @Override
    public AttributedString visitPrompt(PromptParser.PromptContext ctx) {
      CommandVisitor commandVisitor = new CommandVisitor(builder, sqlLine);
      ctx.command().forEach(c -> c.children.forEach(cc -> cc.accept(commandVisitor)));
      return builder.toAttributedString();
    }

  }

  public static class CommandVisitor extends PromptParserBaseVisitor<AttributedStringBuilder> {

    private final AttributedStringBuilder builder;
    private final SqlLine sqlLine;

    public CommandVisitor(AttributedStringBuilder builder, SqlLine sqlLine) {
      this.builder = builder;
      this.sqlLine = sqlLine;
    }

    @Override
    public AttributedStringBuilder visitSchema(PromptParser.SchemaContext ctx) {
      DatabaseConnection databaseConnection = sqlLine.getDatabaseConnection();
      if (databaseConnection != null) {
        String schema = databaseConnection.getCurrentSchema();
        if (schema != null) {
          builder.append(schema);
        }
      }
      return builder;
    }

    @Override
    public AttributedStringBuilder visitUser_name(PromptParser.User_nameContext ctx) {
      DatabaseConnection databaseConnection = sqlLine.getDatabaseConnection();
      if (databaseConnection != null) {
        builder.append(databaseConnection.getUrl());
      }
      return builder;
    }

    @Override
    public AttributedStringBuilder visitProperty(PromptParser.PropertyContext ctx) {
      String text = ctx.PROPERTY().getText();
      SqlLineProperty property = BuiltInProperty.valueOf(text.substring(2, text.length() - 1), true);
      if (property != null) {
        builder.append(sqlLine.getOpts().get(property));
      }
      return builder;
    }

    @Override
    public AttributedStringBuilder visitText(PromptParser.TextContext ctx) {
      builder.append(ctx.TEXT().getText());
      return builder;
    }
  }

}
