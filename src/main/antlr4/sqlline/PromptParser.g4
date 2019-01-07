parser grammar PromptParser;

options{
  language=Java;
  tokenVocab=PromptLexer;
}

@header {
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
}

prompt: (command)* EOF;

command: (sub | text);

sub
: CONNECTION_INDEX # connection_index
| DATABASE_PRODUCT_NAME # database_product_name
| USER_NAME # user_name
| URL # url
| SCHEMA # schema
| PROPERTY # property
;

text: TEXT;