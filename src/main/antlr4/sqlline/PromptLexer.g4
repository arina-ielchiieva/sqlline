lexer grammar PromptLexer;

options {
  language=Java;
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

PERCENT_SIGN: '%';
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';
COLON: ':';

// commands
CONNECTION_INDEX: PERCENT_SIGN 'c';
CONNECTION_INDEX_SPACED: PERCENT_SIGN 'C';
DATABASE_PRODUCT_NAME: PERCENT_SIGN 'd';
USER_NAME: PERCENT_SIGN 'n';
URL: PERCENT_SIGN 'u';
SCHEMA: PERCENT_SIGN 'S';
PROPERTY: PERCENT_SIGN COLON LETTERS COLON;

TEXT: (LETTER | DIGIT | SPACE | '>')+;

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];
fragment SPACE: [ \t];
fragment LETTERS: [a-zA-Z]+;

// https://github.com/julianhyde/sqlline/pull/250/files

// date time formats
/*
            put('D', () -> getFormattedDateTime("yyyy-MM-dd HH:mm:ss.SSS"));
            put('m', () -> getFormattedDateTime("mm"));
            put('o', () -> getFormattedDateTime("MM"));
            put('O', () -> getFormattedDateTime("MMM"));
            put('P', () -> getFormattedDateTime("aa"));
            put('r', () -> getFormattedDateTime("hh:mm"));
            put('R', () -> getFormattedDateTime("HH:mm"));
            put('s', () -> getFormattedDateTime("ss"));
            put('w', () -> getFormattedDateTime("d"));
            put('W', () -> getFormattedDateTime("E"));
            put('y', () -> getFormattedDateTime("YY"));
            put('Y', () -> getFormattedDateTime("YYYY"));
*/

